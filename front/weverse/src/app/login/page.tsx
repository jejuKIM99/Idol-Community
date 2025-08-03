'use client';

import { useState } from 'react';
import Link from 'next/link';
import styles from './login.module.css';
import { BsGoogle, BsTwitterX, BsApple } from 'react-icons/bs';

// 데모용으로 이메일 존재 여부를 확인하는 함수
const checkEmailExists = async (email: string): Promise<boolean> => {
  console.log(`Checking if ${email} exists...`);
  // 실제로는 여기에 API 요청을 보냅니다.
  // 지금은 'test@example.com'만 가입된 것으로 가정합니다.
  return new Promise(resolve =>
    setTimeout(() => {
      resolve(email === 'test@example.com');
    }, 500)
  );
};


export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [step, setStep] = useState(1); // 1: email, 2: password
  const [isRegistered, setIsRegistered] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

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

  const handleFinalSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!password) {
        alert('비밀번호를 입력해주세요.');
        return;
    }
    setIsLoading(true);
    // 실제 로그인/회원가입 로직
    setTimeout(() => {
        if (isRegistered) {
            console.log('--- 로그인 시도 ---');
            console.log('Email:', email);
            console.log('Password:', password);
            alert(`${email}으로 로그인을 시도합니다.`);
        } else {
            console.log('--- 회원가입 시도 ---');
            console.log('Email:', email);
            console.log('Password:', password);
            alert(`${email}으로 회원가입을 시도합니다.`);
        }
        setIsLoading(false);
    }, 1000);
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
