insert into permission (name) values
    ('CREATE_AD'), ('VIEW_AD'), ('UPDATE_AD'), ('DELETE_AD'), ('POST_COMMENT'),
    ('READ_COMMENT'),  ('DELETE_COMMENT'), ('UPDATE_COMMENT'), ('POST_RATE'), ('READ_RATE'),
    ('UPDATE_RATE'), ('CREATE_REQUEST'), ('LOGIN'), ('RECEIVE_MESSAGE'), ('REGISTER'),
    ('RENT_A_CAR'), ('SEARCH'), ('SEND_MESSAGE'), ('UPLOAD_PHOTO'), ('DELETE_RATE'),
    ('CREATE_AGENT'), ('READ_REQUEST'), ('APPROVE_REQUEST');

insert into authority (name) values ('ROLE_ADMIN'), ('ROLE_AGENT'), ('ROLE_SIMPLE_USER'),
    ('ROLE_REVIEWER_USER'), ('ROLE_MESSAGE_USER'), ('ROLE_RENT_USER'), ('ROLE_COMMENT_USER'), ('ROLE_REQUEST');

insert into authorities_permissions (authority_id, permission_id) values
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 21),
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 19), (2, 22), (2, 23),
    (3, 13), (3, 15), (3, 17), (3, 2), (3, 6), (3, 10),
    (4, 9), (4, 10), (4, 11), (4, 20),
    (5, 14), (5, 18),
    (6, 16), (6, 12),
    (7, 5), (7, 6), (7, 7), (7, 8),
    (8, 22);

