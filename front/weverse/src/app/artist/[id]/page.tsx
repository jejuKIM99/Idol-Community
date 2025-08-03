import { allArtists } from '@/data/artists';
import { notFound } from 'next/navigation';
import styles from './ArtistPage.module.css';
import MemberCard from '@/components/artistInfo/MemberCard';
import LiveCard from '@/components/artistInfo/LiveCard';
import MediaCard from '@/components/artistInfo/MediaCard';
import MerchCard from '@/components/artistInfo/MerchCard';
import AnnouncementCard from '@/components/artistInfo/AnnouncementCard';

interface ArtistPageProps {
  params: {
    id: string;
  };
}

const ArtistPage = async ({ params }: ArtistPageProps) => {
  const { id } = await params;
  const artist = allArtists.find((a) => a.id.toString() === id);

  if (!artist) {
    notFound();
  }

  return (
    <div className={styles.container}>
      {/* Introduction Section */}
      <section className={styles.introductionSection} style={{ backgroundImage: `url(${artist.imageUrl})` }}>
        <div className={styles.overlay}></div>
        <div className={styles.introductionContent}>
          <h1 className={styles.artistName}>{artist.name}</h1>
          <p className={styles.artistDescription}>
            여기에 {artist.name}에 대한 소개 내용이 들어갑니다. {artist.name}은(는) 전 세계 팬들에게 사랑받는 아티스트입니다. 그들의 음악과 퍼포먼스는 많은 사람들에게 영감을 줍니다.
          </p>
        </div>
      </section>

      {/* Profile Section */}
      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>프로필</h2>
        {artist.members && artist.members.length > 0 && (
          <> 
            <div className={styles.memberGrid}>
              {artist.members.map((member, index) => (
                <MemberCard key={index} member={member} artistName={artist.name} artistId={artist.id} />
              ))}
            </div>
          </>
        )}
      </section>

      {/* Live Section */}
      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>Live</h2>
        <div className={styles.liveGrid}>
          {[...Array(3)].map((_, index) => (
            <LiveCard
              key={index}
              thumbnail={`https://via.placeholder.com/250x150?text=Live+${index + 1}`}
              date="2024년 8월 2일"
              time="18:00"
              title={`아티스트 라이브 ${index + 1}`}
            />
          ))}
        </div>
      </section>

      {/* Media Section */}
      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>미디어</h2>
        <div className={styles.mediaGrid}>
          {[...Array(4)].map((_, index) => (
            <MediaCard
              key={index}
              thumbnail={`https://via.placeholder.com/200x120?text=Media+${index + 1}`}
              title={`미디어 제목 ${index + 1}`}
              date="2024년 7월 20일"
            />
          ))}
        </div>
      </section>

      {/* Merch Section */}
      <section className={styles.section}>
        <div className={styles.sectionHeader}>
          <h2 className={styles.sectionTitle}>Merch</h2>
          <button className={styles.artistShopButton}>Artist Shop &gt;</button>
        </div>
        <div className={styles.merchGrid}>
          {[...Array(5)].map((_, index) => (
            <MerchCard
              key={index}
              image={`https://via.placeholder.com/180x180?text=Merch+${index + 1}`}
              name={`상품명 ${index + 1}`}
              price={`${(index + 1) * 10000}원`}
            />
          ))}
        </div>
      </section>

      <div style={ {width: '96%', height: '2px', background: '#929292', marginBottom: '16px'}}></div>

      {/* Announcements Section */}
      <section className={styles.section}>
        <div className={styles.announcementList}>
          {[...Array(3)].map((_, index) => (
            <AnnouncementCard
              key={index}
              title={`[NOTICE] 제목 ${index + 1}`}
            />
          ))}
        </div>
      </section>

      {/* SNS Section */}
      <section className={styles.section}>
        <div className={styles.snsButtonsContainer}>
          <a href="#" target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/FF0000/FFFFFF?text=YT" alt="YouTube" className={styles.snsIcon} />
          </a>
          <a href="#" target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/E4405F/FFFFFF?text=IG" alt="Instagram" className={styles.snsIcon} />
          </a>
          <a href="#" target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/000000/FFFFFF?text=X" alt="X (Twitter)" className={styles.snsIcon} />
          </a>
          <a href="#" target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/00F2EA/FFFFFF?text=TK" alt="TikTok" className={styles.snsIcon} />
          </a>
          <a href="#" target="_blank" rel="noopener noreferrer" className={styles.snsButton}>
            <img src="https://via.placeholder.com/40/007BFF/FFFFFF?text=HP" alt="Homepage" className={styles.snsIcon} />
          </a>
        </div>
      </section>

      {/* Community Shortcut Button */}
      <div className={styles.communityButtonContainer}>
        <button className={styles.communityButton}>
          커뮤니티 바로가기
        </button>
      </div>
    </div>
  );
};

export default ArtistPage;