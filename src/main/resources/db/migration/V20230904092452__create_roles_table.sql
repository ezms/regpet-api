CREATE TABLE public.roles(
    role_id UUID PRIMARY KEY,
    admin SMALLINT,
    user SMALLINT,
    guest SMALLINT,
    animal_protector SMALLINT,
    ngo SMALLINT,
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_roles FOREIGN KEY (user_id)
        REFERENCES public.users(user_id)
);