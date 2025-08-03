import React from 'react';
import styles from './MerchCard.module.css';

interface MerchCardProps {
  image: string;
  name: string;
  price: string;
}

const MerchCard: React.FC<MerchCardProps> = ({ image, name, price }) => {
  return (
    <div className={styles.merchCard}>
      <img src={image} alt={name} className={styles.merchImage} />
      <div className={styles.info}>
        <p className={styles.merchName}>{name}</p>
        <p className={styles.merchPrice}>{price}</p>
      </div>
    </div>
  );
};

export default MerchCard;
