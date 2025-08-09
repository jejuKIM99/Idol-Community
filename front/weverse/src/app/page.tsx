'use client';

import { useState, useEffect } from 'react';
import AdBanner from '@/components/AdBanner';
import ArtistGrid from '@/components/ArtistGrid';
import RecommendedArtistGrid from '@/components/RecommendedArtistGrid';
import ArtistDMModal from '../components/ArtistDMModal';
import { allArtists } from '../data/artists';
import styles from './page.module.css';
import axios from 'axios';

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


interface ApiResponse {
  artistList: Artist[];
  groupList: Group[];
}

export default function Home() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedArtistForDM, setSelectedArtistForDM] = useState('');

  const [artists, setArtists] = useState<Artist[]>([]);
  const [groups, setGroups] = useState<Group[]>([]);
  const [recommendedGroups, setRecommendedGroups] = useState<Group[]>([]);
  const [error, setError] = useState<string | null>(null);

  const getRandomGroups = (sourceGroups: Group[], count: number) => {
    const shuffled = [...sourceGroups].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, count);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<ApiResponse>('http://localhost:80/api/main/artist');
        const groupData = response.data.groupList;
        console.log(response.data)
        setArtists(response.data.artistList);
        setGroups(groupData);
        setRecommendedGroups(getRandomGroups(groupData, 5)); // Display 5 recommended groups initially
      } catch (err) {
        console.error('API 호출 에러:', err);
        setError('Spring Boot API 호출에 실패했습니다.');
      }
    };
    fetchData();
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
    setRecommendedGroups(getRandomGroups(groups, 5)); // Refresh with 5 new random groups
  };

  return (
    <main className={styles.main}>
      <div className={styles.container}>
        <AdBanner />
        <RecommendedArtistGrid artists={artists} groups={recommendedGroups} onArtistClick={handleArtistCardClick} onRefreshClick={handleRefreshClick} />
        <ArtistGrid artists={artists} groups={groups} />
      </div>
      <ArtistDMModal
        isOpen={isModalOpen}
        onClose={closeModal}
        selectedArtistName={selectedArtistForDM}
        allArtists={artists}
        allGroups={groups}
      />
    </main>
  );
}