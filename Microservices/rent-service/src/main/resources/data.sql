insert into request (id, customer_id, status, reception_date, deleted, pick_up_address) values
    ('100abd9a-aa4c-437d-ac4d-dc0d117616ba', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'PAID', '2020-06-11', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('3d8fc1fa-1c0e-467d-ba75-1d2df016b538', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'PAID', '2020-06-01', 'false', 'Serbia,Beograd, Dusana Savica, 21'),
    ('84481f50-7deb-4b8c-bbf5-fcd52e6b20fe', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'PAID', '2020-05-17', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('d0a328f2-de8e-434f-9b47-8a2231ef3b7e', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'RESERVED', '2020-06-13', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('2bd2a882-28ca-40be-afd7-4be12edc2e19', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'RESERVED', '2020-06-16', 'false', 'Serbia,Nis, Cara Lazara, 12'),
    ('9655d361-fa72-4b95-a3fa-121009a00458', '1cfe4238-9b0c-4611-abea-ddd20b4cc415', 'CANCELED', '2020-04-16', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('1dde1375-a88a-4ddf-9ef6-c7b14bb1db96', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'PAID', '2020-06-11', 'false', 'Serbia,Nis, Cara Lazara, 12'),
    ('25c3206d-2b5e-4bd9-aa52-8d20cc80c924', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'PAID', '2020-06-01', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('dc88abd5-fa65-4b5b-90e2-ce459f5d5200', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'PAID', '2020-05-17', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('b425bbe1-2955-4f81-8a30-7c172a980d0f', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'RESERVED', '2020-06-13', 'false', 'Serbia,Beograd, Dusana Savica, 21'),
    ('8e37d0ee-e1cd-4ec2-830b-8d573290a611', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'RESERVED', '2020-06-16', 'false', 'Serbia,Novi Sad, Pionirska, 21'),
    ('675eeddd-3ba6-4068-bec9-83703081bd70', '9220c03b-b0b5-46af-a821-249e2a97dcaa', 'CANCELED', '2020-04-16', 'false', 'Serbia,Nis, Cara Lazara, 12');

insert into request_ad (id, request_id, ad_id, pick_up_date, return_date, pick_up_time, return_time) values
    ('e7f986f5-90ff-421b-91da-351e824d0bf5', '100abd9a-aa4c-437d-ac4d-dc0d117616ba', '991938f8-4834-421c-b48e-c2e28e06aae9', '2020-06-20', '2020-06-23', '12:30', '07:30'),
    ('fc6684c4-645e-45b2-9ea8-d18f2c3f845d', '100abd9a-aa4c-437d-ac4d-dc0d117616ba', '6a22b2eb-705e-4311-ab90-bc438bc226fe', '2020-06-20', '2020-06-23', '11:00', '12:00'),
    ('341c894d-f695-4f07-809e-21eb808e1a31', '100abd9a-aa4c-437d-ac4d-dc0d117616ba', '1124e496-a070-4b6e-b9c2-1b5e9aa00b60', '2020-06-20', '2020-06-23', '10:30', '11:00'),
    ('3360ded8-2d1d-43ae-8111-f947d1567e9c', '3d8fc1fa-1c0e-467d-ba75-1d2df016b538', '6a22b2eb-705e-4311-ab90-bc438bc226fe', '2020-06-15', '2020-06-17', '14:30', '14:00'),
    ('91589299-79bb-4797-8604-35e8e2853130', '84481f50-7deb-4b8c-bbf5-fcd52e6b20fe', '1124e496-a070-4b6e-b9c2-1b5e9aa00b60', '2020-05-25', '2020-05-27', '12:00', '14:30'),
    ('9a110421-c004-4db7-acf1-d35d7e50f44d', 'd0a328f2-de8e-434f-9b47-8a2231ef3b7e', '991938f8-4834-421c-b48e-c2e28e06aae9', '2020-06-25', '2020-06-27', '10:30', '11:00'),
    ('a79a751a-2c13-49c7-9a98-67833e39a396', 'd0a328f2-de8e-434f-9b47-8a2231ef3b7e', '1124e496-a070-4b6e-b9c2-1b5e9aa00b60', '2020-06-25', '2020-06-27', '08:30', '09:30'),
    ('deedf02d-96e9-4106-b4cf-8c11f0393917', '2bd2a882-28ca-40be-afd7-4be12edc2e19', '87ea3abc-1bef-4c17-9ce6-4938e947a917', '2020-06-20', '2020-06-23', '07:00', '08:00'),
    ('e6d3660f-1d63-4e83-9ea8-ee89317e3e55', '9655d361-fa72-4b95-a3fa-121009a00458', '92cc7fc1-5823-40cb-8b36-6162f1cdb5a0', '2020-06-20', '2020-06-23', '15:30', '17:30'),
    ('1b59339c-94d4-464a-b664-b90c34c6bbf9', '1dde1375-a88a-4ddf-9ef6-c7b14bb1db96', '87ea3abc-1bef-4c17-9ce6-4938e947a917', '2020-06-20', '2020-06-23', '16:00', '18:00'),
    ('a3ef8896-94fa-4df3-9c5f-9f74bef61834', '1dde1375-a88a-4ddf-9ef6-c7b14bb1db96', '6a22b2eb-705e-4311-ab90-bc438bc226fe', '2020-06-25', '2020-06-27', '17:00', '18:00'),
    ('349f6608-295d-433c-9efd-eb843dffb8f1', '1dde1375-a88a-4ddf-9ef6-c7b14bb1db96', '92cc7fc1-5823-40cb-8b36-6162f1cdb5a0', '2020-06-25', '2020-06-27', '14:00', '15:30'),
    ('7c93ed87-61a9-4449-bcbc-2c8e2bfa7d22', '25c3206d-2b5e-4bd9-aa52-8d20cc80c924', '991938f8-4834-421c-b48e-c2e28e06aae9', '2020-06-25', '2020-06-27', '18:30', '15:30'),
    ('321e9b3a-69d4-47ba-8fb4-3c4f1aa8114b', 'dc88abd5-fa65-4b5b-90e2-ce459f5d5200', '1124e496-a070-4b6e-b9c2-1b5e9aa00b60', '2020-06-25', '2020-06-27', '21:00', '22:30'),
    ('37c49f60-099b-4c20-b883-63898152594e', 'b425bbe1-2955-4f81-8a30-7c172a980d0f', '87ea3abc-1bef-4c17-9ce6-4938e947a917', '2020-06-25', '2020-06-27', '14:00', '22:00'),
    ('0c330648-9b1a-45e7-9599-fc2adeeaf426', 'b425bbe1-2955-4f81-8a30-7c172a980d0f', '6a22b2eb-705e-4311-ab90-bc438bc226fe', '2020-06-25', '2020-06-27', '10:00', '21:00'),
    ('f7495b1b-c76d-429e-8ab0-49d595379dab', '8e37d0ee-e1cd-4ec2-830b-8d573290a611', '1124e496-a070-4b6e-b9c2-1b5e9aa00b60', '2020-06-25', '2020-06-27', '10:30', '11:00'),
    ('3e571fd5-7d33-4f4a-9f9b-f61997472fda', '675eeddd-3ba6-4068-bec9-83703081bd70', '6a22b2eb-705e-4311-ab90-bc438bc226fe', '2020-06-25', '2020-06-27', '11:30', '15:30');

