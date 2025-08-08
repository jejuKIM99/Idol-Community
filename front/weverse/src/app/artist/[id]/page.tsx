'use client';

import { useState, useEffect } from 'react';
import { notFound, useRouter, useParams } from 'next/navigation';
import axios from 'axios';
import styles from './ArtistPage.module.css';
import MemberCard from '@/components/artistInfo/MemberCard';
import LiveCard from '@/components/artistInfo/LiveCard';
import MediaCard from '@/components/artistInfo/MediaCard';
import MerchCard from '@/components/artistInfo/MerchCard';
import AnnouncementCard from '@/components/artistInfo/AnnouncementCard';
import { group } from 'console';

interface Member {
  artistId: number;
  stageName: string;
  profileImage: string;
}

interface GroupInfo {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
  groupYoutube: string;
  groupHomepage: string;
  groupX: string;
  groupTikTok: string;
  groupInstagram: string;
}

interface Streaming {
  id: number;
  title: string;
  thumbnail: string;
  videoId: string;
  date: string;
  time: string;
}

interface Media {
  id: number;
  title: string;
  thumbnail: string;
  videoId: string;
  date: string;
  time: string;
}

interface Product {
  id: number;
  productName: string;
  description: string;
  thumbnail: string;
  price: string;
  stockQty: string;
}

interface Notice {
  id: number;
  title: string;
  create_at: string;
}


