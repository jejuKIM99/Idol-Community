import React from 'react';
import styles from './MediaCard.module.css';

interface MediaCardProps {
  thumbnail: string;
  title: string;
  date: string;
}

const MediaCard: React.FC<MediaCardProps> = ({ thumbnail, title, date }) => {
  return (
    <div className={styles.mediaCard}>
      <img src={thumbnail} alt={title} className={styles.thumbnail} />
      <div className={styles.info}>
        <h3 className={styles.title}>{title}</h3>
        <p className={styles.date}>{date}</p>
      </div>
    </div>
  );
};

export default MediaCard;
