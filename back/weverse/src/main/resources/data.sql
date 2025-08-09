-- =================================================================
-- Weverse Project Local Development Dummy Data Script
-- =================================================================
-- [중요]
-- 이 스크립트는 여러 번 실행해도 DB 에러가 발생하지 않도록
-- 모든 INSERT 문에 'INSERT IGNORE'를 사용해주세요.

-- 최종 업데이트 일시: 2025.08.09. 23:25
-- 최종 수정 작업자: 김예지
-- =================================================================


-- 👤 user (회원 도메인)
-- =================================================================
-- 1. 기본 테스트 유저 생성
INSERT IGNORE INTO `USER` (email, password, role, name, nickname, phone_number, country, profile_image, jelly_balance, cash_balance, is_email_verified, is_sms_verified, created_at)
VALUES ('testuser@example.com', 'hashed_password123', 'ROLE_USER', '김위버스', '위버스팬1', '010-1234-5678', 'South Korea', NULL, 0, 0, true, true, NOW());

-- 2. 생성된 유저에게 테스트 캐시 충전
UPDATE user SET cash_balance = 100000 WHERE user_id = 1;

-- 3. 테스트 유저의 기본 배송지 등록
INSERT IGNORE INTO `delivery_address`
	(user_id, recipient_name, phone_number, postal_code, country, city, address, address_detail, delivery_request, is_default)
VALUES (1, '김위버스', '010-1234-5678', '12345', 'South Korea', 'Seoul', '강남구 테헤란로 132', '역삼동 한독약품빌딩 8층 쌍용교육센터', '부재 시 문 앞에 놔주세요', true);

-- 🎤 artist (아티스트 도메인)
-- =================================================================
-- 1. 'NCT WISH' 그룹 생성
--    (artist_group_map 테이블이 이제 Group 테이블 역할을 합니다)
INSERT IGNORE INTO `group` (group_name, group_logo)
VALUES ('NCT WISH', 'NCT_WISH_logo.png');

-- 2. '사쿠야' 아티스트 데이터 생성
INSERT IGNORE INTO `artist` (group_id, stage_name, email, password, birthday)
VALUES (1, '사쿠야', 'sakuya@example.com', 'password123', '2007-11-18 00:00:00');


-- 🗣️ community (커뮤니티 도메인)
-- =================================================================
-- (Post, Comment 등 커뮤니티 관련 더미 데이터 추가 영역)


-- 👕 product (상품 도메인)
-- =================================================================
-- 1. 상품 카테고리 생성
INSERT IGNORE INTO `product_category` (category_name) VALUES ('MERCH');

INSERT IGNORE INTO `product_category` (category_name) VALUES ('MAGAZINE');

-- 2. 상품 생성
INSERT IGNORE INTO `product` (group_id, category_id, product_name, description, price, stock_qty)
VALUES (1, 1, '[NCT WISH] OFFICIAL FANLIGHT', 'NCT WISH의 공식 응원봉입니다. 파츠를 분리하여 자유롭게 커스터마이징 할 수 있습니다.', 45000.00, 300);

INSERT IGNORE INTO `product` (artist_id, group_id, category_id, product_name, description, price, stock_qty)
VALUES (1, 1, 2, '[SAKUYA] DICON VOLUME N°29 NCT WISH MY YOUTH, MY WISH', '이벤트 기간 동안 해당 상품 구매시, 단독 미공개 포토카드가 제공됩니다.', 39700.00, 300);

-- 3. 상품 기본 옵션 생성
INSERT IGNORE INTO `product_option` (product_id, option_name, stock_qty, additional_price)
VALUES (1, '기본', 300, 0.00);

INSERT IGNORE INTO `product_option` (product_id, option_name, stock_qty, additional_price)
VALUES (2, '기본', 300, 0.00);

-- 🚚 order (주문/배송 도메인)
-- =================================================================
-- 1. 기본 배송 국가 생성
INSERT IGNORE INTO `shipping_country` (country_name)
VALUES ('South Korea'), ('Japan'), ('United States');

-- 2. 기본 택배사 생성
INSERT IGNORE INTO `shipping_carrier` (carrier_name, tracking_url)
VALUES ('CJ 대한통운', 'https://www.cjlogistics.com/ko/tool/parcel/tracking');

-- 3. 국가별 사용가능 택배사 생성
INSERT IGNORE INTO `shipping_country_carrier` (country_id, carrier_id, carrier_name, tracking_url)
VALUES (1, 1, 'CJ 대한통운', 'https://www.cjlogistics.com/ko/tool/parcel/tracking');

-- 4. 배송정책 생성
INSERT IGNORE INTO `shipping_option` (product_id, country_carrier_id, shipping_fee, estimated_days)
VALUES (1, 1, 3000.00, 3);

INSERT IGNORE INTO `shipping_option` (product_id, country_carrier_id, shipping_fee, estimated_days)
VALUES (2, 1, 3000.00, 3);

-- 💳 payment (결제/재화 도메인)
-- =================================================================
-- 1. 젤리 상품 목록 생성
INSERT IGNORE INTO jelly_product (product_name, price, jelly_amount, bonus_jelly, benefit_description)
VALUES  ('젤리 4', 1200.00, 4, NULL, NULL),
		('젤리 8', 2400.00, 8, NULL, NULL),
		('젤리 20', 6000.00, 20, 1, '5% 혜택'),
		('젤리 40', 12000.00, 40, 3, '7% 혜택'),
		('젤리 60', 18000.00, 60, 5, '8% 혜택'),
		('젤리 80', 24000.00, 80, 7, '8% 혜택'),
		('젤리 120', 36000.00, 120, 11, '9% 혜택'), 
		('젤리 160', 48000.00, 160, 15, '9% 혜택'),
		('젤리 240', 72000.00, 240, 24, '10% 혜택');


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
-- COMMIT;