CREATE TABLE public.rescues(
    rescue_id UUID PRIMARY KEY,
    rescue_date DATE NOT NULL,
    story TEXT,
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_rescues FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
);
