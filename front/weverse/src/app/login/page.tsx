'use client';

import { useState } from 'react';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import styles from './login.module.css';
import { BsGoogle, BsTwitterX, BsApple } from 'react-icons/bs';
import { useAuth } from '../context/AuthContext';

// 데모용으로 이메일 존재 여부를 확인하는 함수
const checkEmailExists = async (email: string): Promise<boolean> => {
  try {
    const response = await fetch('http://localhost:80/api/auth/check-email', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email }),
    });
    if (response.ok) {
      const data = await response.json();
      return data.exists;
    } else {
      let errorMessage = `이메일 존재 여부 확인 실패: ${response.statusText}`;
      const contentType = response.headers.get("content-type");
      if (contentType && contentType.includes("application/json")) {
        const errorData = await response.json();
        errorMessage = `이메일 존재 여부 확인 실패: ${errorData.message || response.statusText}`;
      } else {
        const errorText = await response.text();
        errorMessage = `이메일 존재 여부 확인 실패: ${errorText || response.statusText}`;
      }
      console.error(errorMessage);
      return false;
    }
  } catch (error) {
    console.error('이메일 존재 여부 확인 중 오류 발생:', error);
    return false;
  }
};


export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [step, setStep] = useState(1); // 1: email, 2: password
  const [isRegistered, setIsRegistered] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const router = useRouter();
  const auth = useAuth();

  const handleEmailSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!email) {
      alert('이메일을 입력해주세요.');
      return;
    }
    setIsLoading(true);
    const exists = await checkEmailExists(email);
    setIsRegistered(exists);
    setIsLoading(false);
    setStep(2);
  };

  const handleFinalSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!password) {
        alert('비밀번호를 입력해주세요.');
        return;
    }
    setIsLoading(true);
    try {
      let response;
      let successMessage;
      let errorMessagePrefix;

      if (isRegistered) { // 로그인 시도
        response = await fetch('http://localhost:80/api/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ email, password }),
        });
        successMessage = '로그인 성공!';
        errorMessagePrefix = '로그인 실패';
      } else { // 회원가입 시도
        response = await fetch('http://localhost:80/api/auth/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ email, password }),
        });
        successMessage = '회원가입 성공!';
        errorMessagePrefix = '회원가입 실패';
      }

      if (response.ok) {
        let responseData;
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
          responseData = await response.json();
        } else {
          responseData = await response.text(); // Read as text if not JSON
        }
        alert(successMessage);
        console.log(successMessage, responseData);
        if (!isRegistered) { // 회원가입 성공 시
          setIsRegistered(true); // 로그인 폼으로 전환
          setStep(2); // 비밀번호 입력 단계 유지
        } else if (typeof responseData === 'object' && responseData !== null && 'token' in responseData && 'userId' in responseData) {
          auth.login(responseData.token, responseData.userId);
          router.push('/'); // 메인 페이지로 리다이렉트
        }
      } else {
        let errorMessage = `${errorMessagePrefix}: ${response.statusText}`;
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
          const errorData = await response.json();
          errorMessage = `${errorMessagePrefix}: ${errorData.message || response.statusText}`;
        } else {
          const errorText = await response.text();
          errorMessage = `${errorMessagePrefix}: ${errorText || response.statusText}`;
        }
        alert(errorMessage);
        console.error(errorMessage, response);
      }
    } catch (error) {
      alert('네트워크 오류 또는 서버 응답 없음');
      console.error('요청 중 오류 발생:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // 이메일 입력창을 수정하면 다시 첫 단계로 돌아감
    if (step === 2) {
        setStep(1);
        setPassword('');
    }
    setEmail(e.target.value);
  }

  return (
    <div className={styles.container}>
      <div className={styles.loginBox}>
        <h1 className={styles.logo}>weverse account</h1>
        <h2 className={styles.title}>
          위버스 계정으로<br />로그인이나 회원가입해 주세요
        </h2>

        {step === 1 && (
          <form onSubmit={handleEmailSubmit}>
            <div className={styles.inputGroup}>
              <label htmlFor="email">이메일</label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="your@email.com"
                required
              />
            </div>
            <button type="submit" className={styles.submitButton} disabled={isLoading}>
              {isLoading ? '확인 중...' : '이메일로 계속하기'}
            </button>
          </form>
        )}

        {step === 2 && (
          <form onSubmit={handleFinalSubmit}>
            <div className={styles.inputGroup}>
              <label htmlFor="email">이메일</label>
              <div className={styles.emailDisplay} onClick={() => setStep(1)}>
                {email}
                <span>ⓘ</span>
              </div>
            </div>
            <div className={styles.inputGroup}>
              <label htmlFor="password">비밀번호</label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="비밀번호"
                required
                autoFocus
              />
            </div>
            <button type="submit" className={styles.submitButton} disabled={isLoading}>
              {isLoading ? '처리 중...' : (isRegistered ? '로그인' : '회원가입')}
            </button>
          </form>
        )}

        <div className={styles.links}>
            {step === 2 && !isRegistered && (
                 <p className={styles.authSwitch}>
                    이미 계정이 있으신가요? <span onClick={() => setIsRegistered(true)}>로그인</span>
                </p>
            )}
            <Link href="/forgot-password" className={styles.forgotPassword}>
                비밀번호를 잊어버리셨나요?
            </Link>
        </div>

        <div className={styles.divider}>
          <span>또는</span>
        </div>

        <div className={styles.socialLogin}>
          <button className={styles.socialButton}><BsTwitterX /></button>
          <button className={styles.socialButton}><BsGoogle /></button>
          <button className={styles.socialButton}><BsApple /></button>
        </div>
        
        {step === 2 && isRegistered && (
            <p className={styles.authSwitch}>
                아직 계정이 없다면? <Link href="#" onClick={(e) => { e.preventDefault(); setIsRegistered(false); }}>위버스 계정으로 가입하기</Link>
            </p>
        )}
      </div>
    </div>
  );
}
