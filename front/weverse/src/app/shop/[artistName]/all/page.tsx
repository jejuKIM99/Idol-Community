"use client";

import React, { useState, useEffect, useMemo } from 'react';
import { useParams } from 'next/navigation';
import Header from '@/components/Header';
import ProductCard from '@/components/shop/ProductCard';
import styles from '@/styles/ArtistAllProductsPage.module.css';

interface ShopProductDTO {
  productId: number;
  productName: string;
  price: number;
  productImage: string;
  artistId: number;
  artistName: string;
  category?: { // Optional category property
    categoryId: number;
    categoryName: string;
  };
}

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

const ArtistAllProductsPage = () => {
  const params = useParams();
  const artistName = decodeURIComponent(params.artistName as string);

  const [products, setProducts] = useState<ShopProductDTO[]>([]);
  const [artist, setArtist] = useState<ShopArtistDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [activeCategory, setActiveCategory] = useState('ALL');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const artistsResponse = await fetch(`http://localhost:80/api/shop/artists`);
        if(!artistsResponse.ok) {
            throw new Error(`HTTP error! status: ${artistsResponse.status}`);
        }
        const artists: ShopArtistDTO[] = await artistsResponse.json();
        const currentArtist = artists.find(a => a.stageName === artistName);
        
        if (!currentArtist) {
            setError('아티스트를 찾을 수 없습니다.');
            setLoading(false);
            return;
        }
        setArtist(currentArtist);

        const response = await fetch(`http://localhost:80/api/shop/artists/${currentArtist.artistId}/products`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data: ShopProductDTO[] = await response.json();
        setProducts(data.map(p => ({
          ...p,
          productImage: `http://localhost:80${p.productImage}`
        })));
      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    if (artistName) {
        fetchProducts();
    }
  }, [artistName]);

  const categories = useMemo(() => {
    const cats = new Set(products.map(p => p.category?.categoryName).filter(Boolean));
    return ['ALL', ...Array.from(cats) as string[]];
  }, [products]);

  const filteredProducts = useMemo(() => {
    if (activeCategory === 'ALL') {
      return products;
    }
    return products.filter(p => p.category?.categoryName === activeCategory);
  }, [products, activeCategory]);

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
            <ProductCard key={product.productId} product={product} />
          ))}
        </div>
      </main>
    </>
  );
};

export default ArtistAllProductsPage;