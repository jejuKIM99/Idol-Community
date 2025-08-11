'use client';

import { useState, useEffect, useCallback, useMemo } from 'react';
import { useAuth } from '../context/AuthContext';
import { useRouter } from 'next/navigation';
import styles from './jelly.module.css';
import { GiJelly } from 'react-icons/gi';
import { FaCheckCircle } from 'react-icons/fa';

// 포트원 결제 라이브러리 타입 정의
interface IMP {
  init: (merchantId: string) => void;
  request_pay: (data: any, callback: (response: any) => void) => void;
}

type JellyProduct = {
  id: number; // 백엔드와 연동을 위한 ID 추가
  jellyAmount: number;
  bonusJelly?: number;
  price: number;
  benefitPercent?: number;
  bestOffer?: boolean;
};

type JellyUserInfo = {
  email: string;
  name: string;
  chargedJelly: number;
  bonusJelly: number;
  totalJelly: number;
};

type JellyHistoryItem = {
  historyId: number;
  changeType: string;
  changeAmount: number;
  balanceAfter: number;
  description: string;
  createdAt: string;
  formattedDate: string;
  formattedTime: string;
};



// 결제 모달 컴포넌트
const PaymentModal = ({ product, onClose, onPayment, isProcessing }: { 
  product: JellyProduct | null; 
  onClose: () => void; 
  onPayment: (product: JellyProduct, paymentMethod: string, pg: string) => void;
  isProcessing: boolean;
}) => {
    const [selectedPaymentMethod, setSelectedPaymentMethod] = useState<string>('CREDIT_CARD');
    const [selectedPg, setSelectedPg] = useState<string>('html5_inicis');

    if (!product) return null;

    const handlePaymentMethodSelect = (method: string) => {
        setSelectedPaymentMethod(method);
        
        // pg사 설정
        if (method === 'KAKAO_PAY') {
            setSelectedPg('kakaopay');
        } else if (method === 'NAVER_PAY') {
            setSelectedPg('naverpay');
        } else {
            setSelectedPg('html5_inicis');
        }
        console.log('설정된 PG:', selectedPg); 
    };

    const handlePayment = () => {
      console.log('결제 요청 - 수단:', selectedPaymentMethod, 'PG:', selectedPg); 
        onPayment(product, selectedPaymentMethod, selectedPg);
    };

    return (
        <div className={styles.modalOverlay} onClick={onClose}>
            <div className={styles.modalContent} onClick={(e) => e.stopPropagation()}>
                <h2 className={styles.modalTitle}>결제</h2>
                <div className={styles.modalSection}>
                    <h3 className={styles.modalSectionTitle}>결제상품</h3>
                    <div className={styles.paymentProduct}>
                         <GiJelly className={styles.jellyIcon} />
                         <div>
                            <p className={styles.productName}>젤리 {product.jellyAmount}</p>
                            <p className={styles.paymentPrice}>KRW {product.price.toLocaleString()}</p>
                         </div>
                    </div>
                </div>
                <div className={styles.modalSection}>
                    <h3 className={styles.modalSectionTitle}>결제수단</h3>
                    <div 
                        className={styles.paymentOption}
                        onClick={() => handlePaymentMethodSelect('CREDIT_CARD')}
                        style={selectedPaymentMethod === 'CREDIT_CARD' ? {
                            borderColor: '#00c4b4',
                            backgroundColor: '#f0fffe'
                        } : {}}
                    >
                        <div style={{ width: '24px', display: 'flex', justifyContent: 'center' }}>
                            <FaCheckCircle
                                className={styles.checkIcon}
                                style={{
                                    visibility: selectedPaymentMethod === 'CREDIT_CARD' ? 'visible' : 'hidden',
                                    color: selectedPaymentMethod === 'CREDIT_CARD' ? '#00c4b4' : 'transparent'
                                }}
                            />
                        </div>
                        <span>체크/신용카드</span>
                    </div>
                    <div 
                        className={styles.paymentOption}
                        onClick={() => handlePaymentMethodSelect('NAVER_PAY')}
                        style={selectedPaymentMethod === 'NAVER_PAY' ? {
                            borderColor: '#00c4b4',
                            backgroundColor: '#f0fffe'
                        } : {}}
                    >
                        <div style={{ width: '24px', display: 'flex', justifyContent: 'center' }}>
                    <FaCheckCircle
                        className={styles.checkIcon}
                        style={{
                            visibility: selectedPaymentMethod === 'NAVER_PAY' ? 'visible' : 'hidden',
                            color: selectedPaymentMethod === 'NAVER_PAY' ? '#00c4b4' : 'transparent'
                        }}
                    />
                </div>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7eSS9iP-1g2_oG_p1sQyGk2yVdVYd_Vz_gA&s" alt="네이버페이 로고" />
                        <span>네이버페이</span>
                    </div>
                    <div 
                        className={styles.paymentOption}
                        onClick={() => handlePaymentMethodSelect('KAKAO_PAY')}
                        style={selectedPaymentMethod === 'KAKAO_PAY' ? {
                            borderColor: '#00c4b4',
                            backgroundColor: '#f0fffe'
                        } : {}}
                    >
                        <div style={{ width: '24px', display: 'flex', justifyContent: 'center' }}>
                    <FaCheckCircle
                        className={styles.checkIcon}
                        style={{
                            visibility: selectedPaymentMethod === 'KAKAO_PAY' ? 'visible' : 'hidden',
                            color: selectedPaymentMethod === 'KAKAO_PAY' ? '#00c4b4' : 'transparent'
                        }}
                    />
                </div>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Y-5k2j-hXf_i_M4l_T9G8M-p_b_8Y2z-w&s" alt="카카오페이 로고" />
                        <span>카카오페이</span>
                    </div>
                </div>
                <button 
                    className={`${styles.paymentButton} ${!isProcessing ? styles.active : ''}`}
                    onClick={handlePayment}
                    disabled={isProcessing}
                    style={!isProcessing ? {
                        backgroundColor: '#00c4b4',
                        color: 'white',
                        cursor: 'pointer'
                    } : {}}
                >
                    {isProcessing ? '처리 중...' : '결제하기'}
                </button>
                <p className={styles.modalFooter}>© WEVERSE COMPANY Inc.</p>
            </div>
        </div>
    )
}

