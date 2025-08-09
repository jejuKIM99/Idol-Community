
import React, { useEffect, useState } from 'react';
import styles from './ArtistSNSOverview.module.css';
import ArtistHeaderInfo from './ArtistHeaderInfo';
import PostCard from './PostCard';
import CommentCard from './CommentCard';
import { FaImage, FaVideo } from 'react-icons/fa';
import axios from 'axios';

interface ArtistSNSOverviewProps {
  artistId?: string | null;
  groupId? : string | null;
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

interface Comment {
  comment_id : number;
}

const ArtistSNSOverview: React.FC<ArtistSNSOverviewProps> = ({ artistId, groupId, memberName }) => {
  const [activeSubTab, setActiveSubTab] = React.useState('posts');

  const [profile, setProfile] = useState<Profile[]>([]); // 라이브 데이터 상태 추가
  const [post, setPost] = useState<Post[]>([]); // 라이브 데이터 상태 추가
  const [commentList, setComment] = useState<Comment[]>([]); // 라이브 데이터 상태 추가

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
      if (selectedImage) {
        formData.append('image', selectedImage);
      }

      try {
        await axios.post('http://localhost:80/api/artistSNS/home/InputPost', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
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

  // comment
  useEffect(() => {
    const fetchComment = async () => {
      console.log("fetch comment")
      if (groupId) {
        try {
          console.log("Fetching comment data for groupId:", groupId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/post`, {
            params: { groupId: groupId }
          });
          console.log('comment Data Response:', response.data); // API 응답 전체를 다시 확인
          setComment(response.data.commentList);
        } catch (err) {
          console.error('Failed to fetch comment data:', err);
        }
      }
    };
    fetchComment();
  }, [groupId]);

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
            <div>
              {/* 테스트용 */}
              <div className={styles.postInputContainer}>
                <img src={`http://localhost:80${profile.profileImage}`} alt="Profile" className={styles.profileImage} />
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
