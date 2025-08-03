// app/shop/[artistName]/all/page.tsx (Updated)
"use client";

import React, { useState, useMemo } from 'react';
import { useParams } from 'next/navigation';
import Header from '@/components/Header';
import { allArtists } from '@/data/mockData';
import styles from '@/styles/ArtistAllProductsPage.module.css';
import ProductCard from '@/components/shop/ProductCard'; // Import the shared, linked ProductCard

const ArtistAllProductsPage = () => {
  const params = useParams();
  const artistName = decodeURIComponent(params.artistName as string);
  
  const [activeCategory, setActiveCategory] = useState('ALL');

  const artist = useMemo(() => 
    allArtists.find(a => a.name === artistName),
    [artistName]
  );

  const categories = useMemo(() => {
    if (!artist) return [];
    const cats = new Set(artist.products.map(p => p.category).filter(Boolean));
    return ['ALL', ...Array.from(cats) as string[]];
  }, [artist]);

  const filteredProducts = useMemo(() => {
    if (!artist) return [];
    if (activeCategory === 'ALL') {
      return artist.products;
    }
    return artist.products.filter(p => p.category === activeCategory);
  }, [artist, activeCategory]);

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

  return (
    <>
      <Header />
      <main className={styles.container}>
        <div className={styles.categoryHeader}>
          {categories.map(cat => (
            <button 
              key={cat}
              className={`${styles.categoryButton} ${activeCategory === cat ? styles.active : ''}`}
              onClick={() => setActiveCategory(cat)}
            >
              {cat}
            </button>
          ))}
        </div>
        <div className={styles.productGrid}>
          {filteredProducts.map(product => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      </main>
    </>
  );
};

export default ArtistAllProductsPage;
