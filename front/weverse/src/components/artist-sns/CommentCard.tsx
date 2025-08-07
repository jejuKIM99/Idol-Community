import React from 'react';
import styles from './CommentCard.module.css';

interface CommentCardProps {
  originalPost: string;
  commentContent: string;
  commentDateTime: string;
}

const CommentCard: React.FC<CommentCardProps> = ({ originalPost, commentContent, commentDateTime }) => {
  const formatDateTime = (dateTimeString: string) => {
    const date = new Date(dateTimeString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${year}년 ${month}월 ${day}일 ${hours}시 ${minutes}분`;
  };

  return (
    <div className={styles.commentCard}>
      <p className={styles.originalPost}>{originalPost}</p>
      <hr className={styles.divider} />
      <p className={styles.commentContent}>{commentContent}</p>
      <span className={styles.commentDateTime}>{formatDateTime(commentDateTime)}</span>
    </div>
  );
};

export default CommentCard;