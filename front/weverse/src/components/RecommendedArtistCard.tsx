'use client';

import styles from '@/styles/RecommendedArtistCard.module.css';
import { Artist } from '@/data/artists';

interface RecommendedArtistCardProps {
  artist: Artist;
  onClick: (artistName: string) => void;
}

const RecommendedArtistCard: React.FC<RecommendedArtistCardProps> = ({ artist, onClick }) => {
  return (
    <div className={styles.card} onClick={() => onClick(artist.name)}>
      <img src={artist.imageUrl} alt={artist.name} className={styles.image} />
      <h3 className={styles.name}>{artist.name}</h3>
    </div>
  );
};

export default RecommendedArtistCard;
