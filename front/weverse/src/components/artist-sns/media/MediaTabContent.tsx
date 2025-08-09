'use client';

import React, { useEffect, useState } from 'react';
import styles from './MediaTabContent.module.css';
import MediaItemCard from './MediaItemCard';
import axios from 'axios';

interface MediaTabContentProps {
  artistName?: string;
  groupId?: string | null;
  artistId?: string | null;
  onItemClick: (item: any) => void;
}

// Artist와 Group은 중복되므로 한 곳에 정의하여 재사용할 수 있습니다.
export interface Group {
  id: number;
  name: string;
  profileImage: string;
}

export interface Artist {
  id: number;
  name: string;
  profileImage: string;
  birthDate: string;
  description: string;
}

// 영상 데이터의 인터페이스
export interface UploadedVideo {
  id: number;
  artist: Artist;
  title: string;
  description: string;
  price: number;
  thumbnail: string;
  uploadedAt: string;
  scheduledAt: string | null;
  group: Group;
}

// 최종 응답 데이터 구조의 인터페이스
export interface VideoWithCategory {
  categoryTitle: string;
  videos: UploadedVideo[];
}

export interface Streaming {
  streamingId: number;
  owner: Artist;
  streamer: Artist;
  videoId: string;
  title: string;
  thumbnail: string;
  createdAt: string;
  scheduledAt: string | null;
  group: Group;
}


