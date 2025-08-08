'use client';

import styles from '@/styles/RecommendedArtistCard.module.css';

// interface Artist {
//   artistId: number;
//   name: string;
//   profileImageUrl: string;
//   logoImageUrl: string;
// }

interface Group {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
}

interface RecommendedArtistCardProps {
 // artists: Artist[];
  group: Group;

  onClick: () => void;
}

const RecommendedArtistCard: React.FC<RecommendedArtistCardProps> = ({ group, onClick }) => {
  return (
    <div className={styles.card} onClick={onClick}>
      <img src={`http://localhost:80/${group.groupProfileImage}`} alt={group.groupName} className={styles.image} />
      <h3 className={styles.name}>{group.groupName}</h3>
    </div>
  );
};

export default RecommendedArtistCard;
