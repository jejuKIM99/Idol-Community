
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
  artistId: number;
  birthday: string;
  profileImage: string;
  stageName: string;
}

const FanTabContent: React.FC<FanTabContentProps> = ({ artistId, groupId, artistName }) => {

  const [post, setPost] = useState<TopPost[]>([]); // 라이브 데이터 상태 추가
  const [fanPost, setFanPost] = useState<Post[]>([]); // 라이브 데이터 상태 추가
  const [profile, setProfile] = useState<Profile[]>([]); // 라이브 데이터 상태 추가
  const [user, setUser] = useState<User[]>([]); // 라이브 데이터 상태 추가
  const [notice, setNotice] = useState<Notice[]>([]); // 라이브 데이터 상태 추가
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

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

  useEffect(() => {
    const fetchFanPost = async () => {
      console.log("fetchUser")
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/fan/myProfile`, {
          params: { userId: 1 }
        });
        console.log('user Post Data Response:', response.data); // API 응답 전체를 다시 확인
        setUser(response.data);
      } catch (err) {
        console.error('Failed to fetch post data:', err);
      }
    };
    fetchFanPost();
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
            <input type="text" placeholder="Write a post..." className={styles.postInput} />
            <div className={styles.iconButtons}>
              <button className={styles.iconButton}><FaImage /></button>
              <button className={styles.iconButton}><FaVideo /></button>
            </div>
          </div>
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
            artistName={artistName || "Artist Name"}
          />
          <MembershipCard artistName={artistName || "Artist Name"} />
          <ProfileCard profileImage={`http://localhost:80${user.profileImage}`} nickname={user.nickname} followerCount={16545} />
          <div className={styles.noticeSection}>
            <h3 className={styles.noticeTitle}>Notice</h3>
            <ul className={styles.noticeList}>
              <li>[공지] 위버스샵 이용 안내</li>
              <li>[공지] 개인정보처리방침 개정 안내</li>
              <li>[공지] 위버스 이용약관 개정 안내</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FanTabContent;