const ArtistPage = () => {
  const params = useParams();
  const id = params.id as string;
  
  const [groupInfo, setGroupInfo] = useState<GroupInfo | null>(null);
  const [artistList, setArtistList] = useState<Member[]>([]);
  const [liveData, setLiveData] = useState<Streaming[]>([]); // 라이브 데이터 상태 추가
  const [mediaData, setMediaData] = useState<Media[]>([]); // 라이브 데이터 상태 추가
  const [products, setProduct] = useState<Product[]>([]); // 라이브 데이터 상태 추가
  const [noties, setNotice] = useState<Notice[]>([]); // 라이브 데이터 상태 추가

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const router = useRouter();

  useEffect(() => {
    if (id) {
      const fetchGroupData = async () => {
        try {
          setLoading(true);
          const response = await axios.get(`http://localhost:80/api/artist/group`, { 
            params: { groupId: id }
          });
          console.log("Group API Response Data:", response.data); // 서버 응답 데이터 전체를 확인하기 위한 로그
          setGroupInfo(response.data.group);
          setArtistList(response.data.artists || []);
          setError(null);
        } catch (err) {
          setError('그룹 정보를 불러오는 데 실패했습니다.');
          console.error('API Error:', err);
        } finally {
          setLoading(false);
        }
      };
      fetchGroupData();
    }
  }, [id]);

  useEffect(() => {
    const fetchLiveData = async () => {
      console.log("fetchLiveData")
      if (id) {
        try {
          console.log("Fetching live data for groupId:", id);
          const response = await axios.get(`http://localhost:80/api/artistinfo/artistLive`, {
            params: { groupId: id }
          });
          console.log('Live Data Response:', response.data); // API 응답 전체를 다시 확인
          setLiveData(response.data);
        } catch (err) {
          console.error('Failed to fetch live data:', err);
        }
      }
    };
    fetchLiveData();
  }, [id]);

  useEffect(() => {
    const fetchMediaData = async () => {
      console.log("fetchMediaData")
      if (id) {
        try {
          console.log("Fetching live data for groupId:", id);
          const response = await axios.get(`http://localhost:80/api/artistinfo/artistMedia`, {
            params: { groupId: id }
          });
          console.log('Media Data Response:', response.data); // API 응답 전체를 다시 확인
          setMediaData(response.data);
        } catch (err) {
          console.error('Failed to fetch live data:', err);
        }
      }
    };
    fetchMediaData();
  }, [id]);

  useEffect(() => {
    const fetchProductData = async () => {
      console.log("fetchProductData")
      if (id) {
        try {
          console.log("Fetching live data for groupId:", id);
          const response = await axios.get(`http://localhost:80/api/artistinfo/product`, {
            params: { groupId: id }
          });
          console.log('product Data Response:', response.data); // API 응답 전체를 다시 확인
          setProduct(response.data);
        } catch (err) {
          console.error('Failed to fetch product data:', err);
        }
      }
    };
    fetchProductData();
  }, [id]);

  useEffect(() => {
    const fetchNotice = async () => {
      console.log("fetchProductData")
      if (id) {
        try {
          console.log("Fetching notice data for groupId:", id);
          const response = await axios.get(`http://localhost:80/api/artistinfo/board`, {
            params: { groupId: id }
          });
          console.log('notice Data Response:', response.data); // API 응답 전체를 다시 확인
          setNotice(response.data);
        } catch (err) {
          console.error('Failed to fetch notice data:', err);
        }
      }
    };
    fetchNotice();
  }, [id]);

  if (loading) {
    return <div className={styles.centeredMessage}>로딩 중...</div>;
  }

  if (error) {
    return <div className={styles.centeredMessage}>{error}</div>;
  }

  if (!groupInfo) {
    notFound();
    return null;
  }

  const handleLiveCardClick = (liveItem: Streaming) => {
    router.push(`/artist-sns?artistId=${groupInfo.groupId}&artistName=${groupInfo.groupName}&activeTab=live&selectedItem=${encodeURIComponent(JSON.stringify(liveItem))}`);
  };

  const handleMediaCardClick = (mediaItem: Media) => {
    router.push(`/artist-sns?artistId=${groupInfo.groupId}&artistName=${groupInfo.groupName}&activeTab=media&selectedItem=${encodeURIComponent(JSON.stringify(mediaItem))}`);
  };

  return (
    <div className={styles.container}>
      <section className={styles.introductionSection} >
        <div className={styles.overlay}><div className={styles.imageContainer} style={{ backgroundImage: `url(http://localhost:80/${groupInfo.groupProfileImage})` }}></div></div>
        <div className={styles.introductionContent}>
          <h1 className={styles.artistName}>{groupInfo.groupName}</h1>
          <p className={styles.artistDescription}>
            여기에 {groupInfo.groupName}에 대한 소개 내용이 들어갑니다. {groupInfo.groupName}은(는) 전 세계 팬들에게 사랑받는 아티스트입니다. 그들의 음악과 퍼포먼스는 많은 사람들에게 영감을 줍니다.
          </p>
        </div>
      </section>

      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>프로필</h2>
        {artistList && artistList.length > 0 && (
          <div className={styles.memberGrid}>
            {artistList.map((member, index) => (
              <MemberCard key={index} name={member.stageName} imageUrl={member.profileImage} artistName={member.stageName} artistId={member.artistId} groupId={groupInfo.groupId} />
            ))}
          </div>
        )}
      </section>

      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>Live</h2>
        <div className={styles.liveGrid}>
          {liveData.map((live) => (
            <LiveCard
              key={live.id} // live.id와 같은 고유한 키를 사용해야 합니다.
              thumbnail={`http://localhost:80${live.thumbnail}`}
              date={live.date} // API 응답에 맞게 필드 이름을 조정해야 합니다.
              time={live.time} // API 응답에 맞게 필드 이름을 조정해야 합니다.
              title={live.title}
              onClick={() => handleLiveCardClick(live)}
            />
          ))}
        </div>
      </section>

      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>미디어</h2>
        <div className={styles.mediaGrid}>
          {mediaData.map((media) => (
            <MediaCard
              key={media.id} // live.id와 같은 고유한 키를 사용해야 합니다.
              thumbnail={`http://localhost:80${media.thumbnail}`}
              date={media.date} // API 응답에 맞게 필드 이름을 조정해야 합니다.
              time={media.time} // API 응답에 맞게 필드 이름을 조정해야 합니다.
              title={media.title}
              onClick={() => handleMediaCardClick(media)}
            />
          ))}
        </div>
      </section>

      <section className={styles.section}>
        <div className={styles.sectionHeader}>
          <h2 className={styles.sectionTitle}>Merch</h2>
          <button className={styles.artistShopButton}>Artist Shop &gt;</button>
        </div>
        <div className={styles.merchGrid}>
          {products.map((product) => (
            <MerchCard
              key={product.id}
              image={`http://localhost:80${product.thumbnail}`}
              name={`상품명 ${product.productName}`}
              price={`${product.price}원`}
            />
          ))}
        </div>
      </section>

      <div style={{ width: '96%', height: '2px', background: '#929292', marginBottom: '16px' }}></div>

      <section className={styles.section}>
        <div className={styles.announcementList}>
          {noties.map((notice) => (
            <AnnouncementCard
              key={notice.id}
              title={`[NOTICE] 제목 ${notice.title}`}
              create_at={notice.create_at}
            />
          ))}
        </div>
      </section>

      <section className={styles.section}>
        <div className={styles.snsButtonsContainer}>
          <a href={groupInfo.groupYoutube} target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/FF0000/FFFFFF?text=YT" alt="YouTube" className={styles.snsIcon} />
          </a>
          <a href={groupInfo.groupInstagram} target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/E4405F/FFFFFF?text=IG" alt="Instagram" className={styles.snsIcon} />
          </a>
          <a href={groupInfo.groupX} target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/000000/FFFFFF?text=X" alt="X (Twitter)" className={styles.snsIcon} />
          </a>
          <a href={groupInfo.groupTikTok} target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/00F2EA/FFFFFF?text=TK" alt="TikTok" className={styles.snsIcon} />
          </a>
          <a href={groupInfo.groupHomepage} target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/007BFF/FFFFFF?text=HP" alt="Homepage" className={styles.snsIcon} />
          </a>
        </div>
      </section>

      <div className={styles.communityButtonContainer}>
        <button className={styles.communityButton}>
          커뮤니티 바로가기
        </button>
      </div>
    </div>
  );
};

export default ArtistPage;