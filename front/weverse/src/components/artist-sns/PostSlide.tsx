'use client';

import React from 'react';
import styles from './PostSlide.module.css';

interface PostSlideProps {
  artistImage: string;
  artistName: string;
  postContent: string;
}

const PostSlide: React.FC<PostSlideProps> = ({ artistImage, artistName, postContent }) => {
  return (
    <div className={styles.slideContainer}>
      <div className={styles.imageWrapper}>
        <img alt={artistName} className={styles.artistImage} />
      </div>
      <div className={styles.content}>
        <h3 className={styles.artistName}>{artistName}</h3>
        <p className={styles.postContent}>{postContent}</p>
      </div>
    </div>
  );
};

export default PostSlide;
