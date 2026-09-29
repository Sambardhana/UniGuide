--
-- PostgreSQL database dump
--

\restrict AcfJI3074VRBBeIzpUYxf1jHtathzKkX2BBf2HYL9h5D1erAxFe11cmIliDwk96

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: activities; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.activities (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    category character varying(100),
    description text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    coordinator_name character varying(150),
    coordinator_contact character varying(20),
    location_id bigint
);


--
-- Name: activities_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: activities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;


--
-- Name: campus_locations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.campus_locations (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    category character varying(100),
    description text,
    building_name character varying(150),
    latitude numeric(10,7),
    longitude numeric(10,7),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    code character varying(30),
    floor_count integer,
    qr_code_key character varying(100),
    CONSTRAINT chk_latitude CHECK (((latitude IS NULL) OR ((latitude >= ('-90'::integer)::numeric) AND (latitude <= (90)::numeric)))),
    CONSTRAINT chk_longitude CHECK (((longitude IS NULL) OR ((longitude >= ('-180'::integer)::numeric) AND (longitude <= (180)::numeric))))
);


--
-- Name: campus_locations_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.campus_locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: campus_locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.campus_locations_id_seq OWNED BY public.campus_locations.id;


--
-- Name: clubs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.clubs (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    description text,
    category character varying(100),
    meeting_location text,
    contact_name character varying(150),
    contact_number character varying(20),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: clubs_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.clubs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: clubs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.clubs_id_seq OWNED BY public.clubs.id;


--
-- Name: contacts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.contacts (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    category character varying(100),
    phone_number character varying(20),
    email character varying(150),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    designation character varying(100),
    office_location character varying(150),
    department_id bigint
);


--
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.contacts_id_seq OWNED BY public.contacts.id;


--
-- Name: courses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.courses (
    id bigint NOT NULL,
    department_id bigint NOT NULL,
    title character varying(150) CONSTRAINT courses_name_not_null NOT NULL,
    code character varying(30) NOT NULL,
    description text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    credits integer,
    semester integer
);


--
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- Name: departments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.departments (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    code character varying(20) NOT NULL,
    description text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    contact_email character varying(100),
    contact_phone character varying(20),
    location_id bigint
);


--
-- Name: departments_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.departments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: departments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.departments_id_seq OWNED BY public.departments.id;


--
-- Name: events; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.events (
    id bigint NOT NULL,
    title character varying(200) NOT NULL,
    description text,
    venue text,
    start_date timestamp with time zone CONSTRAINT events_start_time_not_null NOT NULL,
    end_date timestamp with time zone,
    organizer character varying(150),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    registration_link character varying(500),
    location_id bigint,
    CONSTRAINT chk_event_date CHECK (((end_date IS NULL) OR (end_date >= start_date)))
);


--
-- Name: events_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.events_id_seq OWNED BY public.events.id;


--
-- Name: facilities; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.facilities (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    description text,
    contact_number character varying(20),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    type character varying(50),
    opening_hours character varying(100),
    location_id bigint
);


--
-- Name: facilities_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.facilities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: facilities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.facilities_id_seq OWNED BY public.facilities.id;


--
-- Name: hostels; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.hostels (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    description text,
    capacity integer,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    type character varying(50),
    warden_name character varying(100),
    warden_contact character varying(20),
    location_id bigint
);


--
-- Name: hostels_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hostels_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: hostels_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.hostels_id_seq OWNED BY public.hostels.id;


--
-- Name: notices; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.notices (
    id bigint NOT NULL,
    title character varying(200) NOT NULL,
    content text NOT NULL,
    published_at timestamp with time zone,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    category character varying(50),
    attachment_url character varying(500),
    is_pinned boolean DEFAULT false NOT NULL,
    author_id bigint,
    department_id bigint
);


--
-- Name: notices_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.notices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: notices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.notices_id_seq OWNED BY public.notices.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(100) CONSTRAINT users_login_identifier_not_null NOT NULL,
    password text CONSTRAINT users_password_hash_not_null NOT NULL,
    role character varying(20) NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    full_name character varying(100) NOT NULL,
    phone_number character varying(20),
    student_id character varying(30),
    department_id bigint,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['STUDENT'::character varying, 'TEACHER'::character varying, 'ADMIN'::character varying])::text[])))
);


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: activities id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);


--
-- Name: campus_locations id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.campus_locations ALTER COLUMN id SET DEFAULT nextval('public.campus_locations_id_seq'::regclass);


--
-- Name: clubs id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.clubs ALTER COLUMN id SET DEFAULT nextval('public.clubs_id_seq'::regclass);


--
-- Name: contacts id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts ALTER COLUMN id SET DEFAULT nextval('public.contacts_id_seq'::regclass);


