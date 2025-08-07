import React from 'react';
import Link from 'next/link';
import styles from './MemberCard.module.css';

interface MemberCardProps {
  member: {
    name: string;
    imageUrl: string;
  };
  artistName: string;
  artistId: string;
}

const MemberCard: React.FC<MemberCardProps> = ({ member, artistName, artistId }) => {
  return (
    <Link href={`/nickname?artistName=${artistName}&memberName=${member.name}&memberImage=${member.imageUrl}&artistId=${artistId}`} legacyBehavior>
      <a className={styles.memberCard}>
        <img src={member.imageUrl} alt={member.name} className={styles.memberImage} />
        <p className={styles.memberName}>{member.name}</p>
      </a>
    </Link>
  );
};

export default MemberCard;
