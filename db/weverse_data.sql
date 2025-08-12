--
select * from `group`;
-- banner 테이블 더미 데이터
INSERT INTO `group` (group_name, group_logo, group_profile_image)
VALUES ('10CM' , 
		'/images/10CM/10CM_profile/10CM_logo.png',
		'images/10CM/10CM_profile/10CM.png'
		), 
        ('BABYMONSTER',
		 '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_logo.png',
		 'images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_group.png'),
		('BLACKPINK',
		 '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_logo.png',
		 'images/BLACKPINK/BLACKPINK_profile/BLACKPINK_group.png'),

		('LESSERAFIM',
		 '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_logo.png',
		 'images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_group.png'),

		('NCT_WISH',
		 '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_logo.png',
		 'images/NCT_WISH/NCT_WISH_profile/NCT_WISH_group.png'),

		('NewJeans',
		 '/images/NewJeans/NewJeans_profile/NewJeans_logo.png',
		 'images/NewJeans/NewJeans_profile/NewJeans_group.png'),

		('RIIZE',
		 '/images/RIIZE/RIIZE_profile/RIIZE_logo.png',
		 'images/RIIZE/RIIZE_profile/RIIZE_group.png'),

		('STAYC',
		 '/images/STAYC/STAYC_profile/STAYC_logo.png',
		 'images/STAYC/STAYC_profile/STAYC_group.png'),

		('TWS',
		 '/images/TWS/TWS_profile/TWS_logo.png',
		 'images/TWS/TWS_profile/TWS_group.png')
		ON DUPLICATE KEY UPDATE
		  group_logo = VALUES(group_logo),
		  group_profile_image = VALUES(group_profile_image);
    

-- 예: 그룹 3(Blackpink)에 아티스트가 없을 때
select * from ARTIST;
-- 10CM (group_id=1)
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(1, 'Kwon Jung-yeol', '권정열', '10cm@weverse.io', 'pass123', '/images/10CM/10CM_profile/10CM_Kwon.png', 'https://www.instagram.com/10cm_official_kr/', '1983-01-12', NULL, NULL);

