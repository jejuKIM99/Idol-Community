'use client';

import { useState } from 'react';
import styles from './coupons.module.css';
import { FiInfo } from 'react-icons/fi';

export default function CouponsPage() {
  const [activeTab, setActiveTab] = useState('owned'); // 'owned', 'get'

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>쿠폰함</h1>
      <div className={styles.registerSection}>
        <input type="text" placeholder="쿠폰 코드를 입력해 주세요." className={styles.couponInput} />
        <button className={styles.registerButton}>등록</button>
      </div>
      <div className={styles.tabContainer}>
        <div className={styles.tabs}>
          <button
            className={`${styles.tab} ${activeTab === 'owned' ? styles.active : ''}`}
            onClick={() => setActiveTab('owned')}
          >
            보유 쿠폰
          </button>
          <button
            className={`${styles.tab} ${activeTab === 'get' ? styles.active : ''}`}
            onClick={() => setActiveTab('get')}
          >
            쿠폰 받기
          </button>
        </div>
        <div className={styles.couponCount}>
          <span>전체 0</span>
          <FiInfo size={14} />
        </div>
      </div>
      <div className={styles.content}>
        <p>쿠폰이 없습니다.</p>
      </div>
    </div>
  );
}
