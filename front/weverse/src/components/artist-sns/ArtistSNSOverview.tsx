
import React, { useEffect, useState } from 'react';
import styles from './ArtistSNSOverview.module.css';
import ArtistHeaderInfo from './ArtistHeaderInfo';
import PostCard from './PostCard';
import CommentCard from './CommentCard';
import { FaImage, FaVideo } from 'react-icons/fa';
import axios from 'axios';

interface ArtistSNSOverviewProps {
  artistId?: string | null;
  memberName?: string | null;
}

interface Profile {
  artistId: number;
  birthday: string;
  profileImage: string;
  stageName: string;
}

interface Post {
  postId: number;
  artistId: number;
  profgroupId: number;
  userId: number;
  content: string;
  createdAt: string;
  image: string;
  likeCount: number;
  commentCount: number;
  artistName: string;
}

const ArtistSNSOverview: React.FC<ArtistSNSOverviewProps> = ({ artistId, memberName }) => {
  const [activeSubTab, setActiveSubTab] = React.useState('posts');

  const [profile, setProfile] = useState<Profile[]>([]); // 라이브 데이터 상태 추가
  const [post, setPost] = useState<Post[]>([]); // 라이브 데이터 상태 추가

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
    const fetchNotice = async () => {
      console.log("fetchPost")
      if (artistId) {
        try {
          console.log("Fetching post data for artistId:", artistId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/artist`, {
            params: { artistID: artistId }
          });
          console.log('post Data Response:', response.data); // API 응답 전체를 다시 확인
          setPost(response.data.postList);
        } catch (err) {
          console.error('Failed to fetch post data:', err);
        }
      }
    };
    fetchNotice();
  }, [artistId]);

  return (
    <div className={styles.container}>
      <div className={styles.section1}>
        <ArtistHeaderInfo
          artistImage={`http://localhost:80${profile.profileImage}`}
          artistName={profile.stageName}
          artistBirthday={profile.birthday}
        />
        <button className={styles.followButton}>Follow</button>
      </div>

      <div className={styles.section2}>
        <div className={styles.leftSection}>
          <div className={styles.postInputContainer}>
            <img src="/vercel.svg" alt="Profile" className={styles.profileImage} />
            <input type="text" placeholder="Write a post..." className={styles.postInput} />
            <div className={styles.iconButtons}>
              <button className={styles.iconButton}><FaImage /></button>
              <button className={styles.iconButton}><FaVideo /></button>
            </div>
          </div>
          <div className={styles.subTabNav}>
            <button
              className={`${styles.subTabButton} ${activeSubTab === 'posts' ? styles.activeSubTab : ''}`}
              onClick={() => setActiveSubTab('posts')}
            >
              포스트
            </button>
            <button
              className={`${styles.subTabButton} ${activeSubTab === 'comments' ? styles.activeSubTab : ''}`}
              onClick={() => setActiveSubTab('comments')}
            >
              댓글
            </button>
          </div>
          <div className={styles.sortContainer}>
            <select className={styles.sortSelect}>
              <option value="latest">최신순</option>
              <option value="hot">HOT</option>
            </select>
          </div>
          {activeSubTab === 'posts' && (
            <div className={styles.postList}>
              {post.map((post) => (
                <PostCard
                  key={post.postId}
                  profileImage={profile.profileImage}
                  nickname={post.artistName}
                  postDate={post.createdAt}
                  postContent={post.content}
                  likes={post.likeCount}
                  comments={post.commentCount}
                  img={post.image}
                />
              ))}
            </div>
          )}
          {activeSubTab === 'comments' && (
            <div className={styles.commentsSection}>
              <CommentCard originalPost="Original post content 1" commentContent="This is a test comment 1." commentDateTime="07.30. 23:29" />
              <CommentCard originalPost="Original post content 2" commentContent="This is a test comment 2." commentDateTime="07.30. 23:30" />
              <CommentCard originalPost="Original post content 3" commentContent="This is a test comment 3." commentDateTime="07.30. 23:31" />
              <CommentCard originalPost="Original post content 4" commentContent="This is a test comment 4." commentDateTime="07.30. 23:32" />
              <CommentCard originalPost="Original post content 5" commentContent="This is a test comment 5." commentDateTime="07.30. 23:33" />
              <CommentCard originalPost="Original post content 6" commentContent="This is a test comment 6." commentDateTime="07.30. 23:34" />
              <CommentCard originalPost="Original post content 7" commentContent="This is a test comment 7." commentDateTime="07.30. 23:35" />
              <CommentCard originalPost="Original post content 8" commentContent="This is a test comment 8." commentDateTime="07.30. 23:36" />
              <CommentCard originalPost="Original post content 9" commentContent="This is a test comment 9." commentDateTime="07.30. 23:37" />
            </div>
          )}
        </div>
        <div className={styles.rightSection}>
          <div className={styles.momentSection}>
            <h3 className={styles.momentTitle}>Moment</h3>
            <div className={styles.momentImages}>
              <div className={styles.momentImage} style={{ backgroundImage: `` }}></div>
              <div className={styles.momentImage} style={{ backgroundColor: '#FFD6A5' }}></div>
              <div className={styles.momentImage} style={{ backgroundColor: '#FDFFB6' }}></div>
            </div>
          </div>
          <div className={styles.bannerImage} style={{ backgroundColor: '#A2D2FF' }}></div>
        </div>
      </div>
    </div>
  );
};

export default ArtistSNSOverview;
