import React from 'react';
import styles from './NoticeSection.module.css';
import { Notice } from '@/data/mockData';

// Props 인터페이스에 title 속성 추가
interface Props {
  notices: Notice[];
  title: string; 
}

const NoticeSection = ({ notices, title }: Props) => {
  return (
    <section className={styles.section}>
        <div className={styles.sectionHeader}>
            {/* 전달받은 title을 헤더에 표시 */}
            <h3 className={styles.sectionTitle}>{title}</h3>
            <a href="#" className={styles.viewMore}>더보기</a>
        </div>
        <ul className={styles.noticeList}>
            {notices.length > 0 ? (
                notices.map(notice => (
                    <li key={notice.id}>
                        <a href="#">
                            <span className={styles.noticeCategory}>[{notice.category}]</span>
                            <span className={styles.noticeTitle}>{notice.title}</span>
                        </a>
                    </li>
                ))
            ) : (
                <li className={styles.noNotice}>등록된 공지가 없습니다.</li>
            )}
        </ul>
    </section>
  );
};

export default NoticeSection;
