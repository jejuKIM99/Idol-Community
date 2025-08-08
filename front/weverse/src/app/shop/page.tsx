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
// import { allArtists, bestProducts, notices, Artist } from '@/data/mockData'; // 목업 데이터 제거

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

interface ShopBannerDTO {
  bannerId: number;
  imageUrl: string;
  linkUrl: string;
}

interface ShopNoticeDTO {
  noticeId: number;
  title: string;
  content: string;
  createdAt: string;
}

interface ShopMainResponseDTO {
  banners: ShopBannerDTO[];
  artists: ShopArtistDTO[];
  products: ShopProductDTO[];
  notices: ShopNoticeDTO[];
}

const WeverseShopPage = () => {
  const [shopData, setShopData] = useState<ShopMainResponseDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [recommendedArtists, setRecommendedArtists] = useState<ShopArtistDTO[]>([]);
  const [artistsForProducts, setArtistsForProducts] = useState<ShopArtistDTO[]>([]);

  useEffect(() => {
    const fetchShopData = async () => {
      try {
        const response = await fetch('http://localhost:80/api/shop/main');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data: ShopMainResponseDTO = await response.json();

        const processedBanners = data.banners.map((banner: any) => {
          const artist = data.artists.find(a => a.groupId === banner.groupId);
          return {
            ...banner,
            linkUrl: artist ? `/shop/${encodeURIComponent(artist.stageName)}` : '#'
          };
        });

        setShopData({ ...data, banners: processedBanners });
      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchShopData();
  }, []);

  useEffect(() => {
    if (shopData) {
      const shuffledArtists = [...shopData.artists].sort(() => 0.5 - Math.random());
      const recommended = shuffledArtists.slice(0, 10);
      const forProducts = recommended.slice(0, 5);
      setRecommendedArtists(recommended);
      setArtistsForProducts(forProducts);
    }
  }, [shopData]);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  if (loading) {
    return <div className={styles.container}><p>로딩 중...</p></div>;
  }

  if (error) {
    return <div className={styles.container}><p>오류: {error}</p></div>;
  }

  if (!shopData) {
    return <div className={styles.container}><p>데이터를 불러올 수 없습니다.</p></div>;
  }

  return (
    <>
      <Header />
      
      <main className={styles.container}>
        <MainBanner banners={shopData.banners} />

        {/* --- 추천 아티스트 --- */}
        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h3 className={styles.sectionTitle}>Recommended Artist</h3>
          </div>
          {/* Desktop View */}
          <div className={`${styles.recommendedArtistsList} ${styles.desktopOnly}`}>
            {recommendedArtists.map(artist => (
              <Link href={`/shop/${encodeURIComponent(artist.stageName)}`} key={artist.artistId} className={styles.artistItem}>
                <img src={`http://localhost:80${artist.profileImage}`} alt={`${artist.stageName} logo`} className={styles.artistLogo} />
                <span className={styles.artistName}>{artist.stageName}</span>
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
                <SwiperSlide key={artist.artistId} style={{ width: 'auto' }}>
                  <Link href={`/shop/${encodeURIComponent(artist.stageName)}`} className={styles.artistItem}>

                <img src={`http://localhost:80${artist.profileImage}`} alt={`${artist.stageName} logo`} className={styles.artistLogo} />
                    <span className={styles.artistName}>{artist.stageName}</span>
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
        {artistsForProducts.map(artist => (
          <ArtistProductSection key={artist.artistId} artist={artist} products={shopData.products.filter(p => p.artistId === artist.artistId)} />
        ))}

        <BestProductSection products={shopData.products} />
        <NoticeSection notices={shopData.notices} title="Notice" />
      </main>

      <ArtistSearchModal
        isOpen={isModalOpen}
        onClose={closeModal}
        artists={shopData.artists}
      />
    </>
  );
};

export default WeverseShopPage;