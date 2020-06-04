insert into fuelType (type, tankCapacity, gas, deleted) values
    ('diesel', '50l', true, false),
    ('diesel', '60l', true, false),
    ('diesel', '70l', true, false),
    ('diesel', '80l', true, false),
    ('benzine', '50l', true, false),
    ('benzine', '60l', true, false),
    ('benzine', '70l', true, false),
    ('benzine', '80l', true, false);

insert into gearshiftType (type, numberOfGears, deleted) values
    ('automatic', 'five'),
    ('automatic', 'six'),
    ('automatic', 'seven'),
    ('automatic', 'eight'),
    ('automatic', 'nine'),
    ('automatic', 'ten'),
    ('manual', 'four'),
    ('manual', 'five'),
    ('manual', 'six');

insert into carClass (name, description, deleted) values
    ('SUV', 'Sport utility vehicle (SUV) is a type of automobile that combine elements of road-going passenger cars with features from off-road vehicles, such as raised ground clearance and four-wheel drive.', false),
    ('Old Timer', 'A classic car is an older automobile, typically 20 years or older, though definitions vary. The common theme is of an older car of sufficient historical interest to be collectable and worth preserving or restoring rather than scrapping.', false),
    ('Coupe', 'A coupé or coupe is a passenger car with a sloping or truncated rear roofline and generally two doors (although several four-door cars have also been marketed as coupés).', false),
    ('Sedan', 'Sedans first recorded use as a name for a car body was in 1912. The name comes from a 17th-century development of a litter, the sedan chair, a one-person enclosed box with windows and carried by porters.', false),
    ('Hatchback', 'A hatchback is a car with a hatch-type rear door that often opens upwards and often a shared volume for the passenger and cargo areas.', false);

insert into carBrand (name, country, deleted) values
    ('Jeep', 'USA', false),
    ('Ford', 'USA', false),
    ('Mazda', 'Japan', false),
    ('Fiat', 'Italy', false),
    ('Seat', 'Spain', false),
    ('Volkswagen', 'Germany', false);