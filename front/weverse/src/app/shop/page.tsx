// app/shop/page.tsx (Updated)
"use client";

import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Header from '@/components/Header';
import MainBanner from '@/components/shop/MainBanner';
import ArtistProductSection from '@/components/shop/ArtistProductSection';
import BestProductSection from '@/components/shop/BestProductSection';
import NoticeSection from '@/components/shop/NoticeSection';
import ArtistSearchModal from '@/components/shop/ArtistSearchModal';
import { FiSearch } from 'react-icons/fi';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import styles from './Shop.module.css';
import { allArtists, bestProducts, notices, Artist } from '@/data/mockData';

const WeverseShopPage = () => {
  const [recommendedArtists, setRecommendedArtists] = useState<Artist[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const shuffled = [...allArtists].sort(() => 0.5 - Math.random());
    setRecommendedArtists(shuffled);
  }, []);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <>
      <Header />
      
      <main className={styles.container}>
        <MainBanner />

        {/* --- 추천 아티스트 --- */}
        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h3 className={styles.sectionTitle}>Recommended Artist</h3>
          </div>
          {/* Desktop View */}
          <div className={`${styles.recommendedArtistsList} ${styles.desktopOnly}`}>
            {recommendedArtists.map(artist => (
              <Link href={`/shop/${encodeURIComponent(artist.name)}`} key={artist.id} className={styles.artistItem}>
                <img src={artist.logoUrl} alt={`${artist.name} logo`} className={styles.artistLogo} />
                <span className={styles.artistName}>{artist.name}</span>
              </Link>
            ))}
          </div>
          {/* Mobile View */}
          <div className={`${styles.recommendedArtistsSlider} ${styles.mobileOnly}`}>
            <Swiper
              spaceBetween={10}
              slidesPerView={'auto'}
              className="artist-swiper"
            >
              {recommendedArtists.map(artist => (
                <SwiperSlide key={artist.id} style={{ width: 'auto' }}>
                  <Link href={`/shop/${encodeURIComponent(artist.name)}`} className={styles.artistItem}>
                    <img src={artist.logoUrl} alt={`${artist.name} logo`} className={styles.artistLogo} />
                    <span className={styles.artistName}>{artist.name}</span>
                  </Link>
                </SwiperSlide>
              ))}
            </Swiper>
          </div>
          <div className={styles.searchBar} onClick={openModal}>
            <FiSearch />
            <span>아티스트</span>
          </div>
        </section>

        {/* --- 아티스트별 상품 섹션 (동적 생성) --- */}
        {recommendedArtists.map(artist => (
          <ArtistProductSection key={artist.id} artist={artist} />
        ))}

        <BestProductSection products={bestProducts} />
        <NoticeSection notices={notices} title="Notice" />
      </main>

      <ArtistSearchModal
        isOpen={isModalOpen}
        onClose={closeModal}
        artists={allArtists}
      />
    </>
  );
};

export default WeverseShopPage;
