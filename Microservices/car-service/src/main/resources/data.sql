insert into car_brand (id, name, country, deleted) values
('52adb2e9-5f79-4839-bb9b-1b66bdd693c2', 'Audi', 'Germany', 'false'),
('c1fc00da-de6c-4a42-89a5-792d462af560', 'BMW', 'Germany', 'false'),
('ecfdf192-b39d-49b9-9588-cba7667313a5', 'Volkswagen', 'Germany', 'false'),
('c17bad83-8a5f-4153-b7a3-e986fb24ecfb', 'Opel', 'Germany', 'false'),
('4fec0ab2-6cc4-42e3-bcfa-1d02eeb06063', 'Peugeot', 'France', 'false'),
('baae6743-61c9-4467-87f9-aaf0e0ce4605', 'Citroen', 'France', 'false'),
('0c86e027-57c8-403a-89ef-91fb9117d042', 'Ford', 'USA', 'false'),
('0b48f7ed-8b8f-418b-beda-0bcd6eafee14', 'Seat', 'Spain', 'false'),
('2078cfb3-9f17-4933-9d40-67a9136395b7', 'Toyota', 'Japan', 'false'),
('1c2a425e-89d6-43ae-b1a1-4ed2ea2a11dc', 'Zastava', 'Serbia', 'false'),
('75a482e5-3ad4-43a7-ac6d-74ebc53f8528', 'Fiat', 'Italy', 'false');

insert into car_class (id, name, description, deleted) values
('3f0ac840-66c8-4c23-b0ff-54cf8c5e760b', 'Small city', 'Small city cars perfect for parking and city crowd.', 'false'),
('28cea716-dbe8-410b-ab5a-0f67c63f16f3', 'Hatchback', 'Shortened rear part of the body.', 'false'),
('4304edc8-36ea-4dbe-af07-5322ad40558f', 'Mid-size sedan', 'Mid-size limousines.', 'false'),
('b1065642-c87b-4f3b-8c9e-eb2d53556ff9', 'Full-size sedan', 'Full-size limousines.', 'false'),
('76738ec4-fe52-421c-8574-41b8536e7f8b', 'Small SUV', 'Small jeeps.', 'false'),
('9d5bb205-c685-4ed0-a000-c9db919ccdc3', 'MiniVan', 'Small vans.', 'false'),
('e3e63927-846f-4708-bdb8-1bfe6f3e1ef4', 'Full-size SUV', 'Jeeps.', 'false');

