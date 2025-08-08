'use client';

import RecommendedArtistCard from './RecommendedArtistCard';
import styles from '@/styles/RecommendedArtistGrid.module.css';
import { FiRefreshCw } from 'react-icons/fi';

interface Artist {
  artistId: number;
  stageName: string;
  profileImage: string;
  group: Group;
}

interface Group {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
}

interface RecommendedArtistGridProps {
  artists: Artist[];
  groups: Group[];
  onArtistClick: (artistName: string) => void;
  onRefreshClick: () => void;
}

const RecommendedArtistGrid: React.FC<RecommendedArtistGridProps> = ({ artists, groups, onArtistClick, onRefreshClick }) => {
  return (
    <div className={styles.grid}>
      {groups.map((group) => {
        const artistInGroup = artists.find(artist => artist.group && artist.group.groupId === group.groupId);
        return (
          <RecommendedArtistCard 
            key={group.groupId} 
            group={group} 
            onClick={() => artistInGroup && onArtistClick(artistInGroup.stageName)} 
          />
        );
      })}
      <button onClick={onRefreshClick} className={styles.refreshButton}>
        <FiRefreshCw />
      </button>
    </div>
  );
};

export default RecommendedArtistGrid;
