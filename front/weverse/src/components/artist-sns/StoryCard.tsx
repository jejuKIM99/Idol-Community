
import React from 'react';
import styles from './StoryCard.module.css';

interface StoryCardProps {
  storyImage: string;
  artistImage: string;
  artistName: string;
}

const StoryCard: React.FC<StoryCardProps> = ({
  storyImage,
  artistImage,
  artistName,
}) => {
  return (
    <div className={styles.storyCard}>
      <div className={styles.storyImage} style={{ backgroundColor: storyImage }}>
        <img className={styles.storyImgSrc} src={`http://localhost:80${storyImage}`} alt="storyImage" />
      </div>
      <div className={styles.artistImageContainer}>
        <img src={`http://localhost:80${artistImage}`} alt={artistImage} className={styles.artistImage} />
      </div>
      <span className={styles.artistName}>{artistName}</span>
    </div>
  );
};

export default StoryCard;
