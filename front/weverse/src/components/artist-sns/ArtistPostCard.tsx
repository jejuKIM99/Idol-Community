
import React from 'react';
import styles from './ArtistPostCard.module.css';

interface ArtistPostCardProps {
  artistImage: string;
  date: string;
  month: string;
  artistName: string;
  postContent: string;
  postDateTime: string;
  postImageColor: string;
}

const ArtistPostCard: React.FC<ArtistPostCardProps> = ({
  artistImage,
  date,
  month,
  artistName,
  postContent,
  postDateTime,
  postImageColor,
}) => {
  return (
    <div className={styles.artistPostCard}>
      <div className={styles.leftContent}>
        <img alt="Artist" className={styles.artistImage} />
        <span className={styles.date}>{date}</span>
        <span className={styles.month}>{month}</span>
      </div>
      <div className={styles.rightContent}>
        <div className={styles.messageBubble}>
          <div className={styles.msgContent}>
            <span className={styles.bubbleArtistName}>{artistName}</span>
            <p className={styles.bubblePostContent}>{postContent}</p>
            <span className={styles.bubblePostDateTime}>{postDateTime}</span>
          </div>
          <div className={styles.bubbleImage} style={{ backgroundColor: postImageColor }}></div>
        </div>
      </div>
    </div>
  );
};

export default ArtistPostCard;
