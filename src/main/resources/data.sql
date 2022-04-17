INSERT INTO users (id, username) VALUES(1, 'nathan');
INSERT INTO users (id, username) VALUES(2, 'mr dog');

INSERT INTO stocks (id, ticker) VALUES(1, 'msft');
INSERT INTO stocks (id, ticker) VALUES(2, 'baba');
INSERT INTO stocks (id, ticker) VALUES(3, 'pltr');

INSERT INTO transactions (id, type, user_id, stock_id, quantity, price, ts) VALUES (1, 'BUY', 1, 1, 45.3, '134', '2020-01-17 18:47:52.69');
INSERT INTO transactions (id, type, user_id, stock_id, quantity, price, ts) VALUES (2, 'BUY', 1, 2, 50.6, '231', '2020-02-18 19:48:53.70');
INSERT INTO transactions (id, type, user_id, stock_id, quantity, price, ts) VALUES (3, 'BUY', 2, 3, 500, '542', '2020-03-17 18:47:52.69');

-- msft
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (1, 1, '2017-01-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (2, 1, '2017-02-01', 135.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (3, 1, '2017-03-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (4, 1, '2017-04-01', 145.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (5, 1, '2017-05-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (6, 1, '2017-06-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (7, 1, '2017-07-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (8, 1, '2017-08-01', 150.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (9, 1, '2017-09-01', 160.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (10, 1, '2017-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (11, 1, '2017-11-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (12, 1, '2017-12-01', 160.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (13, 1, '2018-01-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (14, 1, '2018-02-01', 185.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (15, 1, '2018-03-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (16, 1, '2018-04-01', 165.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (17, 1, '2018-05-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (18, 1, '2018-06-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (19, 1, '2018-07-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (20, 1, '2018-08-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (21, 1, '2018-09-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (22, 1, '2018-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (23, 1, '2018-11-01', 190.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (24, 1, '2018-12-01', 130.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (25, 1, '2019-01-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (26, 1, '2019-02-01', 185.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (27, 1, '2019-03-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (28, 1, '2019-04-01', 165.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (29, 1, '2019-05-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (30, 1, '2019-06-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (31, 1, '2019-07-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (32, 1, '2019-08-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (33, 1, '2019-09-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (34, 1, '2019-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (35, 1, '2019-11-01', 190.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (36, 1, '2019-12-01', 130.15);

-- baba
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (37, 2, '2017-01-01', 90.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (38, 2, '2017-02-01', 94.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (39, 2, '2017-03-01', 95.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (40, 2, '2017-04-01', 93.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (41, 2, '2017-05-01', 96.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (42, 2, '2017-06-01', 100.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (43, 2, '2017-07-01', 98.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (44, 2, '2017-08-01', 101.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (45, 2, '2017-09-01', 102.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (46, 2, '2017-10-01', 103.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (47, 2, '2017-11-01', 105.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (48, 2, '2017-12-01', 112.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (49, 2, '2018-01-01', 93.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (50, 2, '2018-02-01', 90.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (51, 2, '2018-03-01', 102.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (52, 2, '2018-04-01', 103.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (53, 2, '2018-05-01', 104.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (54, 2, '2018-06-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (55, 2, '2018-07-01', 90.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (56, 2, '2018-08-01', 91.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (57, 2, '2018-09-01', 92.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (58, 2, '2018-10-01', 93.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (59, 2, '2018-11-01', 94.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (60, 2, '2018-12-01', 95.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (61, 2, '2019-01-01', 93.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (62, 2, '2019-02-01', 94.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (63, 2, '2019-03-01', 95.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (64, 2, '2019-04-01', 96.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (65, 2, '2019-05-01', 97.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (66, 2, '2019-06-01', 98.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (67, 2, '2019-07-01', 99.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (68, 2, '2019-08-01', 100.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (69, 2, '2019-09-01', 101.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (70, 2, '2019-10-01', 102.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (71, 2, '2019-11-01', 103.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (72, 2, '2019-12-01', 104.15);

-- pltr
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (73, 3, '2017-01-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (74, 3, '2017-02-01', 135.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (75, 3, '2017-03-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (76, 3, '2017-04-01', 145.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (77, 3, '2017-05-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (78, 3, '2017-06-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (79, 3, '2017-07-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (80, 3, '2017-08-01', 150.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (81, 3, '2017-09-01', 160.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (82, 3, '2017-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (83, 3, '2017-11-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (84, 3, '2017-12-01', 160.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (85, 3, '2018-01-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (86, 3, '2018-02-01', 185.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (87, 3, '2018-03-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (88, 3, '2018-04-01', 165.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (89, 3, '2018-05-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (90, 3, '2018-06-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (91, 3, '2018-07-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (92, 3, '2018-08-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (93, 3, '2018-09-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (94, 3, '2018-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (95, 3, '2018-11-01', 190.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (96, 3, '2018-12-01', 130.15);

INSERT INTO stock_history (id, stock_id, ts, price) VALUES (97, 3, '2019-01-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (98, 3, '2019-02-01', 185.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (99, 3, '2019-03-01', 130.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (100, 3, '2019-04-01', 165.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (101, 3, '2019-05-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (102, 3, '2019-06-01', 180.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (103, 3, '2019-07-01', 140.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (104, 3, '2019-08-01', 120.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (105, 3, '2019-09-01', 110.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (106, 3, '2019-10-01', 170.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (107, 3, '2019-11-01', 190.15);
INSERT INTO stock_history (id, stock_id, ts, price) VALUES (108, 3, '2019-12-01', 130.15);



