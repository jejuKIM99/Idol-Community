
'use client';

import React from 'react';
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

interface FanTabContentProps {
  artistName?: string | null;
}

const posts = [
  { artistImage: '/next.svg', artistName: 'Artist 1', postContent: 'Post content 1' },
  { artistImage: '/vercel.svg', artistName: 'Artist 2', postContent: 'Post content 2' },
  { artistImage: '/globe.svg', artistName: 'Artist 3', postContent: 'Post content 3' },
  { artistImage: '/file.svg', artistName: 'Artist 4', postContent: 'Post content 4' },
  { artistImage: '/window.svg', artistName: 'Artist 5', postContent: 'Post content 5' },
  { artistImage: '/next.svg', artistName: 'Artist 6', postContent: 'Post content 6' },
  { artistImage: '/vercel.svg', artistName: 'Artist 7', postContent: 'Post content 7' },
  { artistImage: '/globe.svg', artistName: 'Artist 8', postContent: 'Post content 8' },
  { artistImage: '/file.svg', artistName: 'Artist 9', postContent: 'Post content 9' },
];

const FanTabContent: React.FC<FanTabContentProps> = ({ artistName }) => {
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
          {posts.map((post, index) => (
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
            <PostCard profileImage="/vercel.svg" nickname="User 1" postDate="2 hours ago" postImageColor="#ffcccb" postContent="This is the first post." />
            <PostCard profileImage="/next.svg" nickname="User 2" postDate="3 hours ago" postImageColor="#add8e6" postContent="This is the second post." />
            <PostCard profileImage="/globe.svg" nickname="User 3" postDate="5 hours ago" postImageColor="#90ee90" postContent="This is the third post." />
            <PostCard profileImage="/file.svg" nickname="User 4" postDate="1 day ago" postImageColor="#ffd700" postContent="This is the fourth post." />
            <PostCard profileImage="/window.svg" nickname="User 5" postDate="2 days ago" postImageColor="#ffb6c1" postContent="This is the fifth post." />
            <PostCard profileImage="/vercel.svg" nickname="User 6" postDate="3 days ago" postImageColor="#e6e6fa" postContent="This is the sixth post." />
          </div>
        </div>
        <div className={styles.rightSection}>
          <ArtistInfoCard
            backgroundImage="/next.svg"
            memberCount={5.1}
            artistName={artistName || "Artist Name"}
          />
          <MembershipCard artistName={artistName || "Artist Name"} />
          <ProfileCard profileImage="/vercel.svg" nickname="My Profile" followerCount={12345} />
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
