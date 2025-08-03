'use client';

import styles from '@/styles/RecommendedArtistCard.module.css';
import { Artist } from '@/data/artists';

interface RecommendedArtistCardProps {
  artist: {
    id: number;
    name: string;
    image: string;
    description: string;
  };
  onClick: (artistName: string) => void;
}

const RecommendedArtistCard: React.FC<RecommendedArtistCardProps> = ({ artist, onClick }) => {
  return (
    <div className={styles.card} onClick={() => onClick(artist.name)}>
      {/* src={artist.image} */}
      <img  alt={artist.name} className={styles.image} />
      <h3 className={styles.name}>{artist.name}</h3>
    </div>
  );
};

export default RecommendedArtistCard;
