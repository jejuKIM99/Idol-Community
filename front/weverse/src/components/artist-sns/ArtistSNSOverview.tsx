
import React from 'react';
import styles from './ArtistSNSOverview.module.css';
import ArtistHeaderInfo from './ArtistHeaderInfo';
import PostCard from './PostCard';
import CommentCard from './CommentCard';
import { FaImage, FaVideo } from 'react-icons/fa';

interface ArtistSNSOverviewProps {
  artistName?: string | null;
  memberName?: string | null;
}

const ArtistSNSOverview: React.FC<ArtistSNSOverviewProps> = ({ memberName }) => {
  const [activeSubTab, setActiveSubTab] = React.useState('posts');

  return (
    <div className={styles.container}>
      <div className={styles.section1}>
        <ArtistHeaderInfo
          artistImage="/vercel.svg"
          artistName={memberName || "Artist Name"}
          artistBirthday="1997.09.01"
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
              <PostCard profileImage="/vercel.svg" nickname="User 1" postDate="2 hours ago" postImageColor="#ffcccb" postContent="This is the first post." likes={10} comments={5} />
              <PostCard profileImage="/next.svg" nickname="User 2" postDate="3 hours ago" postImageColor="#add8e6" postContent="This is the second post." likes={20} comments={10} />
              <PostCard profileImage="/globe.svg" nickname="User 3" postDate="5 hours ago" postImageColor="#90ee90" postContent="This is the third post." likes={30} comments={15} />
              <PostCard profileImage="/file.svg" nickname="User 4" postDate="1 day ago" postImageColor="#ffd700" postContent="This is the fourth post." likes={40} comments={20} />
              <PostCard profileImage="/window.svg" nickname="User 5" postDate="2 days ago" postImageColor="#ffb6c1" postContent="This is the fifth post." likes={50} comments={25} />
              <PostCard profileImage="/vercel.svg" nickname="User 6" postDate="3 days ago" postImageColor="#e6e6fa" postContent="This is the sixth post." likes={60} comments={30} />
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
              <div className={styles.momentImage} style={{ backgroundColor: '#FFADAD' }}></div>
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
