
import React from 'react';
import styles from './StoryCard.module.css';

interface StoryCardProps {
  storyImageColor: string;
  artistImageAlt: string;
  artistName: string;
}

const StoryCard: React.FC<StoryCardProps> = ({
  storyImageColor,
  artistImageAlt,
  artistName,
}) => {
  return (
    <div className={styles.storyCard}>
      <div className={styles.storyImage} style={{ backgroundColor: storyImageColor }}></div>
      <div className={styles.artistImageContainer}>
        <img src="" alt={artistImageAlt} className={styles.artistImage} />
      </div>
      <span className={styles.artistName}>{artistName}</span>
    </div>
  );
};

export default StoryCard;
