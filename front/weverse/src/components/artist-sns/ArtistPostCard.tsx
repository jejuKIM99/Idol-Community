
import React from 'react';
import styles from './ArtistPostCard.module.css';

interface ArtistPostCardProps {
  artistImage: string;
  artistName: string;
  postContent: string;
  postDateTime: string;
  postImage: string;
  likes: number;
  comments: number;
}

const ArtistPostCard: React.FC<ArtistPostCardProps> = ({
  artistImage,
  artistName,
  postContent,
  postDateTime,
  postImage,
}) => {
  return (
    <div className={styles.artistPostCard}>
      <div className={styles.leftContent}>
        <img src={`http://localhost:80${artistImage}`} alt="Artist" className={styles.artistImage} />
        <span className={styles.month}>{postDateTime}</span>
      </div>
      <div className={styles.rightContent}>
        <div className={styles.messageBubble}>
          <div className={styles.msgContent}>
            <span className={styles.bubbleArtistName}>{artistName}</span>
            <p className={styles.bubblePostContent}>{postContent}</p>
            <span className={styles.bubblePostDateTime}>{postDateTime}</span>
          </div>
          <div className={styles.bubbleImage}>
            <img className={styles.bubbleimgsrc} src={`http://localhost:80${postImage}`} alt="img" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ArtistPostCard;
