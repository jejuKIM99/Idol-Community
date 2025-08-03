'use client';

import { useState } from 'react';
import styles from './notices.module.css';
import Link from 'next/link';
import { FiChevronLeft, FiChevronRight } from 'react-icons/fi';

type Notice = {
  id: number;
  category: string;
  title: string;
  date: string;
  isNew?: boolean;
};

// 페이지네이션을 위한 15개의 목업 데이터
const noticesData: Notice[] = [
  { id: 1, category: '공지', title: 'Weverse Company 개인정보 처리방침 개정 예정 안내 (2025.7.31)', date: '2025.07.30', isNew: true },
  { id: 2, category: '공지', title: '[공지] 위버스 영상 콘텐츠의 TV 시청 방법 변경 안내(2025.07.24)', date: '2025.07.24' },
  { id: 3, category: '공지', title: '위버스 배지 요금 관련 안내', date: '2025.06.26' },
  { id: 4, category: '공지', title: '위버스 멤버십 유효 기간 정책 개편 안내 (2025.07.01)', date: '2025.06.24' },
  { id: 5, category: '공지', title: 'Weverse 3.5.0 업데이트 안내 (2025.06.04)', date: '2025.06.04' },
  { id: 6, category: '공지', title: '디지털 멤버십 혜택 추가 안내 : 영상 업로드 업그레이드(고화질 영상 업로드) (2025.06.04)', date: '2025.06.04' },
  { id: 7, category: '이벤트', title: '[이벤트] 디지털 멤버십 구독자를 위한 2025 Weverse Con Festival 티켓 증정 이벤트 진행 안내 (2025.05.21)', date: '2025.05.21' },
  { id: 8, category: '이벤트', title: '[이벤트] 리스닝 파티와 함께 우리 모두의 파티를 즐겨보세요! 이벤트 당첨자 발표', date: '2025.05.20' },
  { id: 9, category: '공지', title: '헬로랑 함께 기능 오픈 안내 (2025.05.08)', date: '2025.05.08' },
  { id: 10, category: '공지', title: 'Weverse 3.4.0 업데이트 안내 (2025.04.29)', date: '2025.04.29' },
  { id: 11, category: '공지', title: '시스템 점검 안내 (2025.04.15)', date: '2025.04.15' },
  { id: 12, category: '공지', title: '서비스 이용약관 개정 안내 (2025.04.01)', date: '2025.04.01' },
];


export default function NoticesPage() {
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10;

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = noticesData.slice(indexOfFirstItem, indexOfLastItem);

  const totalPages = Math.ceil(noticesData.length / itemsPerPage);

  const paginate = (pageNumber: number) => {
    if (pageNumber > 0 && pageNumber <= totalPages) {
      setCurrentPage(pageNumber);
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>공지사항</h1>
      <div className={styles.noticeList}>
        {currentItems.map((notice) => (
          <Link href="#" key={notice.id} className={styles.noticeItem}>
            <div>
              <span className={styles.category}>{notice.category}</span>
              <p className={styles.noticeTitle}>
                {notice.isNew && <span className={styles.newTag}>new</span>}
                {notice.title}
              </p>
              <p className={styles.noticeDate}>{notice.date}</p>
            </div>
          </Link>
        ))}
      </div>
      <div className={styles.pagination}>
        <button onClick={() => paginate(currentPage - 1)} disabled={currentPage === 1}>
          <FiChevronLeft />
        </button>
        {Array.from({ length: totalPages }, (_, i) => i + 1).map(number => (
          <button
            key={number}
            onClick={() => paginate(number)}
            className={currentPage === number ? styles.active : ''}
          >
            {number}
          </button>
        ))}
        <button onClick={() => paginate(currentPage + 1)} disabled={currentPage === totalPages}>
          <FiChevronRight />
        </button>
      </div>
    </div>
  );
}
