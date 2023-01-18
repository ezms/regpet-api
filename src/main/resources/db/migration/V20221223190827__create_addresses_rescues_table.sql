CREATE TABLE public.addresses_rescues(
    address_rescue_id UUID PRIMARY KEY,
    address_id UUID NOT NULL,
    rescue_id UUID NOT NULL,
    CONSTRAINT fk_addresses_addresses_rescues FOREIGN KEY(address_id)
        REFERENCES public.addresses(address_id),
    CONSTRAINT fk_rescues_addresses_rescues FOREIGN KEY(rescue_id)
        REFERENCES public.rescues(rescue_id)
);
