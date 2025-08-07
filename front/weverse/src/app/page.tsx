'use client';

import { useState, useEffect } from 'react';
import AdBanner from '@/components/AdBanner';
import ArtistGrid from '@/components/ArtistGrid';
import RecommendedArtistGrid from '@/components/RecommendedArtistGrid';
import ArtistDMModal from '../components/ArtistDMModal';
import { allArtists, Artist } from '../data/artists';
import styles from './page.module.css';
import axios from 'axios';

interface ArtistAll {
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

interface ApiResponse {
  artistList: ArtistAll[];
  groupList: Group[];
}

export default function Home() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedArtistForDM, setSelectedArtistForDM] = useState('');
  const [displayedArtists, setDisplayedArtists] = useState<Artist[]>([]);

  const [artists, setArtists] = useState<ArtistAll[]>([]);
  const [groups, setGroups] = useState<Group[]>([]);
  const [error, setError] = useState<string | null>(null);

  const getRandomArtists = () => {
    const shuffled = [...allArtists].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, 9);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<ApiResponse>('http://localhost:80/api/main/artist');
        setArtists(response.data.artistList);
        setGroups(response.data.groupList);
      } catch (err) {
        console.error('API 호출 에러:', err);
        setError('Spring Boot API 호출에 실패했습니다.');
      }
    };
    fetchData();
  }, []);

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
        <ArtistGrid artists={artists} groups={groups} />
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
