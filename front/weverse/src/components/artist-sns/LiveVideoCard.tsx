
import React from 'react';
import styles from './LiveVideoCard.module.css';
import { FaEye, FaHeart, FaCommentDots } from 'react-icons/fa';

export interface LiveVideoCardProps {
  thumbnailSrc: string;
  duration: string;
  title: string;
  views: string;
  likes: string;
  chats: string;
  hasSubtitles: boolean;
  artistImageSrc: string;
  artistName: string | null | undefined;
  uploadDate: string;
}

const LiveVideoCard: React.FC<LiveVideoCardProps> = ({
  thumbnailSrc,
  duration,
  title,
  views,
  likes,
  chats,
  hasSubtitles,
  artistImageSrc,
  artistName,
  uploadDate,
}) => {
  return (
    <div className={styles.cardContainer}>
      <div className={styles.thumbnailWrapper}>
        <img src={`http://localhost:80${thumbnailSrc}`} alt={title} className={styles.thumbnail} />
        <span className={styles.duration}>{duration}</span>
      </div>
      <h3 className={styles.title}>{title}</h3>
      <div className={styles.stats}>
      <span><FaEye /> {views}</span>
      <span><FaHeart /> {likes}</span>
      <span><FaCommentDots /> {chats}</span>
        {hasSubtitles && <span className={styles.cc}>CC</span>}
      </div>
      <div className={styles.artistInfo}>
        <img src={`http://localhost:80${thumbnailSrc}`} alt={artistName} className={styles.artistImage} />
        <div className={styles.artistDetails}>
          <span className={styles.artistName}>{artistName}</span>
          <span className={styles.uploadDate}>{uploadDate}</span>
        </div>
      </div>
    </div>
  );
};

export default LiveVideoCard;
