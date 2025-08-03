'use client';

import { useState, useEffect } from 'react';
import styles from './jelly.module.css';
import { GiJelly } from 'react-icons/gi';
import { FaCheckCircle } from 'react-icons/fa';

type JellyProduct = {
  jellyAmount: number;
  bonusJelly?: number;
  price: number;
  benefitPercent?: number;
  bestOffer?: boolean;
};

const jellyProducts: JellyProduct[] = [
  { jellyAmount: 4, price: 1200 },
  { jellyAmount: 8, price: 2400 },
  { jellyAmount: 20, bonusJelly: 1, price: 6000, benefitPercent: 5 },
  { jellyAmount: 40, bonusJelly: 3, price: 12000, benefitPercent: 7 },
  { jellyAmount: 60, bonusJelly: 5, price: 18000, benefitPercent: 8 },
  { jellyAmount: 80, bonusJelly: 7, price: 24000, benefitPercent: 8 },
  { jellyAmount: 120, bonusJelly: 11, price: 36000, benefitPercent: 8 },
  { jellyAmount: 160, bonusJelly: 15, price: 48000, benefitPercent: 8 },
  { jellyAmount: 240, bonusJelly: 24, price: 72000, benefitPercent: 10, bestOffer: true },
];

// 결제 모달 컴포넌트
const PaymentModal = ({ product, onClose }: { product: JellyProduct | null; onClose: () => void; }) => {
    if (!product) return null;

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
                    <div className={styles.paymentOption}>
                        <FaCheckCircle className={styles.checkIcon} />
                        <span>체크/신용카드</span>
                    </div>
                    <div className={styles.paymentOption}>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7eSS9iP-1g2_oG_p1sQyGk2yVdVYd_Vz_gA&s" alt="네이버페이 로고" />
                        <span>네이버페이</span>
                    </div>
                     <div className={styles.paymentOption}>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Y-5k2j-hXf_i_M4l_T9G8M-p_b_8Y2z-w&s" alt="카카오페이 로고" />
                        <span>카카오페이</span>
                    </div>
                </div>
                <button className={styles.paymentButton} disabled>결제하기</button>
                <p className={styles.modalFooter}>© WEVERSE COMPANY Inc.</p>
            </div>
        </div>
    )
}


export default function JellyPage() {
  const [activeTab, setActiveTab] = useState('charge'); // 'charge', 'history'
  const [selectedProduct, setSelectedProduct] = useState<JellyProduct | null>(null);

  const handleProductClick = (product: JellyProduct) => {
    setSelectedProduct(product);
  };

  const closeModal = () => {
    setSelectedProduct(null);
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
              <span>0</span>
            </div>
          </div>
          <div className={styles.jellyDetails}>
            <div className={styles.detailItem}>
              <span>충전젤리</span>
              <span>0</span>
            </div>
            <div className={styles.detailItem}>
              <span>적립젤리</span>
              <span>0</span>
            </div>
          </div>
          <div className={styles.chargeLimit}>
            <span>충전한도</span>
            <p>
              <strong>1,500</strong> / 최대 1,500
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
                {jellyProducts.map((product, index) => (
                  <div
                    key={index}
                    className={`${styles.productItem} ${selectedProduct?.price === product.price ? styles.selected : ''} ${product.bestOffer ? styles.bestOffer : ''}`}
                    onClick={() => handleProductClick(product)}
                  >
                    {product.bestOffer && <div className={styles.bestOfferBadge}>Best Offer</div>}
                    <div className={styles.productInfo}>
                      <GiJelly className={styles.jellyIcon} />
                      <div>
                        <p className={styles.productName}>젤리 {product.jellyAmount}</p>
                        {product.bonusJelly && (
                          <p className={styles.bonusJelly}>적립젤리 {product.bonusJelly}</p>
                        )}
                      </div>
                    </div>
                    <div className={styles.productPrice}>
                      <span>₩{product.price.toLocaleString()}</span>
                      {product.benefitPercent && (
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
                      <div>
                          <button className={styles.active}>충전 / 적립</button>
                          <button>사용</button>
                      </div>
                      <select>
                          <option>전체</option>
                      </select>
                  </div>
                  <div className={styles.emptyHistory}>
                      <p>해당 내역이 없습니다.</p>
                      <button onClick={() => setActiveTab('charge')}>젤리 충전</button>
                  </div>
              </div>
          )}
        </main>
      </div>
      <PaymentModal product={selectedProduct} onClose={closeModal} />
    </>
  );
}
