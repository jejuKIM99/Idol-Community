
import React from 'react';
import styles from './MediaItemCard.module.css';

interface MediaItemCardProps {
  thumbnailSrc: string;
  duration?: string; // Optional for videos
  title: string;
  type: 'image' | 'video';
}

const MediaItemCard: React.FC<MediaItemCardProps> = ({
  thumbnailSrc,
  duration,
  title,
  type,
}) => {
  return (
    <div className={styles.cardContainer}>
      <div className={styles.thumbnailWrapper}>
        {/* src={thumbnailSrc} */}
        <img alt={title} className={styles.thumbnail} /> 
        {duration && <span className={styles.duration}>{duration}</span>}
      </div>
      <h3 className={styles.title}>{title}</h3>
      <p className={styles.typeText}>{type === 'video' ? '영상' : '이미지'}</p>
    </div>
  );
};

export default MediaItemCard;
