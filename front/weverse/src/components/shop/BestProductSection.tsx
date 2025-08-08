// components/shop/BestProductSection.tsx (Updated)
"use client";

import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Grid, Navigation } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/grid';
import 'swiper/css/navigation';
import styles from './BestProductSection.module.css';
// import { Product } from '@/data/mockData'; // 목업 데이터 제거
import ProductCard from './ProductCard';

interface ShopProductDTO {
  productId: number;
  productName: string;
  price: number;
  productImage: string;
  artistId: number;
  artistName: string;
}

interface Props {
  products: ShopProductDTO[];
  title?: string;
}

const BestProductSection = ({ products, title = "Best" }: Props) => {
  return (
    <section className={styles.section}>
      <div className={styles.sectionHeader}>
        <h3 className={styles.sectionTitle}>{title}</h3>
      </div>
      
      {/* Desktop Grid View */}
      <div className={`${styles.productGrid} ${styles.desktopOnly}`}>
        {products.map(product => (
          <ProductCard key={product.productId} product={product} />
        ))}
      </div>

      {/* Mobile Slider View */}
      <div className={`${styles.productSlider} ${styles.mobileOnly}`}>
        <Swiper
          modules={[Grid, Navigation]}
          spaceBetween={15}
          slidesPerView={2.2}
          navigation
          grid={{
            rows: 2,
            fill: 'row',
          }}
          className="best-product-swiper"
        >
          {products.map(product => (
            <SwiperSlide key={product.productId} style={{ height: 'auto' }}>
              <ProductCard product={product} />
            </SwiperSlide>
          ))}
        </Swiper>
      </div>
    </section>
  );
};

export default BestProductSection;