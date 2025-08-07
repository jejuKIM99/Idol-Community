import ArtistCard from './ArtistCard';
import styles from '@/styles/ArtistGrid.module.css';

interface Artist {
  artistId: number;
  name: string;
  profileImageUrl: string;
  logoImageUrl: string;
}

interface Group {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
}

interface ArtistGridProps {
  artists: Artist[];
  groups: Group[];
}

const ArtistGrid: React.FC<ArtistGridProps> = ({ groups }) => {

  return (
    <div className={styles.gridContainer}>
      <h2 className={styles.title}>새로운 아티스트를 만나보세요!</h2>
      <div className={styles.grid}>
        {groups.map((group) => (
          <ArtistCard
            key={group.groupId}
            group={{
              id: group.groupId,
              name: group.groupName,
              imageUrl: group.groupProfileImage,
              groupLogo: group.groupLogo,
            }}
          />
        ))}
      </div>
    </div>
  );
};

export default ArtistGrid;