use weverse_db;

show tables;

-- SELECT * FROM 테이블명;

-- USER 더미데이터
INSERT INTO `USER` 
    (user_id, email, password, name, nickname, phone_number, country, profile_image, jelly_balance, cash_balance, is_email_verified, is_sms_verified, created_at)
VALUES 
    (1, 'testuser@example.com', 'hashed_password123', '김위버스', '위버스팬1', '010-1234-5678', 'South Korea', NULL, 0, 0, true, true, NOW());

-- JELLY 더미데이터   
INSERT INTO jelly_product (jelly_product_id, product_name, price, jelly_amount, bonus_jelly, benefit_description) VALUES
(1, '젤리 4', 1200, 4, NULL, NULL), -- 4개 상품은 혜택 정보가 없으므로 NULL
(2, '젤리 8', 2400, 8, NULL, NULL), -- 8개 상품은 혜택 정보가 없으므로 NULL
(3, '젤리 20', 6000, 20, 1, '5% 혜택'),
(4, '젤리 40', 12000, 40, 3, '7% 혜택'),
(5, '젤리 60', 18000, 60, 5, '8% 혜택'),
(6, '젤리 80', 24000, 80, 7, '8% 혜택'),
(7, '젤리 120', 36000, 120, 11, '9% 혜택'), 
(8, '젤리 160', 48000, 160, 15, '9% 혜택'),
(9, '젤리 240', 72000, 240, 24, '10% 혜택');
COMMIT;