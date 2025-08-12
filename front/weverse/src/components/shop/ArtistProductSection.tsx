// components/shop/ArtistProductSection.tsx (Updated)
"use client";

import React from 'react';
import Link from 'next/link';
import { FiChevronRight } from 'react-icons/fi';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import styles from './ArtistProductSection.module.css';
// import { Artist } from '@/data/mockData'; // 목업 데이터 제거
import ProductCard from './ProductCard'; // Import the shared ProductCard

interface ShopArtistDTO {
  artistId: number;
  groupId: number;
  stageName: string;
  email: string;
  password?: string;
  profileImage: string;
  snsUrl: string;
  birthday: string;
  statusMessage: string;
  dmNickname: string;
}

interface ShopProductDTO {
  productId: number;
  productName: string;
  price: number;
  productImage: string;
  artistId: number;
  artistName: string;
}

interface Props {
  artists: ShopArtistDTO[];
  products: ShopProductDTO[];
  groupName: string;
}

const ArtistProductSection = ({ artists, products, groupName }: Props) => {
  if (artists.length === 0 || products.length === 0) return null;

  const groupId = artists[0].groupId;

  return (
    <section className={`${styles.section} ${styles.nowSection}`}>
      <Link href={`/shop/group/${groupId}`} className={styles.headerLink}>
        <div className={styles.nowHeader}>
          <span>Now</span>
          <FiChevronRight />
          <span>{groupName}</span>
        </div>
      </Link>
      
      {/* Desktop Grid View */}
      <div className={`${styles.productGrid} ${styles.desktopOnly}`}>
        {products.slice(0, 4).map(product => (
          <ProductCard key={product.productId} product={product} />
        ))}
      </div>

      {/* Mobile Slider View */}
      <div className={`${styles.productSlider} ${styles.mobileOnly}`}>
        <Swiper
          modules={[Navigation]}
          spaceBetween={15}
          slidesPerView={2.2}
          navigation
          className="product-swiper"
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

export default ArtistProductSection;
