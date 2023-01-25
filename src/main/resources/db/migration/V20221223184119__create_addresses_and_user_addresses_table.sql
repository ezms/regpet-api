CREATE TABLE public.addresses(
    address_id UUID PRIMARY KEY,
    zip_code VARCHAR(8) NOT NULL,
    state VARCHAR(20) NOT NULL,
    city VARCHAR(30) NOT NULL,
    district VARCHAR(90) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(100)
);

CREATE TABLE public.users_addresses(
    user_id UUID NOT NULL,
    address_id UUID NOT NULL,
    CONSTRAINT fk_users_users_addresses FOREIGN KEY(user_id)
        REFERENCES public.users(user_id),
    CONSTRAINT fk_address_users_addresses FOREIGN KEY(address_id)
        REFERENCES public.addresses(address_id)
);