--
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- Name: departments id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departments ALTER COLUMN id SET DEFAULT nextval('public.departments_id_seq'::regclass);


--
-- Name: events id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.events ALTER COLUMN id SET DEFAULT nextval('public.events_id_seq'::regclass);


--
-- Name: facilities id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facilities ALTER COLUMN id SET DEFAULT nextval('public.facilities_id_seq'::regclass);


--
-- Name: hostels id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hostels ALTER COLUMN id SET DEFAULT nextval('public.hostels_id_seq'::regclass);


--
-- Name: notices id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notices ALTER COLUMN id SET DEFAULT nextval('public.notices_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: activities activities_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_name_key UNIQUE (name);


--
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);


--
-- Name: campus_locations campus_locations_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT campus_locations_name_key UNIQUE (name);


--
-- Name: campus_locations campus_locations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT campus_locations_pkey PRIMARY KEY (id);


--
-- Name: clubs clubs_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_name_key UNIQUE (name);


--
-- Name: clubs clubs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_pkey PRIMARY KEY (id);


--
-- Name: contacts contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- Name: courses courses_code_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_code_key UNIQUE (code);


--
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- Name: departments departments_code_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_code_key UNIQUE (code);


--
-- Name: departments departments_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_name_key UNIQUE (name);


--
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- Name: facilities facilities_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT facilities_name_key UNIQUE (name);


--
-- Name: facilities facilities_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT facilities_pkey PRIMARY KEY (id);


--
-- Name: hostels hostels_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT hostels_name_key UNIQUE (name);


--
-- Name: hostels hostels_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT hostels_pkey PRIMARY KEY (id);


--
-- Name: notices notices_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT notices_pkey PRIMARY KEY (id);


--
-- Name: campus_locations uq_campus_locations_code; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT uq_campus_locations_code UNIQUE (code);


--
-- Name: campus_locations uq_campus_locations_qr_code_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT uq_campus_locations_qr_code_key UNIQUE (qr_code_key);


--
-- Name: users users_login_identifier_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_identifier_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: idx_activities_category; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_activities_category ON public.activities USING btree (category);


--
-- Name: idx_activities_location_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_activities_location_id ON public.activities USING btree (location_id);


--
-- Name: idx_campus_locations_category; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_campus_locations_category ON public.campus_locations USING btree (category);


--
-- Name: idx_clubs_category; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_clubs_category ON public.clubs USING btree (category);


--
-- Name: idx_contacts_category; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_contacts_category ON public.contacts USING btree (category);


--
-- Name: idx_contacts_department_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_contacts_department_id ON public.contacts USING btree (department_id);


--
-- Name: idx_courses_department_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_courses_department_id ON public.courses USING btree (department_id);


--
-- Name: idx_courses_semester; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_courses_semester ON public.courses USING btree (semester);


--
-- Name: idx_events_location_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_events_location_id ON public.events USING btree (location_id);


--
-- Name: idx_events_start_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_events_start_date ON public.events USING btree (start_date);


--
-- Name: idx_events_start_time; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_events_start_time ON public.events USING btree (start_date);


--
-- Name: idx_facilities_location_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_facilities_location_id ON public.facilities USING btree (location_id);


--
-- Name: idx_facilities_type; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_facilities_type ON public.facilities USING btree (type);


--
-- Name: idx_hostels_location_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_hostels_location_id ON public.hostels USING btree (location_id);


--
-- Name: idx_hostels_type; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_hostels_type ON public.hostels USING btree (type);


--
-- Name: idx_notices_category; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_notices_category ON public.notices USING btree (category);


--
-- Name: idx_notices_department_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_notices_department_id ON public.notices USING btree (department_id);


--
-- Name: idx_notices_published_at; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_notices_published_at ON public.notices USING btree (published_at);


--
-- Name: idx_users_department_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_users_department_id ON public.users USING btree (department_id);


--
-- Name: idx_users_role; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_users_role ON public.users USING btree (role);


--
-- Name: activities fk_activities_location; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT fk_activities_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: contacts fk_contacts_department; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT fk_contacts_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: courses fk_courses_department; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT fk_courses_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: departments fk_departments_location; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT fk_departments_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: events fk_events_location; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_events_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: facilities fk_facilities_location; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT fk_facilities_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: hostels fk_hostels_location; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT fk_hostels_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: notices fk_notices_author; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fk_notices_author FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: notices fk_notices_department; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fk_notices_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: users fk_users_department; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_users_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- PostgreSQL database dump complete
--

\unrestrict AcfJI3074VRBBeIzpUYxf1jHtathzKkX2BBf2HYL9h5D1erAxFe11cmIliDwk96

