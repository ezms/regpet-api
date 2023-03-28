CREATE TABLE public.animals_images(
    animal_image_id UUID PRIMARY KEY,
    last_modified_date DATE,
    name VARCHAR(150) NOT NULL,
    size BIGINT NOT NULL,
    mime VARCHAR(20) NOT NULL,
    image BYTEA NOT NULL,
    key VARCHAR NOT NULL,
    image_url VARCHAR,
    animal_id UUID NOT NULL,
    CONSTRAINT fk_animals_animals_images FOREIGN KEY(animal_id)
        REFERENCES public.animals(animal_id)
);
