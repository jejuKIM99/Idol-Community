
import React from 'react';
import styles from './FanLetterCard.module.css';
import { FaEnvelope } from 'react-icons/fa';

interface FanLetterCardProps {
  artistName: string;
}

const FanLetterCard: React.FC<FanLetterCardProps> = ({ artistName }) => {
  return (
    <div className={styles.fanLetterCard}>
      <div className={styles.fanIcon}>
        <FaEnvelope className={styles.envelopeIcon} />
        <span className={styles.fanLetterText}>팬레터</span>
      </div>
      <button className={styles.sendButton}>To {artistName}</button>
    </div>
  );
};

export default FanLetterCard;
