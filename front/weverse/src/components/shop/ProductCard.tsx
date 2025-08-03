// components/shop/ProductCard.tsx (Updated)
import React from 'react';
import Link from 'next/link';
import { Product } from '@/data/mockData';
import styles from './ProductCard.module.css';

interface Props {
  product: Product;
}

const ProductCard = ({ product }: Props) => {
  return (
    <Link href={`/shop/products/${product.id}`} className={styles.cardLink}>
      <div className={styles.productCard}>
        <div className={styles.productImageWrapper}>
          <img src={product.imageUrl} alt={product.name} className={styles.productImage} />
        </div>
        <div className={styles.productInfo}>
          <div>
            <h4 className={styles.productName}>{product.name}</h4>
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