insert into car_model (id, name, deleted, car_brand_id, car_class_id) values
('c97ab058-9d6f-4289-a787-54e97f327dae', 'A3', 'false', '52adb2e9-5f79-4839-bb9b-1b66bdd693c2', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('df5de38e-0fee-4e3c-a630-41b1eca2647b', 'A6', 'false', '52adb2e9-5f79-4839-bb9b-1b66bdd693c2', 'b1065642-c87b-4f3b-8c9e-eb2d53556ff9'),
('dbaccebd-5491-41db-95bb-bac04300f1bb', 'Q5', 'false', '52adb2e9-5f79-4839-bb9b-1b66bdd693c2', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('ced52fd7-752d-462e-b75d-fb6a45af83a5', '320', 'false', 'c1fc00da-de6c-4a42-89a5-792d462af560', '4304edc8-36ea-4dbe-af07-5322ad40558f'),
('bb7f9619-9c84-442a-a5b7-ea73adedc651', '520', 'false', 'c1fc00da-de6c-4a42-89a5-792d462af560', 'b1065642-c87b-4f3b-8c9e-eb2d53556ff9'),
('c1a47ee4-8d05-4b5e-8d43-f45f3cbea8d9', 'X4', 'false', 'c1fc00da-de6c-4a42-89a5-792d462af560', '76738ec4-fe52-421c-8574-41b8536e7f8b'),
('cdf968a4-0b7e-46f2-b87a-464560f8b602', 'X5', 'false', 'c1fc00da-de6c-4a42-89a5-792d462af560', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('c071a881-ef8c-45ad-82d8-2d306a946de2', 'Tiguan', 'false', 'ecfdf192-b39d-49b9-9588-cba7667313a5', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('646451c2-3e6d-47bd-a94d-2a0508589c7b', 'Toureg', 'false', 'ecfdf192-b39d-49b9-9588-cba7667313a5', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('1a485b64-ae60-4051-99fc-76db4d067887', 'Passat', 'false', 'ecfdf192-b39d-49b9-9588-cba7667313a5', '4304edc8-36ea-4dbe-af07-5322ad40558f'),
('d19159ac-9ec3-4d27-9de1-83cbc78f1ea3', 'Golf 4', 'false', 'ecfdf192-b39d-49b9-9588-cba7667313a5', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('807812c2-88d3-4fc5-83bf-f1f706951f2a', 'Golf 5', 'false', 'ecfdf192-b39d-49b9-9588-cba7667313a5', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('faf1f1f0-f3fc-4297-acc3-d734eeaed3c7', 'Astra', 'false', 'c17bad83-8a5f-4153-b7a3-e986fb24ecfb', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('5276b54e-c459-4b19-989e-83e1351679db', 'Insignia', 'false', 'c17bad83-8a5f-4153-b7a3-e986fb24ecfb', '4304edc8-36ea-4dbe-af07-5322ad40558f'),
('4cddcc84-6fae-49e8-abed-58bdbef4f78f', 'Meriva', 'false', 'c17bad83-8a5f-4153-b7a3-e986fb24ecfb', '9d5bb205-c685-4ed0-a000-c9db919ccdc3'),
('40482c0f-7d36-49f4-bb2a-8a0fea783358', '208', 'false', '4fec0ab2-6cc4-42e3-bcfa-1d02eeb06063', '3f0ac840-66c8-4c23-b0ff-54cf8c5e760b'),
('c4e2824e-7060-45af-8619-fb50daa526a9', '307', 'false', '4fec0ab2-6cc4-42e3-bcfa-1d02eeb06063', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('adc5a8df-7867-409c-90ca-c322c533b895', 'Fiesta', 'false', 'baae6743-61c9-4467-87f9-aaf0e0ce4605', '3f0ac840-66c8-4c23-b0ff-54cf8c5e760b'),
('67d4faef-f998-487a-b95f-a42cdec35542', 'Focus', 'false', 'baae6743-61c9-4467-87f9-aaf0e0ce4605', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('e34ad23e-ec98-46ce-a9ac-b4b85ea0e409', 'Mondeo', 'false', 'baae6743-61c9-4467-87f9-aaf0e0ce4605', '4304edc8-36ea-4dbe-af07-5322ad40558f'),
('e6045a4c-69b0-414b-ad5c-1a2df7270a93', 'Kuga', 'false', 'baae6743-61c9-4467-87f9-aaf0e0ce4605', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('c6f30d41-f1dc-4bbd-9806-a95dddcaa112', 'Ibitza', 'false', '0b48f7ed-8b8f-418b-beda-0bcd6eafee14', '3f0ac840-66c8-4c23-b0ff-54cf8c5e760b'),
('7a2cd87b-e99d-4654-9e91-a34276e2172f', 'Corolla', 'false', '2078cfb3-9f17-4933-9d40-67a9136395b7', '4304edc8-36ea-4dbe-af07-5322ad40558f'),
('51d7f8c6-8e09-484b-b13b-43e0538d6f64', 'Yaris', 'false', '2078cfb3-9f17-4933-9d40-67a9136395b7', '3f0ac840-66c8-4c23-b0ff-54cf8c5e760b'),
('f106939b-2503-4d0b-a408-f1e178e5be3d', 'RAW 5', 'false', '2078cfb3-9f17-4933-9d40-67a9136395b7', 'e3e63927-846f-4708-bdb8-1bfe6f3e1ef4'),
('62170159-dad3-4e84-9936-4d69e3b5fadf', 'Yugo 45', 'false', '1c2a425e-89d6-43ae-b1a1-4ed2ea2a11dc', '3f0ac840-66c8-4c23-b0ff-54cf8c5e760b'),
('2e592145-a698-4c3e-9091-2fc298cfaf08', 'Stilo', 'false', '75a482e5-3ad4-43a7-ac6d-74ebc53f8528', '28cea716-dbe8-410b-ab5a-0f67c63f16f3'),
('f4b139b0-7920-48df-b963-0695276ac3ed', 'Tipo', 'false', '75a482e5-3ad4-43a7-ac6d-74ebc53f8528', '4304edc8-36ea-4dbe-af07-5322ad40558f');

insert into gearshift_type(id, type, number_of_gears, deleted) values
('21a367e0-fefb-457a-8bcc-bf3479f107a4', 'Manuel', 'Four', 'true'),
('26499388-9c1d-4836-972c-ba114a8753d5', 'Manuel', 'Five', 'false'),
('2f823dfa-5fc1-4ac7-b496-d976aac53a66', 'Manuel', 'Six', 'false'),
('d547d724-68ca-4a00-89a1-a9b6241c5fa7', 'Automatic', 'Eight', 'false'),
('49d60643-6db1-4fb3-be69-9f1a3ba66ef5', 'Automatic', 'Nine', 'false'),
('b96f7968-e076-4523-a93a-6b7039f6d1a4', 'Automatic', 'Ten', 'false');

insert into fuel_type(id, type, tank_capacity, gas, deleted) values
('9989f5f8-3142-4ee6-9373-f7d09dc1b005', 'Diesel', '50L', 'false', 'false'),
('4b6268de-ae0b-4eed-805e-7c4d0d813384', 'Diesel', '50L', 'true', 'false'),
('59dcb7a7-0d7a-4224-a6ba-9cf0388c9540', 'Benzine', '50L', 'false', 'false'),
('44e7a371-7796-4bcb-a1a3-a85aa4b33699', 'Benzine', '50L', 'true', 'false'),
('9d492fe6-7b85-4d8a-b785-1069f67a0949', 'Diesel', '60L', 'false', 'false'),
('c2cce5ed-723c-4cdc-8da3-2b56917d935e', 'Diesel', '60L', 'true', 'false'),
('512bebd7-32aa-4ad8-9294-0608f29460d9', 'Benzine', '60L', 'false', 'false'),
('d560edb1-b2d9-474b-9dbd-622c66bb296f', 'Benzine', '60L', 'true', 'false'),
('8363c844-8900-43e6-962f-2d7eb5031bc2', 'Diesel', '80L', 'false', 'false'),
('8efd6379-3f8c-4c9b-b39f-03e2efee6a14', 'Diesel', '80L', 'true', 'false'),
('42c6146b-1ba5-4737-a25b-c76406f273e1', 'Benzine', '80L', 'false', 'false'),
('7e3d3e0c-03d6-4acf-a620-f09fe00af45d', 'Benzine', '80L', 'true', 'false'),
('446e7318-ba50-4b5e-949c-8c1baf8f0a36', 'Diesel', '90L', 'false', 'false'),
('574930be-7738-48a3-8dc2-9b503b0fe7e3', 'Diesel', '90L', 'true', 'true'),
('0dc3ab55-1827-46b6-a2ad-309d83bbd28f', 'Benzine', '90L', 'false', 'false'),
('5343655c-07cf-45cf-b911-68fdc33dbf7d', 'Benzine', '90L', 'true', 'true');

--insert into fuelType (type, tankCapacity, gas, deleted) values
--    ('diesel', '50l', true, false),
--    ('diesel', '60l', true, false),
--    ('diesel', '70l', true, false),
--    ('diesel', '80l', true, false),
--    ('benzine', '50l', true, false),
--    ('benzine', '60l', true, false),
--    ('benzine', '70l', true, false),
--    ('benzine', '80l', true, false);
--
--insert into gearshiftType (type, numberOfGears, deleted) values
--    ('automatic', 'five'),
--    ('automatic', 'six'),
--    ('automatic', 'seven'),
--    ('automatic', 'eight'),
--    ('automatic', 'nine'),
--    ('automatic', 'ten'),
--    ('manual', 'four'),
--    ('manual', 'five'),
--    ('manual', 'six');
--
--insert into carClass (name, description, deleted) values
--    ('SUV', 'Sport utility vehicle (SUV) is a type of automobile that combine elements of road-going passenger cars with features from off-road vehicles, such as raised ground clearance and four-wheel drive.', false),
--    ('Old Timer', 'A classic car is an older automobile, typically 20 years or older, though definitions vary. The common theme is of an older car of sufficient historical interest to be collectable and worth preserving or restoring rather than scrapping.', false),
--    ('Coupe', 'A coupé or coupe is a passenger car with a sloping or truncated rear roofline and generally two doors (although several four-door cars have also been marketed as coupés).', false),
--    ('Sedan', 'Sedans first recorded use as a name for a car body was in 1912. The name comes from a 17th-century development of a litter, the sedan chair, a one-person enclosed box with windows and carried by porters.', false),
--    ('Hatchback', 'A hatchback is a car with a hatch-type rear door that often opens upwards and often a shared volume for the passenger and cargo areas.', false);
--
--insert into carBrand (name, country, deleted) values
--    ('Jeep', 'USA', false),
--    ('Ford', 'USA', false),
--    ('Mazda', 'Japan', false),
--    ('Fiat', 'Italy', false),
--    ('Seat', 'Spain', false),
--    ('Volkswagen', 'Germany', false);