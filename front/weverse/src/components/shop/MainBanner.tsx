// components/shop/MainBanner.tsx (Updated)
"use client";

import Link from 'next/link';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import styles from './MainBanner.module.css';
import { mainBanners } from '@/data/mockData';

const MainBanner = () => {
  return (
    <div className={styles.bannerContainer}>
      <Swiper
        modules={[Navigation, Pagination, Autoplay]}
        spaceBetween={0}
        slidesPerView={1}
        navigation
        pagination={{ clickable: true }}
        loop={true}
        autoplay={{
          delay: 4000,
          disableOnInteraction: false,
        }}
      >
        {mainBanners.map((banner, index) => (
          <SwiperSlide key={index}>
            <Link href={banner.artistName ? `/shop/${encodeURIComponent(banner.artistName)}` : '#'}>
              <div className={styles.mainBanner}>
                <div className={styles.bannerBg} style={{ backgroundImage: `url(${banner.imgSrc})` }}></div>
                <div className={styles.bannerContent}>
                  <img 
                    src={banner.albumArt}
                    alt={`${banner.title} Album Art`}
                    className={styles.bannerAlbumArt}
                  />
                  <div className={styles.bannerText}>
                    <h2>{banner.title}</h2>
                    <p>{banner.subtitle}</p>
                  </div>
                </div>
              </div>
            </Link>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default MainBanner;
