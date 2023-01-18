CREATE TABLE public.volunteers(
    volunteer_id UUID PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    ngo_id UUID NOT NULL,
    CONSTRAINT fk_ngos_volunteers FOREIGN KEY(ngo_id)
        REFERENCES public.ngos(ngo_id)
);

CREATE TABLE public.employees(
    employee_id UUID PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    ngo_id UUID NOT NULL,
    CONSTRAINT fk_ngos_employees FOREIGN KEY(ngo_id)
        REFERENCES public.ngos(ngo_id)
);