const MediaTabContent: React.FC<MediaTabContentProps> = ({ artistName, groupId, artistId, onItemClick }) => {
  const [activeTab, setActiveTab] = useState('home');
  const [activeHomeSubTab, setActiveHomeSubTab] = useState('Home');

  const [data, setData] = useState<VideoWithCategory[] | null>(null);
  const [streamingData, setStreamingData] = useState<Streaming[]>([]);

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchMedia = async () => {
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/media`, {
          params: { groupId: groupId }
        });        
        const fetchedData = response.data;
        console.log("fetch data : ", fetchedData);
        setData(fetchedData);
      } catch (err) {
        console.error('Failed to fetch notice data:', err);
      }
    };

    fetchMedia();
  }, [groupId]);

  useEffect(() => {
    const fetchStreamingData = async () => {
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/streamingAll`, {
          params: { groupId: groupId }
        });  
        setStreamingData(response.data);
        console.log("streaming data fetch : " ,response.data)
      } catch (err) {
        console.error('Failed to fetch streaming data:', err);
        setError('스트리밍 데이터를 불러오는 데 실패했습니다.');
      } finally {
        setLoading(false);
      }
    };
    if (groupId) {
      fetchStreamingData();
    }
  }, [groupId]);


  const getVideosByCategory = (categoryTitle: string) => {
    return data?.find(item => item.categoryTitle === categoryTitle)?.videos || [];
  };

  const continueWatchingData = streamingData;

  const latestMediaData = data
    ? data
        .flatMap(category => category.videos) // 모든 카테고리의 videos를 하나의 배열로 합칩니다.
        .sort((a, b) => new Date(b.uploadedAt).getTime() - new Date(a.uploadedAt).getTime()) // uploadedAt을 기준으로 내림차순 정렬합니다.
        .slice(0, 10) // 상위 10개만 선택합니다.
    : [];

  const forWeverseData = getVideosByCategory('forweverse');
  const hebiContentsData = getVideosByCategory('contents');
  const musicContentsData = getVideosByCategory('music');

  const allVideoMediaData = [
    ...continueWatchingData,
    ...latestMediaData,
    ...forWeverseData,
    ...musicContentsData,
    ...hebiContentsData,
  ];

  return (
    <div className={styles.mediaTabContainer}>
      <nav className={styles.mediaTabNav}>
        <button
          className={`${styles.navButton} ${activeTab === 'home' ? styles.active : ''}`}
          onClick={() => setActiveTab('home')}
        >
          Home
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'continueWatching' ? styles.active : ''}`}
          onClick={() => setActiveTab('continueWatching')}
        >
          이어보기
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'latestMedia' ? styles.active : ''}`}
          onClick={() => setActiveTab('latestMedia')}
        >
          최신미디어
        </button>
        <button
          className={`${styles.navButton} ${activeTab === 'viewAllMedia' ? styles.active : ''}`}
          onClick={() => setActiveTab('viewAllMedia')}
        >
          전체 미디어 보기
        </button>
      </nav>
      <div className={styles.tabContent}>
        {activeTab === 'home' && (
          <div>
            <nav className={styles.homeSubNav}>
              <button
                className={`${styles.navButton} ${activeHomeSubTab === 'Home' ? styles.active : ''}`}
                onClick={() => setActiveHomeSubTab('Home')}
              >
                Home
              </button>
              <button
                className={`${styles.navButton} ${activeHomeSubTab === 'forWeverse' ? styles.active : ''}`}
                onClick={() => setActiveHomeSubTab('forWeverse')}
              >
                For Weverse
              </button>
              <button
                className={`${styles.navButton} ${activeHomeSubTab === 'content' ? styles.active : ''}`}
                onClick={() => setActiveHomeSubTab('content')}
              >
                Hebi Contents
              </button>
              <button
                className={`${styles.navButton} ${activeHomeSubTab === 'musicContents' ? styles.active : ''}`}
                onClick={() => setActiveHomeSubTab('musicContents')}
              >
                Music Contents
              </button>
            </nav>
            <div className={styles.homeSubContent}>
              {activeHomeSubTab === 'Home' && (
                <div className={styles.homeContentSections}>
                  <div className={styles.section}>
                    <div className={styles.sectionHeader}>
                      <h3 className={styles.sectionTitle}>이어보기</h3>
                      <button className={styles.moreButton} onClick={() => setActiveTab('continueWatching')}>더보기</button>
                    </div>
                    <div className={styles.mediaCardGrid}>
                      {continueWatchingData.map((item, index) => (
                        <div key={index} onClick={() => onItemClick(item)}>
                          <MediaItemCard {...item} />
                        </div>
                      ))}
                    </div>
                  </div>
                  <div className={styles.section}>
                    <div className={styles.sectionHeader}>
                      <h3 className={styles.sectionTitle}>최신 미디어</h3>
                      <button className={styles.moreButton} onClick={() => setActiveTab('latestMedia')}>더보기</button>
                    </div>
                    <div className={styles.mediaCardGrid}>
                      {latestMediaData.map((item, index) => (
                        <div key={index} onClick={() => onItemClick(item)}>
                          <MediaItemCard {...item} />
                        </div>
                      ))}
                    </div>
                  </div>
                  <div className={styles.section}>
                    <div className={styles.sectionHeader}>
                      <h3 className={styles.sectionTitle}>For Weverse</h3>
                      <button className={styles.moreButton} onClick={() => setActiveHomeSubTab('forWeverse')}>더보기</button>
                    </div>
                    <div className={styles.mediaCardGrid}>
                      {forWeverseData.map((item, index) => (
                        <div key={index} onClick={() => onItemClick(item)}>
                          <MediaItemCard {...item} />
                        </div>
                      ))}
                    </div>
                  </div>
                  <div className={styles.section}>
                    <div className={styles.sectionHeader}>
                      <h3 className={styles.sectionTitle}>Music Contents</h3>
                      <button className={styles.moreButton} onClick={() => setActiveHomeSubTab('musicContents')}>더보기</button>
                    </div>
                    <div className={styles.mediaCardGrid}>
                      {musicContentsData.map((item, index) => (
                        <div key={index} onClick={() => onItemClick(item)}>
                          <MediaItemCard {...item} />
                        </div>
                      ))}
                    </div>
                  </div>
                  <button className={styles.viewAllMediaButton}>전체 미디어 보기</button>
                </div>
              )}
              {activeHomeSubTab === 'forWeverse' && (
                <div className={styles.section}>
                  <div className={styles.sectionHeader}>
                    <h3 className={styles.sectionTitle}>For Weverse</h3>
                    <button className={styles.moreButton} onClick={() => setActiveHomeSubTab('forWeverse')}>더보기</button>
                  </div>
                  <div className={styles.mediaCardGrid}>
                    {forWeverseData.map((item, index) => (
                      <div key={index} onClick={() => onItemClick(item)}>
                        <MediaItemCard {...item} />
                      </div>
                    ))}
                  </div>
                </div>
              )}
              {activeHomeSubTab === 'content' && (
                <div className={styles.section}>
                  <h3 className={styles.sectionTitle}>{artistName} Content</h3>
                  <div className={styles.mediaCardGrid}>
                    {hebiContentsData.map((item, index) => (
                      <div key={index} onClick={() => onItemClick(item)}>
                        <MediaItemCard {...item} />
                      </div>
                    ))}
                  </div>
                </div>
              )}
              {activeHomeSubTab === 'musicContents' && (
                <div className={styles.section}>
                  <div className={styles.sectionHeader}>
                    <h3 className={styles.sectionTitle}>Music Contents</h3>
                    <button className={styles.moreButton} onClick={() => setActiveHomeSubTab('musicContents')}>더보기</button>
                  </div>
                  <div className={styles.mediaCardGrid}>
                    {musicContentsData.map((item, index) => (
                      <div key={index} onClick={() => onItemClick(item)}>
                        <MediaItemCard {...item} />
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          </div>
        )}
        {activeTab === 'continueWatching' && (
          <div className={styles.section}>
            <div className={styles.sectionHeader}>
              <button className={styles.moreButton} onClick={() => setActiveTab('continueWatching')}>더보기</button>
            </div>
            <div className={styles.mediaCardGrid}>
              {continueWatchingData.map((item, index) => (
                <div key={index} onClick={() => onItemClick(item)}>
                  <MediaItemCard {...item} />
                </div>
              ))}
            </div>
          </div>
        )}
        {activeTab === 'latestMedia' && (
          <div className={styles.section}>
            <div className={styles.sectionHeader}>
              <button className={styles.moreButton} onClick={() => setActiveTab('latestMedia')}>더보기</button>
            </div>
            <div className={styles.mediaCardGrid}>
              {latestMediaData.map((item, index) => (
                <div key={index} onClick={() => onItemClick(item)}>
                  <MediaItemCard {...item} />
                </div>
              ))}
            </div>
          </div>
        )}
        {activeTab === 'viewAllMedia' && (
          <div className={styles.section}>
            <div className={styles.mediaCardGrid}>
              {allVideoMediaData.map((item, index) => (
                <div key={index} onClick={() => onItemClick(item)}>
                  <MediaItemCard {...item} />
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default MediaTabContent;