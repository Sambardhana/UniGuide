--
-- PostgreSQL database dump
--

\restrict 89wDYT4mRUNgVeJM49ZjNzHADCiHACX19XGMEQrqFgNp0WAbUtocXPsZN3964V6

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
-- Name: activities; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.activities OWNER TO postgres;

--
-- Name: activities_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.activities_id_seq OWNER TO postgres;

--
-- Name: activities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;


--
-- Name: campus_locations; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.campus_locations OWNER TO postgres;

--
-- Name: campus_locations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.campus_locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.campus_locations_id_seq OWNER TO postgres;

--
-- Name: campus_locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.campus_locations_id_seq OWNED BY public.campus_locations.id;


--
-- Name: clubs; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.clubs OWNER TO postgres;

--
-- Name: clubs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clubs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clubs_id_seq OWNER TO postgres;

--
-- Name: clubs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clubs_id_seq OWNED BY public.clubs.id;


--
-- Name: contacts; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.contacts OWNER TO postgres;

--
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contacts_id_seq OWNER TO postgres;

--
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contacts_id_seq OWNED BY public.contacts.id;


--
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.courses OWNER TO postgres;

--
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.courses_id_seq OWNER TO postgres;

--
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- Name: departments; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.departments OWNER TO postgres;

--
-- Name: departments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.departments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.departments_id_seq OWNER TO postgres;

--
-- Name: departments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.departments_id_seq OWNED BY public.departments.id;


--
-- Name: events; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.events OWNER TO postgres;

--
-- Name: events_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.events_id_seq OWNER TO postgres;

--
-- Name: events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.events_id_seq OWNED BY public.events.id;


--
-- Name: facilities; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.facilities OWNER TO postgres;

--
-- Name: facilities_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.facilities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.facilities_id_seq OWNER TO postgres;

--
-- Name: facilities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.facilities_id_seq OWNED BY public.facilities.id;


--
-- Name: hostels; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.hostels OWNER TO postgres;

--
-- Name: hostels_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hostels_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.hostels_id_seq OWNER TO postgres;

--
-- Name: hostels_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.hostels_id_seq OWNED BY public.hostels.id;


--
-- Name: notices; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.notices OWNER TO postgres;

--
-- Name: notices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.notices_id_seq OWNER TO postgres;

--
-- Name: notices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notices_id_seq OWNED BY public.notices.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: activities id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);


--
-- Name: campus_locations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campus_locations ALTER COLUMN id SET DEFAULT nextval('public.campus_locations_id_seq'::regclass);


--
-- Name: clubs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clubs ALTER COLUMN id SET DEFAULT nextval('public.clubs_id_seq'::regclass);


--
-- Name: contacts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contacts ALTER COLUMN id SET DEFAULT nextval('public.contacts_id_seq'::regclass);


--
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- Name: departments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments ALTER COLUMN id SET DEFAULT nextval('public.departments_id_seq'::regclass);


--
-- Name: events id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events ALTER COLUMN id SET DEFAULT nextval('public.events_id_seq'::regclass);


--
-- Name: facilities id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facilities ALTER COLUMN id SET DEFAULT nextval('public.facilities_id_seq'::regclass);


--
-- Name: hostels id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hostels ALTER COLUMN id SET DEFAULT nextval('public.hostels_id_seq'::regclass);


