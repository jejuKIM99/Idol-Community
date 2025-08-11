import React, { useEffect, useState } from 'react';
import styles from './ArtistSNSOverview.module.css';
import ArtistHeaderInfo from './ArtistHeaderInfo';
import PostCard from './PostCard';
import CommentCard from './CommentCard';
import { FaImage } from 'react-icons/fa';
import axios from 'axios';

interface ArtistSNSOverviewProps {
  artistId: string | null;
  groupId?: string | null;
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

interface PostComment {
  content: string;
}

interface Comment {
  comment_id: number;
  post: PostComment;
  content: string;
  createdAt: string;
}

const ArtistSNSOverview: React.FC<ArtistSNSOverviewProps> = ({ artistId, groupId, memberName }) => {
  const [activeSubTab, setActiveSubTab] = React.useState('posts');

  const [profile, setProfile] = useState<Profile | null>(null);
  const [post, setPost] = useState<Post[]>([]);
  const [commentList, setComment] = useState<Comment[]>([]);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [isFollowing, setIsFollowing] = useState(false);

  const [postContent, setPostContent] = useState('');
  const [selectedImage, setSelectedImage] = useState<File | null>(null);
  const fileInputRef = React.useRef<HTMLInputElement>(null);

  const handlePostSubmit = async (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter' && artistId) {
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
    const fetchProfile = async () => {
      if (artistId) {
        try {
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/profile`, {
            params: { artistId: artistId }
          });
          setProfile(response.data);
        } catch (err) {
          console.error('Failed to fetch profile data:', err);
        }
      }
    };
    fetchProfile();
  }, [artistId]);

  useEffect(() => {
    const fetchPost = async () => {
      if (artistId) {
        try {
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/artist`, {
            params: { artistID: artistId }
          });
          setPost(response.data.postList);
        } catch (err) {
          console.error('Failed to fetch post data:', err);
        }
      }
    };
    fetchPost();
  }, [artistId]);

  useEffect(() => {
    const fetchComment = async () => {
      if (groupId) {
        try {
          const response = await axios.get(`http://localhost:80/api/artistSNS/home/post`, {
            params: { groupId: groupId }
          });
          setComment(response.data.commentList);
        } catch (err) {
          console.error('Failed to fetch comment data:', err);
        }
      }
    };
    fetchComment();
  }, [groupId]);

  useEffect(() => {
    const fetchIsFollowing = async () => {
      if (artistId) {
        try {
          const response = await axios.get(`http://localhost:80/api/artistSNS/post/isFollow`, {
            params: { userId: 4, artistId: artistId }
          });
          setIsFollowing(response.data);
        } catch (err) {
          console.error('Failed to fetch isFollowing data:', err);
        }
      }
    };
    fetchIsFollowing();
  }, [artistId]);

  const handleFollow = async () => {
    const formData = new FormData();
    formData.append('artistId', artistId);
    formData.append('userId', '4');

    const url = isFollowing ? 'http://localhost:80/api/artistSNS/home/unfollow' : 'http://localhost:80/api/artistSNS/home/follow';

    try {
      const response = await axios.post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      if (response.data === 'success') {
        setIsFollowing(!isFollowing);
      }
    } catch (error) {
      console.error('Error processing request:', error);
      alert('요청 처리에 실패했습니다.');
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.section1}>
        {profile && (
          <ArtistHeaderInfo
            artistImage={`http://localhost:80${profile.profileImage}`}
            artistName={profile.stageName}
            artistBirthday={profile.birthday}
          />
        )}
        <button className={styles.followButton} onClick={handleFollow}>
          {isFollowing ? 'Following' : 'Follow'}
        </button>
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
              <div className={styles.postInputContainer}>
                {profile && (
                  <img src={`http://localhost:80${profile.profileImage}`} alt="Profile" className={styles.profileImage} />
                )}
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
                {post.map((p) => (
                  <PostCard
                    key={p.postId}
                    postId={p.postId}
                    profileImage={profile?.profileImage || ''}
                    nickname={p.artistName}
                    postDate={p.createdAt}
                    postContent={p.content}
                    likes={p.likeCount}
                    comments={p.commentCount}
                    img={p.image}
                  />
                ))}
              </div>
            </div>
          )}
          {activeSubTab === 'comments' && (
            <div className={styles.commentsSection}>
              {commentList.map((comment) => (
                <CommentCard
                  key={comment.comment_id}
                  originalPost={comment.post.content}
                  commentContent={comment.content}
                  commentDateTime={comment.createdAt}
                />
              ))}
            </div>
          )}
        </div>
        <div className={styles.rightSection}>
          <div className={styles.momentSection}>
            <h3 className={styles.momentTitle}>Moment</h3>
            <div className={styles.momentImages}>
              {post.length > 0 && (
                <>
                  <div className={styles.momentImage}>
                    <img src={`http://localhost:80${post[0].image || (profile?.profileImage || '')}`} alt="momentImage" className={styles.momentImageSrc} />
                  </div>
                  {post.length > 1 && (
                    <div className={styles.momentImage}>
                      <img src={`http://localhost:80${post[1].image || (profile?.profileImage || '')}`} alt="momentImage" className={styles.momentImageSrc} />
                    </div>
                  )}
                  {post.length > 2 && (
                    <div className={styles.momentImage}>
                      <img src={`http://localhost:80${post[2].image || (profile?.profileImage || '')}`} alt="momentImage" className={styles.momentImageSrc} />
                    </div>
                  )}
                </>
              )}
            </div>
          </div>
          <div className={styles.bannerImage}>
            {profile && (
              <img src={`http://localhost:80${profile.profileImage}`} alt="momentImage" className={styles.momentImageSrc} />
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ArtistSNSOverview;