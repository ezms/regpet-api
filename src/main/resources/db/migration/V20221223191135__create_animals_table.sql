CREATE TABLE public.animals(
    animal_id UUID PRIMARY KEY,
    species VARCHAR(100) NOT NULL,
    breed VARCHAR(100) NOT NULL,
    name VARCHAR(150),
    found_on TIMESTAMP NOT NULL,
    gender SMALLINT NOT NULL,
    description TEXT,
    status SMALLINT NOT NULL
);
