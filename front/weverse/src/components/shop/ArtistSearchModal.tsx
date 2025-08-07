// components/shop/ArtistSearchModal.tsx (Updated)
import React, { useState, useMemo } from 'react';
import Link from 'next/link';
import styles from './ArtistSearchModal.module.css';
import { Artist } from '@/data/mockData';
import { FiSearch, FiX } from 'react-icons/fi';

interface Props {
  isOpen: boolean;
  onClose: () => void;
  artists: Artist[];
}

const ArtistSearchModal = ({ isOpen, onClose, artists }: Props) => {
  const [searchTerm, setSearchTerm] = useState('');

  const filteredArtists = useMemo(() => {
    if (!searchTerm) return artists;
    return artists.filter(artist =>
      artist.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }, [searchTerm, artists]);

  if (!isOpen) return null;

  return (
    <div className={styles.modalOverlay} onClick={onClose}>
      <div className={styles.modalContent} onClick={e => e.stopPropagation()}>
        <div className={styles.modalHeader}>
          <h4>아티스트 검색</h4>
          <button onClick={onClose} className={styles.closeButton}><FiX size={24} /></button>
        </div>
        <div className={styles.searchContainer}>
          <FiSearch />
          <input
            type="text"
            placeholder="Search"
            value={searchTerm}
            onChange={e => setSearchTerm(e.target.value)}
          />
        </div>
        <div className={styles.artistList}>
          {filteredArtists.map(artist => (
            <Link 
              href={`/shop/${encodeURIComponent(artist.name)}`} 
              key={artist.id} 
              className={styles.artistListItem}
              onClick={onClose} // 모달 닫기
            >
              <img src={artist.logoUrl} alt={artist.name} />
              <span>{artist.name}</span>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ArtistSearchModal;
