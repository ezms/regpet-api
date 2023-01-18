CREATE TABLE public.users(
    user_id UUID PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    username VARCHAR(100) NOT NULL,
    bio TEXT,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR,
    phone_number varchar(50),
    profile_photo BYTEA,
    is_active BOOLEAN
);
