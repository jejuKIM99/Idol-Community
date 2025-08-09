import React from 'react';
import Image from 'next/image';
import styles from './ItemDetailView.module.css';
import { FaHeart, FaShareAlt, FaClosedCaptioning, FaSyncAlt, FaPaperPlane } from 'react-icons/fa';
import MerchCard from '../artistInfo/MerchCard';
import CommentCard from './CommentCard';
import LiveVideoCard from './LiveVideoCard';
import MediaItemCard from './media/MediaItemCard';

interface ItemDetailViewProps {
  item: any; // Can be LiveVideo or MediaItem
  type: 'live' | 'media';
}

const ItemDetailView: React.FC<ItemDetailViewProps> = ({ item, type }) => {
  const formatNumber = (num: number | string) => {
    if (typeof num === 'string') return num;
    if (num >= 1000000) return (num / 1000000).toFixed(1) + 'M';
    if (num >= 1000) return (num / 1000).toFixed(1) + 'K';
    return num;
  };

  const merchData = [
    { image: '/images/merch1.jpg', name: 'Artist T-Shirt', price: '₩35,000' },
    { image: '/images/merch2.jpg', name: 'Official Light Stick', price: '₩40,000' },
    { image: '/images/merch3.jpg', name: 'Photo Card Set', price: '₩15,000' },
    { image: '/images/merch4.jpg', name: 'Concert Hoodie', price: '₩60,000' },
  ];

  const commentsData = [
    { profileImage: '/images/user1.jpg', nickname: '팬1', timestamp: '8월 3일 10:00', content: '너무 좋아요! 응원합니다!', likes: 12 },
    { profileImage: '/images/user2.jpg', nickname: '팬2', timestamp: '8월 3일 10:05', content: '다음 라이브도 기대돼요.', likes: 8 },
    { profileImage: '/images/user3.jpg', nickname: '팬3', timestamp: '8월 3일 10:10', content: '오늘 의상 너무 예뻐요.', likes: 25 },
    { profileImage: '/images/user4.jpg', nickname: '팬4', timestamp: '8월 3일 10:15', content: '항상 좋은 음악 감사합니다.', likes: 18 },
    { profileImage: '/images/user4.jpg', nickname: '팬4', timestamp: '8월 3일 10:15', content: '항상 좋은 음악 감사합니다.', likes: 18 },
  ];

  const liveVideosData = Array.from({ length: 10 }, (_, i) => ({
    thumbnailSrc: `/images/live${(i % 3) + 1}.jpg`,
    duration: `00:0${i + 1}:00`,
    title: `임시 라이브 영상 ${i + 1}`,
    views: `${(i + 1) * 100}K`,
    likes: `${(i + 1) * 10}K`,
    chats: `${(i + 1) * 5}K`,
    hasSubtitles: i % 2 === 0,
    artistImageSrc: '/images/default_artist.jpg',
    artistName: '임시 아티스트',
    uploadDate: `8월 ${i + 1}일 14:30`,
  }));

  const mediaItemsData = Array.from({ length: 10 }, (_, i) => ({
    thumbnailSrc: `/images/thumbnail${(i % 9) + 1}.jpg`,
    duration: i % 2 === 0 ? `00:0${i + 1}:00` : undefined,
    title: `임시 미디어 아이템 ${i + 1}`,
    type: i % 2 === 0 ? 'video' : 'image',
  }));

  return (
    <div className={styles.container}>
      <div className={styles.topSection}>
        <div className={styles.topContent}>
          <div className={styles.leftSection}>
            <div className={styles.videoPlayer}>
              {/* Placeholder for video player */}
              <img src={`http://localhost:80${item.thumbnailSrc || item.thumbnail}`} alt={item.title} style={{ width: '100%', height: '100%', objectFit: 'contain' }} />
            </div>
            <div className={styles.videoInfo}>
              <div className={styles.artistProfile}>
                <img src={item.artistImageSrc || '/images/default_artist.jpg'} alt={item.artistName} width={50} height={50} className={styles.artistImage} />
                <div className={styles.artistDetails}>
                  <span className={styles.artistName}>{item.artistName}</span>
                  <span className={styles.uploadInfo}>
                    {item.artistName} • {item.uploadDate}
                    {item.hasSubtitles && <span className={styles.actionButton}><FaClosedCaptioning /> 자막</span>}
                  </span>
                </div>
              </div>
              <div className={styles.actionButtons}>
                <button className={styles.actionButton}>
                  <FaHeart /> {formatNumber(item.likes)}
                </button>
                <button className={styles.actionButton}>
                  <FaShareAlt />
                </button>
              </div>
            </div>
            <div className={styles.merchSection}>
              <h3>MERCH</h3>
              <div className={styles.merchGrid}>
                {merchData.map((merch, index) => (
                  <MerchCard key={index} image={merch.image} name={merch.name} price={merch.price} />
                ))}
              </div>
            </div>
          </div>
          <div className={styles.rightSection}>
            <div className={styles.commentsHeader}>
              <span className={styles.commentCount}>{commentsData.length}개의 댓글</span>
              <button className={styles.refreshButton}><FaSyncAlt /></button>
            </div>
            <button className={styles.liveChatButton}>LIVE CHAT</button>
            <div className={styles.commentsList}>
              {commentsData.map((comment, index) => (
                <CommentCard key={index} {...comment} />
              ))}
            </div>
            <div className={styles.commentInputContainer}>
              <input type="text" placeholder="댓글을 입력하세요..." className={styles.commentInput} />
              <button className={styles.sendButton}><FaPaperPlane /></button>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.bottomSection}>
        {type === 'live' ? (
          <>
            <h3>지난 LIVE</h3>
            <div className={styles.bottomGrid}>
              {liveVideosData.map((video, index) => (
                <LiveVideoCard key={index} {...video} />
              ))}
            </div>
          </>
        ) : (
          <>
            <h3>관련 미디어</h3>
            <div className={styles.bottomGrid}>
              {mediaItemsData.map((media, index) => (
                <MediaItemCard key={index} {...media} />
              ))}
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default ItemDetailView;
