import React, { useState, useEffect } from 'react';
import styles from './ArtistDMModal.module.css';
import { FaSearch, FaTimes } from 'react-icons/fa';
import Image from 'next/image';

interface Artist {
  artistId: number;
  name: string;
  stageName: string;
  profileImage: string;
  group: Group;
}

interface Group {
  groupId: number;
  groupName: string;
  groupProfileImage: string;
  groupLogo: string;
}

interface ArtistDMModalProps {
  isOpen: boolean;
  onClose: () => void;
  selectedArtistName: string;
  allArtists: Artist[];
  allGroups: Group[];
}

const ArtistDMModal: React.FC<ArtistDMModalProps> = ({ isOpen, onClose, selectedArtistName, allArtists, allGroups }) => {
  const [showSearchInput, setShowSearchInput] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedGroup, setSelectedGroup] = useState<Group | null>(null);

  useEffect(() => {
    if (isOpen) {
      const initialArtist = allArtists.find(artist => artist.stageName === selectedArtistName);
      if (initialArtist && initialArtist.group) {
        const group = allGroups.find(g => g.groupId === initialArtist.group.groupId);
        setSelectedGroup(group || null);
      } else if (allGroups.length > 0) {
        setSelectedGroup(allGroups[0]);
      } else {
        setSelectedGroup(null);
      }
    }
  }, [isOpen, selectedArtistName, allArtists, allGroups]);

  if (!isOpen) return null;

  const filteredGroups = allGroups.filter(group =>
    group.groupName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const membersOfSelectedGroup = selectedGroup
    ? allArtists.filter(artist => artist.group && artist.group.groupId === selectedGroup.groupId)
    : [];

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
                  placeholder="그룹 검색..."
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
          {filteredGroups.map((group) => (
            <span
              key={group.groupId}
              className={`${styles.artistNameTag} ${selectedGroup?.groupId === group.groupId ? styles.activeTag : ''}`}
              onClick={() => setSelectedGroup(group)}
            >
              {group.groupName}
            </span>
          ))}
        </div>

        <div className={styles.selectedArtistInfo}>
          {selectedGroup && (
            <>
              <p>{selectedGroup.groupName}</p>
              <div className={styles.artistDetailGrid}>
                {membersOfSelectedGroup.map(member => (
                  <div key={member.artistId} className={styles.artistDetail}>
                    <div className={styles.artistImage}>
                      <img src={`http://localhost:80${member.profileImage}`} alt={member.stageName} style={{ width: '100%', height: '100%', objectFit: 'contain' }}/>
                    </div>
                    <p className={styles.artistDetailName}>{member.stageName}</p>
                  </div>
                ))}
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ArtistDMModal;