insert into user_entity (id, username, password, deleted, has_signed_in, last_password_reset_date, user_role) values
    ('9bbbd6c1-34b4-4ea6-8889-be247cfebc34', 'admin@gmail.com', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', false, false, '2019-10-01 21:58:58.508-07', 2),
    ('105496cd-30f2-4b62-8082-cc14d282e845', 'agent@gmail.com', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', false, false, '2019-10-01 21:58:58.508-07', 1),
    ('d0535564-08ec-464c-a2db-d930d2c4fcde', 'agent1@gmail.com', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', false, false, '2019-10-01 21:58:58.508-07', 1),
    ('4fb1b61b-cc4e-45c3-86f0-cbf50de4cf54', 'customer@gmail.com', '$2y$12$Wr3ak.8cQEj9.cALsNHQyejJ2gaRS4nWMjc5dIl7PUuesV632B1p6', false, false, '2019-10-01 21:58:58.508-07', 0),
    ('b9362264-17db-411e-8ed0-db8310cba9f1', 'customer2@gmail.com', '$2y$10$.YHs6oWKNn3ZqfLwxWYrouWDw54jjJ62Q9kUT.D0NPSsTYE/v8nMK', false, false, '2020-10-01 21:58:58.508-07', 0);

insert into user_authority (user_id, authority_id) values
    ('9bbbd6c1-34b4-4ea6-8889-be247cfebc34', 1),
    ('105496cd-30f2-4b62-8082-cc14d282e845', 2),
    ('105496cd-30f2-4b62-8082-cc14d282e845', 3),
    ('105496cd-30f2-4b62-8082-cc14d282e845', 4),
    ('d0535564-08ec-464c-a2db-d930d2c4fcde', 2),
    ('d0535564-08ec-464c-a2db-d930d2c4fcde', 3),
    ('d0535564-08ec-464c-a2db-d930d2c4fcde', 4),
    ('4fb1b61b-cc4e-45c3-86f0-cbf50de4cf54', 3),
    ('4fb1b61b-cc4e-45c3-86f0-cbf50de4cf54', 6),
    ('4fb1b61b-cc4e-45c3-86f0-cbf50de4cf54', 8),
    ('b9362264-17db-411e-8ed0-db8310cba9f1', 3),
    ('b9362264-17db-411e-8ed0-db8310cba9f1', 6);

insert into admin (id, first_name, last_name, user_id) values
    ('51d5e58d-ac22-4233-a1dc-e4251a18e815', 'Ms', 'Misoni', '9bbbd6c1-34b4-4ea6-8889-be247cfebc34');

insert into agent (id, bank_account_number, date_founded, name, tin, user_id) values
    ('b38a64e2-299b-4a05-bc30-5a45dd2ebdc0', 'DE72 3702 0500 0009 7097 00', '2020/02/25', 'Marko Kraljevic',  '123-45-6789', '105496cd-30f2-4b62-8082-cc14d282e845'),
    ('c72721c4-437f-4a06-b3cc-00b9a86056bc', '0500 0009 3702 FE22 7097 00', '2020/02/25', 'Dragan Topalovic', '321-54-9876', 'd0535564-08ec-464c-a2db-d930d2c4fcde');

insert into simple_user (id, address, city, country, first_name, last_name, request_status, ssn, user_id) values
    ('1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'Pionirska 26', 'Novi Sad', 'Serbia', 'Somi', 'Misoni', 'APPROVED', '1547854896523', '4fb1b61b-cc4e-45c3-86f0-cbf50de4cf54'),
    ('9220c03b-b0b5-46af-a821-249e2a97dcaa', 'Njegoseva 55', 'Novi Sad', 'Serbia', 'Didi', 'Mimica-Kostovic', 'APPROVED', '1547858576523', 'b9362264-17db-411e-8ed0-db8310cba9f1');

insert into address (id, name) values
    ('04fe195c-5409-4657-a009-3732eedf1f6c', 'Serbia, Novi Sad, Pionirska, 21'),
    ('7df3aad6-03bd-4724-9047-bb96403bdc16', 'Serbia, Beograd, Dusana Savica, 21'),
    ('b65ac4e5-a5a8-46d4-897e-a7d080ff147e', 'Serbia, Cara Lazara, Nis, 12'),
    ('72e5d652-a984-41c5-8e91-473d71f2c9b5', 'Serbia, Milosa Obilica, Subotica, 17');

insert into agent_ads (ad_id, agent_id) values
    ('991938f8-4834-421c-b48e-c2e28e06aae9', 'b38a64e2-299b-4a05-bc30-5a45dd2ebdc0'),
    ('991938f8-4834-421c-b48e-c2e28e06aae9', 'b38a64e2-299b-4a05-bc30-5a45dd2ebdc0'),
    ('6a22b2eb-705e-4311-ab90-bc438bc226fe', 'c72721c4-437f-4a06-b3cc-00b9a86056bc'),
    ('1124e496-a070-4b6e-b9c2-1b5e9aa00b60', 'b38a64e2-299b-4a05-bc30-5a45dd2ebdc0'),
    ('92cc7fc1-5823-40cb-8b36-6162f1cdb5a0', 'b38a64e2-299b-4a05-bc30-5a45dd2ebdc0'),
    ('87ea3abc-1bef-4c17-9ce6-4938e947a917', 'c72721c4-437f-4a06-b3cc-00b9a86056bc');

insert into user_requests (request_id, user_id) values
    ('100abd9a-aa4c-437d-ac4d-dc0d117616ba', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('3d8fc1fa-1c0e-467d-ba75-1d2df016b538', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('84481f50-7deb-4b8c-bbf5-fcd52e6b20fe', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('d0a328f2-de8e-434f-9b47-8a2231ef3b7e', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('2bd2a882-28ca-40be-afd7-4be12edc2e19', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('9655d361-fa72-4b95-a3fa-121009a00458', '1cfe4238-9b0c-4611-abea-ddd20b4cc415'),
    ('1dde1375-a88a-4ddf-9ef6-c7b14bb1db96', '9220c03b-b0b5-46af-a821-249e2a97dcaa'),
    ('25c3206d-2b5e-4bd9-aa52-8d20cc80c924', '9220c03b-b0b5-46af-a821-249e2a97dcaa'),
    ('dc88abd5-fa65-4b5b-90e2-ce459f5d5200', '9220c03b-b0b5-46af-a821-249e2a97dcaa'),
    ('b425bbe1-2955-4f81-8a30-7c172a980d0f', '9220c03b-b0b5-46af-a821-249e2a97dcaa'),
    ('8e37d0ee-e1cd-4ec2-830b-8d573290a611', '9220c03b-b0b5-46af-a821-249e2a97dcaa'),
    ('675eeddd-3ba6-4068-bec9-83703081bd70', '9220c03b-b0b5-46af-a821-249e2a97dcaa');

insert into agent_address (address_id, agent_id) values
    ('04fe195c-5409-4657-a009-3732eedf1f6c', 'b38a64e2-299b-4a05-bc30-5a45dd2ebdc0'),
    ('7df3aad6-03bd-4724-9047-bb96403bdc16', 'c72721c4-437f-4a06-b3cc-00b9a86056bc'),
    ('b65ac4e5-a5a8-46d4-897e-a7d080ff147e', 'c72721c4-437f-4a06-b3cc-00b9a86056bc');