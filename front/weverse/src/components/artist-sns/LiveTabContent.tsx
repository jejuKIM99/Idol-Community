import React from 'react';
import styles from './LiveTabContent.module.css';
import LiveVideoCard, { LiveVideoCardProps } from './LiveVideoCard';

interface LiveTabContentProps {
  artistName?: string | null;
  onItemClick: (item: any) => void;
}

const LiveTabContent: React.FC<LiveTabContentProps> = ({ artistName, onItemClick }) => {
  const liveVideos: LiveVideoCardProps[] = [
    {
      thumbnailSrc: "/images/live1.jpg",
      duration: "1:23:45",
      title: `${artistName}의 특별 라이브!`,
      views: "1.2M",
      likes: "250K",
      chats: "10K",
      hasSubtitles: true,
      artistImageSrc: "/images/artist_profile.jpg",
      artistName: artistName,
      uploadDate: "8월 1일 14:30",
    },
    {
      thumbnailSrc: "/images/live2.jpg",
      duration: "0:45:00",
      title: `${artistName}의 팬들과의 소통 시간`,
      views: "800K",
      likes: "180K",
      chats: "8K",
      hasSubtitles: false,
      artistImageSrc: "/images/artist_profile.jpg",
      artistName: artistName,
      uploadDate: "7월 25일 20:00",
    },
    {
      thumbnailSrc: "/images/live3.jpg",
      duration: "2:00:00",
      title: `${artistName}의 콘서트 비하인드 라이브`,
      views: "1.5M",
      likes: "300K",
      chats: "12K",
      hasSubtitles: true,
      artistImageSrc: "/images/artist_profile.jpg",
      artistName: artistName,
      uploadDate: "7월 10일 19:00",
    },
  ];

  return (
    <div className={styles.contentSection}>
      <h2 className={styles.liveSectionTitle}>지난 LIVE</h2>
      <div className={styles.liveVideoGrid}>
        {liveVideos.map((video, index) => (
          <div key={index} onClick={() => onItemClick(video)}>
            <LiveVideoCard {...video} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default LiveTabContent;