import React from 'react';
import Image from 'next/image';
import styles from './CommentCard.module.css';
import { FaHeart, FaReply } from 'react-icons/fa';

interface CommentCardProps {
  profileImage: string;
  nickname: string;
  timestamp: string;
  content: string;
  likes: number;
}

const CommentCard: React.FC<CommentCardProps> = ({ profileImage, nickname, timestamp, content, likes }) => {
  return (
    <div className={styles.commentCard}>
      <div className={styles.profileSection}>
        <Image src={profileImage} alt={nickname} width={40} height={40} className={styles.profileImage} />
      </div>
      <div className={styles.contentSection}>
        <div className={styles.header}>
          <span className={styles.nickname}>{nickname}</span>
          <span className={styles.timestamp}>{timestamp}</span>
        </div>
        <p className={styles.content}>{content}</p>
        <div className={styles.actions}>
          <button className={styles.actionButton}>
            <FaHeart /> {likes}
          </button>
          <button className={styles.actionButton}>
            <FaReply /> 대댓글
          </button>
        </div>
      </div>
    </div>
  );
};

export default CommentCard;