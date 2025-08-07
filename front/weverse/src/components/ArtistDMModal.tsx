import React, { useState } from 'react';
import styles from './ArtistDMModal.module.css';
import { FaSearch, FaTimes } from 'react-icons/fa';
import Image from 'next/image';

interface ArtistDMModalProps {
  isOpen: boolean;
  onClose: () => void;
  selectedArtistName: string;
  allArtists: Artist[];
}

const ArtistDMModal: React.FC<ArtistDMModalProps> = ({ isOpen, onClose, selectedArtistName, allArtists }) => {
  const [showSearchInput, setShowSearchInput] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentSelectedArtist, setCurrentSelectedArtist] = useState(allArtists.find(artist => artist.name === selectedArtistName) || allArtists[0]);

  if (!isOpen) return null;

  return (
    <div className={styles.overlay}>
      <div className={styles.modal}>
        <div className={styles.header}>
          <h2>weverse DM</h2>
          <div className={styles.searchContainer}>
            {showSearchInput ? (
              <div className={styles.searchInputWrapper}>
                <FaSearch className={styles.searchIconInsideInput} />
                <input
                  type="text"
                  placeholder="검색..."
                  className={styles.searchInput}
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
              </div>
            ) : (
              <button className={styles.searchButton} onClick={() => setShowSearchInput(!showSearchInput)}>
                <FaSearch />
              </button>
            )}
          </div>
          <button className={styles.closeButton} onClick={onClose}>
            <FaTimes />
          </button>
        </div>

        <div className={styles.allArtistsScroll}>
          {allArtists.map((artist, index) => (
            <span
              key={index}
              className={styles.artistNameTag}
              onClick={() => setCurrentSelectedArtist(artist)}
            >
              {artist.name}
            </span>
          ))}
        </div>

        <div className={styles.selectedArtistInfo}>
          <p>{currentSelectedArtist?.name}</p>
          {currentSelectedArtist && (
            <div className={styles.artistDetail}>
              <div className={styles.artistImage}>
                <Image src={currentSelectedArtist.imageUrl} alt={currentSelectedArtist.name} width={300} height={300} />
              </div>
              <p className={styles.artistDetailName}>{currentSelectedArtist.name}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ArtistDMModal;
