'use client';

import React, { useState, useEffect } from 'react';
import { useSearchParams } from 'next/navigation';
import styles from './artist-sns.module.css';
import FanTabContent from '../../components/artist-sns/FanTabContent';
import ArtistTabContent from '../../components/artist-sns/ArtistTabContent';
import ArtistSNSOverview from '../../components/artist-sns/ArtistSNSOverview';
import LiveTabContent from '../../components/artist-sns/LiveTabContent';
import MediaTabContent from '../../components/artist-sns/media/MediaTabContent';
import ItemDetailView from '../../components/artist-sns/ItemDetailView';
import axios from 'axios';



const ArtistSNSPage = () => {
  const searchParams = useSearchParams();
  const artistId = searchParams.get('artistId');
  const artistName = searchParams.get('artistName') || 'Unknown Artist';
  const memberName = searchParams.get('memberName');
  const groupId = searchParams.get('groupId');

  const [activeTab, setActiveTab] = useState('home');
  const [selectedItem, setSelectedItem] = useState<any>(null);


  useEffect(() => {
    const tabFromUrl = searchParams.get('activeTab');
    const itemFromUrl = searchParams.get('selectedItem');

    if (tabFromUrl) {
      setActiveTab(tabFromUrl);
    }
    if (itemFromUrl) {
      try {
        setSelectedItem(JSON.parse(decodeURIComponent(itemFromUrl)));
      } catch (error) {
        console.error("Failed to parse selectedItem from URL:", error);
      }
    }
  }, [searchParams]);

  const handleItemClick = (item: any, type: 'live' | 'media') => {
    setSelectedItem(item);
    setActiveTab(type);
  };

  const handleTabClick = (tab: string) => {
    setActiveTab(tab);
    setSelectedItem(null);
  };

  const renderContent = () => {
    if (selectedItem) {
      return <ItemDetailView item={selectedItem} type={activeTab === 'live' ? 'live' : 'media'} />;
    }

    switch (activeTab) {
      case 'home':
        return <ArtistSNSOverview artistId={artistId} groupId={groupId} memberName={memberName} />;
      case 'fan':
        return <FanTabContent groupId={groupId} artistId={artistId} />;
      case 'artist':
        return <ArtistTabContent groupId={groupId} artistId={artistId} />;
      case 'media':
        return <MediaTabContent groupId={groupId} artistId={artistId} onItemClick={(item) => handleItemClick(item, 'media')} />;
      case 'live':
        return <LiveTabContent groupId={groupId} onItemClick={(item) => handleItemClick(item, 'live')} />;
      case 'shop':
        return <div className={styles.contentSection}><h2>{artistId} 샵 페이지 내용</h2><p>이곳은 외부 샵 페이지로 이동하는 링크가 될 것입니다.</p></div>;
      default:
        return <ArtistSNSOverview artistId={artistId} memberName={memberName} />;
    }
  };

  return (
    <div className={`${styles.container} ${selectedItem ? styles.detailViewActiveContainer : activeTab === 'media' ? styles.mediaActiveContainer : activeTab === 'live' ? styles.liveActiveContainer : ''}`}>
      <nav className={styles.snsNav}>
        <button
          className={`${styles.navButton} ${activeTab === 'home' ? styles.active : ''}`}
          onClick={() => handleTabClick('home')}
        >
          Home
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'fan' ? styles.active : ''}`}
          onClick={() => handleTabClick('fan')}
        >
          Fan
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'artist' ? styles.active : ''}`}
          onClick={() => handleTabClick('artist')}
        >
          Artist
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'media' ? styles.active : ''}`}
          onClick={() => handleTabClick('media')}
        >
          Media
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'live' ? styles.active : ''}`}
          onClick={() => handleTabClick('live')}
        >
          Live
        </button>
        <a
          href={`/shop?artistId=${artistId}`}
          target="_blank"
          rel="noopener noreferrer"
          className={`${styles.navButton} ${styles.shopButton}`}
        >
          Shop
        </a>
      </nav>
      <div className={styles.content}>
        {renderContent()}
      </div>
    </div>
  );
};

export default ArtistSNSPage;
