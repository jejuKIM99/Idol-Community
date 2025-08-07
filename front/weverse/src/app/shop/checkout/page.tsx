// app/shop/checkout/page.tsx (Modified)
"use client";

import React, { Suspense, useState, useMemo, useEffect } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import Script from 'next/script';
import Cookies from 'js-cookie';
import Header from '@/components/Header';
import { allProducts } from '@/data/mockData';
import styles from '@/styles/CheckoutPage.module.css';
import { FiChevronDown, FiChevronUp, FiX } from "react-icons/fi";

// PortOne(iamport) window 객체 타입 확장
declare global {
    interface Window {
        IMP?: any;
    }
}

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
    const router = useRouter(); // 라우터 사용

    // URL에서 상품 정보 가져오기
    const productId = searchParams.get('productId');
    const quantity = searchParams.get('quantity');
    const totalPrice = searchParams.get('totalPrice');

    // 상태 관리
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
    const [selectedPayment, setSelectedPayment] = useState<string | null>(null);
    const [isAgreementChecked, setIsAgreementChecked] = useState(false);

    // 컴포넌트 마운트 시 쿠키에서 주문자 정보 불러오기
    useEffect(() => {
        const savedInfo = Cookies.get('ordererInfo');
        if (savedInfo) {
            const parsedInfo = JSON.parse(savedInfo);
            setOrdererInfo(parsedInfo);
            setFormState(parsedInfo); // 수정 모달을 위해 formState도 업데이트
        }
    }, []);

    // 상품 정보 조회
    const product = useMemo(() =>
        allProducts.find(p => p.id === parseInt(productId || '')),
        [productId]
    );

    // 결제 버튼 활성화 여부 결정
    const isPayButtonEnabled = !!ordererInfo && !!selectedPayment && isAgreementChecked;

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

    // 입력 폼 변경 핸들러
    const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState(prevState => ({ ...prevState, [name]: value }));
    };

    // 주문자 정보 저장 핸들러
    const handleFormSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // 모든 필드가 채워졌는지 확인
        if (Object.values(formState).some(field => field.trim() === '')) {
            alert('모든 필수 정보를 입력해주세요.');
            return;
        }
        setOrdererInfo(formState);
        Cookies.set('ordererInfo', JSON.stringify(formState), { expires: 7 }); // 7일간 쿠키에 저장
        setIsModalOpen(false);
    };

    // 결제 처리 핸들러
    const handlePayment = () => {
        if (!isPayButtonEnabled || !ordererInfo || !product) return;

        const { IMP } = window;
        IMP.init('imp10391932');

        const paymentData = {
            // [수정] pg사 설정: 토스페이먼츠는 'tosspayments.상점MID' 형태로 입력해야 합니다.
            // 포트원 관리자 콘솔에서 해당 값을 확인하고 YOUR_TOSSPAYMENTS_MID 부분을 교체해주세요.
            pg: selectedPayment === 'kakao' 
                ? 'kakaopay' 
                : 'tosspayments.YOUR_TOSSPAYMENTS_MID', // ❗️ 중요: 이곳을 실제 값으로 변경하세요.
            pay_method: 'card',
            merchant_uid: `order_${new Date().getTime()}`,
            name: `${product.name} 등`,
            amount: finalPrice,
            buyer_email: ordererInfo.email,
            buyer_name: `${ordererInfo.lastName} ${ordererInfo.firstName}`,
            buyer_tel: ordererInfo.phone,
            buyer_addr: ordererInfo.address,
            buyer_postcode: '123-456', // 필요한 경우 우편번호도 동적으로 처리
        };

        IMP.request_pay(paymentData, (rsp: any) => {
            if (rsp.success) {
                console.log('Payment success:', rsp);
                alert('결제가 성공적으로 완료되었습니다.');
                // TODO: 결제 성공 시, 백엔드에 결제 정보 검증 및 저장 요청
                // 예: router.push(`/shop/checkout/success?imp_uid=${rsp.imp_uid}&merchant_uid=${rsp.merchant_uid}`);
            } else {
                console.error('Payment failed:', rsp);
                alert(`결제에 실패하였습니다. 에러: ${rsp.error_msg}`);
            }
        });
    };

    return (
        <>
            <Script src="https://cdn.iamport.kr/v1/iamport.js" strategy="lazyOnload" />

            <main className={styles.container}>
                <h1 className={styles.title}>주문서</h1>
                <div className={styles.contentWrapper}>
                    <div className={styles.leftColumn}>
                        {/* 주문 상품 */}
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

                        {/* 결제 수단 */}
                        <div className={styles.section}>
                            <h2 className={styles.sectionTitle}>결제 수단</h2>
                            <div className={styles.paymentGrid}>
                                <button
                                    className={`${styles.paymentButton} ${selectedPayment === 'kakao' ? styles.selectedKakao : ''}`}
                                    onClick={() => setSelectedPayment('kakao')}
                                    disabled={!ordererInfo}
                                >
                                    카카오페이
                                </button>
                                <button
                                    className={`${styles.paymentButton} ${selectedPayment === 'toss' ? styles.selectedToss : ''}`}
                                    onClick={() => setSelectedPayment('toss')}
                                    disabled={!ordererInfo}
                                >
                                    토스페이
                                </button>
                            </div>
                            {!ordererInfo && <p className={styles.infoText}>주문자 정보를 등록해야 결제 수단을 선택할 수 있습니다.</p>}
                        </div>
                    </div>

                    {/* 결제 금액 */}
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
                                <input
                                    type="checkbox"
                                    id="agreement"
                                    checked={isAgreementChecked}
                                    onChange={(e) => setIsAgreementChecked(e.target.checked)}
                                    disabled={!ordererInfo || !selectedPayment}
                                />
                                <label htmlFor="agreement">주문내용을 확인했으며, 결제에 동의합니다.</label>
                            </div>
                            <button
                                className={`${styles.finalPayButton} ${!isPayButtonEnabled ? styles.finalPayButtonDisabled : ''}`}
                                onClick={handlePayment}
                                disabled={!isPayButtonEnabled}
                            >
                                {isPayButtonEnabled ? `KRW ${finalPrice.toLocaleString()} 결제하기` : '결제 정보를 확인해주세요'}
                            </button>
                        </div>
                    </div>
                </div>

                {/* 주문자 정보 등록/수정 모달 */}
                {isModalOpen && (
                    <div className={styles.modalOverlay}>
                        <div className={styles.modalContent}>
                            <div className={styles.modalHeader}>
                                <h3>주문자 정보</h3>
                                <button onClick={() => setIsModalOpen(false)} className={styles.closeButton}><FiX /></button>
                            </div>
                            <form onSubmit={handleFormSubmit} className={styles.form}>
                                <div className={styles.formGroup}>
                                    <label htmlFor="lastName">성*</label>
                                    <input type="text" id="lastName" name="lastName" value={formState.lastName} onChange={handleFormChange} placeholder="성 입력 (예: 홍)" required />
                                </div>
                                <div className={styles.formGroup}>
                                    <label htmlFor="firstName">이름*</label>
                                    <input type="text" id="firstName" name="firstName" value={formState.firstName} onChange={handleFormChange} placeholder="이름 입력 (예: 길동)" required />
                                </div>
                                <div className={styles.formGroup}>
                                    <label htmlFor="address">주소*</label>
                                    <input type="text" id="address" name="address" value={formState.address} onChange={handleFormChange} placeholder="주소 입력" required />
                                </div>
                                <div className={styles.formGroup}>
                                    <label htmlFor="email">이메일*</label>
                                    <input type="email" id="email" name="email" value={formState.email} onChange={handleFormChange} placeholder="이메일 주소 입력" required />
                                </div>
                                <div className={styles.formGroup}>
                                    <label htmlFor="phone">전화번호*</label>
                                    <input type="tel" id="phone" name="phone" value={formState.phone} onChange={handleFormChange} placeholder="'-' 없이 숫자만 입력" required />
                                </div>
                                <div className={styles.modalActions}>
                                    <button type="submit" className={styles.saveButton}>저장</button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}
            </main>
        </>
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
