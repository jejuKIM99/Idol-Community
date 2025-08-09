import React, { useEffect, useState } from 'react';
import styles from './LiveTabContent.module.css';
import LiveVideoCard, { LiveVideoCardProps } from './LiveVideoCard';
import axios from 'axios';

interface LiveTabContentProps {
  groupId?: string | null;
  artistName?: string | null;
  onItemClick: (item: any) => void;
}
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

const LiveTabContent: React.FC<LiveTabContentProps> = ({ groupId, artistName, onItemClick }) => {
  const [streamingData, setStreamingData] = useState<Streaming[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchStreamingData = async () => {
      try {
        const response = await axios.get<Streaming[]>(`http://localhost:80/api/artistSNS/streamingAll`, {
          params: { groupId: groupId }
        });
        setStreamingData(response.data);
        console.log("streaming data fetch : ", response.data);
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

  // 더미 데이터에서 백엔드 데이터에 없는 필드들을 가져와 사용합니다.
  // 이 부분은 실제 서비스에서는 백엔드에서 가져오는 것이 좋습니다.
  const getDummyStats = (index: number) => {
    const dummyStats = [
      { views: "1.2M", likes: "250K", chats: "10K", hasSubtitles: true },
      { views: "800K", likes: "180K", chats: "8K", hasSubtitles: false },
      { views: "1.5M", likes: "300K", chats: "12K", hasSubtitles: true },
    ];
    return dummyStats[index % dummyStats.length];
  };

  const transformedLiveVideos: LiveVideoCardProps[] = streamingData.map((streaming, index) => {
    const dummyStats = getDummyStats(index);

    // LiveVideoCardProps에 맞춰 데이터를 변환합니다.
    return {
      thumbnailSrc: streaming.thumbnail,
      // duration은 백엔드 데이터에 없으므로 하드코딩하거나 다른 로직으로 생성해야 합니다.
      duration: "1:23:45",
      title: streaming.title,
      views: dummyStats.views,
      likes: dummyStats.likes,
      chats: dummyStats.chats,
      hasSubtitles: dummyStats.hasSubtitles,
      artistImageSrc: streaming.owner.profileImage, // owner의 프로필 이미지를 사용합니다.
      artistName: streaming.owner.name,
      uploadDate: new Date(streaming.createdAt).toLocaleDateString(), // createdAt을 사용해 날짜 형식으로 변환합니다.
    };
  });

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className={styles.contentSection}>
      <h2 className={styles.liveSectionTitle}>지난 LIVE</h2>
      <div className={styles.liveVideoGrid}>
        {/* transformedLiveVideos를 사용하여 렌더링합니다. */}
        {transformedLiveVideos.map((video, index) => (
          <div key={index} onClick={() => onItemClick(video)}>
            <LiveVideoCard {...video} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default LiveTabContent;