--
-- Name: notices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices ALTER COLUMN id SET DEFAULT nextval('public.notices_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: activities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.activities (id, name, category, description, created_at, updated_at, coordinator_name, coordinator_contact, location_id) FROM stdin;
1	Coding Club Activities	Technical	Programming workshops, coding contests and technical sessions	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	Activity Coordinator	9000000020	\N
2	University Football	Sports	Football training and university sports activities	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	Sports Coordinator	9000000021	\N
3	Cultural Activities	Cultural	Music, dance, drama and cultural events	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	Cultural Coordinator	9000000022	\N
\.


--
-- Data for Name: campus_locations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.campus_locations (id, name, category, description, building_name, latitude, longitude, created_at, updated_at, code, floor_count, qr_code_key) FROM stdin;
1	Main Auditorium	Academic	Main university auditorium	Auditorium Block	20.2961000	85.8245000	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	AUD-001	1	MAIN-AUDITORIUM
2	Central Library	Academic	Central university library	Library Block	20.2965000	85.8250000	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	LIB-001	3	CENTRAL-LIBRARY
3	Sports Ground	Sports	Main outdoor sports ground	Sports Complex	20.2970000	85.8255000	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	SPG-001	1	SPORTS-GROUND
\.


--
-- Data for Name: clubs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clubs (id, name, description, category, meeting_location, contact_name, contact_number, created_at, updated_at) FROM stdin;
1	Coding Club	Student club focused on programming and technology	Technical	CSE Seminar Hall	Club Coordinator	9000000030	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30
2	Literary Club	Student club for writing, reading and public speaking	Literary	Student Activity Centre	Club Coordinator	9000000031	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30
3	Photography Club	Student club for photography and visual storytelling	Creative	Student Activity Centre	Club Coordinator	9000000032	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30
\.


--
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contacts (id, name, category, phone_number, email, created_at, updated_at, designation, office_location, department_id) FROM stdin;
1	Student Help Desk	Student Support	9000000050	helpdesk@uniguide.example	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
2	Emergency Services	Emergency	9000000051	emergency@uniguide.example	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
3	Academic Office	Academic	9000000052	academic@uniguide.example	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
\.


--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.courses (id, department_id, title, code, description, created_at, updated_at, credits, semester) FROM stdin;
1	1	B.Tech Computer Science and Engineering	BTECH-CSE	Undergraduate program in Computer Science and Engineering	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	4	1
2	1	B.Tech Artificial Intelligence and Machine Learning	BTECH-AIML	Undergraduate program in Artificial Intelligence and Machine Learning	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	4	1
3	2	B.Tech Electronics and Communication Engineering	BTECH-ECE	Undergraduate program in Electronics and Communication Engineering	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	4	1
\.


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departments (id, name, code, description, created_at, updated_at, contact_email, contact_phone, location_id) FROM stdin;
1	Computer Science and Engineering	CSE	Department of Computer Science and Engineering	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
2	Electronics and Communication Engineering	ECE	Department of Electronics and Communication Engineering	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
\.


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.events (id, title, description, venue, start_date, end_date, organizer, created_at, updated_at, registration_link, location_id) FROM stdin;
1	Freshers Orientation	Orientation program for newly admitted students	Main Auditorium	2026-10-05 10:00:00+05:30	2026-10-05 13:00:00+05:30	Student Affairs	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N
2	Technology Exhibition	Student technology projects and demonstrations	Innovation Centre	2026-10-15 10:00:00+05:30	2026-10-15 16:00:00+05:30	Technical Committee	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N
\.


--
-- Data for Name: facilities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.facilities (id, name, description, contact_number, created_at, updated_at, type, opening_hours, location_id) FROM stdin;
1	Central Library	Central academic library with books and digital resources	9000000010	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
2	University Health Centre	Basic medical and first-aid facility for students	9000000011	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
3	Sports Complex	Indoor and outdoor sports facilities	9000000012	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	\N
\.


--
-- Data for Name: hostels; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hostels (id, name, description, capacity, created_at, updated_at, type, warden_name, warden_contact, location_id) FROM stdin;
1	Block A Hostel	Student hostel for undergraduate students	300	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	STUDENT	\N	\N	\N
2	Block B Hostel	Student hostel with common study and recreation areas	250	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	STUDENT	\N	\N	\N
\.


--
-- Data for Name: notices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notices (id, title, content, published_at, created_at, updated_at, category, attachment_url, is_pinned, author_id, department_id) FROM stdin;
1	Welcome to UniGuide	New students can use UniGuide to explore university information and facilities.	2026-09-22 09:00:00+05:30	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	f	\N	\N
2	Orientation Program	Freshers orientation will be conducted in the Main Auditorium.	2026-09-22 10:00:00+05:30	2026-09-22 23:07:26.375987+05:30	2026-09-22 23:07:26.375987+05:30	\N	\N	f	\N	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, role, created_at, updated_at, full_name, phone_number, student_id, department_id) FROM stdin;
\.


--
-- Name: activities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.activities_id_seq', 3, true);


--
-- Name: campus_locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.campus_locations_id_seq', 3, true);


--
-- Name: clubs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clubs_id_seq', 3, true);


--
-- Name: contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contacts_id_seq', 3, true);


--
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_id_seq', 3, true);


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departments_id_seq', 2, true);


--
-- Name: events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.events_id_seq', 2, true);


--
-- Name: facilities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.facilities_id_seq', 3, true);