export default function JellyPage() {
  const { isAuthenticated, token, isLoading } = useAuth();
  const router = useRouter();
  const [activeTab, setActiveTab] = useState('charge');
  const [selectedProduct, setSelectedProduct] = useState<JellyProduct | null>(null);
  const [isProcessing, setIsProcessing] = useState(false);
  const [products, setProducts] = useState<JellyProduct[]>([]);
  const [isLoadingProducts, setIsLoadingProducts] = useState(true);
  const [userInfo, setUserInfo] = useState<JellyUserInfo | null>(null);
  
  // 조회탭 관련 상태 추가
  const [historyType, setHistoryType] = useState('ALL');
  const [historyDays, setHistoryDays] = useState(30);
  const [historyData, setHistoryData] = useState<JellyHistoryItem[]>([]);
  const [isLoadingHistory, setIsLoadingHistory] = useState(false);
  const [activeHistoryTab, setActiveHistoryTab] = useState('charge');

  // 사용자 정보 가져오기
  const fetchUserInfo = useCallback(async () => {
    if (!token) return;
  
    try {
      const response = await fetch('http://localhost:80/api/jelly/user-info', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
  
      if (response.ok) {
        const data = await response.json();
        setUserInfo({
          email: data.email,
          name: data.name,
          chargedJelly: data.chargedJelly,
          bonusJelly: data.bonusJelly,
          totalJelly: data.totalJelly
        });
      }
    } catch (error) {
      console.error('사용자 정보 조회 중 오류:', error);
    }
  }, [token]);

  // 젤리 상품 목록을 useMemo로 최적화
  const jellyProducts = useMemo(() => [
    { id: 1, jellyAmount: 4, price: 1200 },
    { id: 2, jellyAmount: 8, price: 2400 },
    { id: 3, jellyAmount: 20, bonusJelly: 1, price: 6000, benefitPercent: 5 },
    { id: 4, jellyAmount: 40, bonusJelly: 3, price: 12000, benefitPercent: 7 },
    { id: 5, jellyAmount: 60, bonusJelly: 5, price: 18000, benefitPercent: 8 },
    { id: 6, jellyAmount: 80, bonusJelly: 7, price: 24000, benefitPercent: 8 },
    { id: 7, jellyAmount: 120, bonusJelly: 11, price: 36000, benefitPercent: 8 },
    { id: 8, jellyAmount: 160, bonusJelly: 15, price: 48000, benefitPercent: 8 },
    { id: 9, jellyAmount: 240, bonusJelly: 24, price: 72000, benefitPercent: 10, bestOffer: true },
  ], []);

  // 사용자 정보 기반 계산된 값들을 useMemo로 최적화
  const userDisplayInfo = useMemo(() => {
    if (!userInfo) return null;
    
    return {
      totalJelly: userInfo.totalJelly || 0,
      chargedJelly: userInfo.chargedJelly || 0,
      bonusJelly: userInfo.bonusJelly || 0,
      chargeLimit: Math.min((userInfo.chargedJelly || 0), 1500),
      maxChargeLimit: 1500
    };
  }, [userInfo]);

  // 상품 렌더링을 위한 최적화된 데이터를 useMemo로 생성
  const optimizedProducts = useMemo(() => {
    return jellyProducts.map(product => ({
      ...product,
      formattedPrice: product.price.toLocaleString(),
      hasBonus: !!product.bonusJelly,
      hasBenefit: !!product.benefitPercent,
      isBestOffer: !!product.bestOffer
    }));
  }, [jellyProducts]);

  // 인증 상태 확인
  useEffect(() => {
    if (!isLoading && !isAuthenticated) {
      router.push('/login');
    }
  }, [isAuthenticated, isLoading, router]);

          // 사용자 정보 가져오기
        useEffect(() => {
          if (isAuthenticated && token) {
            fetchUserInfo();
          }
        }, [isAuthenticated, token, fetchUserInfo]);

  // 젤리 상품 목록 가져오기
  const fetchProducts = useCallback(async () => {
    if (!token) return;
    
    try {
      const response = await fetch('http://localhost:80/api/jelly/products', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      
      if (response.ok) {
        const data = await response.json();
        setProducts(data);
      } else {
        console.error('상품 목록 조회 실패');
        // 백엔드 API가 아직 구현되지 않은 경우 기본 데이터 사용
        setProducts(jellyProducts);
      }
    } catch (error) {
      console.error('상품 목록 조회 중 오류:', error);
      // 에러 발생 시 기본 데이터 사용
      setProducts(jellyProducts);
    } finally {
      setIsLoadingProducts(false);
    }
  }, [token]);

  useEffect(() => {
    if (isAuthenticated && token) {
      fetchProducts();
    }
  }, [isAuthenticated, token, fetchProducts]);

  // 거래 내역 가져오기
  const fetchHistory = useCallback(async (type: string = 'ALL', days: number = 30) => {
    if (!token) return;
    
    setIsLoadingHistory(true);
    try {
      const url = `http://localhost:80/api/jelly/history?type=${type}&days=${days}`;
      
      const response = await fetch(url, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      
      if (response.ok) {
        const data = await response.json();
        setHistoryData(data);
      } else {
        const errorText = await response.text();
        setHistoryData([]);
      }
    } catch (error) {
      console.error('거래 내역 조회 중 오류:', error);
      setHistoryData([]);
    } finally {
      setIsLoadingHistory(false);
    }
  }, [token]);

  // 조회탭이 활성화될 때 거래 내역 가져오기
  useEffect(() => {
    
    if (activeTab === 'history' && isAuthenticated && token) {
      fetchHistory(historyType, historyDays);
    }
  }, [activeTab, historyType, historyDays, isAuthenticated, token, fetchHistory]);

  // 로딩 중이거나 인증되지 않은 경우
  if (isLoading || !isAuthenticated) {
    return <div>로딩 중...</div>;
  }

  const handleProductClick = (product: JellyProduct) => {
    setSelectedProduct(product);
  };

  const closeModal = () => {
    setSelectedProduct(null);
  };

  const handlePayment = async (product: JellyProduct, paymentMethod: string, pg: string) => {
    console.log('JellyPage handlePayment 호출:', { paymentMethod, pg }); 
    if (!token) {
      alert('로그인이 필요합니다.');
      return;
    }

    setIsProcessing(true);
    
    try {
      // 1. 백엔드에 결제 준비 요청
      const response = await fetch('http://localhost:80/api/jelly/charge', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          productId: product.id,
          paymentMethod: paymentMethod,
          pg: pg
        })
      });

      if (response.ok) {
        const paymentData = await response.json();
        
        // 2. 포트원 결제 요청
        console.log('결제 준비 완료:', paymentData);
        console.log('전달받은 결제 수단:', paymentMethod);
        console.log('전달받은 PG:', pg);
        
        // 포트원 결제 라이브러리 로드 및 초기화
        const script = document.createElement('script');
        script.src = 'https://cdn.iamport.kr/v1/iamport.js';
        script.onload = () => {
          const IMP = (window as any).IMP;
          IMP.init('imp36568640'); // 실제 가맹점 식별코드로 변경 필요
          
          IMP.request_pay({
            pg: pg,
            pay_method: paymentMethod === 'KAKAO_PAY' ? 'kakaopay' : 
                       paymentMethod === 'NAVER_PAY' ? 'naverpay' : 'card',
            merchant_uid: paymentData.merchantUid,
            amount: product.price,
            name: `젤리 ${product.jellyAmount}개`,
            buyer_email: userInfo?.email || 'test@test.com',
            buyer_name: userInfo?.name || '구매자',

          }, (response: any) => {
            if (response.success) {
              // 결제 성공 시 백엔드에 검증 요청
              verifyPayment(response.imp_uid, paymentData.merchantUid);
            } else {
              alert(`결제 실패: ${response.error_msg}`);
              setIsProcessing(false);
            }
          });
        };
        document.head.appendChild(script);
        
      } else {
        const errorData = await response.json();
        alert(`결제 준비 실패: ${errorData.message || '알 수 없는 오류'}`);
        setIsProcessing(false);
      }
    } catch (error) {
      console.error('결제 요청 중 오류:', error);
      alert('네트워크 오류가 발생했습니다.');
      setIsProcessing(false);
    }
  };



  // 결제 검증 함수
  const verifyPayment = async (impUid: string, merchantUid: string) => {
    try {
      const response = await fetch('http://localhost:80/api/jelly/verify', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          impUid: impUid,
          merchantUid: merchantUid
        })
      });

      if (response.ok) {
        alert('결제가 완료되었습니다!');
        closeModal();
        // 사용자 젤리 잔액 업데이트
        await fetchUserInfo();
        
      } else {
        const errorData = await response.json();
        alert(`결제 검증 실패: ${errorData.message || '알 수 없는 오류'}`);
      }
    } catch (error) {
      console.error('결제 검증 중 오류:', error);
      alert('결제 검증 중 오류가 발생했습니다.');
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <>
      <div className={styles.pageContainer}>
        <aside className={styles.sidebar}>
          <h1 className={styles.shopTitle}>Jelly Shop</h1>
          <div className={styles.myJellyBox}>
            <p>My Jelly</p>
            <div className={styles.jellyAmount}>
              <GiJelly className={styles.jellyIconLarge} />
              <span>{userDisplayInfo?.totalJelly || 0}</span>
            </div>
          </div>
          <div className={styles.jellyDetails}>
            <div className={styles.detailItem}>
              <span>충전젤리</span>
              <span>{userDisplayInfo?.chargedJelly || 0}</span>
            </div>
            <div className={styles.detailItem}>
              <span>적립젤리</span>
              <span>{userDisplayInfo?.bonusJelly || 0}</span>
            </div>
          </div>
          <div className={styles.chargeLimit}>
            <span>충전한도</span>
            <p>
              <strong>{userDisplayInfo?.chargeLimit || 0}</strong> / 최대 {userDisplayInfo?.maxChargeLimit || 1500}
            </p>
          </div>
          <div className={styles.sidebarFooter}>
            <div className={styles.weverseLogo}>weverse</div>
            <p>© WEVERSE COMPANY Inc.</p>
          </div>
        </aside>

        <main className={styles.mainContent}>
                            <nav className={styles.mainNav}>
                    <button
                      className={activeTab === 'charge' ? styles.active : ''}
                      onClick={() => setActiveTab('charge')}
                    >
                      일반 충전
                    </button>
                    <button
                      className={activeTab === 'history' ? styles.active : ''}
                      onClick={() => setActiveTab('history')}
                    >
                      조회
                    </button>
                  </nav>

          {activeTab === 'charge' && (
            <div className={styles.chargeSection}>
              <h2 className={styles.sectionTitle}>일반 충전</h2>
              <div className={styles.productList}>
                {optimizedProducts.map((product, index) => (
                  <div
                    key={index}
                    className={`${styles.productItem} ${selectedProduct?.price === product.price ? styles.selected : ''} ${product.isBestOffer ? styles.bestOffer : ''}`}
                    onClick={() => handleProductClick(product)}
                  >
                    {product.isBestOffer && <div className={styles.bestOfferBadge}>Best Offer</div>}
                    <div className={styles.productInfo}>
                      <GiJelly className={styles.jellyIcon} />
                      <div>
                        <p className={styles.productName}>젤리 {product.jellyAmount}</p>
                        {product.hasBonus && (
                          <p className={styles.bonusJelly}>적립젤리 {product.bonusJelly}</p>
                        )}
                      </div>
                    </div>
                    <div className={styles.productPrice}>
                      <span>₩{product.formattedPrice}</span>
                      {product.hasBenefit && (
                        <span className={styles.benefitTag}>{product.benefitPercent}% 혜택</span>
                      )}
                    </div>
                  </div>
                ))}
              </div>
              <div className={styles.infoSection}>
                  <h3>혜택 안내</h3>
                  <ul>
                      <li>'적립젤리'는 프로모션 혜택으로 추가 지급하는 무료 젤리입니다.</li>
                  </ul>
                  <h3>이용 안내</h3>
                  <ul>
                      <li>젤리는 다양한 디지털 상품 구입에 사용할 수 있습니다.</li>
                      <li>충전한 젤리의 유효기간은 유료 서비스 이용약관에 따릅니다.</li>
                      <li>젤리 구매 가격에는 부가가치세가 포함되어 있습니다.</li>
                      <li>젤리를 충전할 때 추가 지급되는 적립젤리의 수량은 변경될 수 있습니다.</li>
                      <li>젤리는 적립 젤리부터 유효기간이 임박한 순으로 먼저 사용됩니다.</li>
                      <li>젤리 충전/적립/사용 내역은 조회 &gt; 충전/적립, 메뉴에서 확인 가능합니다.</li>
                  </ul>
                  <h3>구매 취소</h3>
                  <ul>
                      <li>젤리샵에서 충전한 젤리의 구매 취소는 위버스 고객센터를 통해서만 가능합니다.</li>
                      <li>적립 젤리는 구매 취소 및 환불 대상이 아닙니다. 충전 젤리 환불 시 적립젤리 또한 회수됩니다.</li>
                      <li>단, 적립 젤리를 사용한 경우, 환불 시 차감됩니다.</li>
                      <li>사용한 젤리 환불 및 사용량에 따라 환불 금액이 '결제 금액'보다 적거나 없을 수 있습니다.</li>
                  </ul>
              </div>
            </div>
          )}

          {activeTab === 'history' && (
              <div className={styles.historySection}>
                  <h2 className={styles.sectionTitle}>조회</h2>
                  <div className={styles.historyTabs}>
                      <div style={{ display: 'flex', gap: '8px', marginBottom: '16px' }}>
                          <button 
                              className={activeHistoryTab === 'charge' ? styles.active : ''}
                              onClick={() => {
                                  setActiveHistoryTab('charge');
                                  setHistoryType('CHARGE_AND_BONUS');
                              }}
                              style={{
                                  padding: '8px 16px',
                                  border: '1px solid #e0e0e0',
                                  borderRadius: '6px',
                                  backgroundColor: activeHistoryTab === 'charge' ? '#00c4b4' : 'white',
                                  color: activeHistoryTab === 'charge' ? 'white' : '#333',
                                  cursor: 'pointer'
                              }}
                          >
                              충전 / 적립
                          </button>
                          <button 
                              className={activeHistoryTab === 'use' ? styles.active : ''}
                              onClick={() => {
                                  setActiveHistoryTab('use');
                                  setHistoryType('USE');
                              }}
                              style={{
                                  padding: '8px 16px',
                                  border: '1px solid #e0e0e0',
                                  borderRadius: '6px',
                                  backgroundColor: activeHistoryTab === 'use' ? '#00c4b4' : 'white',
                                  color: activeHistoryTab === 'use' ? 'white' : '#333',
                                  cursor: 'pointer'
                              }}
                          >
                              사용
                          </button>
                          <button 
                              className={activeHistoryTab === 'all' ? styles.active : ''}
                              onClick={() => {
                                  setActiveHistoryTab('all');
                                  setHistoryType('ALL');
                              }}
                              style={{
                                  padding: '8px 16px',
                                  border: '1px solid #e0e0e0',
                                  borderRadius: '6px',
                                  backgroundColor: activeHistoryTab === 'all' ? '#00c4b4' : 'white',
                                  color: activeHistoryTab === 'all' ? 'white' : '#333',
                                  cursor: 'pointer'
                              }}
                          >
                              전체
                          </button>
                      </div>
                      <select 
                          value={historyDays} 
                          onChange={(e) => setHistoryDays(Number(e.target.value))}
                          style={{
                              padding: '8px 12px',
                              border: '1px solid #e0e0e0',
                              borderRadius: '6px',
                              backgroundColor: 'white',
                              cursor: 'pointer'
                          }}
                      >
                          <option value={7}>최근 7일</option>
                          <option value={30}>최근 30일</option>
                          <option value={90}>최근 90일</option>
                          <option value={365}>최근 1년</option>
                      </select>
                  </div>
                  
                  {isLoadingHistory ? (
                      <div className={styles.loadingHistory} style={{ textAlign: 'center', padding: '40px' }}>
                          <p>거래 내역을 불러오는 중...</p>
                      </div>
                  ) : historyData.length > 0 ? (
                      <div className={styles.historyList} style={{ marginTop: '20px' }}>
                          {historyData.map((item) => (
                              <div key={item.historyId} className={styles.historyItem} style={{
                                  border: '1px solid #e0e0e0',
                                  borderRadius: '8px',
                                  padding: '16px',
                                  marginBottom: '12px',
                                  display: 'flex',
                                  justifyContent: 'space-between',
                                  alignItems: 'center',
                                  backgroundColor: '#fff'
                              }}>
                                  <div className={styles.historyInfo} style={{ flex: 1 }}>
                                      <div className={styles.historyType} style={{
                                          fontWeight: 'bold',
                                          color: item.changeType === 'CHARGE' ? '#00c4b4' : 
                                                 item.changeType === 'BONUS' ? '#ff6b6b' : '#666',
                                          marginBottom: '4px'
                                      }}>
                                          {item.changeType === 'CHARGE' && '충전'}
                                          {item.changeType === 'BONUS' && '적립'}
                                          {item.changeType === 'USE' && '사용'}
                                      </div>
                                      <div className={styles.historyDescription} style={{
                                          fontSize: '14px',
                                          color: '#333',
                                          marginBottom: '4px'
                                      }}>
                                          {item.description}
                                      </div>
                                      <div className={styles.historyDate} style={{
                                          fontSize: '12px',
                                          color: '#999'
                                      }}>
                                          {item.formattedDate} {item.formattedTime}
                                      </div>
                                  </div>
                                  <div className={styles.historyAmount} style={{ textAlign: 'right' }}>
                                      <span className={item.changeAmount > 0 ? styles.positive : styles.negative} style={{
                                          fontSize: '18px',
                                          fontWeight: 'bold',
                                          color: item.changeAmount > 0 ? '#00c4b4' : '#ff6b6b',
                                          display: 'block',
                                          marginBottom: '4px'
                                      }}>
                                          {item.changeAmount > 0 ? '+' : ''}{item.changeAmount}
                                      </span>
                                      <div className={styles.balanceAfter} style={{
                                          fontSize: '12px',
                                          color: '#666'
                                      }}>
                                          잔액: {item.balanceAfter}
                                      </div>
                                  </div>
                              </div>
                          ))}
                      </div>
                  ) : (
                      <div className={styles.emptyHistory} style={{ textAlign: 'center', padding: '40px' }}>
                          <p>해당 내역이 없습니다.</p>
                          <button onClick={() => setActiveTab('charge')} style={{
                              backgroundColor: '#00c4b4',
                              color: 'white',
                              border: 'none',
                              padding: '12px 24px',
                              borderRadius: '6px',
                              cursor: 'pointer',
                              marginTop: '16px'
                          }}>젤리 충전</button>
                      </div>
                  )}
              </div>
          )}
        </main>
      </div>
      <PaymentModal product={selectedProduct} onClose={closeModal} onPayment={handlePayment} isProcessing={isProcessing} />
    </>
  );
}
