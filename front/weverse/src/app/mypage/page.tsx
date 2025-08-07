'use client';

import { useState, useEffect } from 'react';
import Link from 'next/link';
import styles from './mypage.module.css';
import {
  FiChevronRight,
  FiBookOpen, FiMic, FiCheckSquare, FiPlayCircle
} from 'react-icons/fi';
import { useAuth } from '../context/AuthContext';

// 디지털 코드 입력 모달 컴포넌트
const DigitalCodeModal = ({ isOpen, onClose }: { isOpen: boolean, onClose: () => void }) => {
    if (!isOpen) return null;

    return (
        <div className={styles.modalOverlay} onClick={onClose}>
            <div className={styles.modalContent} onClick={(e) => e.stopPropagation()}>
                <h2 className={styles.modalTitle}>등록할 코드를 입력하세요.</h2>
                <div className={styles.inputGroup}>
                    <label htmlFor="digital-code">코드 입력</label>
                    <input type="text" id="digital-code" />
                </div>
                <p className={styles.modalInfo}>
                    한번 등록된 코드는 다른 계정으로 이전할 수 없습니다.
                    <br />
                    아래 로그인된 계정 정보를 반드시 확인해 주세요.
                </p>
                <div className={styles.userInfo}>
                    <span>이메일</span>
                    <strong>s20***@naver.com</strong>
                </div>
                <button className={styles.submitButton}>등록하기</button>
            </div>
        </div>
    );
};


// 각 서비스 링크에 대한 타입 정의
type ServiceLink = {
  name: string;
  icon: React.ComponentType<any>;
  color: string;
};

const serviceLinks: ServiceLink[] = [
  { name: 'Weverse Magazine', icon: FiBookOpen, color: '#4caf50' },
  { name: 'Weverse Concerts', icon: FiMic, color: '#ff9800' },
  { name: 'Weverse Survey', icon: FiCheckSquare, color: '#2196f3' },
  { name: 'BTS Island: In the SEOM', icon: FiPlayCircle, color: '#9c27b0' },
];


export default function MyPage() {
  const [activeTab, setActiveTab] = useState('media');
  const [isCodeModalOpen, setIsCodeModalOpen] = useState(false); // 모달 상태 추가
  const [userData, setUserData] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const { token, isAuthenticated, isLoading: isAuthLoading } = useAuth();

  useEffect(() => {
    if (isAuthLoading) {
      return; // Wait for AuthContext to finish loading
    }

    if (!isAuthenticated || !token) {
      setLoading(false);
      setError('로그인이 필요합니다.');
      return;
    }

    const fetchUserData = async () => {
      try {
                const response = await fetch('http://localhost:80/api/user/me', {
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setUserData(data);
        } else {
          const errorText = await response.text();
          setError(`데이터를 불러오는데 실패했습니다: ${errorText}`);
        }
      } catch (err) {
        setError('네트워크 오류가 발생했습니다.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, [token, isAuthenticated]);

  if (loading) {
    return <div className={styles.pageContainer}><p>로딩 중...</p></div>;
  }

  if (error) {
    return <div className={styles.pageContainer}><p>오류: {error}</p></div>;
  }

  return (
    <>
      <div className={styles.pageContainer}>
        <main className={styles.mainContent}>
          {/* 왼쪽 사이드바 */}
          <aside className={styles.sidebar}>
            <div className={styles.profileSection}>
              <h2 className={styles.username}>{userData.nickname || userData.name || '사용자'}</h2>
              <p className={styles.email}>{userData.email}</p>
              <button className={styles.logoutButton}>로그아웃</button>
            </div>

            <div className={styles.cashSection}>
              <div className={styles.cashInfo}>
                <span className={styles.cashIcon}>J</span>
                <span>{userData.jellyBalance}</span>
              </div>
              <Link href="/jelly">
                <button className={styles.chargeButton}>충전</button>
              </Link>
            </div>

            <nav className={styles.sidebarNav}>
              <Link href="/mypage/orders" className={styles.navItem}>
                <span>상품 주문 내역</span>
                <FiChevronRight />
              </Link>
              {/* 쿠폰함 링크를 /mypage/coupons로 수정했습니다. */}
              <Link href="/mypage/coupons" className={styles.navItem}>
                <span>쿠폰함</span>
                <FiChevronRight />
              </Link>
            </nav>
            
            <div className={styles.serviceSection}>
              {serviceLinks.map((service) => (
                <a href="#" key={service.name} className={styles.serviceItem}>
                  <div className={styles.serviceIcon} style={{ backgroundColor: service.color }}>
                    <service.icon size={20} color="white" />
                  </div>
                  <span>{service.name}</span>
                  <FiChevronRight />
                </a>
              ))}
            </div>
          </aside>

          {/* 오른쪽 메인 컨텐츠 */}
          <section className={styles.contentArea}>
            <div className={styles.contentHeader}>
              <h2 className={styles.contentTitle}>미디어 구매 내역</h2>
              <div className={styles.actionButtons}>
                  <Link href="/mypage/notices">
                      <button>공지사항</button>
                  </Link>
                  <Link href="/mypage/settings">
                    <button>계정 설정</button>
                  </Link>
                  <button onClick={() => setIsCodeModalOpen(true)}>디지털 코드 입력</button>
              </div>
            </div>
            <div className={styles.tabs}>
              <button
                className={`${styles.tab} ${activeTab === 'media' ? styles.active : ''}`}
                onClick={() => setActiveTab('media')}
              >
                무료 상품
              </button>
              <button
                className={`${styles.tab} ${activeTab === 'order' ? styles.active : ''}`}
                onClick={() => setActiveTab('order')}
              >
                디지털 코드
              </button>
              <div className={styles.tabFilter}>
                  <label><input type="radio" name="status" defaultChecked /> 사용 중</label>
                  <label><input type="radio" name="status" /> 전체</label>
              </div>
            </div>

            <div className={styles.tabContent}>
              {activeTab === 'media' && (
                <div className={styles.emptyContent}>
                  <p>해당 내역이 없습니다.</p>
                </div>
              )}
              {activeTab === 'order' && (
                <div className={styles.emptyContent}>
                  <p>디지털 코드 내역이 없습니다.</p>
                </div>
              )}
            </div>
          </section>
        </main>
      </div>
      <DigitalCodeModal isOpen={isCodeModalOpen} onClose={() => setIsCodeModalOpen(false)} />
    </>
  );
}
