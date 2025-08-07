
import React from 'react';
import styles from './ArtistHeaderInfo.module.css';

interface ArtistHeaderInfoProps {
  artistImage: string;
  artistName: string;
  artistBirthday: string;
}

const ArtistHeaderInfo: React.FC<ArtistHeaderInfoProps> = ({
  artistImage,
  artistName,
  artistBirthday,
}) => {
  return (
    <div className={styles.artistHeaderInfo}>
      <img src={artistImage} alt="Artist Profile" className={styles.artistImage} />
      <div className={styles.infoText}>
        <span className={styles.artistName}>{artistName}</span>
        <span className={styles.artistBirthday}>{artistBirthday}</span>
      </div>
    </div>
  );
};

export default ArtistHeaderInfo;
