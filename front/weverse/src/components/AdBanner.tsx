'use client';

import { useRef, useState, useEffect } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/autoplay';
import styles from '@/styles/AdBanner.module.css';
import { FiChevronLeft, FiChevronRight } from 'react-icons/fi';
import axios from 'axios';

interface Banner {
  bannerId: number;
  bannerImage: string;
}

const AdBanner = () => {
  const prevRef = useRef(null);
  const nextRef = useRef(null);
  const [swiper, setSwiper] = useState<any>(null);
  const [banner, setBanner] = useState<Banner[]>([]);

  useEffect(() => {
    if (swiper) {
      swiper.params.navigation.prevEl = prevRef.current;
      swiper.params.navigation.nextEl = nextRef.current;
      swiper.navigation.init();
      swiper.navigation.update();
    }
  }, [swiper]);

  useEffect(() => {
    const fetchBanner = async () => {
      try {
        const response = await axios.get(`http://localhost:80/api/shop/main`);
        console.log("Banner : " , response.data)
        setBanner(response.data);
        // setIsFollowing(response.data);
      } catch (err) {
        console.error('Failed to fetch Banner data:', err);
      }
    };
    fetchBanner();
  }, []);

  const dummyImages = [
    { id: 1, color: '#ffadad' },
    { id: 2, color: '#ffd6a5' },
    { id: 3, color: '#fdffb6' },
    { id: 4, color: '#caffbf' },
    { id: 5, color: '#9bf6ff' },
  ];

  return (
    <div className={styles.adBannerContainer}>
      <div ref={prevRef} className={styles.swiperButtonPrev}>
        <FiChevronLeft size={24} />
      </div>
      <Swiper
        modules={[Navigation, Autoplay]}
        onSwiper={setSwiper}
        spaceBetween={20}
        slidesPerView={2}
        loop={true}
        autoplay={{
          delay: 5000,
          disableOnInteraction: false,
        }}
      >
        {banner.map((banner) => (
          <SwiperSlide key={banner.bannerId}>
            <div className={styles.adBannerItem}>
              <img className={styles.bannerImage} src={`http://localhost:80${banner.bannerImage}`} alt="bannerImage" />
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
      <div ref={nextRef} className={styles.swiperButtonNext}>
        <FiChevronRight size={24} />
      </div>
    </div>
  );
};

export default AdBanner;
