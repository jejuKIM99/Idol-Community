
import React, { useEffect, useState } from 'react';
import styles from './PostCard.module.css';
import { FaHeart, FaComment } from 'react-icons/fa';
import axios from 'axios';

interface PostCardProps {
  postId: number;
  profileImage: string;
  nickname: string;
  postDate: string;
  postContent: string;
  likes: number;
  comments: number;
  img: string;
}

const PostCard: React.FC<PostCardProps> = ({
  postId,
  profileImage,
  nickname,
  postDate,
  postContent,
  likes,
  comments,
  img
}) => {
  const [liked, setLiked] = useState(false);

  // artistprofile
  useEffect(() => {
    const fetchIsLike = async () => {
      console.log("fetchProfile")
      if (postId) {
        try {
          console.log("Fetching siLike data for PostId:", postId);
          const response = await axios.get(`http://localhost:80/api/artistSNS/post/isLike`, {
            params: { userId: 3, postId: postId }
          });
          console.log('post Data Response:', response.data); // API 응답 전체를 다시 확인
          setLiked(response.data);
        } catch (err) {
          console.error('Failed to fetch notice data:', err);
        }
      }
    };
    fetchIsLike();
  }, [postId]);

  const handleLike = async () => {
    const formData = new FormData();
    formData.append('postId', postId.toString());
    formData.append('userId', '3');

    const url = liked ? 'http://localhost:80/api/artistSNS/home/dislike' : 'http://localhost:80/api/artistSNS/home/like';

    try {
      const response = await axios.post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      if (response.data === 'success') {
        setLiked(!liked);
      }
    } catch (error) {
      console.error('Error processing request:', error);
      alert('요청 처리에 실패했습니다.');
    }
  };

  return (
    <div className={styles.postCard}>
      <div className={styles.header}>
        <div className={styles.userInfo}>
          <img src={`http://localhost:80${profileImage}`} alt="Profile" className={styles.profileImage} />
          <div className={styles.headerInfo}>
            <span className={styles.nickname}>{nickname}</span>
            <span className={styles.postDate}>{postDate}</span>
          </div>
        </div>
        <button className={styles.moreButton}>⋮</button>
      </div>
      <div className={styles.postImageContainer}>
        <img src={`http://localhost:80${img}`} alt="Profile" className={styles.postImage} />
      </div>
      <div className={styles.content}>
        <p>{postContent}</p>
      </div>
      <div className={styles.actions}>
        <button className={styles.actionButton} onClick={handleLike}>
          <FaHeart style={{ color: liked ? 'red' : 'inherit' }} />
          <span>{likes}</span>
        </button>
        <button className={styles.actionButton}>
          <FaComment />
          <span>{comments}</span>
        </button>
      </div>
    </div>
  );
};

export default PostCard;
