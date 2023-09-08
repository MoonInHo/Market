INSERT INTO MEMBER (member_id, user_id, password, name, address, address_detail, role) VALUES (1, 'mart123', '$2a$10$zdq4vG1L84zDjQUBdo8oUunAFVzC9tus2L5wAMT6Ln78pPCbQAnCa', '김마트', '테스트 마트 주소', '테스트 마트 상세 주소', 'ROLE_MART');
INSERT INTO MEMBER (member_id, user_id, password, name, address, address_detail, role) VALUES (2, 'user123', '$2a$10$gCx8GuxGlqJz2XebO6qF6eKlNYIH39s2/zaWO0yOghrXDAT7GpPDK', '김유저', '테스트 유저 주소', '테스트 유저 상세 주소', 'ROLE_USER');

INSERT INTO PRODUCT (product_id, product_name, price, timestamp) VALUES (1, '테스트 상품1', 15000, '22-10-07 12:30:05');
INSERT INTO PRODUCT (product_id, product_name, price, timestamp) VALUES (2, '테스트 상품2', 20000, '22-12-25 18:30:30');
INSERT INTO PRODUCT (product_id, product_name, price, timestamp) VALUES (3, '테스트 상품3', 25000, '23-01-15 11:00:25');
INSERT INTO PRODUCT (product_id, product_name, price, timestamp) VALUES (4, '테스트 상품4', 30000, '23-09-08 16:59:59');

INSERT INTO ORDERS (order_id, member_id, delivery_tips, order_status) VALUES (1, 1, 3000, 'DELIVERY_COMPLETED');
INSERT INTO ORDERS (order_id, member_id, delivery_tips, order_status) VALUES (2, 1, 0, 'ORDER_COMPLETED');

INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (1, 1, 1, 3);
INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (2, 1, 2, 2);
INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (3, 1, 3, 5);
INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (4, 1, 4, 8);
INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (5, 2, 2, 2);
INSERT INTO ORDER_DETAIL (order_detail_id, order_id, product_id, quantity) VALUES (6, 2, 3, 4);

INSERT INTO COUPON (coupon_id, coupon_name, description, coupon_policy, coupon_scope, discount_value) VALUES (1, '테스트 10% 할인 쿠폰', '10% 할인 쿠폰', 'PERCENTAGE', 'ENTIRE_ORDER', 0.1);
INSERT INTO COUPON (coupon_id, coupon_name, description, coupon_policy, coupon_scope, discount_value) VALUES (2, '테스트 5000원 할인 쿠폰', '5000원 할인 쿠폰', 'FIXED', 'SPECIFIC_PRODUCT', 5000);