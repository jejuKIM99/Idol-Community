'use client';

import { useRef, useState, useEffect } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/autoplay';
import styles from '@/styles/AdBanner.module.css';
import { FiChevronLeft, FiChevronRight } from 'react-icons/fi';

const AdBanner = () => {
  const prevRef = useRef(null);
  const nextRef = useRef(null);
  const [swiper, setSwiper] = useState<any>(null);

  useEffect(() => {
    if (swiper) {
      swiper.params.navigation.prevEl = prevRef.current;
      swiper.params.navigation.nextEl = nextRef.current;
      swiper.navigation.init();
      swiper.navigation.update();
    }
  }, [swiper]);

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
        {dummyImages.map((image) => (
          <SwiperSlide key={image.id}>
            <div className={styles.adBannerItem} style={{ backgroundColor: image.color }}>
              Ad Banner {image.id}
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
