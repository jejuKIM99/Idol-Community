'use client';

import { useState, useEffect } from 'react';
import AdBanner from '@/components/AdBanner';
import ArtistGrid from '@/components/ArtistGrid';
import RecommendedArtistGrid from '@/components/RecommendedArtistGrid';
import ArtistDMModal from '../components/ArtistDMModal';
import { allArtists } from '../data/artists';
import styles from './page.module.css';
import { FaSyncAlt } from 'react-icons/fa';

export default function Home() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedArtistForDM, setSelectedArtistForDM] = useState('');
  const [displayedArtists, setDisplayedArtists] = useState([]);

  const getRandomArtists = () => {
    const shuffled = [...allArtists].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, 9);
  };

  useEffect(() => {
    setDisplayedArtists(getRandomArtists());
  }, []);

  const handleArtistCardClick = (artistName: string) => {
    setSelectedArtistForDM(artistName);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedArtistForDM('');
  };

  const handleRefreshClick = () => {
    setDisplayedArtists(getRandomArtists());
  };

  return (
    <main className={styles.main}>
      <div className={styles.container}>
        <AdBanner />
        <RecommendedArtistGrid artists={displayedArtists} onArtistClick={handleArtistCardClick} onRefreshClick={handleRefreshClick} />
        <ArtistGrid />
      </div>
      <ArtistDMModal
        isOpen={isModalOpen}
        onClose={closeModal}
        selectedArtistName={selectedArtistForDM}
        allArtists={allArtists}
      />
    </main>
  );
}
