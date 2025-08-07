// components/shop/ArtistStoreBanner.tsx (New File)
"use client";

import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import styles from './MainBanner.module.css'; // Reusing MainBanner styles
import { Banner } from '@/data/mockData';

interface Props {
  banners: Banner[];
}

const ArtistStoreBanner = ({ banners }: Props) => {
  if (!banners || banners.length === 0) {
    return null;
  }

  return (
    <div className={styles.bannerContainer} style={{ aspectRatio: '16 / 5' }}>
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
        {banners.map((banner, index) => (
          <SwiperSlide key={index}>
            <div className={styles.mainBanner}>
              <div className={styles.bannerBg} style={{ backgroundImage: `url(${banner.imgSrc})` }}></div>
              <div className={styles.bannerContent}>
                {banner.albumArt && (
                  <img 
                    src={banner.albumArt}
                    alt={`${banner.title} Album Art`}
                    className={styles.bannerAlbumArt}
                  />
                )}
                <div className={styles.bannerText}>
                  <h2>{banner.title}</h2>
                  <p>{banner.subtitle}</p>
                </div>
              </div>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default ArtistStoreBanner;
