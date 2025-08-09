// components/shop/ProductCard.tsx (Updated)
import React from 'react';
import Link from 'next/link';
// import { Product } from '@/data/mockData'; // 목업 데이터 제거
import styles from './ProductCard.module.css';

interface ShopProductDTO {
  productId: number;
  productName: string;
  price: number;
  productImage: string;
  artistId: number;
  artistName: string;
}

interface Props {
  product: ShopProductDTO;
}

const ProductCard = ({ product }: Props) => {
  return (
    <Link href={`/shop/products/${product.productId}`} className={styles.cardLink}>
      <div className={styles.productCard}>
        <div className={styles.productImageWrapper}>
          <img src={product.productImage} alt={product.productName} className={styles.productImage} />
        </div>
        <div className={styles.productInfo}>
          <div>
            <h4 className={styles.productName}>{product.productName}</h4>
          </div>
          <p className={styles.productPrice}>
            {product.price.toLocaleString()}<span>원</span>
          </p>
        </div>
      </div>
    </Link>
  );
};


export default ProductCard;
