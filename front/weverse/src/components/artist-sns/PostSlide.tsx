'use client';

import React from 'react';
import styles from './PostSlide.module.css';

interface PostSlideProps {
  image: string;
  artistName: string;
  content: string;
}

const PostSlide: React.FC<PostSlideProps> = ({ image, artistName, content }) => {
  return (
    <div className={styles.slideContainer}>
      <div className={styles.imageWrapper}>
        <img src={`http://localhost:80${image}`} alt={artistName} className={styles.artistImage} />
      </div>
      <div className={styles.content}>
        <h3 className={styles.artistName}>{artistName}</h3>
        <p className={styles.postContent}>{content}</p>
      </div>
    </div>
  );
};

export default PostSlide;
