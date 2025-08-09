// app/shop/[artistName]/page.tsx (Modified)
"use client";

import React, { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';
import Header from '@/components/Header';
import ArtistStoreBanner from '@/components/shop/ArtistStoreBanner';
import NoticeSection from '@/components/shop/NoticeSection';
// import { allArtists, notices } from '@/data/mockData'; // 목업 데이터 제거
import styles from '@/styles/ArtistStorePage.module.css';
import ProductCard from '@/components/shop/ProductCard';

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

const ArtistStorePage = () => {
  const params = useParams();
  const artistName = decodeURIComponent(params.artistName as string);
  
  const [artistData, setArtistData] = useState<ShopArtistDTO | null>(null);
  const [artistProducts, setArtistProducts] = useState<ShopProductDTO[]>([]);
  const [artistBanners, setArtistBanners] = useState<ShopBannerDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [activeTab, setActiveTab] = useState('MERCH');

  useEffect(() => {
    const fetchArtistData = async () => {
      try {
        // 1. 모든 아티스트 목록을 가져와서 artistId 찾기
        const artistsResponse = await fetch('http://localhost:80/api/shop/artists');
        if (!artistsResponse.ok) {
          throw new Error(`HTTP error! status: ${artistsResponse.status}`);
        }
        const allArtists: ShopArtistDTO[] = await artistsResponse.json();
        const foundArtist = allArtists.find(a => a.stageName === artistName);

        if (!foundArtist) {
          setError('아티스트를 찾을 수 없습니다.');
          setLoading(false);
          return;
        }
        setArtistData(foundArtist);

        // 2. 해당 아티스트의 상품 목록 가져오기
        const productsResponse = await fetch(`http://localhost:80/api/shop/artists/${foundArtist.artistId}/products`);
        if (!productsResponse.ok) {
          throw new Error(`HTTP error! status: ${productsResponse.status}`);
        }
        const productsData: ShopProductDTO[] = await productsResponse.json();
        setArtistProducts(productsData.map(p => ({
          ...p,
          productImage: `http://localhost:80${p.productImage}` // 이미지 경로 수정
        })));

        // 3. 메인 상점 데이터에서 현재 아티스트의 배너 필터링
        const mainShopResponse = await fetch('http://localhost:80/api/shop/main');
        if (!mainShopResponse.ok) {
          throw new Error(`HTTP error! status: ${mainShopResponse.status}`);
        }
        const mainShopData = await mainShopResponse.json();
        const artistBanners = mainShopData.banners.filter((banner: any) => banner.groupId === foundArtist.groupId);
        setArtistBanners(artistBanners); 

      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchArtistData();
  }, [artistName]);

  // 공지사항 데이터 (현재 백엔드에 아티스트별 공지사항 API가 없으므로 임시로 빈 배열)
  const notices: ShopNoticeDTO[] = [];

  if (loading) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.loading}>로딩 중...</div>
        </main>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.error}>오류: {error}</div>
        </main>
      </>
    );
  }

  if (!artistData) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.notFound}>아티스트를 찾을 수 없습니다.</div>
        </main>
      </>
    );
  }

  const hasBanners = artistBanners && artistBanners.length > 0;

  return (
    <>
      <Header />
      <main className={styles.container}>
        {hasBanners && <ArtistStoreBanner banners={artistBanners.map(b => ({ bannerId: b.bannerId, imageUrl: b.imageUrl, linkUrl: b.linkUrl }))} />}
        
        <div className={styles.contentGrid}>
          <div className={styles.productsSection}>
            <h3 className={styles.sectionTitle}>Products</h3>
            <div className={styles.tabs}>
              {/* 탭은 MERCH만 유지하거나, 백엔드 카테고리에 따라 동적으로 생성 */}
              <button 
                key={'MERCH'}
                className={`${styles.tabButton} ${activeTab === 'MERCH' ? styles.active : ''}`}
                onClick={() => setActiveTab('MERCH')}
              >
                MERCH
              </button>
            </div>
            
            <div className={styles.productGrid}>
              {artistProducts.length > 0 ? (
                artistProducts.map(product => (
                  <ProductCard key={product.productId} product={product} />
                ))
              ) : (
                <div className={styles.noProducts}>
                  <p>상품이 없습니다.</p>
                </div>
              )}
            </div>

            {/* 모두보기 버튼은 상품이 있을 경우에만 표시 */}
            {artistProducts.length > 0 && (
              <div className={styles.moreButtonWrapper}>
                <Link href={`/shop/${encodeURIComponent(artistData.stageName)}/all`} className={styles.moreButton}>
                  모두 보기
                </Link>
              </div>
            )}
          </div>

          <aside className={styles.sidebar}>
            <NoticeSection notices={notices} title="Notice" />
            <NoticeSection notices={[]} title="Event" />
          </aside>
        </div>
      </main>
    </>
  );
};

export default ArtistStorePage;