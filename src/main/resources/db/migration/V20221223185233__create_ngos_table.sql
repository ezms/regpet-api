CREATE TABLE public.ngos(
    ngo_id UUID PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL,
    company_name VARCHAR(150) NOT NULL,
    trading_name VARCHAR(100),
    user_id UUID NOT NULL,
    CONSTRAINT fk_users_ngos FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
);
