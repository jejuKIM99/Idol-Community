
// app/shop/group/[groupId]/all/page.tsx
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
  category?: { 
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

const GroupAllProductsPage = () => {
  const params = useParams();
  const groupId = parseInt(params.groupId as string, 10);

  const [products, setProducts] = useState<ShopProductDTO[]>([]);
  const [groupArtists, setGroupArtists] = useState<ShopArtistDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [activeCategory, setActiveCategory] = useState('ALL');

  useEffect(() => {
    if (isNaN(groupId)) return;

    const fetchProducts = async () => {
      try {
        const artistsResponse = await fetch(`http://localhost:80/api/shop/artists`);
        if(!artistsResponse.ok) {
            throw new Error(`HTTP error! status: ${artistsResponse.status}`);
        }
        const artists: ShopArtistDTO[] = await artistsResponse.json();
        const artistsInGroup = artists.filter(a => a.groupId === groupId);
        
        if (artistsInGroup.length === 0) {
            setError('그룹을 찾을 수 없습니다.');
            setLoading(false);
            return;
        }
        setGroupArtists(artistsInGroup);

        let allProducts: ShopProductDTO[] = [];
        for (const artist of artistsInGroup) {
          const response = await fetch(`http://localhost:80/api/shop/artists/${artist.artistId}/products`);
          if (response.ok) {
            const data: any[] = await response.json();
            const artistProducts = data.map(p => ({
              productId: p.productId,
              productName: p.productName,
              price: p.price,
              productImage: p.images && p.images.length > 0 ? p.images[0].imageUrl : '/images/default.png',
              artistId: artist.artistId,
              artistName: artist.stageName,
              category: p.category
            }));
            allProducts = [...allProducts, ...artistProducts];
          }
        }
        setProducts(allProducts);

      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, [groupId]);

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

export default GroupAllProductsPage;
