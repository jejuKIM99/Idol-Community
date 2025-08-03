import React from 'react';
import styles from './SNSCard.module.css';

interface SNSCardProps {
  icon: string; // This could be a URL to an image or a class name for an icon font
  name: string;
}

const SNSCard: React.FC<SNSCardProps> = ({ icon, name }) => {
  return (
    <div className={styles.snsCard}>
      {/* For simplicity, using an img tag for icon. Could be replaced with actual icon component/font */}
      <img src={icon} alt={name} className={styles.snsIcon} />
      <p className={styles.snsName}>{name}</p>
    </div>
  );
};

export default SNSCard;
