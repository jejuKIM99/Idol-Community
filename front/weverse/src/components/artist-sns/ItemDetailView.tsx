import React, { useEffect, useState, useRef } from 'react';
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

  // 라이브별 댓글
  const [commentsMap, setCommentsMap] = useState<Record<string | number, any[]>>({});

  // 라이브별 WebSocket 상태 관리
  const wsMap = useRef<Record<string | number, WebSocket>>({});
  const wsRef = useRef<WebSocket | null>(null);

  // 로컬스토리지에서 userId 가져오기
  const [userId, setUserId] = useState<number | null>(null);

  const [message, setMessage] = useState('');
  const inputRef = useRef<HTMLInputElement | null>(null);

  const formatNumber = (num: number | string) => {
    if (typeof num === 'string') return num;
    if (num >= 1000000) return (num / 1000000).toFixed(1) + 'M';
    if (num >= 1000) return (num / 1000).toFixed(1) + 'K';
    return num;
  };

  const ANONYMOUS = '익명';

  

  // 라이브별 WebSocket 연결
  useEffect(() => {
    const id = type === 'live' ? item.streamingId : item.id;

    // media 타입일 때만 기존 댓글 불러오기
    if (type === 'media') {
      fetch(`http://localhost:80/api/chat/messages/media?id=${id}`)
        .then((res) => {
          if (!res.ok) throw new Error('댓글 불러오기 실패');
          return res.json();
        })
        .then((data) => {
          console.log('댓글 불러오기 성공:', data);
          setCommentsMap((prev) => ({
            ...prev,
            [id]: data.chatList || [],
          }));
        })
        .catch((error) => {
          console.error('댓글 불러오기 중 오류:', error);
        });
    }
  
    // 기존 연결 닫기
    if (wsMap.current[id]) {
      wsMap.current[id].close();
      delete wsMap.current[id];
    }
  
    if (wsRef.current) {
      wsRef.current.close();
      wsRef.current = null;
    }
  
    // WebSocket URL 분기
    const wsUrl =
      type === 'live'
        ? `ws://localhost:80/ws/api/artistSNS/home/live?streamingId=${id}`
        : `ws://localhost:80/ws/api/artistSNS/home/media?id=${id}`;
  
    const ws = new WebSocket(wsUrl);
    wsMap.current[id] = ws;
    wsRef.current = ws;
  
    ws.onopen = () => {
      console.log(`${item.title} (${type}) WS 연결 성공`);
      if (inputRef.current) inputRef.current.focus();
    };
  
    ws.onmessage = (event) => {
      const data = event.data;
      const arr = data.split(':');
      const sender = arr[0];
      const msg = arr.slice(1).join(':');
  
      setCommentsMap((prev) => {
        const prevComments = prev[id] || [];
        return {
          ...prev,
          [id]: [...prevComments, { nickname: sender, content: msg }],
        };
      });
    };
  
    ws.onclose = () => {
      console.log(`${item.title} (${type}) WS 연결 종료`);
      delete wsMap.current[id];
    };
  
    ws.onerror = (err) => {
      console.error(`${item.title} (${type}) WS 에러`, err);
    };
  
    return () => {
      if (wsMap.current[id]) {
        wsMap.current[id].close();
        delete wsMap.current[id];
      }
    };
  }, [item, type]);
  
  // 댓글 상태도 id 기준으로 분기
  const id = type === 'live' ? item.streamingId : item.id;
  const comments = commentsMap[id] || [];
  
  // 메시지 전송 함수
  const sendMessage = async () => {
    const ws = wsRef.current;
    const userId = localStorage.getItem('userId');
    const nickname = userId !== null ? `User${userId}` : ANONYMOUS;
  
    console.log('sendMessage 호출, ws 상태: ', ws?.readyState, '메시지:', message);
  
    if (ws && ws.readyState === WebSocket.OPEN && message.trim() !== '') {
      ws.send(`${nickname}:${message}`);
      console.log(`보내는 메시지: ${nickname}:${message}`);
  
      try {

        if (type === 'live') {
          const savePayload = {
            streamingId: item.streamingId,
            userId,
            nickname,
            content: message.trim(),
            sentAt: new Date().toISOString(),
          };
    
          console.log('저장할 메세지 정보 :', savePayload);
    
          const response = await fetch('http://localhost:80/api/chat/messages/live', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(savePayload),
          });

          if (!response.ok) {
            console.error('메시지 저장 실패:', response.statusText);
          } else {
            console.log('메시지 DB 저장 성공');
          }
        } else if(type === 'media') {
          const savePayload = {
            mediaId: item.id,
            userId,
            nickname,
            content: message.trim(),
            sentAt: new Date().toISOString(),
          };
    
          console.log('저장할 메세지 정보 :', savePayload);
    
          const response = await fetch('http://localhost:80/api/chat/messages/media', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(savePayload),
          });

          if (!response.ok) {
            console.error('메시지 저장 실패:', response.statusText);
          } else {
            console.log('메시지 DB 저장 성공');
          }
        }

      } catch (error) {
        console.error('메시지 저장 중 오류:', error);
      }
  
      setMessage('');
      if (inputRef.current) inputRef.current.focus();
    } else {
      console.warn('WebSocket이 연결되어 있지 않거나 메시지가 비어있습니다.');
    }
  };
  

  const merchData = [
    { image: '/images/merch1.jpg', name: 'Artist T-Shirt', price: '₩35,000' },
    { image: '/images/merch2.jpg', name: 'Official Light Stick', price: '₩40,000' },
    { image: '/images/merch3.jpg', name: 'Photo Card Set', price: '₩15,000' },
    { image: '/images/merch4.jpg', name: 'Concert Hoodie', price: '₩60,000' },
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
  /*
    INSERT INTO streaming 
    (created_at, group_id, owner_artist_id, scheduled_at, streamer_artist_id, thumbnail, title, video_id)
    VALUES
    ('2025-08-09 10:00:00.000000', 1, 1, '2025-08-10 18:00:00.000000', 1, '/images/NewJeans/NewJeans_profile/NewJeans_live_01.jpg', 'First Streaming', 'https://youtu.be/wU2siJ2c5TA?si=4gGHQFcQmWfxEO--'),
    ('2025-08-09 11:00:00.000000', 1, 2, '2025-08-11 19:00:00.000000', 2, '/images/NewJeans/NewJeans_profile/NewJeans_live_02.jpg', 'Second Streaming', 'https://youtu.be/aJqS7BY5XE4?si=-C5NucKka4jJZKjZ');


    INSERT INTO uploaded_video 
    (price, artist_id, group_id, scheduled_at, uploaded_at, description, thumbnail, title, video_url)
    VALUES
    (10000.00, 1, 1, '2025-08-15 18:00:00.000000', '2025-08-09 10:00:00.000000', 'Live concert performance of Newjeans', '/images/NewJeans/NewJeans_profile/NewJeans_media_01.jpg', 'NewJeans Live Concert', 'https://youtu.be/wLApx-mWc5Y?si=GMJKox7vJz_bmh6Y'),
    (10000.00, 2, 1, NULL, '2025-08-09 11:00:00.000000', 'Behind-the-scenes footage from recent album recording', '/images/NewJeans/NewJeans_profile/NewJeans_media_02.jpg', 'NewJeans Album Recording', 'https://youtu.be/yWvzZW_g5b0?si=F-zJKDtBEoQGriNS'),
    (10000.00, 3, 1, '2025-08-20 20:00:00.000000', '2025-08-08 09:30:00.000000', 'Exclusive interview with Newjeans', '/images/NewJeans/NewJeans_profile/NewJeans_media_03.jpg', 'NewJeans Exclusive Interview', 'https://youtu.be/WOlVl32HaoY?si=slhAAUnD80tJQImh'),
    (10000.00, 4, 1, NULL, '2025-08-07 15:45:00.000000', 'Special fan meeting highlights', '/images/NewJeans/NewJeans_profile/NewJeans_media_04.jpg', 'NewJeans Fan Meeting Highlights', 'https://youtu.be/gY8_MPI3p4Q?si=hacg_d5ES8eMsVPO');
  */

  // youtube 링크 iframe src로 변환하는 함수 
  const youtubeToEmbed = (url: string) => {
    try {
      const urlObj = new URL(url);
      let videoId = '';

      if (urlObj.hostname === 'youtu.be') {
        videoId = urlObj.pathname.slice(1);
      } else if (urlObj.hostname.includes('youtube.com')) {
        videoId = urlObj.searchParams.get('v') || '';
      }

      let embedUrl = `https://www.youtube.com/embed/${videoId}`;

      const startTime = urlObj.searchParams.get('t');
      if (startTime) {
        const seconds = parseInt(startTime.replace('s', ''), 10);
        embedUrl += `?start=${seconds}`;
      }

      return embedUrl;
    } catch {
      return '';
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.topSection}>
        <div className={styles.topContent}>
          <div className={styles.leftSection}>
            <div className={styles.videoPlayer}>
              <iframe
                width="100%"
                height="100%"
                src={type === 'media' ? youtubeToEmbed(item.videoUrl || 'https://youtu.be/js1CtxSY38I') 
                : youtubeToEmbed(item.videoId || 'https://youtu.be/js1CtxSY38I')}
                title="YouTube video player"
                frameBorder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              />
            </div>
            <div className={styles.videoInfo}>
              <div className={styles.artistProfile}>
                <img
                  src={item.artistImageSrc || '/images/default_artist.jpg'}
                  alt={item.artistName}
                  width={50}
                  height={50}
                  className={styles.artistImage}
                />
                <div className={styles.artistDetails}>
                  <span className={styles.artistName}>{item.artistName}</span>
                  <span className={styles.uploadInfo}>
                    {item.artistName} • {item.uploadDate}
                    {item.hasSubtitles && (
                      <span className={styles.actionButton}>
                        <FaClosedCaptioning /> 자막
                      </span>
                    )}
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
              <span className={styles.commentCount}>{comments.length}개의 댓글</span>
              <button className={styles.refreshButton}>
                <FaSyncAlt />
              </button>
            </div>
            <button className={styles.liveChatButton}>LIVE CHAT</button>
            <div className={styles.commentsList}>
              {comments.map((comment, index) => (
                <CommentCard
                  key={index}
                  originalPost={comment.nickname}
                  commentContent={comment.content}
                  commentDateTime={new Date().toISOString()}
                />
              ))}
            </div>
            <div className={styles.commentInputContainer}>
              <input
                type="text"
                placeholder="댓글을 입력하세요..."
                ref={inputRef}
                className={styles.commentInput}
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                onKeyDown={(e) => {
                  if (e.key === 'Enter') {
                    e.preventDefault();
                    sendMessage();
                  }
                }}
              />
              <button className={styles.sendButton} onClick={sendMessage}>
                <FaPaperPlane />
              </button>
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
