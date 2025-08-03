import React from 'react';
import styles from './LiveCard.module.css';

interface LiveCardProps {
  thumbnail: string;
  date: string;
  time: string;
  title: string;
}

const LiveCard: React.FC<LiveCardProps> = ({ thumbnail, date, time, title }) => {
  return (
    <div className={styles.liveCard}>
      <img src={thumbnail} alt={title} className={styles.thumbnail} />
      <div className={styles.info}>
        <p className={styles.dateTime}>{date} {time}</p>
        <h3 className={styles.title}>{title}</h3>
      </div>
    </div>
  );
};

export default LiveCard;
