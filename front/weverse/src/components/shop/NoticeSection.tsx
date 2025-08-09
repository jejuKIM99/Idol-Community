import React from 'react';
import styles from './NoticeSection.module.css';
// import { Notice } from '@/data/mockData'; // 목업 데이터 제거

interface ShopNoticeDTO {
  noticeId: number;
  title: string;
  content: string;
  createdAt: string;
}

// Props 인터페이스에 title 속성 추가
interface Props {
  notices: ShopNoticeDTO[];
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
                    <li key={notice.noticeId}>
                        <a href="#">
                            {/* 백엔드 DTO에 category 필드가 없으므로 제거 */}
                            {/* <span className={styles.noticeCategory}>[{notice.category}]</span> */}
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
