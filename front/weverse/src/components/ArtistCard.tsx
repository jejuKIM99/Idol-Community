import Link from 'next/link';
import styles from '@/styles/ArtistCard.module.css';
import { url } from 'inspector';

interface ArtistCardProps {
  artist: {
    id: number;
    name: string;
    imageUrl: string;
    groupLogo: string;
  };
}

const ArtistCard = ({ artist }: ArtistCardProps) => {
  return (
    <Link href={`/artist/${artist.id}`} passHref>
      <div className={styles.card}>
        <div className={styles.cardImage} 
          style={{ backgroundImage:`url(http://localhost:80/${artist.imageUrl})`}}></div>
        <div className={styles.cardContent}>
          <div className={styles.logoContainer}>
            <img src={`http://localhost:80${artist.groupLogo}`}  alt={`${artist.name} logo`} className={styles.logo} />
          </div>
          <h3 className={styles.artistName}>{artist.name}</h3>
        </div>
      </div>
    </Link>
  );
};

export default ArtistCard;
