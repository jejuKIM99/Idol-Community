
import React from 'react';
import styles from './PostCard.module.css';
import { FaHeart, FaComment } from 'react-icons/fa';

interface PostCardProps {
  key:number;
  profileImage: string;
  nickname: string;
  postDate: string;
  postContent: string;
  likes: number;
  comments: number;
  img: string;
}

const PostCard: React.FC<PostCardProps> = ({
  key,
  profileImage,
  nickname,
  postDate,
  postContent,
  likes,
  comments,
  img
}) => {
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
        <button className={styles.actionButton}>
          <FaHeart />
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
