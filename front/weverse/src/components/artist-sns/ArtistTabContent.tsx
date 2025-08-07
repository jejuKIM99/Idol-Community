import React from 'react';
import styles from './ArtistTabContent.module.css';
import StoryCard from './StoryCard';
import ArtistPostCard from './ArtistPostCard';
import MembershipCard from './MembershipCard';
import ProfileCard from './ProfileCard';
import FanLetterCard from './FanLetterCard';

interface ArtistTabContentProps {
  artistName?: string | null;
}

const ArtistTabContent: React.FC<ArtistTabContentProps> = ({ artistName }) => {
  return (
    <div className={styles.container}>
      <div className={styles.leftSection}>
        <div className={styles.storySection}>
          <StoryCard storyImageColor="#FFDDC1" artistImageAlt="Artist 1" artistName={artistName || "Artist Name"} />
          <StoryCard storyImageColor="#CCE5FF" artistImageAlt="Artist 2" artistName={artistName || "Artist Name"} />
          <StoryCard storyImageColor="#D4EDDA" artistImageAlt="Artist 3" artistName={artistName || "Artist Name"} />
          <StoryCard storyImageColor="#FFF3CD" artistImageAlt="Artist 4" artistName={artistName || "Artist Name"} />
        </div>
        <div className={styles.artistPostList}>
          <ArtistPostCard
            artistImage="/vercel.svg"
            date="01"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Hello everyone! Hope you are having a great day!"
            postDateTime="2025.08.01 10:00"
            postImageColor="#FFDDC1"
          />
          <ArtistPostCard
            artistImage="/next.svg"
            date="02"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Just finished a new song! Can't wait to share it with you all."
            postDateTime="2025.08.02 14:30"
            postImageColor="#CCE5FF"
          />
          <ArtistPostCard
            artistImage="/globe.svg"
            date="03"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Practicing for the upcoming concert. See you soon!"
            postDateTime="2025.08.03 18:00"
            postImageColor="#D4EDDA"
          />
          <ArtistPostCard
            artistImage="/file.svg"
            date="04"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Enjoying some downtime. What are you all up to?"
            postDateTime="2025.08.04 09:15"
            postImageColor="#FFF3CD"
          />
          <ArtistPostCard
            artistImage="/window.svg"
            date="05"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Working hard in the studio. Stay tuned for updates!"
            postDateTime="2025.08.05 22:00"
            postImageColor="#F8D7DA"
          />
          <ArtistPostCard
            artistImage="/vercel.svg"
            date="06"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="Thanks for all your support! You guys are the best."
            postDateTime="2025.08.06 11:45"
            postImageColor="#FFDDC1"
          />
          <ArtistPostCard
            artistImage="/next.svg"
            date="07"
            month="AUG"
            artistName={artistName || "Artist Name"}
            postContent="New merchandise coming soon! Get ready!"
            postDateTime="2025.08.07 16:00"
            postImageColor="#CCE5FF"
          />
        </div>
      </div>
      <div className={styles.rightSection}>
        <FanLetterCard artistName={artistName || "Artist Name"} />
        <MembershipCard artistName={artistName || "Artist Name"} />
        <ProfileCard profileImage="/vercel.svg" nickname="My Profile" followerCount={12345} />
        <div className={styles.noticeSection}>
            <h3 className={styles.noticeTitle}>Notice</h3>
            <ul className={styles.noticeList}>
              <li>[공지] 위버스샵 이용 안내</li>
              <li>[공지] 개인정보처리방침 개정 안내</li>
              <li>[공지] 위버스 이용약관 개정 안내</li>
            </ul>
          </div>
      </div>
    </div>
  );
};

export default ArtistTabContent;