-- BABYMONSTER (group_id=2)
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(2, 'Ruka',      '루카',      'ruka@weverse.io',      'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_ruka.png',     'https://www.instagram.com/babymonster_ygofficial/', '2002-03-20', NULL, NULL),
(2, 'Pharita',   '파리타',     'pharita@weverse.io',   'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_pharita.png',  'https://www.instagram.com/babymonster_ygofficial/', '2005-08-26', NULL, NULL),
(2, 'Asa',       '아사',       'asa@weverse.io',       'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_asa.png',      'https://www.instagram.com/babymonster_ygofficial/', '2006-04-17', NULL, NULL),
(2, 'Ahyeon',    '아현',       'ahyeon@weverse.io',    'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_ahyeon.png',   'https://www.instagram.com/babymonster_ygofficial/', '2007-04-11', NULL, NULL),
(2, 'Rami',      '라미',       'rami@weverse.io',      'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_rami.png',     'https://www.instagram.com/babymonster_ygofficial/', '2007-10-17', NULL, NULL),
(2, 'Rora',      '로라',       'rora@weverse.io',      'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_rora.png',     'https://www.instagram.com/babymonster_ygofficial/', '2008-08-14', NULL, NULL),
(2, 'Chiquita',  '치키타',     'chiquita@weverse.io',  'pass123', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_chiquita.png', 'https://www.instagram.com/babymonster_ygofficial/', '2009-02-17', NULL, NULL);

-- BLACKPINK (group_id=3)
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(3, 'Kim Jisoo',  '지수',   'jisoo@weverse.io',  'pass123', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_Jisoo.png',  'https://www.instagram.com/sooyaaa__/', '1995-01-03', NULL, NULL),
(3, 'Jennie Kim',  '제니',   'jennie@weverse.io', 'pass123', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_Jennie.png', 'https://www.instagram.com/jennierubyjane/', '1996-01-16', NULL, NULL),
(3, 'Park Chaeyoung','로제', 'rose@weverse.io',   'pass123', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_Rose.png',   'https://www.instagram.com/roses_are_rosie/', '1997-02-11', NULL, NULL),
(3, 'Lalisa Manobal','리사', 'lisa@weverse.io',   'pass123', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_Lisa.png',   'https://www.instagram.com/lalalalisa_m/', '1997-03-27', NULL, NULL);

-- LESSERAFIM (group_id=4) – 생년월일은 빈칸(YYYY-MM-DD) 또는 NULL로 채우세요
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(4, 'Miyawaki Sakura',  '사쿠라',        'sakura@weverse.io',  'pass123', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_Sakura.png',  'https://www.instagram.com/39saku_chan/', '1998-03-19', NULL, NULL),
(4, 'Kim Chaewon',      '채원',          'chaewon@weverse.io', 'pass123', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_KimChaewon.png', 'https://www.instagram.com/_chaechae_1/', '2000-08-01', NULL, NULL),
(4, 'Huh Yunjin',       '허윤진',         'yunjin@weverse.io',  'pass123', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_HuhYunjin.png',  'https://www.instagram.com/le_sserafim/', '2001-10-08', NULL, NULL),
(4, 'Nakamura Kazuha',  '카즈하',         'kazuha@weverse.io',  'pass123', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_Kazuha.png',     'https://www.instagram.com/le_sserafim/', '2003-08-09', NULL, NULL),
(4, 'Hong Eunchae',     '은채',           'eunchae@weverse.io', 'pass123', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_HongEunchae.png', 'https://www.instagram.com/hhh.e_c.v/', '2006-11-10', NULL, NULL);

-- NCT_WISH (group_id=5) – 생년월일 빈칸
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(5, 'Sion',   '시온',    'sion@weverse.io',    'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Sion.png',    'https://www.instagram.com/nctnewteam/', '2002-5-11', NULL, NULL),
(5, 'Riku',   '리쿠',    'riku@weverse.io',    'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Riku.png',    'https://www.instagram.com/nctnewteam/', '2003-06-28', NULL, NULL),
(5, 'Yushi',  '유시',    'yushi@weverse.io',   'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Yushi.png',   'https://www.instagram.com/nctnewteam/', '2004-04-05', NULL, NULL),
(5, 'Jaehee', '재희',    'jaehee@weverse.io',  'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Jaehee.png',  'https://www.instagram.com/nctnewteam/', '2005-06-21', NULL, NULL),
(5, 'Ryo',    '료',      'ryo@weverse.io',     'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Ryo.png',     'https://www.instagram.com/nctnewteam/', '2006-08-04', NULL, NULL),
(5, 'Sakuya', '사쿠야',  'sakuya@weverse.io',  'pass123', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_Sakuya.png',  'https://www.instagram.com/nctnewteam/', '2007-11-18', NULL, NULL);

-- NewJeans (group_id=6) – 생년월일 빈칸
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(6, 'Minji',   '민지',  'minji@weverse.io',   'pass123', '/images/NewJeans/NewJeans_profile/NewJeans_Minji.png',   'https://www.instagram.com/accounts/login/?next=%2Fnewjeans_official%2F&source=omni_redirect', '2004-05-07', NULL, NULL),
(6, 'Hanni',   '하니',  'hanni@weverse.io',   'pass123', '/images/NewJeans/NewJeans_profile/NewJeans_Hanny.png',   'https://www.instagram.com/accounts/login/?next=%2Fnewjeans_official%2F&source=omni_redirect', '2004-10-06', NULL, NULL),
(6, 'Daniel',  '다니엘','daniel@weverse.io',  'pass123', '/images/NewJeans/NewJeans_profile/NewJeans_Daniel.png',  'https://www.instagram.com/accounts/login/?next=%2Fnewjeans_official%2F&source=omni_redirect', '2005-04-11', NULL, NULL),
(6, 'Haerin',  '해린',  'haerin@weverse.io',  'pass123', '/images/NewJeans/NewJeans_profile/NewJeans_Haerin.png',  'https://www.instagram.com/accounts/login/?next=%2Fnewjeans_official%2F&source=omni_redirect', '2006-05-15', NULL, NULL),
(6, 'Hyein',   '혜인',  'hyein@weverse.io',   'pass123', '/images/NewJeans/NewJeans_profile/NewJeans_Hyein.png',   'https://www.instagram.com/accounts/login/?next=%2Fnewjeans_official%2F&source=omni_redirect', '2008-04-21', NULL, NULL);

-- RIIZE (group_id=7) – 생년월일 빈칸
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(7, 'Shotaro','쇼타로','shotaro@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Shotaro.png','https://www.instagram.com/riize_official/','2000-11-25',NULL,NULL),
(7, 'Eunseok','은석','eunseok@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Eunseok.png','https://www.instagram.com/riize_official/','2001-03-19',NULL,NULL),
(7, 'Sungchan','성찬','sungchan@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Sungchan.png','https://www.instagram.com/riize_official/','2001-09-13',NULL,NULL),
(7, 'Wonbin','원빈','wonbin@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Wonbin.png','https://www.instagram.com/riize_official/','2002-03-02',NULL,NULL),
(7, 'Sohee','소희','sohee@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Sohee.png','https://www.instagram.com/riize_official/','2003-11-21',NULL,NULL),
(7, 'Anton','안톤','anton@weverse.io','pass123','/images/RIIZE/RIIZE_profile/RIIZE_Anton.png','https://www.instagram.com/riize_official/','2004-03-21',NULL,NULL);

-- STAYC (group_id=8) – 생년월일 빈칸
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(8, 'Sumin','수민','sumin@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Sumin.png','https://www.instagram.com/stayc_highup/','2001-03-13',NULL,NULL),
(8, 'Sieun','시은','sieun@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Sieun.png','https://www.instagram.com/stayc_highup/','2001-08-01',NULL,NULL),
(8, 'Isa','아이사','isa@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Isa.png','https://www.instagram.com/stayc_highup/','2002-01-23',NULL,NULL),
(8, 'Seeun','세은','seeun@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Seeun.png','https://www.instagram.com/stayc_highup/','2003-06-14',NULL,NULL),
(8, 'Yoon','윤','yoon@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Yoon.png','https://www.instagram.com/stayc_highup/','2004-04-14',NULL,NULL),
(8, 'Jaei','재이','jaei@weverse.io','pass123','/images/STAYC/STAYC_profile/STAYC_Jaei.png','https://www.instagram.com/stayc_highup/','2004-12-09',NULL,NULL);

-- TWS (group_id=9) – 생년월일 빈칸
INSERT INTO artist
(group_id, name, stage_name, email, password, profile_image, sns_url, birthday, status_message, dm_nickname)
VALUES
(9, 'Shinyu',  '신유',  'shinyu@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Shinyu.png',  'https://www.instagram.com/tws_pledis/','2003-11-07',NULL,NULL),
(9, 'Dohoon',  '도훈',  'dohoon@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Dohoon.png',  'https://www.instagram.com/tws_pledis/','2005-01-30',NULL,NULL),
(9, 'Youngjae','영재', 'youngjae@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Youngjae.png','https://www.instagram.com/tws_pledis/','2005-05-31',NULL,NULL),
(9, 'Hanjin',  '한진',  'hanjin@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Hanjin.png',  'https://www.instagram.com/tws_pledis/','2006-01-05',NULL,NULL),
(9, 'Jihoon',  '지훈',  'jihoon@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Jihoon.png',  'https://www.instagram.com/tws_pledis/','2006-03-28',NULL,NULL),
(9, 'Gyeongmin','경민','gyeongmin@weverse.io','pass123','/images/TWS/TWS_profile/TWS_Gyeongmin.png','https://www.instagram.com/tws_pledis/','2007-10-02',NULL,NULL);

ALTER TABLE post CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE live_chat_message CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE media_chat_message CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SELECT * FROM post;

desc post;

INSERT INTO post (like_count, artist_id, created_at, image, content, author_type)
VALUES
(0, 2, NOW(),  '/images/NewJeans/NewJeans_media_01.jpg', 'NewJeans의 첫 번째 미디어!', 'artist'),
(0, 3, NOW(),  '/images/NewJeans/NewJeans_media_02.jpg', '오늘의 특별한 순간', 'artist'),
(0, 4, NOW(),  '/images/NewJeans/NewJeans_media_03.jpg', '다니엘의 근황 공개', 'artist'),
(0, 5, NOW(),  '/images/NewJeans/NewJeans_media_04.jpg', '해린의 셀카📸', 'artist');

SELECT * FROM automated_message;
INSERT INTO automated_message (artist_id, message_type, content)
VALUES
(2, 'WELCOME', '민지의 팬이 되어주셔서 감사합니다!'),
(3, 'WELCOME', '하니의 팬이 되어주셔서 감사합니다!'),
(4, 'WELCOME', '다니엘의 팬이 되어주셔서 감사합니다!'),
(5, 'WELCOME', '해린의 팬이 되어주셔서 감사합니다!'),
(6, 'WELCOME', '혜인의 팬이 되어주셔서 감사합니다!');



SELECT * FROM board_category;
INSERT INTO board_category (board_category_id, title) VALUES
(1, 'Fan'),
(2, 'Artist'),
(3, 'Media'),
(4, 'Live'),
(5, 'Shop')
ON DUPLICATE KEY UPDATE title = VALUES(title);

desc board;
SELECT * FROM board;
-- 10CM (group_id=1)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, '10CM 공지 #1', '공지 내용입니다.', NOW(), 1),
(2, '10CM 이벤트 #1', '이벤트 내용입니다.', NOW(), 1);

-- BABYMONSTER (group_id=2)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'BABYMONSTER 공지 #1', '공지 내용입니다.', NOW(), 2),
(2, 'BABYMONSTER 이벤트 #1', '이벤트 내용입니다.', NOW(), 2);

-- BLACKPINK (group_id=3)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'BLACKPINK 공지 #1', '공지 내용입니다.',NOW(), 3),
(2, 'BLACKPINK 이벤트 #1', '이벤트 내용입니다.', NOW(), 3);

-- LE SSERAFIM (group_id=4)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'LE SSERAFIM 공지 #1', '공지 내용입니다.', NOW(), 4),
(2, 'LE SSERAFIM 이벤트 #1', '이벤트 내용입니다.', NOW(), 4);

-- NCT_WISH (group_id=5)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'NCT WISH 공지 #1', '공지 내용입니다.',NOW(), 5),
(2,  'NCT WISH 이벤트 #1', '이벤트 내용입니다.', NOW(), 5);

-- NewJeans (group_id=6)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1,  'NewJeans 공지 #1', '공지 내용입니다.', NOW(), 6),
(2, 'NewJeans 이벤트 #1', '이벤트 내용입니다.', NOW(), 6);

-- RIIZE (group_id=7)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1,  'RIIZE 공지 #1', '공지 내용입니다.', NOW(), 7),
(2, 'RIIZE 이벤트 #1', '이벤트 내용입니다.', NOW(), 7);

-- STAYC (group_id=8)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'STAYC 공지 #1', '공지 내용입니다.', NOW(), 8),
(2, 'STAYC 이벤트 #1', '이벤트 내용입니다.',NOW(), 8);

-- TWS (group_id=9)
INSERT INTO board (board_category_id, title, content, created_at, group_id) VALUES
(1, 'TWS 공지 #1', '공지 내용입니다.', NOW(), 9),
(2, 'TWS 이벤트 #1', '이벤트 내용입니다.', NOW(), 9);

-- 
SELECT * FROM streaming;
desc streaming;
-- 10CM
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(1, 1,
 'https://youtu.be/Ry1RrIVyl1M?si=ScfxLagTWrLbFw9a', '10CM LIVE #1', '/images/10CM/10CM_profile/10CM_live_01.png', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 1),
(1, 1,
 'https://www.youtube.com/watch?v=qb17TexmrWU', '10CM TALK',    '/images/10CM/10CM_profile/10CM_live_02.png', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 1),
 (1, 1,
 'https://www.youtube.com/watch?v=cikfw1PCNvU&list=RDcikfw1PCNvU&start_radio=1', '10CM LIVE #2', '/images/10CM/10CM_profile/10CM_live_03.png', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 1),
(1, 1,
 'https://www.youtube.com/watch?v=VQZXXciZb_c&list=RDVQZXXciZb_c&start_radio=1', '10CM TALK',    '/images/10CM/10CM_profile/10CM_live_04.png', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 1);

-- BABYMONSTER
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(2, 2,
 'https://www.youtube.com/watch?v=Zp-Jhuhq0bQ', 'BABYMONSTER LIVE #1', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_live_01.png', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 2),
(3, 3,
 'https://www.youtube.com/watch?v=8W5TWR74Mms', 'BABYMONSTER TALK', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_live_02.png', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 2);

-- BLACKPINK
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(10, 10,
 'https://www.youtube.com/watch?v=CgCVZdcKcqY', 'BLACKPINK LIVE #1',  '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_01.png', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 3),
(10, 10,
 'https://www.youtube.com/watch?v=gQlMMD8auMs', 'BLACKPINK TALK',  '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_02.png', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 3),
 (11, 11,
 'https://www.youtube.com/watch?v=skIfzl96Gc0', 'BLACKPINK TALK',    '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_03.png', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 3),
 (11, 11,
 'https://www.youtube.com/watch?v=7WyHtSlvHD4','BLACKPINK TALK',    '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_04.png', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 3),
 (12, 12,
 'https://www.youtube.com/watch?v=32si5cfrCNc',  'BLACKPINK TALK',   '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_05.png', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 3),
 (9, 9,
 'https://www.youtube.com/watch?v=metZ_f8aqC0', 'BLACKPINK TALK',    '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_live_06.png', NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), 3);

-- LE SSERAFIM
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(13, 13,
 'https://www.youtube.com/watch?v=n6B5gQXlB-0', 'LE SSERAFIM LIVE #1',  '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_01.png', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 4),
(13, 13,
 'https://www.youtube.com/watch?v=vPn_c61mW9s', 'LE SSERAFIM TALK',    '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_02.png', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 4),
 (14, 14,
 'https://www.youtube.com/watch?v=8S9rHj7gTsA', 'LE SSERAFIM LIVE #1',  '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_03.png', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 4),
(17, 17,
 'https://www.youtube.com/watch?v=8S9rHj7gTsA', 'LE SSERAFIM TALK',    '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_04.png', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 4),
 (15, 15,
 'https://www.youtube.com/watch?v=h9PN_Hb8vSE', 'LE SSERAFIM LIVE #1',  '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_05.png', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 4),
(16, 16,
 'https://www.youtube.com/watch?v=pXhjs-35jSI', 'LE SSERAFIM TALK',    '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_live_06.png', NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), 4);

-- NCT_WISH
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(18, 18,
 'https://www.youtube.com/watch?v=abKkze8_qJw', 'NCT WISH LIVE #1',  '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_live_01.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 5);

-- NewJeans
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(24, 24,
 'https://youtu.be/wU2siJ2c5TA?si=gTxbYSj823mjY8m8','NewJeans LIVE #1',  '/images/NewJeans/NewJeans_profile/NewJeans_live_01.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 6),
(25, 25,
 'https://youtu.be/aJqS7BY5XE4?si=-C5NucKka4jJZKjZ', 'NewJeans TALK',  '/images/NewJeans/NewJeans_profile/NewJeans_live_02.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 6);

-- RIIZE
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(29, 29,
 'https://youtu.be/jps98Q0A16w?si=InAzzorKI3_RTEMN', 'RIIZE LIVE #1',  '/images/RIIZE/RIIZE_profile/RIIZE_live_01.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 7),
(30, 30,
 'https://youtu.be/IuunSdxrjAs?si=3l2loe_Nl8ffTOYR',  'RIIZE TALK',   '/images/RIIZE/RIIZE_profile/RIIZE_live_02.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 7),
 (31, 31,
 'https://youtu.be/0Ej4FvdmaTk?si=Qp-pZSpIk6a6Kgg5', 'RIIZE LIVE #1',  '/images/RIIZE/RIIZE_profile/RIIZE_live_03.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 7),
(32, 32,
 'https://youtu.be/V-Q4PTbA1Dg?si=1dCLpD9pC2ItytUH', 'RIIZE TALK',    '/images/RIIZE/RIIZE_profile/RIIZE_live_04.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 7),
 (33, 33,
 'https://youtu.be/edsSrUzoJp4?si=73E5HtwnHr0yEdc9', 'RIIZE LIVE #1',  '/images/RIIZE/RIIZE_profile/RIIZE_live_05.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 7),
(34, 34,
 'https://youtu.be/zXQzojCTV30?si=u11TzxCEuQktJH1u', 'RIIZE TALK',    '/images/RIIZE/RIIZE_profile/RIIZE_live_06.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), 7);

-- STAYC
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(35, 35,
 'https://youtu.be/W8ml4ryPwic?si=9Q7-DupbYV6F4GIm', 'STAYC LIVE #1',  '/images/STAYC/STAYC_profile/STAYC_live_01.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 8),
(36, 36,
 'https://youtu.be/nfUHdN_gwTY?si=fcqcfTyb7SAvyM0H', 'STAYC TALK',   '/images/STAYC/STAYC_profile/STAYC_live_02.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 8),
 (37, 37,
  'https://youtu.be/xRNy3N3wKMg?si=wjLs6G34qj2FsG9t', 'STAYC LIVE #1', '/images/STAYC/STAYC_profile/STAYC_live_03.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 8);

-- TWS
INSERT INTO streaming (owner_artist_id, streamer_artist_id, video_id, title, thumbnail, created_at, scheduled_at, group_id) VALUES
(41, 41,
 'https://youtu.be/aRnUkEYIOJs?si=ptjfFKoOxN0_MOJf', 'TWS LIVE #1',  '/images/TWS/TWS_profile/TWS_live_01.png', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 9),
(42, 42,
 'https://youtu.be/EsvfyjclR9M?si=2z7TqV9nFmDuoQ9m', 'TWS TALK',    '/images/TWS/TWS_profile/TWS_live_02.png', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 9),
(43, 43,
 'https://youtu.be/XSgrrarBHoo?si=U-IQMw9cZwx0B7OQ', 'TWS LIVE #1',  '/images/TWS/TWS_profile/TWS_live_03.png', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 9),
(44, 44,
 'https://youtu.be/s8Kn9sNZS7U?si=PBqQxObuRElEg-ua', 'TWS TALK',  '/images/TWS/TWS_profile/TWS_live_04.png', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 9);

insert into media_category (title) values ('예능'), ('비하인드'), ('레코딩');


INSERT INTO uploaded_video (
    price, artist_id, category_idx, group_id, scheduled_at, uploaded_at,
    video_id, description, thumbnail, title, video_url
) VALUES
-- 10CM (group_id=1, artist_id=1)
(8000, 1, 1, 1, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, '10CM media #1', '/images/10CM/10CM_profile/10CM_media_01.png', '10CM media #1', 'https://www.youtube.com/watch?v=vP6ClGmH93A&list=RDvP6ClGmH93A&start_radio=1'),
(5000, 1, 2, 1, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, '10CM TALK', '/images/10CM/10CM_profile/10CM_media_02.png', '10CM TALK', 'https://www.youtube.com/watch?v=5e7qMOZvgwg&list=RD5e7qMOZvgwg&start_radio=1'),
(9000, 1, 3, 1, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, '10CM media #2', '/images/10CM/10CM_profile/10CM_media_03.png', '10CM media #2', 'https://www.youtube.com/watch?v=Fy8acu4JX6o&list=RDFy8acu4JX6o&start_radio=1'),
(10000, 1, 1, 1, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, '10CM TALK', '/images/10CM/10CM_profile/10CM_media_04.png', '10CM TALK', 'https://www.youtube.com/watch?v=GfOW9gtRkzI&list=RDGfOW9gtRkzI&start_radio=1'),

-- BABYMONSTER (group_id=2, artist_id=2)
(7000, 2, 1, 2, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'BABYMONSTER media #1', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_01.png', 'BABYMONSTER media #1', 'https://www.youtube.com/watch?v=2wA_b6YHjqQ'),
(5000, 2, 2, 2, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'BABYMONSTER TALK', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_02.png', 'BABYMONSTER TALK', 'https://www.youtube.com/watch?v=TFujE5lUh9k'),
(9000, 2, 3, 2, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'BABYMONSTER media #2', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_03.png', 'BABYMONSTER media #2', 'https://www.youtube.com/watch?v=olDWm2veCrM'),
(8000, 2, 1, 2, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'BABYMONSTER TALK', '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_04.png', 'BABYMONSTER TALK', 'https://www.youtube.com/watch?v=TOxKCW5E_Bg'),

-- BLACKPINK (group_id=3, artist_id=10)
(5000, 10, 1, 3, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'BLACKPINK media #1', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_01.png', 'BLACKPINK media #1', 'https://www.youtube.com/watch?v=5hwepTxNKtE'),
(10000, 10, 2, 3, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'BLACKPINK TALK', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_02.png', 'BLACKPINK TALK', 'https://www.youtube.com/watch?v=1kYrp_Bs8DU'),
(7000, 10, 3, 3, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'BLACKPINK media #2', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_03.png', 'BLACKPINK media #2', 'https://www.youtube.com/watch?v=o4GHoqGtRkg'),
(9000, 10, 1, 3, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'BLACKPINK TALK', '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_04.png', 'BLACKPINK TALK', 'https://www.youtube.com/watch?v=bqzDuRz_P7g'),

-- LE SSERAFIM (group_id=4, artist_id=13)
(6000, 13, 1, 4, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'LE SSERAFIM media #1', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_01.png', 'LE SSERAFIM media #1', 'https://www.youtube.com/watch?v=DWC5EI6vHp4'),
(8000, 13, 2, 4, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'LE SSERAFIM TALK', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_02.png', 'LE SSERAFIM TALK', 'https://www.youtube.com/watch?v=hYoByDW1ZAI'),
(7000, 13, 3, 4, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'LE SSERAFIM media #2', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_03.png', 'LE SSERAFIM media #2', 'https://www.youtube.com/watch?v=Y95ronCEAuk'),
(9000, 13, 1, 4, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'LE SSERAFIM TALK', '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_04.png', 'LE SSERAFIM TALK', 'https://www.youtube.com/watch?v=ZEUFAV9jmGk'),

-- NCT WISH (group_id=5, artist_id=18)
(5000, 18, 1, 5, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'NCT WISH media #1', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_01.jpg', 'NCT WISH media #1', 'https://www.youtube.com/watch?v=LXN52f5dZd0'),
(7000, 18, 2, 5, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'NCT WISH TALK', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_02.jpg', 'NCT WISH TALK', 'https://www.youtube.com/watch?v=RJQZLPmYTnA'),
(8000, 18, 3, 5, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'NCT WISH media #2', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_03.jpg', 'NCT WISH media #2', 'https://www.youtube.com/watch?v=d0mVtyfWc6c'),
(9000, 18, 1, 5, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'NCT WISH TALK', '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_04.jpg', 'NCT WISH TALK', 'https://www.youtube.com/watch?v=UmY0WFzUN-s'),

-- NewJeans (group_id=6, artist_id=24)
(5000, 24, 1, 6, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'NewJeans media #1', '/images/NewJeans/NewJeans_profile/NewJeans_media_01.jpg', 'NewJeans Live Concert', 'https://youtu.be/wLApx-mWc5Y?si=GMJKox7vJz_bmh6Y'),
(6000, 24, 2, 6, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'NewJeans TALK', '/images/NewJeans/NewJeans_profile/NewJeans_media_02.jpg', 'NewJeans Album Recording', 'https://youtu.be/yWvzZW_g5b0?si=F-zJKDtBEoQGriNS'),
(7000, 24, 3, 6, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'NewJeans media #2', '/images/NewJeans/NewJeans_profile/NewJeans_media_03.jpg', 'NewJeans Exclusive Interview', 'https://youtu.be/WOlVl32HaoY?si=slhAAUnD80tJQImh'),
(8000, 24, 1, 6, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'NewJeans TALK', '/images/NewJeans/NewJeans_profile/NewJeans_media_04.jpg', 'NewJeans Fan Meeting Highlights', 'https://youtu.be/gY8_MPI3p4Q?si=hacg_d5ES8eMsVPO'),

-- RIIZE (group_id=7, artist_id=29)
(9000, 29, 1, 7, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'RIIZE media #1', '/images/RIIZE/RIIZE_profile/RIIZE_media_01.jpg', 'RIIZE media #1', 'https://youtu.be/BN9RxaNe2lc?si=8BTizmlA4AtPXvYc'),
(8000, 29, 2, 7, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'RIIZE TALK', '/images/RIIZE/RIIZE_profile/RIIZE_media_02.jpg', 'RIIZE TALK', 'https://youtu.be/SYQMA10Unpg?si=D_eMJ0qIP28rTiLp'),
(7000, 29, 3, 7, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'RIIZE media #2', '/images/RIIZE/RIIZE_profile/RIIZE_media_03.jpg', 'RIIZE media #2', 'https://youtu.be/6uoc_SWTFCc?si=qhFaXotDY89aGjEf'),
(6000, 29, 1, 7, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'RIIZE TALK', '/images/RIIZE/RIIZE_profile/RIIZE_media_04.jpg', 'RIIZE TALK', 'https://youtu.be/5wiRctlreY8?si=zIE7gw1iCV7ixhlm'),

-- STAYC (group_id=8, artist_id=35)
(10000, 35, 1, 8, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'STAYC media #1', '/images/STAYC/STAYC_profile/STAYC_media_01.jpg', 'STAYC media #1', 'https://youtu.be/xEV1U-VAGWU?si=dufdYW4LW4THMWG2'),
(7000, 35, 2, 8, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'STAYC TALK', '/images/STAYC/STAYC_profile/STAYC_media_02.jpg', 'STAYC TALK', 'https://youtu.be/S-8KBwM8_uE?si=PlhCMGBlxJUoTWWE'),
(9000, 35, 3, 8, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'STAYC media #2', '/images/STAYC/STAYC_profile/STAYC_media_03.jpg', 'STAYC media #2', 'https://youtu.be/RfRi8k5ak4I?si=QP3UQwNI6TqU3Wm-'),
(8000, 35, 1, 8, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'STAYC TALK', '/images/STAYC/STAYC_profile/STAYC_media_04.jpg', 'STAYC TALK', 'https://youtu.be/yjQh0r8Cepk?si=SuTrSNHJHxN_xn-w'),

-- TWS (group_id=9, artist_id=41)
(5000, 41, 1, 9, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW(), NULL, 'TWS media #1', '/images/TWS/TWS_profile/TWS_media_01.png', 'TWS media #1', 'https://youtu.be/ZX4Ygv_WpeA?si=6X7ZOOMAC-HG7lQv'),
(6000, 41, 2, 9, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), NULL, 'TWS TALK', '/images/TWS/TWS_profile/TWS_media_02.png', 'TWS TALK', 'https://youtu.be/jE76V4IgFi0?si=Ss9v6B8tuGI6T8_D'),
(7000, 41, 3, 9, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NULL, 'TWS media #2', '/images/TWS/TWS_profile/TWS_media_03.png', 'TWS media #2', 'https://youtu.be/MbtppgtGFb0?si=WI9z0QAlAJwz-B-N'),
(8000, 41, 1, 9, DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), NULL, 'TWS TALK', '/images/TWS/TWS_profile/TWS_media_04.png', 'TWS TALK', 'https://youtu.be/rsrXt2pY7c8?si=GUpBLu4BUL0f6LfS');



-- 2) 배너(B 컬럼 순서로 명시) : artist_id, banner_id, group_id, banner_image, main_title, sub_title
select * from banner;
select * from artist;
-- 10 CM
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES 
(2, 1, 4, '/images/10CM/10CM_profile/10CM_media_01.png', '10CM 미디어 1', '공식 콘텐츠 업데이트'),
(3, 2, 4, '/images/10CM/10CM_profile/10CM_media_02.png', '10CM 미디어 2', '스페셜 영상 공개'),
(4, 3, 4, '/images/10CM/10CM_profile/10CM_media_03.png', '10CM 미디어 3', '팬 여러분을 위한 메시지'),
(5, 4, 4, '/images/10CM/10CM_profile/10CM_media_04.png', '10CM 미디어 4', '무대 비하인드 컷'),
(6, 5, 4, '/images/10CM/10CM_profile/10CM_media_05.png', '10CM 굿즈 1', '신규 상품 출시');
-- BABYMONSTER
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2, 6, 2, '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_01.png', 'BABYMONSTER 미디어 1', '공식 콘텐츠 업데이트'),
(3, 7, 2, '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_02.png', 'BABYMONSTER 미디어 2', '스페셜 영상 공개'),
(4, 8, 2, '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_03.png', 'BABYMONSTER 미디어 3', '팬 여러분을 위한 메시지'),
(5, 9, 2, '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_04.png', 'BABYMONSTER 미디어 4', '무대 비하인드 컷'),
(6, 10, 2, '/images/BABYMONSTER/BABYMONSTER_profile/BABYMONSTER_media_05.png', 'BABYMONSTER 굿즈 1', '신규 상품 출시');
-- BLACKPINK
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  11, 3, '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_01.png', 'BLACKPINK 미디어 1', '공식 콘텐츠 업데이트'),
(3,  12, 3, '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_02.png', 'BLACKPINK 미디어 2', '스페셜 영상 공개'),
(4,  13, 3, '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_03.png', 'BLACKPINK 미디어 3', '팬 여러분을 위한 메시지'),
(5,  14, 3, '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_04.png', 'BLACKPINK 미디어 4', '무대 비하인드 컷'),
(6,  15, 3, '/images/BLACKPINK/BLACKPINK_profile/BLACKPINK_media_05.png', 'BLACKPINK 굿즈 1', '신규 상품 출시');
-- LESSERAFIM (group_id=4) banner_id 시작: 16
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  16, 4, '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_01.png', 'LESSERAFIM 미디어 1', '공식 콘텐츠 업데이트'),
(3,  17, 4, '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_02.png', 'LESSERAFIM 미디어 2', '스페셜 영상 공개'),
(4,  18, 4, '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_03.png', 'LESSERAFIM 미디어 3', '팬 여러분을 위한 메시지'),
(5,  19, 4, '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_04.png', 'LESSERAFIM 미디어 4', '무대 비하인드 컷'),
(6,  20, 4, '/images/LESSERAFIM/LESSERAFIM_profile/LESSERAFIM_media_05.png', 'LESSERAFIM 굿즈 1', '신규 상품 출시');

-- NCT_WISH (group_id=5) banner_id 시작: 21
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  21, 5, '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_01.png', 'NCT_WISH 미디어 1', '공식 콘텐츠 업데이트'),
(3,  22, 5, '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_02.png', 'NCT_WISH 미디어 2', '스페셜 영상 공개'),
(4,  23, 5, '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_03.png', 'NCT_WISH 미디어 3', '팬 여러분을 위한 메시지'),
(5,  24, 5, '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_04.png', 'NCT_WISH 미디어 4', '무대 비하인드 컷'),
(6,  25, 5, '/images/NCT_WISH/NCT_WISH_profile/NCT_WISH_media_05.png', 'NCT_WISH 굿즈 1', '신규 상품 출시');

-- NewJeans (group_id=6) banner_id 시작: 26
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  26, 6, '/images/NewJeans/NewJeans_profile/NewJeans_media_01.png', 'NewJeans 미디어 1', '공식 콘텐츠 업데이트'),
(3,  27, 6, '/images/NewJeans/NewJeans_profile/NewJeans_media_02.png', 'NewJeans 미디어 2', '스페셜 영상 공개'),
(4,  28, 6, '/images/NewJeans/NewJeans_profile/NewJeans_media_03.png', 'NewJeans 미디어 3', '팬 여러분을 위한 메시지'),
(5,  29, 6, '/images/NewJeans/NewJeans_profile/NewJeans_media_04.png', 'NewJeans 미디어 4', '무대 비하인드 컷'),
(6,  30, 6, '/images/NewJeans/NewJeans_profile/NewJeans_media_05.png', 'NewJeans 굿즈 1', '신규 상품 출시');

-- RIIZE (group_id=7) banner_id 시작: 31
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  31, 7, '/images/RIIZE/RIIZE_profile/RIIZE_media_01.png', 'RIIZE 미디어 1', '공식 콘텐츠 업데이트'),
(3,  32, 7, '/images/RIIZE/RIIZE_profile/RIIZE_media_02.png', 'RIIZE 미디어 2', '스페셜 영상 공개'),
(4,  33, 7, '/images/RIIZE/RIIZE_profile/RIIZE_media_03.png', 'RIIZE 미디어 3', '팬 여러분을 위한 메시지'),
(5,  34, 7, '/images/RIIZE/RIIZE_profile/RIIZE_media_04.png', 'RIIZE 미디어 4', '무대 비하인드 컷'),
(6,  35, 7, '/images/RIIZE/RIIZE_profile/RIIZE_media_05.png', 'RIIZE 굿즈 1', '신규 상품 출시');

-- STAYC (group_id=8) banner_id 시작: 36
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  36, 8, '/images/STAYC/STAYC_profile/STAYC_media_01.png', 'STAYC 미디어 1', '공식 콘텐츠 업데이트'),
(3,  37, 8, '/images/STAYC/STAYC_profile/STAYC_media_02.png', 'STAYC 미디어 2', '스페셜 영상 공개'),
(4,  38, 8, '/images/STAYC/STAYC_profile/STAYC_media_03.png', 'STAYC 미디어 3', '팬 여러분을 위한 메시지'),
(5,  39, 8, '/images/STAYC/STAYC_profile/STAYC_media_04.png', 'STAYC 미디어 4', '무대 비하인드 컷'),
(6,  40, 8, '/images/STAYC/STAYC_profile/STAYC_media_05.png', 'STAYC 굿즈 1', '신규 상품 출시');

-- TWS (group_id=9) banner_id 시작: 41
INSERT INTO banner (artist_id, banner_id, group_id, banner_image, main_title, sub_title)
VALUES
(2,  41, 9, '/images/TWS/TWS_profile/TWS_media_01.png', 'TWS 미디어 1', '공식 콘텐츠 업데이트'),
(3,  42, 9, '/images/TWS/TWS_profile/TWS_media_02.png', 'TWS 미디어 2', '스페셜 영상 공개'),
(4,  43, 9, '/images/TWS/TWS_profile/TWS_media_03.png', 'TWS 미디어 3', '팬 여러분을 위한 메시지'),
(5,  44, 9, '/images/TWS/TWS_profile/TWS_media_04.png', 'TWS 미디어 4', '무대 비하인드 컷'),
(6,  45, 9, '/images/TWS/TWS_profile/TWS_media_05.png', 'TWS 굿즈 1', '신규 상품 출시');


-- file: seed_products.sql
-- MySQL stored procedure to auto-generate products/options/images by group NAME
-- Idempotent inserts (safe to run multiple times)
-- Requires: existing rows in `group` with group_name(s) you will pass

DROP PROCEDURE IF EXISTS seed_products;
DELIMITER $$

CREATE PROCEDURE seed_products(
  IN album_cnt INT,                -- number of '앨범' products to create
  IN md_cnt INT,                   -- number of 'MD' products to create
  IN imgs_per_album INT,           -- images per album product
  IN imgs_per_md INT,              -- images per MD product
  IN start_img INT,                -- starting image index (1 => shop_01)
  IN album_group_name VARCHAR(100),-- group name for album products
  IN md_group_name VARCHAR(100),   -- group name for MD products (NULL/'' => same as album group)
  IN base_path VARCHAR(255),       -- e.g., '/images/.../shop_' or 'https://cdn.../shop_'
  IN ext VARCHAR(10)               -- e.g., '.jpg' or '.png'
)
BEGIN
  DECLARE cat_album_id BIGINT;
  DECLARE cat_md_id BIGINT;
  DECLARE g_album BIGINT;
  DECLARE g_md BIGINT;
  DECLARE i INT;
  DECLARE j INT;
  DECLARE img_cursor INT;
  DECLARE pid BIGINT;
  DECLARE main_url VARCHAR(512);

  -- 1) Ensure categories exist
  INSERT INTO product_category (category_name)
  SELECT '앨범' WHERE NOT EXISTS (SELECT 1 FROM product_category WHERE category_name='앨범');
  INSERT INTO product_category (category_name)
  SELECT 'MD'   WHERE NOT EXISTS (SELECT 1 FROM product_category WHERE category_name='MD');

  SELECT category_id INTO cat_album_id FROM product_category WHERE category_name='앨범' LIMIT 1;
  SELECT category_id INTO cat_md_id    FROM product_category WHERE category_name='MD'   LIMIT 1;

  -- 2) Resolve group_id by group_name
  SELECT group_id INTO g_album
  FROM `group`
  WHERE group_name = album_group_name
  ORDER BY group_id
  LIMIT 1;
  IF g_album IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='album_group_name을 찾을 수 없습니다.';
  END IF;

  IF md_group_name IS NULL OR md_group_name = '' THEN
    SET g_md = g_album;
  ELSE
    SELECT group_id INTO g_md
    FROM `group`
    WHERE group_name = md_group_name
    ORDER BY group_id
    LIMIT 1;
    IF g_md IS NULL THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='md_group_name을 찾을 수 없습니다.';
    END IF;
  END IF;

  -- 3) Image cursor init
  SET img_cursor = start_img;

  -- ===================
  -- 4) Albums loop
  -- ===================
  SET i = 1;
  WHILE i <= album_cnt DO
    SET @pname = CONCAT('앨범 #', LPAD(i,3,'0'));

    -- MAIN image URL for this product (first image in the loop)
    SET main_url = CONCAT(base_path, LPAD(img_cursor,2,'0'), ext);

    -- Insert product if not exists (per group)
    INSERT INTO product (price, stock_qty, artist_id, category_id, group_id, product_name, description, thumbnail)
    SELECT 18900, 100, NULL, cat_album_id, g_album, @pname, '자동시드', main_url
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_name=@pname AND group_id=g_album);

    -- Resolve product_id within this group
    SELECT product_id INTO pid FROM product 
    WHERE product_name=@pname AND group_id=g_album
    LIMIT 1;

    -- Backfill thumbnail if missing (idempotent)
    UPDATE product SET thumbnail = main_url
    WHERE product_id = pid AND (thumbnail IS NULL OR thumbnail='');

    -- Ensure default option
    INSERT INTO product_option (additional_price, stock_qty, product_id, option_name)
    SELECT 0, 999, pid, 'DEFAULT'
    WHERE NOT EXISTS (
      SELECT 1 FROM product_option WHERE product_id=pid AND option_name='DEFAULT'
    );

    -- Images
    SET j = 1;
    WHILE j <= imgs_per_album DO
      INSERT INTO product_image (sort_order, product_id, image_type, image_url)
      SELECT j, pid, IF(j=1,'MAIN','SUB'),
             CONCAT(base_path, LPAD(img_cursor,2,'0'), ext)
      WHERE NOT EXISTS (
        SELECT 1 FROM product_image WHERE product_id=pid AND sort_order=j
      );
      SET img_cursor = img_cursor + 1;
      SET j = j + 1;
    END WHILE;

    SET i = i + 1;
  END WHILE;

  -- ===================
  -- 5) MD loop
  -- ===================
  SET i = 1;
  WHILE i <= md_cnt DO
    SET @pname = CONCAT('후드티 #', LPAD(i,3,'0'));

    -- MAIN image URL for this product
    SET main_url = CONCAT(base_path, LPAD(img_cursor,2,'0'), ext);

    -- Insert product if not exists (per group)
    INSERT INTO product (price, stock_qty, artist_id, category_id, group_id, product_name, description, thumbnail)
    SELECT 59000, 200, NULL, cat_md_id, g_md, @pname, '자동시드', main_url
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_name=@pname AND group_id=g_md);

    -- Resolve product_id within this group
    SELECT product_id INTO pid FROM product 
    WHERE product_name=@pname AND group_id=g_md
    LIMIT 1;

    -- Backfill thumbnail if missing (idempotent)
    UPDATE product SET thumbnail = main_url
    WHERE product_id = pid AND (thumbnail IS NULL OR thumbnail='');

    -- Ensure options
    INSERT INTO product_option (additional_price, stock_qty, product_id, option_name)
    SELECT 0, 120, pid, 'SIZE_M'
    WHERE NOT EXISTS (
      SELECT 1 FROM product_option WHERE product_id=pid AND option_name='SIZE_M'
    );
    INSERT INTO product_option (additional_price, stock_qty, product_id, option_name)
    SELECT 0, 80, pid, 'SIZE_L'
    WHERE NOT EXISTS (
      SELECT 1 FROM product_option WHERE product_id=pid AND option_name='SIZE_L'
    );

    -- Images
    SET j = 1;
    WHILE j <= imgs_per_md DO
      INSERT INTO product_image (sort_order, product_id, image_type, image_url)
      SELECT j, pid, IF(j=1,'MAIN','SUB'),
             CONCAT(base_path, LPAD(img_cursor,2,'0'), ext)
      WHERE NOT EXISTS (
        SELECT 1 FROM product_image WHERE product_id=pid AND sort_order=j
      );
      SET img_cursor = img_cursor + 1;
      SET j = j + 1;
    END WHILE;

    SET i = i + 1;
  END WHILE;
END $$

DELIMITER ;

-- Example call:
-- CALL seed_products(3, 4, 3, 4, 1, 'NewJeans', 'NewJeans', '/img/shop_', '.jpg');

-- -----------------------------------------------------------------------
-- (콜 예시)
CALL seed_products(5,5,5,5,1,'10CM','10CM','/images/10CM/10CM_shop/10CM_shop_','.png');
CALL seed_products(5,5,5,5,1,'BABYMONSTER','BABYMONSTER','/images/BABYMONSTER/BABYMONSTER_shop/BABYMONSTER_shop_','.png');
CALL seed_products(5,5,5,5,1,'BLACKPINK','BLACKPINK','/images/BLACKPINK/BLACKPINK_shop/BLACKPINK_shop_','.png');
CALL seed_products(5,5,5,5,1,'LESSERAFIM','LESSERAFIM','/images/LESSERAFIM/LESSERAFIM_shop/LESSERAFIM_shop_','.png');
CALL seed_products(5,5,5,5,1,'NCT_WISH','NCT_WISH','/images/NCT_WISH/NCT_WISH_shop/NCT_WISH_shop_','.png');
CALL seed_products(5,5,5,5,1,'NewJeans','NewJeans','/images/NewJeans/NewJeans_shop/NewJeans_shop_','.png');
CALL seed_products(5,5,5,5,1,'RIIZE','RIIZE','/images/RIIZE/RIIZE_shop/RIIZE_shop_','.png');
CALL seed_products(5,5,5,5,1,'STAYC','STAYC','/images/STAYC/STAYC_shop/STAYC_shop_','.png');
CALL seed_products(5,5,5,5,1,'TWS','TWS','/images/TWS/TWS_shop/TWS_shop_','.png');
-- 값 설명
-- CALL seed_products(
--   album_cnt,       -- 앨범 상품 개수
--   md_cnt,          -- MD 상품 개수
--   imgs_per_album,  -- 앨범 1개당 이미지 장수
--   imgs_per_md,     -- MD 1개당 이미지 장수
--   start_img,       -- 이미지 시작 번호 (shop_01이면 1)
--   album_group_name,
--   md_group_name,
--   base_path,
--   ext
-- );
-- -----------------------------------------------------------------------
INSERT INTO `user` (
  email,
  password,          -- NOT NULL이면 더미값
  name,      -- 있으면
  nickname,
  phone_number,      -- 있으면
  created_at,        -- 있으면
  cash_balance,
  is_email_verified,
  is_sms_verified,
  jelly_balance,
  country
)
SELECT
  'seed_user1@test.com',
  'x',               -- 해시 필요 없으면 'x' 같은 더미
  'Seed User',
  'tester',
  '010-0000-0000',
  NOW(),
  0,
  1,
  1,
  0,
  'KR'

WHERE NOT EXISTS (
  SELECT 1 FROM `user` WHERE email='seed_user1@test.com'
);

-- file: seed_orders_wallet.sql
-- MySQL stored procedures to auto-generate:
-- 1) Orders + OrderItems + Payments + Shipments (+ optional refunds)
-- 2) Jelly purchases + Jelly history + Cash history
--
-- Assumes tables/columns per your DDL logs and that products/options already exist.
-- Idempotent-ish via natural keys (order_number, transaction_id).

/* ------------------------------------------------------------------
   Helper: ensure minimal masters exist (safe to run multiple times)
-------------------------------------------------------------------*/
INSERT INTO shipping_country (country_name)
SELECT '대한민국' WHERE NOT EXISTS (SELECT 1 FROM shipping_country WHERE country_name='대한민국');
INSERT INTO shipping_country (country_name)
SELECT '미국' WHERE NOT EXISTS (SELECT 1 FROM shipping_country WHERE country_name='미국');

INSERT INTO shipping_carrier (carrier_name, tracking_url)
SELECT 'CJ대한통운', 'https://cj.example/track'
WHERE NOT EXISTS (SELECT 1 FROM shipping_carrier WHERE carrier_name='CJ대한통운');

INSERT INTO shipping_carrier (carrier_name, tracking_url)
SELECT 'UPS', 'https://ups.example/track'
WHERE NOT EXISTS (SELECT 1 FROM shipping_carrier WHERE carrier_name='UPS');

/* ------------------------------------------------------------------
   Procedure: seed_orders
   Creates N orders for a given user email, alternating carrier/country,
   auto-creating payments, order items, shipments, cash history.
-------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS seed_orders;
DELIMITER $$

CREATE PROCEDURE seed_orders(
  IN p_user_email VARCHAR(255),
  IN orders_cnt INT,          -- how many orders to create
  IN refund_every INT,        -- every Nth order refunded (0 => no refunds)
  IN start_idx INT            -- start index for order_number suffix (e.g., 1 -> ...001)
)
BEGIN
  DECLARE uid BIGINT;
  DECLARE i INT;
  DECLARE prod_cnt INT;
  DECLARE off INT;
  DECLARE pid BIGINT;
  DECLARE optid BIGINT;
  DECLARE unit_price DECIMAL(15,2);
  DECLARE onum VARCHAR(40);
  DECLARE tid VARCHAR(60);
  DECLARE payid BIGINT;
  DECLARE oid BIGINT;
  DECLARE shipping_fee INT;
  DECLARE city VARCHAR(100);
  DECLARE country VARCHAR(100);
  DECLARE postal VARCHAR(20);
  DECLARE carrier_name VARCHAR(100);
  DECLARE total_price DECIMAL(15,2);

  -- Resolve user
  SELECT user_id INTO uid FROM user WHERE email = p_user_email LIMIT 1;
  IF uid IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='seed_orders: 해당 이메일의 user가 없습니다.';
  END IF;

  -- Need at least 1 product
  SELECT COUNT(*) INTO prod_cnt FROM product;
  IF prod_cnt = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='seed_orders: product 테이블이 비어 있습니다.';
  END IF;

  SET i = 1;
  WHILE i <= orders_cnt DO
    -- Pick product by round-robin
    SET off = (i - 1) % prod_cnt;
    SELECT p.product_id, p.price
      INTO pid, unit_price
    FROM product p
    ORDER BY p.product_id
    LIMIT off, 1;

    -- Pick first option of that product
    SELECT option_id INTO optid
    FROM product_option
    WHERE product_id = pid
    ORDER BY option_id
    LIMIT 1;

    -- Order number & transaction id
    SET onum = CONCAT('O', DATE_FORMAT(NOW(), '%Y%m%d'), 'A', LPAD(start_idx + i - 1, 3, '0'));
    SET tid  = CONCAT('TID-', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '-', LPAD(start_idx + i - 1, 3, '0'));

    -- Domestic for odd, Global for even
    IF (i % 2) = 1 THEN
      SET carrier_name = 'CJ대한통운';
      SET shipping_fee = 3000;
      SET city = '서울';
      SET country = '대한민국';
      SET postal = '06236';
    ELSE
      SET carrier_name = 'UPS';
      SET shipping_fee = 0;
      SET city = 'New York';
      SET country = '미국';
      SET postal = '10001';
    END IF;

    SET total_price = unit_price + shipping_fee;

    -- Payment (idempotent by transaction_id)
    IF NOT EXISTS (SELECT 1 FROM payment WHERE transaction_id = tid) THEN
      INSERT INTO payment (amount, paid_at, user_id, currency, status, payment_gateway, payment_method, transaction_id)
      VALUES (total_price, NOW(), uid, 'KRW', 'PAID', 'PORTONE', 'CARD', tid);
    END IF;
    SELECT payment_id INTO payid FROM payment WHERE transaction_id = tid LIMIT 1;

    -- Order (idempotent by order_number)
    IF NOT EXISTS (SELECT 1 FROM orders WHERE order_number = onum) THEN
      INSERT INTO orders (
        cash_used, shipping_fee, subtotal_price, ordered_at, payment_id, total_price, user_id,
        postal_code, status, order_number, phone_number, carrier_name, city, country, recipient_name,
        delivery_request, full_address
      ) VALUES (
        0, shipping_fee, unit_price, NOW(), payid, total_price, uid,
        postal, IF((refund_every>0 AND (i % refund_every)=0),'REFUND_REQ','PAID'),
        onum, '010-0000-0000', carrier_name, city, country, '홍길동',
        '문앞에 두세요', CONCAT(city, ' ...')
      );
    END IF;
    SELECT order_id INTO oid FROM orders WHERE order_number = onum LIMIT 1;

    -- Order item (idempotent)
    IF NOT EXISTS (SELECT 1 FROM order_item WHERE order_id = oid AND product_id = pid) THEN
      INSERT INTO order_item (order_price, quantity, option_id, order_id, product_id)
      VALUES (unit_price, 1, optid, oid, pid);
    END IF;

    -- Shipment (idempotent by order_id)
    IF NOT EXISTS (SELECT 1 FROM shipment WHERE order_id = oid) THEN
      INSERT INTO shipment (delivered_at, order_id, shipped_at, status, carrier_name, tracking_number)
      VALUES (NULL, oid, NOW(), 'SHIPPED', carrier_name,
              CONCAT(IF(carrier_name='CJ대한통운','KR-CJ-','US-UPS-'),
                     LPAD(100000 + i, 8, '0')));
    END IF;

    -- Cash history (idempotent by order_id+type)
    IF NOT EXISTS (
      SELECT 1 FROM cash_history WHERE order_id = oid AND type = 'ORDER_PAYMENT'
    ) THEN
      INSERT INTO cash_history (amount, created_at, order_id, user_id, type, description)
      VALUES (-total_price, NOW(), oid, uid, 'ORDER_PAYMENT', '결제 차감');
    END IF;

    -- Refund on every Nth order
    IF (refund_every > 0 AND (i % refund_every) = 0) THEN
      IF NOT EXISTS (SELECT 1 FROM refund WHERE payment_id = payid) THEN
        INSERT INTO refund (refund_amount, payment_id, refunded_at, requested_at, status, reason)
        VALUES (total_price, payid, NOW(), NOW(), 'REFUNDED', '자동 환불(테스트)');
      END IF;
    END IF;

    SET i = i + 1;
  END WHILE;
END $$

DELIMITER ;

/* ------------------------------------------------------------------
   Procedure: seed_wallet
   Creates jelly purchases for a user. For each purchase, creates a
   payment, a jelly_purchase row, and a jelly_history bonus entry.
-------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS seed_wallet;
DELIMITER $$

CREATE PROCEDURE seed_wallet(
  IN p_user_email VARCHAR(255),
  IN purchases_cnt INT,        -- how many jelly purchases to create
  IN jelly_product_name VARCHAR(100) -- e.g., '젤리 100' (must exist)
)
BEGIN
  DECLARE uid BIGINT;
  DECLARE j INT;
  DECLARE jpid BIGINT;
  DECLARE jamount INT;
  DECLARE jprice DECIMAL(15,2);
  DECLARE payid BIGINT;
  DECLARE tid VARCHAR(60);

  -- Resolve user
  SELECT user_id INTO uid FROM user WHERE email = p_user_email LIMIT 1;
  IF uid IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='seed_wallet: 해당 이메일의 user가 없습니다.';
  END IF;

  -- Resolve jelly product
  SELECT jelly_product_id, jelly_amount, price
    INTO jpid, jamount, jprice
  FROM jelly_product
  WHERE product_name = jelly_product_name
  LIMIT 1;
  IF jpid IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='seed_wallet: jelly_product를 찾을 수 없습니다.';
  END IF;

  SET j = 1;
  WHILE j <= purchases_cnt DO
    SET tid  = CONCAT('TID-JELLY-', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '-', LPAD(j,3,'0'));

    -- Payment for jelly (idempotent by transaction_id)
    IF NOT EXISTS (SELECT 1 FROM payment WHERE transaction_id = tid) THEN
      INSERT INTO payment (amount, paid_at, user_id, currency, status, payment_gateway, payment_method, transaction_id)
      VALUES (jprice, NOW(), uid, 'KRW', 'PAID', 'PORTONE', 'CARD', tid);
    END IF;
    SELECT payment_id INTO payid FROM payment WHERE transaction_id = tid LIMIT 1;

    -- Jelly purchase (idempotent by payment_id + jelly_product_id)
    IF NOT EXISTS (
      SELECT 1 FROM jelly_purchase WHERE payment_id = payid AND jelly_product_id = jpid
    ) THEN
      INSERT INTO jelly_purchase (jelly_product_id, paid_at, payment_id, user_id)
      VALUES (jpid, NOW(), payid, uid);
    END IF;

    -- Jelly history (bonus entry; adjust columns if NOT NULL constraints differ)
    IF NOT EXISTS (
      SELECT 1 FROM jelly_history
      WHERE user_id = uid AND change_type = 'PURCHASE_BONUS' AND description = CONCAT('자동 적립 #', j)
    ) THEN
      INSERT INTO jelly_history (balance_after, change_amount, created_at, user_id, change_type, description)
      VALUES (jamount, jamount, NOW(), uid, 'PURCHASE_BONUS', CONCAT('자동 적립 #', j));
    END IF;

    SET j = j + 1;
  END WHILE;
END $$

DELIMITER ;

-- Example usage:
-- CALL seed_orders('seed_user1@test.com', 6, 3, 1);
-- CALL seed_wallet('seed_user1@test.com', 2, '젤리 100');


-- 1) 주문/결제/배송(+N번째 환불)
CALL seed_orders(
  'seed_user1@test.com',  -- 유저 이메일 (실존해야 함)
  6,                      -- orders_cnt: 6건 생성
  3,                      -- refund_every: 3번째마다 환불 (0이면 환불 없음)
  1                       -- start_idx: 주문번호 시퀀스 시작(001부터)
);

-- 젤리 삽입 안되어 있을경우 테스트!!!
INSERT INTO jelly_product (bonus_jelly, jelly_amount, price, benefit_description, product_name)
SELECT 0, 100, 1200, '기본 100젤리', '젤리 100'
WHERE NOT EXISTS (SELECT 1 FROM jelly_product WHERE product_name='젤리 100');

-- 2) 젤리 구매/히스토리
CALL seed_wallet(
  'seed_user1@test.com',  -- 유저 이메일
  2,                      -- purchases_cnt: 젤리 구매 2건
  '젤리 100'              -- jelly_product_name: 실제 등록된 이름 그대로
);

--

-- 삽입 값 확인용 쿼리들
SELECT COUNT(*) products, 
       (SELECT COUNT(*) FROM product_option) options,
       (SELECT COUNT(*) FROM product_image) images
FROM product;

SELECT p.product_name, g.group_name, pi.image_url, pi.sort_order, pi.image_type
FROM product p 
JOIN `group` g ON p.group_id=g.group_id
JOIN product_image pi ON pi.product_id=p.product_id
ORDER BY p.product_id, pi.sort_order
LIMIT 10;

SELECT COUNT(*) orders_cnt, 
       (SELECT COUNT(*) FROM order_item) items, 
       (SELECT COUNT(*) FROM payment) payments,
       (SELECT COUNT(*) FROM refund) refunds
FROM orders;

SELECT o.order_number, o.status, p.amount, s.carrier_name, r.status AS refund_status
FROM orders o
LEFT JOIN payment p ON p.payment_id=o.payment_id
LEFT JOIN shipment s ON s.order_id=o.order_id
LEFT JOIN refund r ON r.payment_id=o.payment_id
ORDER BY o.order_id DESC
LIMIT 10;

SELECT carrier_name, COUNT(*) cnt FROM shipment GROUP BY carrier_name;

SELECT COUNT(*) FROM jelly_purchase;
SELECT change_type, COUNT(*) FROM jelly_history GROUP BY change_type;
SELECT type, COUNT(*) FROM cash_history GROUP BY type;

SELECT * FROM shipping_country;
SELECT * FROM shipping_carrier;
