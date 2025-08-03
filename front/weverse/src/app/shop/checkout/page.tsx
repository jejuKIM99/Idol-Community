// app/shop/checkout/page.tsx (Modified)
"use client";

import React, { Suspense, useState, useMemo } from 'react';
import { useSearchParams } from 'next/navigation';
import Header from '@/components/Header';
import { allProducts } from '@/data/mockData';
import styles from '@/styles/CheckoutPage.module.css';
import { FaCheckCircle, FaRegCircle } from "react-icons/fa";
import { FiChevronDown, FiChevronUp, FiX } from "react-icons/fi";

// 주문자 정보 타입 정의
interface OrdererInfo {
    lastName: string;
    firstName: string;
    email: string;
    phone: string;
    address: string;
}

const CheckoutContent = () => {
    const searchParams = useSearchParams();
    const productId = searchParams.get('productId');
    const quantity = searchParams.get('quantity');
    const totalPrice = searchParams.get('totalPrice');

    // 모달 및 토글 상태 관리
    const [isProductDetailsOpen, setIsProductDetailsOpen] = useState(true);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [ordererInfo, setOrdererInfo] = useState<OrdererInfo | null>(null);
    const [formState, setFormState] = useState<OrdererInfo>({
        lastName: '',
        firstName: '',
        email: '',
        phone: '',
        address: ''
    });

    const product = useMemo(() => 
        allProducts.find(p => p.id === parseInt(productId || '')),
        [productId]
    );

    if (!product || !quantity || !totalPrice) {
        return (
            <main className={styles.container}>
                <div className={styles.error}>주문 정보를 불러오는데 실패했습니다.</div>
            </main>
        );
    }

    const numericTotalPrice = parseInt(totalPrice);
    const deliveryFee = numericTotalPrice >= 50000 ? 0 : 3000;
    const finalPrice = numericTotalPrice + deliveryFee;

    const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState(prevState => ({ ...prevState, [name]: value }));
    };

    const handleFormSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setOrdererInfo(formState);
        setIsModalOpen(false);
    };

    return (
        <main className={styles.container}>
            <h1 className={styles.title}>주문서</h1>
            <div className={styles.contentWrapper}>
                <div className={styles.leftColumn}>
                    {/* 주문 상품 (토글 가능) */}
                    <div className={styles.section}>
                        <div className={styles.sectionHeader} onClick={() => setIsProductDetailsOpen(!isProductDetailsOpen)}>
                            <h2 className={styles.sectionTitle}>주문 상품</h2>
                            {isProductDetailsOpen ? <FiChevronUp className={styles.toggleIcon} /> : <FiChevronDown className={styles.toggleIcon} />}
                        </div>
                        {isProductDetailsOpen && (
                            <div className={styles.productDetailsContent}>
                                <div className={styles.productItem}>
                                    <img src={product.imageUrl} alt={product.name} className={styles.productImage} />
                                    <div className={styles.productDesc}>
                                        <p>{product.name}</p>
                                        <p className={styles.productSubDesc}>HOPE ON THE STREET / {quantity}개</p>
                                    </div>
                                    <p className={styles.productPrice}>KRW {numericTotalPrice.toLocaleString()}</p>
                                </div>
                                <div className={styles.totalProductPrice}>
                                    <span>총 상품금액 ({quantity}개)</span>
                                    <span>KRW {numericTotalPrice.toLocaleString()}</span>
                                </div>
                            </div>
                        )}
                    </div>

                    {/* 주문자 */}
                    <div className={styles.section}>
                         <div className={styles.sectionHeader}>
                            <h2 className={styles.sectionTitle}>주문자</h2>
                        </div>
                        {ordererInfo ? (
                             <div className={styles.ordererInfoDisplay}>
                                <p><strong>이름:</strong> {ordererInfo.lastName}{ordererInfo.firstName}</p>
                                <p><strong>이메일:</strong> {ordererInfo.email}</p>
                                <p><strong>연락처:</strong> {ordererInfo.phone}</p>
                                <p><strong>주소:</strong> {ordererInfo.address}</p>
                                <button onClick={() => setIsModalOpen(true)} className={styles.editButton}>수정</button>
                            </div>
                        ) : (
                            <div className={styles.ordererInfo}>
                                <p>주문자 정보를 등록해 주세요.</p>
                                <button onClick={() => setIsModalOpen(true)} className={styles.registerButton}>등록</button>
                            </div>
                        )}
                    </div>

                    {/* 캐시 */}
                    <div className={styles.section}>
                        <h2 className={styles.sectionTitle}>캐시</h2>
                        <div className={styles.cashInfo}>
                            <input type="text" placeholder="KRW 0" className={styles.cashInput} />
                            <button className={styles.useButton}>전액 사용</button>
                        </div>
                        <div className={styles.cashBalance}>
                            <span>보유 KRW 0</span>
                            <label>
                                <input type="checkbox" /> 항상 먼저 사용
                            </label>
                        </div>
                    </div>

                    {/* 결제 수단 */}
                    <div className={styles.section}>
                        <h2 className={styles.sectionTitle}>결제 수단</h2>
                        <div className={styles.paymentMethod}>
                            <button className={`${styles.paymentOption} ${styles.weverseCard}`}>
                                <FaCheckCircle className={styles.checkIcon} /> 위버스 카드
                            </button>
                            <button className={styles.paymentOption}><FaRegCircle className={styles.checkIcon} /> 일반결제</button>
                        </div>
                        <div className={styles.paymentGrid}>
                            <button>체크/신용카드</button>
                            <button className={styles.payco}>PAYCO</button>
                            <button>계좌이체</button>
                            <button className={styles.kakaoPay}>kakaopay</button>
                            <button className={styles.toss}>toss</button>
                            <button>NAVER</button>
                            <button>Alipay+</button>
                            <button>WeChatPay</button>
                        </div>
                        <p className={styles.infoText}>결제 수단은 배송 국가에 따라 다를 수 있습니다.</p>
                    </div>
                </div>

                {/* 오른쪽 결제 금액 섹션 */}
                <div className={styles.rightColumn}>
                    <div className={styles.summaryBox}>
                        <h2 className={styles.summaryTitle}>결제 금액</h2>
                        <div className={styles.priceDetail}>
                            <span>총 상품 금액</span>
                            <span>KRW {numericTotalPrice.toLocaleString()}</span>
                        </div>
                        <div className={styles.priceDetail}>
                            <span>배송비</span>
                            <span>KRW {deliveryFee.toLocaleString()}</span>
                        </div>
                        <hr className={styles.summaryDivider} />
                        <div className={`${styles.priceDetail} ${styles.finalPrice}`}>
                            <span>총 결제 금액</span>
                            <span>KRW {finalPrice.toLocaleString()}</span>
                        </div>
                        <div className={styles.agreement}>
                            <input type="checkbox" id="agreement" />
                            <label htmlFor="agreement">주문내용을 확인했으며, 결제에 동의합니다. (결제대행 서비스 이용약관 포함)</label>
                        </div>
                        <button className={styles.finalPayButton}>동의 후 KRW {finalPrice.toLocaleString()} 결제</button>
                    </div>
                </div>
            </div>

            {/* 주문자 정보 등록 모달 */}
            {isModalOpen && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <div className={styles.modalHeader}>
                            <h3>주문자 등록</h3>
                            <button onClick={() => setIsModalOpen(false)} className={styles.closeButton}><FiX /></button>
                        </div>
                        <form onSubmit={handleFormSubmit} className={styles.form}>
                            <div className={styles.formGroup}>
                                <label htmlFor="lastName">성*</label>
                                <input type="text" id="lastName" name="lastName" value={formState.lastName} onChange={handleFormChange} placeholder="성 입력 (영어)" required />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="firstName">이름*</label>
                                <input type="text" id="firstName" name="firstName" value={formState.firstName} onChange={handleFormChange} placeholder="이름 입력 (영어)" required />
                            </div>
                             <div className={styles.formGroup}>
                                <label htmlFor="address">주소*</label>
                                <input type="text" id="address" name="address" value={formState.address} onChange={handleFormChange} placeholder="주소 입력" required />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="email">이메일*</label>
                                <input type="email" id="email" name="email" value={formState.email} onChange={handleFormChange} placeholder="kkspooto@gmail.com" required />
                            </div>
                            <div className={styles.formGroup}>
                                <label htmlFor="phone">전화번호*</label>
                                <input type="tel" id="phone" name="phone" value={formState.phone} onChange={handleFormChange} placeholder="전화번호 입력" required />
                            </div>
                            <div className={styles.modalActions}>
                                <button type="submit" className={styles.saveButton}>저장</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </main>
    );
};

const CheckoutPage = () => {
  return (
    <>
      <Header />
      <Suspense fallback={<div>Loading...</div>}>
        <CheckoutContent />
      </Suspense>
    </>
  )
}

export default CheckoutPage;
