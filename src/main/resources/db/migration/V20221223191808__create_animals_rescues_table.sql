CREATE TABLE public.animals_rescues(
    animal_rescue_id UUID PRIMARY KEY,
    animal_id UUID NOT NULL,
    rescue_id UUID NOT NULL,
    CONSTRAINT fk_animals_animals_rescues FOREIGN KEY(animal_id)
        REFERENCES public.animals(animal_id),
    CONSTRAINT fk_rescues_animals_rescues FOREIGN KEY(rescue_id)
        REFERENCES public.rescues(rescue_id)
);
