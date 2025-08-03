
import React from 'react';
import styles from './ProfileCard.module.css';

interface ProfileCardProps {
  profileImage: string;
  nickname: string;
  followerCount: number;
}

const ProfileCard: React.FC<ProfileCardProps> = ({
  profileImage,
  nickname,
  followerCount,
}) => {
  return (
    <div className={styles.profileCard}>
      <img src={profileImage} alt="Profile" className={styles.profileImage} />
      <span className={styles.nickname}>{nickname}</span>
      <span className={styles.followerCount}>{followerCount.toLocaleString()} Followers</span>
    </div>
  );
};

export default ProfileCard;
