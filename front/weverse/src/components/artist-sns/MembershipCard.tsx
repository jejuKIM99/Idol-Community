
import React from 'react';
import styles from './MembershipCard.module.css';
import { FaHeart } from 'react-icons/fa';

interface MembershipCardProps {
  artistName: string;
}

const MembershipCard: React.FC<MembershipCardProps> = ({ artistName }) => {
  return (
    <div className={styles.membershipCard}>
      <div className={styles.membershipInfo}>
        <FaHeart className={styles.heartIcon} />
        <span className={styles.membershipText}>{artistName} Digital Membership</span>
      </div>
      <button className={styles.subscribeButton}>구독하기</button>
    </div>
  );
};

export default MembershipCard;
