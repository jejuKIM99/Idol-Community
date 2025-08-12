
// app/shop/group/[groupId]/page.tsx
"use client";

import React, { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';
import Header from '@/components/Header';
import ArtistStoreBanner from '@/components/shop/ArtistStoreBanner';
import NoticeSection from '@/components/shop/NoticeSection';
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
  groupId?: number;
}

interface ShopNoticeDTO {
  noticeId: number;
  title: string;
  content: string;
  createdAt: string;
}

interface GroupInfo {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
}

const GroupStorePage = () => {
  const params = useParams();
  const groupId = parseInt(params.groupId as string, 10);

  const [groupArtists, setGroupArtists] = useState<ShopArtistDTO[]>([]);
  const [groupProducts, setGroupProducts] = useState<ShopProductDTO[]>([]);
  const [groupBanners, setGroupBanners] = useState<ShopBannerDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [activeTab, setActiveTab] = useState('MERCH');
  const [groupInfo, setGroupInfo] = useState<GroupInfo | null>(null);

  useEffect(() => {
    if (isNaN(groupId)) return;

    const fetchGroupData = async () => {
      try {
        const groupResponse = await fetch(`http://localhost:80/api/artist/group?groupId=${groupId}`);
        if (groupResponse.ok) {
          const groupData = await groupResponse.json();
          setGroupInfo(groupData.group);
        }
        // 1. 모든 아티스트 목록을 가져와서 groupId로 필터링
        const artistsResponse = await fetch('http://localhost:80/api/shop/artists');
        if (!artistsResponse.ok) {
          throw new Error(`HTTP error! status: ${artistsResponse.status}`);
        }
        const allArtists: ShopArtistDTO[] = await artistsResponse.json();
        const artistsInGroup = allArtists.filter(a => a.groupId === groupId);

        if (artistsInGroup.length === 0) {
          setError('그룹을 찾을 수 없습니다.');
          setLoading(false);
          return;
        }
        setGroupArtists(artistsInGroup);

        // 2. 그룹에 속한 모든 아티스트의 상품 목록 가져오기
        let allProducts: ShopProductDTO[] = [];
        for (const artist of artistsInGroup) {
          const productsResponse = await fetch(`http://localhost:80/api/shop/artists/${artist.artistId}/products`);
          if (productsResponse.ok) {
            const productsData: any[] = await productsResponse.json();
            const artistProducts = productsData.map(p => ({
              productId: p.productId,
              productName: p.productName,
              price: p.price,
              productImage: p.images && p.images.length > 0 ? p.images[0].imageUrl : '/images/default.png',
              artistId: artist.artistId,
              artistName: artist.stageName
            }));
            allProducts = [...allProducts, ...artistProducts];
          }
        }
        setGroupProducts(allProducts);

        // 3. 메인 상점 데이터에서 현재 그룹의 배너 필터링
        const mainShopResponse = await fetch('http://localhost:80/api/shop/main');
        if (!mainShopResponse.ok) {
          throw new Error(`HTTP error! status: ${mainShopResponse.status}`);
        }
        const bannersData: any[] = await mainShopResponse.json();
        const filteredBanners = bannersData
          .filter((banner: any) => banner.groupId === groupId)
          .map(banner => ({
            bannerId: banner.bannerId,
            imageUrl: `http://localhost:80${banner.bannerImage}`,
            linkUrl: banner.linkUrl || `/shop/group/${groupId}`
          }));
        setGroupBanners(filteredBanners);

      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchGroupData();
  }, [groupId]);

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

  if (groupArtists.length === 0) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.notFound}>그룹을 찾을 수 없습니다.</div>
        </main>
      </>
    );
  }

  const hasBanners = groupBanners && groupBanners.length > 0;

  return (
    <>
      <Header />
      <main className={styles.container}>
        {hasBanners && <ArtistStoreBanner banners={groupBanners} />}
        
        <div className={styles.contentGrid}>
          <div className={styles.productsSection}>
            <h3 className={styles.sectionTitle}>{groupInfo ? groupInfo.groupName : ''} Products</h3>
            <div className={styles.tabs}>
              <button 
                key={'MERCH'}
                className={`${styles.tabButton} ${activeTab === 'MERCH' ? styles.active : ''}`}
                onClick={() => setActiveTab('MERCH')}
              >
                MERCH
              </button>
            </div>
            
            <div className={styles.productGrid}>
              {groupProducts.length > 0 ? (
                groupProducts.map(product => (
                  <ProductCard key={product.productId} product={product} />
                ))
              ) : (
                <div className={styles.noProducts}>
                  <p>상품이 없습니다.</p>
                </div>
              )}
            </div>

            {groupProducts.length > 0 && (
              <div className={styles.moreButtonWrapper}>
                <Link href={`/shop/group/${groupId}/all`} className={styles.moreButton}>
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

export default GroupStorePage;
