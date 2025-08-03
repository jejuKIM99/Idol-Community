// app/shop/products/[productId]/page.tsx (Modified)
"use client";

import React, { useState, useMemo } from 'react';
import { useParams, useRouter } from 'next/navigation';
import Header from '@/components/Header';
import { allProducts, Product } from '@/data/mockData';
import styles from '@/styles/ProductDetailPage.module.css';
import { FiMinus, FiPlus } from 'react-icons/fi';
import BestProductSection from '@/components/shop/BestProductSection';

const ProductDetailPage = () => {
  const params = useParams();
  const router = useRouter();
  const productId = params.productId as string;
  
  const [quantity, setQuantity] = useState(1);
  const [activeTab, setActiveTab] = useState('details');

  const product = useMemo(() => 
    allProducts.find(p => p.id === parseInt(productId)),
    [productId]
  );

  const relatedProducts = useMemo(() => {
    if (!product) return [];
    return allProducts
      .filter(p => p.artistName === product.artistName && p.id !== product.id)
      .slice(0, 5);
  }, [product]);

  if (!product) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.notFound}>상품을 찾을 수 없습니다.</div>
        </main>
      </>
    );
  }

  const handleQuantityChange = (amount: number) => {
    setQuantity(prev => Math.max(1, prev + amount));
  };

  const totalPrice = product.price * quantity;

  const handlePurchase = () => {
    // 결제 페이지로 이동하면서 상품 정보를 쿼리 파라미터로 전달
    router.push(`/shop/checkout?productId=${product.id}&quantity=${quantity}&totalPrice=${totalPrice}`);
  };

  return (
    <>
      <Header />
      <main className={styles.container}>
        <div className={styles.productSummary}>
          <div className={styles.imageWrapper}>
            <img src={product.imageUrl} alt={product.name} className={styles.productImage} />
          </div>
          <div className={styles.infoWrapper}>
            <p className={styles.artistName}>{product.artistName} &gt;</p>
            <h1 className={styles.productName}>{product.name}</h1>
            <p className={styles.productPrice}>{product.price.toLocaleString()}원</p>
            
            <div className={styles.divider}></div>

            <div className={styles.quantitySelector}>
              <span>수량</span>
              <div className={styles.quantityControls}>
                <button onClick={() => handleQuantityChange(-1)}><FiMinus /></button>
                <span>{quantity}</span>
                <button onClick={() => handleQuantityChange(1)}><FiPlus /></button>
              </div>
            </div>
            
            <div className={styles.divider}></div>

            <div className={styles.totalPriceSection}>
              <span>총 상품 금액</span>
              <span className={styles.totalPrice}>{totalPrice.toLocaleString()}원</span>
            </div>

            <div className={styles.actionButtons}>
              <button className={styles.buyButton} onClick={handlePurchase}>구매하기</button>
            </div>
          </div>
        </div>

        <div className={styles.productDetails}>
          <div className={styles.tabHeader}>
            <button 
              className={`${styles.tabButton} ${activeTab === 'details' ? styles.active : ''}`}
              onClick={() => setActiveTab('details')}
            >
              상세정보
            </button>
            <button 
              className={`${styles.tabButton} ${activeTab === 'cautions' ? styles.active : ''}`}
              onClick={() => setActiveTab('cautions')}
            >
              유의사항
            </button>
          </div>
          <div className={styles.tabContent}>
            {activeTab === 'details' && (
              <div className={styles.detailsContent}>
                {product.detailImageUrl ? (
                  <img src={product.detailImageUrl} alt={`${product.name} 상세 정보`} className={styles.detailImage} />
                ) : (
                  <p>상세 정보 이미지가 없습니다.</p>
                )}
              </div>
            )}
            {activeTab === 'cautions' && (
              <div className={styles.cautionsContent}>
                <h4>교환/환불 안내</h4>
                <p>- 상품을 수령하신 날로부터 7일 이내에만 교환/환불이 가능합니다.</p>
                <p>- 고객의 부주의로 인한 상품 훼손 및 포장 개봉의 경우 교환/환불이 불가합니다.</p>
                <h4>유의사항</h4>
                <p>- 본 상품은 한정 수량으로 제작되어 조기 품절될 수 있습니다.</p>
              </div>
            )}
          </div>
        </div>
        
        {relatedProducts.length > 0 && (
            <div className={styles.relatedProductsSection}>
                 <BestProductSection products={relatedProducts} title={`${product.artistName}의 다른 상품`} />
            </div>
        )}

      </main>
    </>
  );
};

export default ProductDetailPage;
