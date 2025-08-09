import React from 'react';
import styles from './MediaCard.module.css';

interface MediaCardProps {
  thumbnail: string;
  title: string;
  date: string;
  time: string;
  onClick: () => void;
}

const MediaCard: React.FC<MediaCardProps> = ({ thumbnail, title, date,time, onClick }) => {
  return (
    <div className={styles.mediaCard} onClick={onClick}>
      <img src={thumbnail} alt={title} className={styles.thumbnail} />
      <div className={styles.info}>
        <h3 className={styles.title}>{title}</h3>
        <p className={styles.date}>{date}</p>
      </div>
    </div>
  );
};

export default MediaCard;
