import Link from 'next/link';
import styles from '@/styles/ArtistCard.module.css';
import { url } from 'inspector';

interface ArtistCardProps {
  group: {
    id: number;
    name: string;
    imageUrl: string;
    groupLogo: string;
  };
}

const ArtistCard = ({ group }: ArtistCardProps) => {
  return (
    <Link href={`/artist/${group.id}`} passHref>
      <div className={styles.card}>
        <div className={styles.cardImage} 
          style={{ backgroundImage:`url(http://localhost:80/${group.imageUrl})`}}></div>
        <div className={styles.cardContent}>
          <div className={styles.logoContainer}>
            <img src={`http://localhost:80${group.groupLogo}`}  alt={`${group.name} logo`} className={styles.logo} />
          </div>
          <h3 className={styles.artistName}>{group.name}</h3>
        </div>
      </div>
    </Link>
  );
};

export default ArtistCard;