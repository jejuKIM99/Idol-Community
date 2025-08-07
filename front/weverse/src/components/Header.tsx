'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { useAuth } from '../app/context/AuthContext';
import styles from '@/styles/Header.module.css';
import { FiLogIn, FiUser, FiGift, FiShoppingCart, FiLogOut } from 'react-icons/fi';

const Header = () => {
  const pathname = usePathname();
  const isArtistSNSPage = pathname.startsWith('/artist/');
  const { isAuthenticated, logout } = useAuth();

  const handleLogout = () => {
    logout();
    // 로그아웃 후 필요한 추가 작업 (예: 페이지 리로드 또는 리다이렉트)
  };

  return (
    <header className={`${styles.header} ${isArtistSNSPage ? styles.artistSNSHeader : ''}`}>
      <div className={styles.logo}>
        <Link href="/">Weverse</Link>
      </div>
      <nav className={styles.nav}>
        {isAuthenticated ? (
          <>
            <button onClick={handleLogout} className={styles.navButton}><FiLogOut size={24} /></button>
            <Link href="/mypage" legacyBehavior><a><FiUser size={24} /></a></Link>
          </>
        ) : (
          <Link href="/login" legacyBehavior><a><FiLogIn size={24} /></a></Link>
        )}
        <Link href="/jelly" legacyBehavior><a><FiGift size={24} /></a></Link>
        <Link href="/shop" legacyBehavior><a><FiShoppingCart size={24} /></a></Link>
      </nav>
    </header>
  );
};

export default Header;