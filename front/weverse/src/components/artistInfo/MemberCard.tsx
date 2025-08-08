import React from 'react';
import Link from 'next/link';
import styles from './MemberCard.module.css';

interface MemberCardProps {
  name: string;
  imageUrl: string;
  artistName: string;
  artistId: number;
  groupId: number;
}

const MemberCard: React.FC<MemberCardProps> = ({ name, imageUrl, artistName, artistId, groupId }) => {
  return (
    <Link href={`/nickname?artistName=${artistName}&memberName=${name}&memberImage=${imageUrl}&artistId=${artistId}&groupId=${groupId}`} legacyBehavior>
      <a className={styles.memberCard}>
        <img src={`http://localhost:80${imageUrl}`} alt={name} className={styles.memberImage} />
        <p className={styles.memberName}>{name}</p>
      </a>
    </Link>
  );
};

export default MemberCard;
