import Link from 'next/link';
import styles from '@/styles/ArtistCard.module.css';

interface ArtistCardProps {
  artist: {
    id: number;
    name: string;
    imageUrl: string;
    logoUrl: string;
  };
}

const ArtistCard = ({ artist }: ArtistCardProps) => {
  return (
    <Link href={`/artist/${artist.id}`} passHref>
      <div className={styles.card}>
        <div className={styles.cardImage} style={{ backgroundColor: artist.imageUrl }}></div>
        <div className={styles.cardContent}>
          <div className={styles.logoContainer}>
            <img src={artist.logoUrl} alt={`${artist.name} logo`} className={styles.logo} />
          </div>
          <h3 className={styles.artistName}>{artist.name}</h3>
        </div>
      </div>
    </Link>
  );
};

export default ArtistCard;
