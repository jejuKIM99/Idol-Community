import ArtistCard from './ArtistCard';
import styles from '@/styles/ArtistGrid.module.css';
import { allArtists } from '@/data/artists';

const ArtistGrid = () => {
  return (
    <div className={styles.gridContainer}>
      <h2 className={styles.title}>새로운 아티스트를 만나보세요!</h2>
      <div className={styles.grid}>
        {allArtists.map((artist) => (
          <ArtistCard key={artist.id} artist={artist} />
        ))}
      </div>
    </div>
  );
};

export default ArtistGrid;
