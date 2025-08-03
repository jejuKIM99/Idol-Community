'use client';

import { useState, useEffect } from 'react';
import { allArtists, Artist } from '@/data/artists';
import RecommendedArtistCard from './RecommendedArtistCard';
import styles from '@/styles/RecommendedArtistGrid.module.css';
import { FiRefreshCw } from 'react-icons/fi';

interface RecommendedArtistGridProps {
  artists: {
    id: number;
    name: string;
    image: string;
    description: string;
  }[];
  onArtistClick: (artistName: string) => void;
  onRefreshClick: () => void;
}

const RecommendedArtistGrid: React.FC<RecommendedArtistGridProps> = ({ artists, onArtistClick, onRefreshClick }) => {
  return (
    <div className={styles.grid}>
      {artists.map((artist) => (
        <RecommendedArtistCard key={artist.id} artist={artist} onClick={onArtistClick} />
      ))}
      <button onClick={onRefreshClick} className={styles.refreshButton}>
        <FiRefreshCw />
      </button>
    </div>
  );
};

export default RecommendedArtistGrid;