CREATE TABLE public.denunciations(
    denunciation_id UUID PRIMARY KEY,
    denunciation_date DATE NOT NULL,
    details TEXT NOT NULL,
    user_id UUID NOT NULL,
    address_id UUID NOT NULL,
    CONSTRAINT fk_users_denunciations FOREIGN KEY(user_id)
        REFERENCES public.users(user_id),
    CONSTRAINT fk_addresses_denunciations FOREIGN KEY(address_id)
        REFERENCES public.addresses(address_id)
);
