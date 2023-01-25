CREATE TABLE public.animals_denunciations(
    animal_id UUID NOT NULL,
    denunciation_id UUID NOT NULL,
    CONSTRAINT fk_animals_animals_denunciations FOREIGN KEY(animal_id)
        REFERENCES public.animals(animal_id),
    CONSTRAINT fk_denunciations_animals_denunciations FOREIGN KEY(denunciation_id)
        REFERENCES public.denunciations(denunciation_id)
);
