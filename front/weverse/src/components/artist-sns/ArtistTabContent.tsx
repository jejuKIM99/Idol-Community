import React, { useEffect, useState } from 'react';
import styles from './ArtistTabContent.module.css';
import StoryCard from './StoryCard';
import ArtistPostCard from './ArtistPostCard';
import MembershipCard from './MembershipCard';
import ProfileCard from './ProfileCard';
import FanLetterCard from './FanLetterCard';
import axios from 'axios';

interface ArtistTabContentProps {
  artistId: string | null;
  groupId: string | null;
}

interface Story {
  storyId: number;
  artistName: string;
  storyCreatedAt: string;
  storyImg: string;
}

interface Post {
  postId: number;
  artistName: string;
  profileImage: string;
  nickname: string;
  postDate: string;
  content: string;
  createdAt: string;
  likeCount: number;
  commentCount: number;
  image: string;
}

interface User {
  userId: number;
  profileImage: string;
  nickname: string;
}

interface Notice {
  id: number;
  title: string;
  groupName: string;
}

const ArtistTabContent: React.FC<ArtistTabContentProps> = ({ artistId, groupId }) => {
  
  const [artistName, setArtistName] = useState<string>("")
  const [story, setStory] = useState<Story[]>([]); // 라이브 데이터 상태 추가
  const [artistPost, setArtistPost] = useState<Post[]>([]); // 라이브 데이터 상태 추가
  const [user, setUser] = useState<User[]>([]); // 라이브 데이터 상태 추가
  const [notice, setNotice] = useState<Notice[]>([]); // 라이브 데이터 상태 추가

  // story
  useEffect(() => {
    const fetchFanPost = async () => {
      if (artistId) {
        try {
          const response = await axios.get(`http://localhost:80/api/story/artiststory`, {
            params: { artistId: artistId }
          });
          setStory(response.data)
          console.log("story data : " , response.data)
        } catch (err) {
          console.error('Failed to fetch story data:', err);
        }
      }
    };
    fetchFanPost();
  }, [artistId]);

  // post
  useEffect(() => {
    const fetchFanPost = async () => {
      if (groupId) {
        try {
          const response = await axios.get(`http://localhost:80/api/artistSNS/artistPost`, {
            params: { groupId: groupId }
          });
          const fetchedPosts = response.data.postList;
          setArtistPost(fetchedPosts);
            if (fetchedPosts && fetchedPosts.length > 0) {
            setArtistName(fetchedPosts[0].artistName);
          }
        } catch (err) {
          console.error('Failed to fetch post data:', err);
        }
      }
    };
    fetchFanPost();
  }, [groupId]);

  // user
  useEffect(() => {
    const fetchFanPost = async () => {
      console.log("fetchUser")
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/fan/myProfile`, {
          params: { userId: 3 }
        });
        console.log('user Post Data Response:', response.data); // API 응답 전체를 다시 확인
        setUser(response.data);
      } catch (err) {
        console.error('Failed to fetch post data:', err);
      }
    };
    fetchFanPost();
  }, [groupId]);

  // notice
  useEffect(() => {
    const fetchFanNotice = async () => {
      console.log("fetchNotice")
      try {
        const response = await axios.get(`http://localhost:80/api/artistSNS/fan/notice`, {
          params: { groupId: groupId }
        });
        console.log('Notice Data Response:', response.data.boardList); // API 응답 전체를 다시 확인
        const noticeData = response.data.boardList || [];
        // id를 기준으로 내림차순 정렬하고 상위 3개 항목을 선택합니다.
        // 참고: Notice 인터페이스에 id 속성이 정의되어 있지 않아 any 타입을 사용합니다.
        const sortedNotices = [...noticeData]
          .sort((a: Notice, b: Notice) => b.id - a.id)
          .slice(0, 3);
        setNotice(sortedNotices);
      } catch (err) {
        console.error('Failed to fetch notice data:', err);
      }
    };
    fetchFanNotice();
  }, [groupId]);

  return (
    <div className={styles.container}>
      <div className={styles.leftSection}>
        <div className={styles.storySection}>
          {story.map((story) => (
            <StoryCard
              key={story.storyId}
              storyImage={story.storyImg}
              artistImage={story.storyImg}
              artistName={artistName}
            />
          ))}
        </div>
        <div className={styles.artistPostList}>
          {artistPost.map((post) => (
            <ArtistPostCard
              key={post.postId}
              artistImage={post.image}
              artistName={post.artistName}
              postDateTime={post.createdAt}
              postContent={post.content}
              likes={post.likeCount}
              comments={post.commentCount}
              postImage={post.image}
            />
          ))}
          
        </div>
      </div>
      <div className={styles.rightSection}>
        <FanLetterCard artistName={artistName || "Artist Name"} />
        <MembershipCard artistName={artistName || "Artist Name"} />
        <ProfileCard profileImage={`http://localhost:80${user.profileImage}`} nickname={user.nickname} followerCount={16545} />
        <div className={styles.noticeSection}>
            <h3 className={styles.noticeTitle}>Notice</h3>
            <ul className={styles.noticeList}>
              {notice.map((note) => (
                <li key={note.id}>[공지] {note.title}</li>
              ))}
            </ul>
          </div>
      </div>
    </div>
  );
};

export default ArtistTabContent;
