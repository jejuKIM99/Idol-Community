'use client';

import React, { useState } from 'react';
import styles from './MediaTabContent.module.css';
import MediaItemCard from './MediaItemCard';

interface MediaTabContentProps {
  artistName?: string | null;
  onItemClick: (item: any) => void;
}

const MediaTabContent: React.FC<MediaTabContentProps> = ({ artistName, onItemClick }) => {
  const [activeTab, setActiveTab] = useState('home');
  const [activeHomeSubTab, setActiveHomeSubTab] = useState('Home');

  const continueWatchingData: { thumbnailSrc: string; duration?: string; title: string; type: 'video' | 'image'; artistImageSrc?: string; uploadDate?: string; views?: string; likes?: string; chats?: string; hasSubtitles?: boolean; artistName?: string; }[] = [
    { thumbnailSrc: "/images/thumbnail1.jpg", duration: "03:45", title: `${artistName} - 영상 제목 1`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '8월 1일 14:30', views: '1.5M', likes: '300K', chats: '10K', hasSubtitles: true, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail2.jpg", duration: "02:10", title: `${artistName} - 영상 제목 2`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '8월 2일 15:00', views: '1.2M', likes: '250K', chats: '8K', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail3.jpg", duration: "05:00", title: `${artistName} - 영상 제목 3`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '8월 3일 16:30', views: '1.0M', likes: '200K', chats: '7K', hasSubtitles: true, artistName: artistName },
  ];

  const latestMediaData: { thumbnailSrc: string; duration?: string; title: string; type: 'video' | 'image'; artistImageSrc?: string; uploadDate?: string; views?: string; likes?: string; chats?: string; hasSubtitles?: boolean; artistName?: string; }[] = [
    { thumbnailSrc: "/images/thumbnail4.jpg", duration: "01:23", title: `${artistName} - 최신 영상 1`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 20일 10:00', views: '800K', likes: '150K', chats: '5K', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail5.jpg", title: `${artistName} - 최신 이미지 1`, type: "image", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 21일 11:00', views: undefined, likes: undefined, chats: undefined, hasSubtitles: undefined, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail6.jpg", duration: "04:50", title: `${artistName} - 최신 영상 2`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 22일 12:00', views: '700K', likes: '120K', chats: '4K', hasSubtitles: true, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail7.jpg", title: `${artistName} - 최신 이미지 2`, type: "image", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 23일 13:00', views: undefined, likes: undefined, chats: undefined, hasSubtitles: undefined, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail8.jpg", duration: "02:00", title: `${artistName} - 최신 영상 3`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 24일 14:00', views: '600K', likes: '100K', chats: '3K', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail9.jpg", duration: "03:15", title: `${artistName} - 최신 영상 4`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 25일 15:00', views: '500K', likes: '90K', chats: '2K', hasSubtitles: true, artistName: artistName },
  ];

  const forWeverseData: { thumbnailSrc: string; duration?: string; title: string; type: 'video' | 'image'; artistImageSrc?: string; uploadDate?: string; views?: string; likes?: string; chats?: string; hasSubtitles?: boolean; artistName?: string; }[] = [
    { thumbnailSrc: "/images/thumbnail10.jpg", duration: "01:00", title: "For Weverse 영상 1", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 10일 09:00', views: '400K', likes: '80K', chats: '1K', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail11.jpg", title: "For Weverse 이미지 1", type: "image", artistImageSrc: '/images/default_artist.jpg', uploadDate: '7월 11일 10:00', views: undefined, likes: undefined, chats: undefined, hasSubtitles: undefined, artistName: artistName },
  ];

  const musicContentsData: { thumbnailSrc: string; duration?: string; title: string; type: 'video' | 'image'; artistImageSrc?: string; uploadDate?: string; views?: string; likes?: string; chats?: string; hasSubtitles?: boolean; artistName?: string; }[] = [
    { thumbnailSrc: "/images/thumbnail12.jpg", duration: "03:30", title: "음악 콘텐츠 1", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '6월 1일 18:00', views: '300K', likes: '70K', chats: '500', hasSubtitles: true, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail13.jpg", duration: "02:45", title: "음악 콘텐츠 2", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '6월 2일 19:00', views: '200K', likes: '60K', chats: '400', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail14.jpg", duration: "04:00", title: "음악 콘텐츠 3", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '6월 3일 20:00', views: '100K', likes: '50K', chats: '300', hasSubtitles: true, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail15.jpg", duration: "03:00", title: "음악 콘텐츠 4", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '6월 4일 21:00', views: '90K', likes: '40K', chats: '200', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/thumbnail16.jpg", duration: "02:20", title: "음악 콘텐츠 5", type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '6월 5일 22:00', views: '80K', likes: '30K', chats: '100', hasSubtitles: true, artistName: artistName },
  ];

  const hebiContentsData: { thumbnailSrc: string; duration?: string; title: string; type: 'video' | 'image'; artistImageSrc?: string; uploadDate?: string; views?: string; likes?: string; chats?: string; hasSubtitles?: boolean; artistName?: string; }[] = [
    { thumbnailSrc: "/images/hebi1.jpg", duration: "01:30", title: `${artistName} - Hebi Content 1`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '5월 1일 10:00', views: '70K', likes: '20K', chats: '50', hasSubtitles: false, artistName: artistName },
    { thumbnailSrc: "/images/hebi2.jpg", title: `${artistName} - Hebi Content 2`, type: "image", artistImageSrc: '/images/default_artist.jpg', uploadDate: '5월 2일 11:00', views: undefined, likes: undefined, chats: undefined, hasSubtitles: undefined, artistName: artistName },
    { thumbnailSrc: "/images/hebi3.jpg", duration: "02:00", title: `${artistName} - Hebi Content 3`, type: "video", artistImageSrc: '/images/default_artist.jpg', uploadDate: '5월 3일 12:00', views: '60K', likes: '10K', chats: '30', hasSubtitles: true, artistName: artistName },
  ];

  const allVideoMediaData = [
    ...continueWatchingData.filter(item => item.type === 'video'),
    ...latestMediaData.filter(item => item.type === 'video'),
    ...forWeverseData.filter(item => item.type === 'video'),
    ...musicContentsData.filter(item => item.type === 'video'),
    ...hebiContentsData.filter(item => item.type === 'video'),
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