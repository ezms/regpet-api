CREATE TABLE public.guests(
    guest_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_guests FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
);

CREATE TABLE public.guest_contacts(
    guest_contact_id UUID PRIMARY KEY,
    instagram_url VARCHAR,
    facebook_url VARCHAR,
    twitter_url VARCHAR,
    cellphone_number VARCHAR(20) NOT NULL,
    guest_id UUID NOT NULL,
    CONSTRAINT fk_guests_guest_contacts FOREIGN KEY(guest_id)
        REFERENCES public.guests(guest_id)
);
