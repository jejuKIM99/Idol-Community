
import React from 'react';
import styles from './ArtistInfoCard.module.css';

interface ArtistInfoCardProps {
  backgroundImage: string;
  memberCount: number;
  artistName: string;
}

const ArtistInfoCard: React.FC<ArtistInfoCardProps> = ({
  backgroundImage,
  memberCount,
  artistName,
}) => {
  return (
    <div className={styles.artistInfoCard} style={{ backgroundImage: `url(${backgroundImage})` }}>
      <div className={styles.overlay}></div>
      <div className={styles.content}>
        <span className={styles.memberCount}>{memberCount}만 members </span>
        <h3 className={styles.artistName}>{artistName}</h3>
      </div>
    </div>
  );
};

export default ArtistInfoCard;
