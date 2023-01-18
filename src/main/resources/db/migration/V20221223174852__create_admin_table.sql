CREATE TABLE public.admins(
    admin_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_admin FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
);

ALTER TABLE public.users
    ADD COLUMN admin_id UUID NOT NULL,
    ADD CONSTRAINT fk_admin_users FOREIGN KEY(admin_id)
        REFERENCES public.admins(admin_id);
