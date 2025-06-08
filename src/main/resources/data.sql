INSERT INTO books (book_title, book_author, book_publish, book_publish_date, book_img, book_price) VALUES
('자바 완전 정복          ', '홍길동              ', '한빛미디어         ', '2021-05-10', 'java_book_img1.jpg', 25000),
('스프링 부트 마스터     ', '김철수              ', '길벗               ', '2022-03-15', 'spring_boot_img2.jpg', 30000),
('데이터베이스 기초       ', '이영희              ', '인사이트           ', '2020-11-20', 'db_book_img3.jpg', 20000),
('JPA 핵심 노트          ', '박지성              ', '위키북스           ', '2023-01-05', 'jpa_book_img4.jpg', 27000),
('알고리즘 문제풀이      ', '최민수              ', '한빛아카데미       ', '2021-08-12', 'algo_book_img5.jpg', 32000),
('웹 개발 입문           ', '정지훈              ', '에이콘출판         ', '2019-06-22', 'webdev_book_img6.jpg', 22000),
('리액트 완벽 가이드     ', '오민석              ', '제이펍             ', '2022-09-01', 'react_book_img7.jpg', 28000),
('파이썬 프로그래밍       ', '한소희              ', '한빛미디어         ', '2021-12-15', 'python_book_img8.jpg', 24000),
('자바스크립트 마스터    ', '윤아름              ', '에이콘출판         ', '2020-10-30', 'js_book_img9.jpg', 26000),
('클라우드 컴퓨팅        ', '이준호              ', '인사이트           ', '2023-04-20', 'cloud_book_img10.jpg', 35000),
('자바 완전 정복          ', '홍길동              ', '한빛미디어         ', '2021-05-10', 'java_book_img1.jpg', 25000),
('스프링 부트 마스터     ', '김철수              ', '길벗               ', '2022-03-15', 'spring_boot_img2.jpg', 30000),
('JPA 핵심 노트          ', '박지성              ', '위키북스           ', '2023-01-05', 'jpa_book_img4.jpg', 27000),
('알고리즘 문제풀이      ', '최민수              ', '한빛아카데미       ', '2021-08-12', 'algo_book_img5.jpg', 32000),
('파이썬 프로그래밍       ', '한소희              ', '한빛미디어         ', '2021-12-15', 'python_book_img8.jpg', 24000);

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1215', '{bcrypt}$2a$10$Oi0Cmk7ZJggiqj14UcBiJeHKZNAOC7zorrNP5Kx5ISs1wTLBHucX.', '010-1234-5678', 'Sales', '서울 지점 소속 영업 담당', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1315', '{bcrypt}$2a$10$Oi0Cmk7ZJggiqj14UcBiJeHKZNAOC7zorrNP5Kx5ISs1wTLBHucX.', '010-2345-6789', 'Marketing', '브랜드 마케팅 담당', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1415', '123456', '010-3456-7890', 'Design', 'UI/UX 디자이너', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1515', '123456', '010-4567-8901', 'IT', '백엔드 개발자', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1615', '123456', '010-5678-9012', 'HR', '인사 관리 담당', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1715', '123456', '010-6789-0123', 'Finance', '재무팀 팀장', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1815', '123456', '010-7890-1234', 'R&D', '신제품 개발자', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj1915', '123456', '010-8901-2345', 'CS', '고객 서비스 담당', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj2015', '123456', '010-9012-3456', 'Legal', '법률 자문 담당', 'USER');

INSERT INTO users (user_name, user_pw, user_phone, user_dept, user_info, user_role) VALUES
('sshj2115', '{bcrypt}$2a$10$Oi0Cmk7ZJggiqj14UcBiJeHKZNAOC7zorrNP5Kx5ISs1wTLBHucX.', '010-1111-2222', 'Admin', '시스템 관리자', 'ADMIN');

INSERT INTO loan (user_id, book_id, loan_date, return_expire_date, returned_date, whether_return) VALUES
(1, 1, '2025-04-10', '2025-04-24', '2025-04-22', 'RETURNED'),
(2, 2, '2025-04-11', '2025-04-25', NULL, 'LOANING'),
(3, 3, '2025-04-12', '2025-04-26', '2025-04-28', 'RETURN_LATE'),
(4, 3, '2025-04-30', '2025-05-14', NULL, 'LOANING'),
(5, 4, '2025-04-13', '2025-04-27', '2025-04-26', 'RETURNED'),
(6, 5, '2025-04-14', '2025-04-28', NULL, 'LOANING'),
(7, 6, '2025-04-15', '2025-04-29', '2025-04-29', 'RETURNED'),
(8, 6, '2025-05-01', '2025-05-15', NULL, 'LOANING'),
(9, 7, '2025-04-16', '2025-04-30', '2025-05-02', 'RETURN_LATE'),
(10, 8, '2025-04-17', '2025-05-01', NULL, 'LOANING'),
(1, 9, '2025-04-18', '2025-05-02', '2025-05-01', 'RETURNED'),
(2, 10, '2025-04-19', '2025-05-03', NULL, 'LOANING'),
(3, 11, '2025-04-20', '2025-05-04', '2025-05-03', 'RETURNED'),
(4, 11, '2025-05-05', '2025-05-19', NULL, 'LOANING'),
(5, 12, '2025-04-21', '2025-05-05', NULL, 'LOANING'),
(6, 13, '2025-04-22', '2025-05-06', '2025-05-08', 'RETURN_LATE'),
(7, 14, '2025-04-23', '2025-05-07', NULL, 'LOANING'),
(8, 15, '2025-04-24', '2025-05-08', NULL, 'LOANING');
