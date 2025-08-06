-- =================================================================
-- Weverse Project Local Development Dummy Data Script
-- =================================================================
-- [중요]
-- 이 스크립트는 여러 번 실행해도 DB 에러가 발생하지 않도록
-- 모든 INSERT 문에 'INSERT IGNORE'를 사용해주세요.

-- 최종 업데이트 일시: 2025.08.06. 22:30
-- 최종 수정 작업자: 김예지
-- =================================================================


-- 👤 user (회원 도메인)
-- =================================================================
-- 1. 기본 테스트 유저 생성
INSERT IGNORE INTO `USER` (user_id, email, password, name, nickname, phone_number, country, profile_image, jelly_balance, cash_balance, is_email_verified, is_sms_verified, created_at)
VALUES (1, 'testuser@example.com', 'hashed_password123', '김위버스', '위버스팬1', '010-1234-5678', 'South Korea', NULL, 0, 0, true, true, NOW());

-- 2. 생성된 유저에게 테스트 캐시 충전
UPDATE user SET cash_balance = 100000 WHERE user_id = 1;


-- 🎤 artist (아티스트 도메인)
-- =================================================================
-- 1. 'NCT WISH' 그룹 생성 (group_id: 10)
--    (artist_group_map 테이블이 이제 Group 테이블 역할을 합니다)
INSERT IGNORE INTO `group` (group_id, group_name, group_logo)
VALUES (10, 'NCT WISH', 'NCT_WISH_logo.png');

-- 2. '사쿠야' 아티스트 데이터 생성
INSERT IGNORE INTO `artist` (artist_id, group_id, stage_name, email, password, birthday)
VALUES
    (202, 10, '사쿠야 (NCT WISH)', 'sakuya@example.com', 'password123', NOW());


-- 🗣️ community (커뮤니티 도메인)
-- =================================================================
-- (Post, Comment 등 커뮤니티 관련 더미 데이터 추가 영역)


-- 👕 product (상품 도메인)
-- =================================================================
-- 1. 상품 카테고리 생성
INSERT IGNORE INTO `product_category` (category_id, category_name) VALUES (1, 'OFFICIAL FANLIGHT');

-- 2. 'NCT WISH' 공식 응원봉 상품 생성 (product_id: 101)
INSERT IGNORE INTO `product` (product_id, artist_id, category_id, product_name, price, stock_qty)
VALUES (101, 202, 10, 1, 'NCT WISH OFFICIAL FANLIGHT', 42000, 200);

-- 3. 상품 기본 옵션 생성
INSERT IGNORE INTO `product_option` (option_id, product_id, group_id, option_name, stock_qty)
VALUES (101, 101, 10, '기본', 200);


-- 🚚 order (주문/배송 도메인)
-- =================================================================
-- (주문, 배송 정보 등 관련 더미 데이터 추가 영역)


-- 💳 payment (결제/재화 도메인)
-- =================================================================
-- 1. 젤리 상품 목록 생성
INSERT INTO jelly_product (jelly_product_id, product_name, price, jelly_amount, bonus_jelly, benefit_description) VALUES
(1, '젤리 4', 1200, 4, NULL, NULL),
(2, '젤리 8', 2400, 8, NULL, NULL),
(3, '젤리 20', 6000, 20, 1, '5% 혜택'),
(4, '젤리 40', 12000, 40, 3, '7% 혜택'),
(5, '젤리 60', 18000, 60, 5, '8% 혜택'),
(6, '젤리 80', 24000, 80, 7, '8% 혜택'),
(7, '젤리 120', 36000, 120, 11, '9% 혜택'), 
(8, '젤리 160', 48000, 160, 15, '9% 혜택'),
(9, '젤리 240', 72000, 240, 24, '10% 혜택');


-- 🔄 subscription (구독 도메인)
-- =================================================================
-- (구독 상품, 구독 내역 등 관련 더미 데이터 추가 영역)


-- 🎬 media (미디어 도메인)
-- =================================================================
-- (스트리밍, VOD 등 관련 더미 데이터 추가 영역)


-- ✉️ dm (다이렉트 메시지 도메인)
-- =================================================================
-- (DM 메시지, 답장 등 관련 더미 데이터 추가 영역)


-- ℹ️ support (고객지원 도메인)
-- =================================================================
-- (공지사항, FAQ 등 관련 더미 데이터 추가 영역)


-- 모든 작업 완료 후 최종 커밋
COMMIT;