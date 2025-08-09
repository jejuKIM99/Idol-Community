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
  artist: ShopArtistDTO;
  products: ShopProductDTO[];
}

const ArtistProductSection = ({ artist, products }: Props) => {
  const { stageName: artistName } = artist;
  if (products.length === 0) return null;

  return (
    <section className={`${styles.section} ${styles.nowSection}`}>
      <Link href={`/shop/${encodeURIComponent(artistName)}`} className={styles.headerLink}>
        <div className={styles.nowHeader}>
          <span>Now</span>
          <FiChevronRight />
          <span>{artistName}</span>
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