--
-- Name: hostels_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hostels_id_seq', 2, true);


--
-- Name: notices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notices_id_seq', 2, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- Name: activities activities_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_name_key UNIQUE (name);


--
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);


--
-- Name: campus_locations campus_locations_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT campus_locations_name_key UNIQUE (name);


--
-- Name: campus_locations campus_locations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT campus_locations_pkey PRIMARY KEY (id);


--
-- Name: clubs clubs_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_name_key UNIQUE (name);


--
-- Name: clubs clubs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_pkey PRIMARY KEY (id);


--
-- Name: contacts contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- Name: courses courses_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_code_key UNIQUE (code);


--
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- Name: departments departments_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_code_key UNIQUE (code);


--
-- Name: departments departments_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_name_key UNIQUE (name);


--
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- Name: facilities facilities_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT facilities_name_key UNIQUE (name);


--
-- Name: facilities facilities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT facilities_pkey PRIMARY KEY (id);


--
-- Name: hostels hostels_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT hostels_name_key UNIQUE (name);


--
-- Name: hostels hostels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT hostels_pkey PRIMARY KEY (id);


--
-- Name: notices notices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT notices_pkey PRIMARY KEY (id);


--
-- Name: campus_locations uq_campus_locations_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT uq_campus_locations_code UNIQUE (code);


--
-- Name: campus_locations uq_campus_locations_qr_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campus_locations
    ADD CONSTRAINT uq_campus_locations_qr_code_key UNIQUE (qr_code_key);


--
-- Name: users users_login_identifier_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_identifier_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: idx_activities_category; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_activities_category ON public.activities USING btree (category);


--
-- Name: idx_activities_location_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_activities_location_id ON public.activities USING btree (location_id);


--
-- Name: idx_campus_locations_category; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_campus_locations_category ON public.campus_locations USING btree (category);


--
-- Name: idx_clubs_category; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_clubs_category ON public.clubs USING btree (category);


--
-- Name: idx_contacts_category; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_contacts_category ON public.contacts USING btree (category);


--
-- Name: idx_contacts_department_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_contacts_department_id ON public.contacts USING btree (department_id);


--
-- Name: idx_courses_department_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_courses_department_id ON public.courses USING btree (department_id);


--
-- Name: idx_courses_semester; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_courses_semester ON public.courses USING btree (semester);


--
-- Name: idx_events_location_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_events_location_id ON public.events USING btree (location_id);


--
-- Name: idx_events_start_date; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_events_start_date ON public.events USING btree (start_date);


--
-- Name: idx_events_start_time; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_events_start_time ON public.events USING btree (start_date);


--
-- Name: idx_facilities_location_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_facilities_location_id ON public.facilities USING btree (location_id);


--
-- Name: idx_facilities_type; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_facilities_type ON public.facilities USING btree (type);


--
-- Name: idx_hostels_location_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_hostels_location_id ON public.hostels USING btree (location_id);


--
-- Name: idx_hostels_type; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_hostels_type ON public.hostels USING btree (type);


--
-- Name: idx_notices_category; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_notices_category ON public.notices USING btree (category);


--
-- Name: idx_notices_department_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_notices_department_id ON public.notices USING btree (department_id);


--
-- Name: idx_notices_published_at; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_notices_published_at ON public.notices USING btree (published_at);


--
-- Name: idx_users_department_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_users_department_id ON public.users USING btree (department_id);


--
-- Name: idx_users_role; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_users_role ON public.users USING btree (role);


--
-- Name: activities fk_activities_location; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT fk_activities_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: contacts fk_contacts_department; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT fk_contacts_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: courses fk_courses_department; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT fk_courses_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: departments fk_departments_location; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT fk_departments_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: events fk_events_location; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_events_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: facilities fk_facilities_location; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facilities
    ADD CONSTRAINT fk_facilities_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: hostels fk_hostels_location; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hostels
    ADD CONSTRAINT fk_hostels_location FOREIGN KEY (location_id) REFERENCES public.campus_locations(id);


--
-- Name: notices fk_notices_author; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fk_notices_author FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: notices fk_notices_department; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fk_notices_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- Name: users fk_users_department; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_users_department FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- PostgreSQL database dump complete
--

\unrestrict 89wDYT4mRUNgVeJM49ZjNzHADCiHACX19XGMEQrqFgNp0WAbUtocXPsZN3964V6

