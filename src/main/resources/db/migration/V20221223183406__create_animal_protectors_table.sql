CREATE TABLE public.animal_protectors(
    animal_protector_id UUID PRIMARY KEY,
    organization_name VARCHAR(100) NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_animal_protectors FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
);
