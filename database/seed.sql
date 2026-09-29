--
-- PostgreSQL database dump
--

\restrict evlXLx29URLCVoWKIFZ4YIOePDVfUg8zWlu5TIGyA41hVbLKcvjinQoCzYvTeOJ

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

--
-- Data for Name: campus_locations; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.campus_locations (id, name, category, description, building_name, latitude, longitude, created_at, updated_at, code, floor_count, qr_code_key) VALUES (1, 'Main Auditorium', 'Academic', 'Main university auditorium', 'Auditorium Block', 20.2961000, 85.8245000, '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'AUD-001', 1, 'MAIN-AUDITORIUM');
INSERT INTO public.campus_locations (id, name, category, description, building_name, latitude, longitude, created_at, updated_at, code, floor_count, qr_code_key) VALUES (2, 'Central Library', 'Academic', 'Central university library', 'Library Block', 20.2965000, 85.8250000, '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'LIB-001', 3, 'CENTRAL-LIBRARY');
INSERT INTO public.campus_locations (id, name, category, description, building_name, latitude, longitude, created_at, updated_at, code, floor_count, qr_code_key) VALUES (3, 'Sports Ground', 'Sports', 'Main outdoor sports ground', 'Sports Complex', 20.2970000, 85.8255000, '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'SPG-001', 1, 'SPORTS-GROUND');


--
-- Data for Name: activities; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.activities (id, name, category, description, created_at, updated_at, coordinator_name, coordinator_contact, location_id) VALUES (1, 'Coding Club Activities', 'Technical', 'Programming workshops, coding contests and technical sessions', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'Activity Coordinator', '9000000020', NULL);
INSERT INTO public.activities (id, name, category, description, created_at, updated_at, coordinator_name, coordinator_contact, location_id) VALUES (2, 'University Football', 'Sports', 'Football training and university sports activities', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'Sports Coordinator', '9000000021', NULL);
INSERT INTO public.activities (id, name, category, description, created_at, updated_at, coordinator_name, coordinator_contact, location_id) VALUES (3, 'Cultural Activities', 'Cultural', 'Music, dance, drama and cultural events', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'Cultural Coordinator', '9000000022', NULL);


--
-- Data for Name: clubs; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.clubs (id, name, description, category, meeting_location, contact_name, contact_number, created_at, updated_at) VALUES (1, 'Coding Club', 'Student club focused on programming and technology', 'Technical', 'CSE Seminar Hall', 'Club Coordinator', '9000000030', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30');
INSERT INTO public.clubs (id, name, description, category, meeting_location, contact_name, contact_number, created_at, updated_at) VALUES (2, 'Literary Club', 'Student club for writing, reading and public speaking', 'Literary', 'Student Activity Centre', 'Club Coordinator', '9000000031', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30');
INSERT INTO public.clubs (id, name, description, category, meeting_location, contact_name, contact_number, created_at, updated_at) VALUES (3, 'Photography Club', 'Student club for photography and visual storytelling', 'Creative', 'Student Activity Centre', 'Club Coordinator', '9000000032', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30');


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.departments (id, name, code, description, created_at, updated_at, contact_email, contact_phone, location_id) VALUES (1, 'Computer Science and Engineering', 'CSE', 'Department of Computer Science and Engineering', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);
INSERT INTO public.departments (id, name, code, description, created_at, updated_at, contact_email, contact_phone, location_id) VALUES (2, 'Electronics and Communication Engineering', 'ECE', 'Department of Electronics and Communication Engineering', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);


--
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.contacts (id, name, category, phone_number, email, created_at, updated_at, designation, office_location, department_id) VALUES (1, 'Student Help Desk', 'Student Support', '9000000050', 'helpdesk@uniguide.example', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);
INSERT INTO public.contacts (id, name, category, phone_number, email, created_at, updated_at, designation, office_location, department_id) VALUES (2, 'Emergency Services', 'Emergency', '9000000051', 'emergency@uniguide.example', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);
INSERT INTO public.contacts (id, name, category, phone_number, email, created_at, updated_at, designation, office_location, department_id) VALUES (3, 'Academic Office', 'Academic', '9000000052', 'academic@uniguide.example', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);


--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.courses (id, department_id, title, code, description, created_at, updated_at, credits, semester) VALUES (1, 1, 'B.Tech Computer Science and Engineering', 'BTECH-CSE', 'Undergraduate program in Computer Science and Engineering', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 4, 1);
INSERT INTO public.courses (id, department_id, title, code, description, created_at, updated_at, credits, semester) VALUES (2, 1, 'B.Tech Artificial Intelligence and Machine Learning', 'BTECH-AIML', 'Undergraduate program in Artificial Intelligence and Machine Learning', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 4, 1);
INSERT INTO public.courses (id, department_id, title, code, description, created_at, updated_at, credits, semester) VALUES (3, 2, 'B.Tech Electronics and Communication Engineering', 'BTECH-ECE', 'Undergraduate program in Electronics and Communication Engineering', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 4, 1);


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.events (id, title, description, venue, start_date, end_date, organizer, created_at, updated_at, registration_link, location_id) VALUES (1, 'Freshers Orientation', 'Orientation program for newly admitted students', 'Main Auditorium', '2026-10-05 10:00:00+05:30', '2026-10-05 13:00:00+05:30', 'Student Affairs', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL);
INSERT INTO public.events (id, title, description, venue, start_date, end_date, organizer, created_at, updated_at, registration_link, location_id) VALUES (2, 'Technology Exhibition', 'Student technology projects and demonstrations', 'Innovation Centre', '2026-10-15 10:00:00+05:30', '2026-10-15 16:00:00+05:30', 'Technical Committee', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL);


--
-- Data for Name: facilities; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.facilities (id, name, description, contact_number, created_at, updated_at, type, opening_hours, location_id) VALUES (1, 'Central Library', 'Central academic library with books and digital resources', '9000000010', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);
INSERT INTO public.facilities (id, name, description, contact_number, created_at, updated_at, type, opening_hours, location_id) VALUES (2, 'University Health Centre', 'Basic medical and first-aid facility for students', '9000000011', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);
INSERT INTO public.facilities (id, name, description, contact_number, created_at, updated_at, type, opening_hours, location_id) VALUES (3, 'Sports Complex', 'Indoor and outdoor sports facilities', '9000000012', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, NULL);


--
-- Data for Name: hostels; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.hostels (id, name, description, capacity, created_at, updated_at, type, warden_name, warden_contact, location_id) VALUES (1, 'Block A Hostel', 'Student hostel for undergraduate students', 300, '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'STUDENT', NULL, NULL, NULL);
INSERT INTO public.hostels (id, name, description, capacity, created_at, updated_at, type, warden_name, warden_contact, location_id) VALUES (2, 'Block B Hostel', 'Student hostel with common study and recreation areas', 250, '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', 'STUDENT', NULL, NULL, NULL);


--
-- Data for Name: notices; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.notices (id, title, content, published_at, created_at, updated_at, category, attachment_url, is_pinned, author_id, department_id) VALUES (1, 'Welcome to UniGuide', 'New students can use UniGuide to explore university information and facilities.', '2026-09-22 09:00:00+05:30', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, false, NULL, NULL);
INSERT INTO public.notices (id, title, content, published_at, created_at, updated_at, category, attachment_url, is_pinned, author_id, department_id) VALUES (2, 'Orientation Program', 'Freshers orientation will be conducted in the Main Auditorium.', '2026-09-22 10:00:00+05:30', '2026-09-22 23:07:26.375987+05:30', '2026-09-22 23:07:26.375987+05:30', NULL, NULL, false, NULL, NULL);


--
-- Name: activities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.activities_id_seq', 3, true);


--
-- Name: campus_locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.campus_locations_id_seq', 3, true);


--
-- Name: clubs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.clubs_id_seq', 3, true);


--
-- Name: contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.contacts_id_seq', 3, true);


--
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.courses_id_seq', 3, true);


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.departments_id_seq', 2, true);


--
-- Name: events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.events_id_seq', 2, true);


--
-- Name: facilities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.facilities_id_seq', 3, true);


--
-- Name: hostels_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hostels_id_seq', 2, true);


--
-- Name: notices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.notices_id_seq', 2, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- PostgreSQL database dump complete
--

\unrestrict evlXLx29URLCVoWKIFZ4YIOePDVfUg8zWlu5TIGyA41hVbLKcvjinQoCzYvTeOJ

