// components/shop/ArtistStoreBanner.tsx (Updated)
"use client";

import Link from 'next/link';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import styles from './MainBanner.module.css'; // Reusing MainBanner styles

interface ShopBannerDTO {
  bannerId: number;
  imageUrl: string;
  linkUrl: string;
}

interface Props {
  banners: ShopBannerDTO[];
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
        {banners.map((banner) => (
          <SwiperSlide key={banner.bannerId}>
            <Link href={banner.linkUrl || '#'}>
              <div className={styles.mainBanner}>
                <div className={styles.bannerBg} style={{ backgroundImage: `url(http://localhost:80${banner.imageUrl})` }}></div>
                {/* Banner content can be added here if needed in the future */}
              </div>
            </Link>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default ArtistStoreBanner;
