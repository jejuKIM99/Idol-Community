import React from 'react';
import styles from './AnnouncementCard.module.css';

interface AnnouncementCardProps {
  title: string;
  create_at: string;
}

const AnnouncementCard: React.FC<AnnouncementCardProps> = ({ title, create_at }) => {
  return (
    <div className={styles.announcementCard}>
      <p className={styles.anon}>공지</p>
      <p className={styles.announcementTitle}>{title}</p>
      <button className={styles.arrowButton}>&gt;</button>
    </div>
  );
};

export default AnnouncementCard;
