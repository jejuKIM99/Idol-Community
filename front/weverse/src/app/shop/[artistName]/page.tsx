// app/shop/[artistName]/page.tsx (Updated)
"use client";

import React, { useState, useMemo } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';
import Header from '@/components/Header';
import ArtistStoreBanner from '@/components/shop/ArtistStoreBanner';
import NoticeSection from '@/components/shop/NoticeSection';
import { allArtists, notices } from '@/data/mockData';
import styles from '@/styles/ArtistStorePage.module.css';
import ProductCard from '@/components/shop/ProductCard'; // Import the shared, linked ProductCard

const ArtistStorePage = () => {
  const params = useParams();
  const artistName = decodeURIComponent(params.artistName as string);
  
  const [activeTab, setActiveTab] = useState('MERCH');

  const artist = useMemo(() => 
    allArtists.find(a => a.name === artistName),
    [artistName]
  );

  const products = useMemo(() => {
    if (!artist) return [];
    // In a real app, you would filter products based on the active tab.
    // Here, we're just showing the first 6 products for demonstration.
    return artist.products.slice(0, 6);
  }, [artist, activeTab]);

  if (!artist) {
    return (
      <>
        <Header />
        <main className={styles.container}>
          <div className={styles.notFound}>아티스트를 찾을 수 없습니다.</div>
        </main>
      </>
    );
  }

  const hasBanners = artist.banners && artist.banners.length > 0;

  return (
    <>
      <Header />
      <main className={styles.container}>
        {hasBanners && <ArtistStoreBanner banners={artist.banners!} />}
        
        <div className={styles.contentGrid}>
          <div className={styles.productsSection}>
            <h3 className={styles.sectionTitle}>Products</h3>
            <div className={styles.tabs}>
              {['MERCH', 'ALBUM', 'TOUR MERCH', 'DVD/MEDIA'].map(tab => (
                <button 
                  key={tab}
                  className={`${styles.tabButton} ${activeTab === tab ? styles.active : ''}`}
                  onClick={() => setActiveTab(tab)}
                >
                  {tab}
                </button>
              ))}
            </div>
            
            <div className={styles.productGrid}>
              {products.map(product => (
                <ProductCard key={product.id} product={product} />
              ))}
            </div>

            <div className={styles.moreButtonWrapper}>
              <Link href={`/shop/${encodeURIComponent(artist.name)}/all`} className={styles.moreButton}>
                더보기
              </Link>
            </div>
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
