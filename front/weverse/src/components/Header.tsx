'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import styles from '@/styles/Header.module.css';
import { FiLogIn, FiUser, FiGift, FiShoppingCart } from 'react-icons/fi';

const Header = () => {
  const pathname = usePathname();
  const isArtistSNSPage = pathname.startsWith('/artist/');

  return (
    <header className={`${styles.header} ${isArtistSNSPage ? styles.artistSNSHeader : ''}`}>
      <div className={styles.logo}>
        <Link href="/">Weverse</Link>
      </div>
      <nav className={styles.nav}>
        <Link href="/login" legacyBehavior><a><FiLogIn size={24} /></a></Link>
        <Link href="/mypage" legacyBehavior><a><FiUser size={24} /></a></Link>
        <Link href="/jelly" legacyBehavior><a><FiGift size={24} /></a></Link>
        <Link href="/shop" legacyBehavior><a><FiShoppingCart size={24} /></a></Link>
      </nav>
    </header>
  );
};

export default Header;