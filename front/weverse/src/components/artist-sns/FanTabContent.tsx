
'use client';

import React, { useEffect, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import styles from './FanTabContent.module.css';
import PostSlide from './PostSlide';
import PostCard from './PostCard';
import ArtistInfoCard from './ArtistInfoCard';
import MembershipCard from './MembershipCard';
import ProfileCard from './ProfileCard';
import { FaImage, FaVideo } from 'react-icons/fa';
import axios from 'axios';

interface FanTabContentProps {
  artistId?: string | null;
  groupId?: string | null;
  artistName?: string | null;
}

interface TopPost {
  artistId: number;
  image: string;
  artistName: string;
  content: string;
}
interface Post {
  postId: number;
  artistName: string;
  profileImage: string;
  nickname: string;
  postDate: string;
  postContent: string;
  createdAt: string;
  likeCount: number;
  commentCount: number;
  image: string;
}
interface Profile {
  artistId: number;
  birthday: string;
  profileImage: string;
  stageName: string;
}
// ===================================
interface User {
  userId: number;
  profileImage: string;
  nickname: string;
}

interface Notice {
  id: number;
  title: string;
  groupName: string;
}

const FanTabContent: React.FC<FanTabContentProps> = ({ artistId, groupId, artistName }) => {

  const [groupName, setGN] = useState<string>("");

  const [post, setPost] = useState<TopPost[]>([]); // 라이브 데이터 상태 추가
  const [fanPost, setFanPost] = useState<Post[]>([]); // 라이브 데이터 상태 추가
  const [profile, setProfile] = useState<Profile[]>([]); // 라이브 데이터 상태 추가
  const [user, setUser] = useState<User[]>([]); // 라이브 데이터 상태 추가
  const [notice, setNotice] = useState<Notice[]>([]); // 라이브 데이터 상태 추가
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [postContent, setPostContent] = useState('');
  const [selectedImage, setSelectedImage] = useState<File | null>(null);
  const fileInputRef = React.useRef<HTMLInputElement>(null);

  const handlePostSubmit = async (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter' && artistId) {
      // 내용과 이미지가 모두 없는 경우 전송 방지
      if (!postContent.trim() && !selectedImage) {
        alert('게시할 내용이나 이미지를 추가해주세요.');
        return;
      }

      const formData = new FormData();
      formData.append('artistID', artistId);
      formData.append('content', postContent);
      formData.append('userId', '4');

      try {
        await axios.post('http://localhost:80/api/artistSNS/fan/fanInput', formData);
        alert('포스트가 성공적으로 등록되었습니다.');
        setPostContent('');
        setSelectedImage(null);
        // 참고: 포스트 목록을 새로고침하는 로직을 여기에 추가할 수 있습니다.
      } catch (err) {
        console.error('포스트 등록에 실패했습니다:', err);
        alert('포스트 등록에 실패했습니다.');
      }
    }
  };

  const handleIconButtonClick = () => {
    fileInputRef.current?.click();
  };

  const handleImageSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      setSelectedImage(event.target.files[0]);
    }
  };

  // artistprofile
  useEffect(() => {
    const fetchNotice = async () => {
      console.log("fetchProfile")
      if (artistId) {
        try {
          console.log("Fetching notice data for artistId:", artistId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/profile`, {
            params: { artistId: artistId }
          });
          console.log('profile Data Response:', response.data); // API 응답 전체를 다시 확인
          setProfile(response.data);
        } catch (err) {
          console.error('Failed to fetch notice data:', err);
        }
      }
    };
    fetchNotice();
  }, [artistId]);

  // latestPost
  useEffect(() => {
    const sessionData = sessionStorage.getItem('user');
    if (sessionData) {
      const user = JSON.parse(sessionData);
      console.log('Session User Info:', user);
    }
    const fetchNotice = async () => {
      console.log("fetchPost")
      if (groupId) {
        try {
          console.log("Fetching post data for artistId:", groupId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/latestPost`, {
            params: { userId: 1, groupId: groupId }
          });
          console.log('Latest Post Data Response:', response.data); // API 응답 전체를 다시 확인
          setPost(response.data.postList);
        } catch (err) {
          console.error('Failed to fetch post data:', err);
        }
      }
    };
    fetchNotice();
  }, [groupId]);

  // post
  useEffect(() => {
    const fetchFanPost = async () => {
      console.log("fetchPost")
      if (groupId) {
        try {
          console.log("Fetching post data for artistId:", groupId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/fan`, {
            params: { groupId: groupId }
          });
          console.log('fans Post Data Response:', response.data.postList); // API 응답 전체를 다시 확인
          setFanPost(response.data.postList);
        } catch (err) {
          console.error('Failed to fetch post data:', err);
        }
      }
    };
    fetchFanPost();
  }, [groupId]);

  // user
  useEffect(() => {
    const fetchFanPost = async () => {
      console.log("fetchUser")
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/fan/myProfile`, {
          params: { userId: 3 }
        });
        console.log('user Post Data Response:', response.data); // API 응답 전체를 다시 확인
        setUser(response.data);
      } catch (err) {
        console.error('Failed to fetch post data:', err);
      }
    };
    fetchFanPost();
  }, [groupId]);

  // notice
  useEffect(() => {
    const fetchFanNotice = async () => {
      console.log("fetchNotice")
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/fan/notice`, {
          params: { groupId: groupId }
        });
        console.log('Notice Data Response:', response.data.boardList); // API 응답 전체를 다시 확인
        const noticeData = response.data.boardList || [];
        // id를 기준으로 내림차순 정렬하고 상위 3개 항목을 선택합니다.
        // 참고: Notice 인터페이스에 id 속성이 정의되어 있지 않아 any 타입을 사용합니다.
        const sortedNotices = [...noticeData]
          .sort((a: Notice, b: Notice) => b.id - a.id)
          .slice(0, 3);
        setNotice(sortedNotices);
        setGN(sortedNotices[0].groupName)
      } catch (err) {
        console.error('Failed to fetch notice data:', err);
      }
    };
    fetchFanNotice();
  }, [groupId]);

  return (
    <div className={styles.fanContainer}>
      <div className="swiper-button-prev" style={ {position:'absolute', top:'42px', left:'-54px'} }></div>
      <div className={styles.swiperContainer}>
        <Swiper
          modules={[Navigation]}
          spaceBetween={30}
          slidesPerView={3}
          slidesPerGroup={3}
          navigation={{
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
          }}
          className={styles.swiperSection}
        >
          {post.map((post, index) => (
            <SwiperSlide key={index}>
              <PostSlide {...post} />
            </SwiperSlide>
          ))}
        </Swiper>
      </div>
      <div className="swiper-button-next" style={ {position:'absolute', top:'42px', right:'-54px'} }></div>
      <div className={styles.mainContent}>
        <div className={styles.leftSection}>
        <div className={styles.postInputContainer}>
            <img src="/vercel.svg" alt="Profile" className={styles.profileImage} />
            <input
              type="text"
              placeholder="Write a post..."
              className={styles.postInput}
              value={postContent}
              onChange={(e) => setPostContent(e.target.value)}
              onKeyDown={handlePostSubmit}
            />
            <div className={styles.iconButtons}>
              <input
                type="file"
                ref={fileInputRef}
                onChange={handleImageSelect}
                style={{ display: 'none' }}
                accept="image/*"
              />
              <button className={styles.iconButton} onClick={handleIconButtonClick}><FaImage /></button>
            </div>
          </div>
          {selectedImage && (
            <div style={{ marginTop: '10px', textAlign: 'center' }}>
              <img
                src={URL.createObjectURL(selectedImage)}
                alt="Selected Preview"
                style={{ maxWidth: '100%', maxHeight: '200px', borderRadius: '8px' }}
              />
            </div>
          )}
          <div className={styles.sortContainer}>
            <select className={styles.sortSelect}>
              <option value="latest">최신순</option>
              <option value="hot">HOT</option>
            </select>
          </div>
          <div className={styles.postList}>
            {fanPost.map((post) => (
              <PostCard
                key={post.postId}
                postId={post.postId}
                profileImage={profile.profileImage}
                nickname={post.artistName}
                postDate={post.createdAt}
                postContent={post.postContent}
                likes={post.likeCount}
                comments={post.commentCount}
                img={post.image}
              />
            ))}
          </div>

        </div>
        <div className={styles.rightSection}>
          <ArtistInfoCard
            backgroundImage="/next.svg"
            memberCount={5.1}
            artistName={groupName}
          />
          <MembershipCard artistName={groupName || "Artist Name"} />
          <ProfileCard profileImage={`http://localhost:80${user.profileImage}`} nickname={user.nickname} followerCount={16545} />
          <div className={styles.noticeSection}>
            <h3 className={styles.noticeTitle}>Notice</h3>
            <ul className={styles.noticeList}>
              {notice.map((note) => (
                <li key={note.id}>[공지] {note.title}</li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FanTabContent;
