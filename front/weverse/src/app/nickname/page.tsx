'use client';

import React from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import styles from './nickname.module.css';

const NicknamePage = () => {
  const searchParams = useSearchParams();
  const router = useRouter();
  const artistName = searchParams.get('artistName');
  const artistId = searchParams.get('artistId'); // Assuming artistId is also passed from MemberCard
  const memberName = searchParams.get('memberName');
  const memberImage = searchParams.get('memberImage');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // In a real application, you would save the nickname here.
    // For now, just navigate.
    router.push(`/artist-sns?artistId=${artistId}&artistName=${artistName}${memberName ? `&memberName=${memberName}` : ''}`);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>
        {artistName} 위버스에 가입하고 최신 콘텐츠를 즐겨보세요!
        <br />
        위버스에서 사용할 닉네임을 입력해주세요.
      </h1>
      <form className={styles.form} onSubmit={handleSubmit}>
        {memberImage && (
          <div className={styles.profileImageContainer}>
            <img src={memberImage} alt={memberName || 'Profile'} className={styles.profileImage} />
          </div>
        )}
        <label htmlFor="nickname" className={styles.label}>닉네임</label>
        <input
          type="text"
          id="nickname"
          placeholder="닉네임을 입력하세요"
          className={styles.input}
        />
        <button type="submit" className={styles.button}>완료</button>
      </form>
    </div>
  );
};

export default NicknamePage;
