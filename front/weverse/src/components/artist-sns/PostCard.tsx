
import React from 'react';
import styles from './PostCard.module.css';
import { FaHeart, FaComment } from 'react-icons/fa';

interface PostCardProps {
  profileImage: string;
  nickname: string;
  postDate: string;
  postImageColor: string;
  postContent: string;
}

const PostCard: React.FC<PostCardProps> = ({
  profileImage,
  nickname,
  postDate,
  postImageColor,
  postContent,
}) => {
  return (
    <div className={styles.postCard}>
      <div className={styles.header}>
        <div className={styles.userInfo}>
          <img src={profileImage} alt="Profile" className={styles.profileImage} />
          <div className={styles.headerInfo}>
            <span className={styles.nickname}>{nickname}</span>
            <span className={styles.postDate}>{postDate}</span>
          </div>
        </div>
        <button className={styles.moreButton}>⋮</button>
      </div>
      <div className={styles.postImageContainer} style={{ backgroundColor: postImageColor }}></div>
      <div className={styles.content}>
        <p>{postContent}</p>
      </div>
      <div className={styles.actions}>
        <button className={styles.actionButton}>
          <FaHeart />
          <span>Like</span>
        </button>
        <button className={styles.actionButton}>
          <FaComment />
          <span>Comment</span>
        </button>
      </div>
    </div>
  );
};

export default PostCard;
