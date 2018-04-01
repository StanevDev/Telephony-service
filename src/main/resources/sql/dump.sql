--
-- PostgreSQL database dump
--

-- Dumped from database version 10.0
-- Dumped by pg_dump version 10.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: servicetype; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE servicetype AS ENUM (
  '3g_internet',
  '2g_internet',
  'internall_call',
  'externall_call',
  'sms',
  'mms'
);


ALTER TYPE servicetype OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: blocked_subscriber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE blocked_subscriber (
  subscriber_id integer NOT NULL,
  blocking_date date NOT NULL
);


ALTER TABLE blocked_subscriber OWNER TO postgres;

--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE service (
  service_id integer NOT NULL,
  price money NOT NULL,
  tarification_value integer NOT NULL,
  service_type servicetype NOT NULL,
  service_name character varying NOT NULL
);


ALTER TABLE service OWNER TO postgres;

--
-- Name: service_mm_subscriber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE service_mm_subscriber (
  service_id integer NOT NULL,
  subscriber_id integer NOT NULL
);


ALTER TABLE service_mm_subscriber OWNER TO postgres;

--
-- Name: service_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE service_service_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE service_service_id_seq OWNER TO postgres;

--
-- Name: service_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE service_service_id_seq OWNED BY service.service_id;


--
-- Name: subscriber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE subscriber (
  subscriber_id integer NOT NULL,
  balance money NOT NULL,
  registration_date date,
  tariff_plan_id integer,
  phone_number bigint,
  ready_to_block boolean DEFAULT false,
  is_in_roaming boolean DEFAULT false,
  street character varying DEFAULT 'Кузнечная'::character varying
);


ALTER TABLE subscriber OWNER TO postgres;

--
-- Name: subscriber_subscriber_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE subscriber_subscriber_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE subscriber_subscriber_id_seq OWNER TO postgres;

--
-- Name: subscriber_subscriber_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE subscriber_subscriber_id_seq OWNED BY subscriber.subscriber_id;


--
-- Name: tariff_mm_region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tariff_mm_region (
  tariff_plan_id integer NOT NULL,
  tariff_region integer NOT NULL
);


ALTER TABLE tariff_mm_region OWNER TO postgres;

--
-- Name: tariff_plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tariff_plan (
  tariff_plan_id integer NOT NULL,
  price money NOT NULL,
  expires_date date NOT NULL,
  region character varying NOT NULL,
  tariff_name character varying NOT NULL,
  tariff_description character varying
);


ALTER TABLE tariff_plan OWNER TO postgres;

--
-- Name: tariff_plan_mm_service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tariff_plan_mm_service (
  tariff_plan_id integer NOT NULL,
  service_id integer NOT NULL
);


ALTER TABLE tariff_plan_mm_service OWNER TO postgres;

--
-- Name: tariff_plan_tariff_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tariff_plan_tariff_plan_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE tariff_plan_tariff_plan_id_seq OWNER TO postgres;

--
-- Name: tariff_plan_tariff_plan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tariff_plan_tariff_plan_id_seq OWNED BY tariff_plan.tariff_plan_id;


--
-- Name: tariff_region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tariff_region (
  region_id integer NOT NULL,
  region_name character varying NOT NULL
);


ALTER TABLE tariff_region OWNER TO postgres;

--
-- Name: tariff_region_region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tariff_region_region_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE tariff_region_region_id_seq OWNER TO postgres;

--
-- Name: tariff_region_region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tariff_region_region_id_seq OWNED BY tariff_region.region_id;


--
-- Name: tech_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tech_request (
  tech_reqeust_id integer NOT NULL,
  problem_description character varying NOT NULL,
  date_id integer,
  subscriber_id integer,
  tech_support_user_id integer
);


ALTER TABLE tech_request OWNER TO postgres;

--
-- Name: tech_request_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tech_request_date (
  date_id integer NOT NULL,
  request_date date NOT NULL
);


ALTER TABLE tech_request_date OWNER TO postgres;

--
-- Name: tech_request_date_date_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tech_request_date_date_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE tech_request_date_date_id_seq OWNER TO postgres;

--
-- Name: tech_request_date_date_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tech_request_date_date_id_seq OWNED BY tech_request_date.date_id;


--
-- Name: tech_request_tech_reqeust_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tech_request_tech_reqeust_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE tech_request_tech_reqeust_id_seq OWNER TO postgres;

--
-- Name: tech_request_tech_reqeust_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tech_request_tech_reqeust_id_seq OWNED BY tech_request.tech_reqeust_id;


--
-- Name: tech_support_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tech_support_user (
  tech_support_user_id integer NOT NULL,
  first_name character varying NOT NULL,
  last_name character varying NOT NULL,
  phone_number bigint
);


ALTER TABLE tech_support_user OWNER TO postgres;

--
-- Name: tech_support_user_tech_support_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tech_support_user_tech_support_user_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE tech_support_user_tech_support_user_id_seq OWNER TO postgres;

--
-- Name: tech_support_user_tech_support_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tech_support_user_tech_support_user_id_seq OWNED BY tech_support_user.tech_support_user_id;


--
-- Name: testentity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testentity (
  id integer NOT NULL,
  content character varying NOT NULL,
  description character varying NOT NULL
);


ALTER TABLE testentity OWNER TO postgres;

--
-- Name: testentity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE testentity_id_seq
  AS integer
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE testentity_id_seq OWNER TO postgres;

--
-- Name: testentity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE testentity_id_seq OWNED BY testentity.id;


--
-- Name: service service_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service ALTER COLUMN service_id SET DEFAULT nextval('service_service_id_seq'::regclass);


--
-- Name: subscriber subscriber_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subscriber ALTER COLUMN subscriber_id SET DEFAULT nextval('subscriber_subscriber_id_seq'::regclass);


--
-- Name: tariff_plan tariff_plan_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_plan ALTER COLUMN tariff_plan_id SET DEFAULT nextval('tariff_plan_tariff_plan_id_seq'::regclass);


--
-- Name: tariff_region region_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_region ALTER COLUMN region_id SET DEFAULT nextval('tariff_region_region_id_seq'::regclass);


--
-- Name: tech_request tech_reqeust_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request ALTER COLUMN tech_reqeust_id SET DEFAULT nextval('tech_request_tech_reqeust_id_seq'::regclass);


--
-- Name: tech_request_date date_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request_date ALTER COLUMN date_id SET DEFAULT nextval('tech_request_date_date_id_seq'::regclass);


--
-- Name: tech_support_user tech_support_user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_support_user ALTER COLUMN tech_support_user_id SET DEFAULT nextval('tech_support_user_tech_support_user_id_seq'::regclass);


--
-- Name: testentity id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testentity ALTER COLUMN id SET DEFAULT nextval('testentity_id_seq'::regclass);


--
-- Data for Name: blocked_subscriber; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY blocked_subscriber (subscriber_id, blocking_date) FROM stdin;
9	2017-05-22
16	2017-11-03
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY service (service_id, price, tarification_value, service_type, service_name) FROM stdin;
1	$20.00	150	3g_internet	Basic internet
2	$35.00	500	3g_internet	Medium internet
4	$200.00	8000	3g_internet	Super duper internet
5	$0.00	1	sms	Tell your mom where are you
6	$10.00	50	sms	Sms messenger
7	$55.00	10	externall_call	Talk with friends
8	$150.00	200	externall_call	If tou have a bussines
9	$250.00	500	externall_call	If you really have a bussines
\.


--
-- Data for Name: service_mm_subscriber; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY service_mm_subscriber (service_id, subscriber_id) FROM stdin;
1	9
1	12
1	15
2	15
4	13
4	9
4	10
8	9
8	14
9	11
9	14
9	10
9	9
\.


--
-- Data for Name: subscriber; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY subscriber (subscriber_id, balance, registration_date, tariff_plan_id, phone_number, ready_to_block, is_in_roaming, street) FROM stdin;
12	$541.00	2013-12-16	2	380639583094	f	f	Дворянская
13	$234.00	2014-02-07	3	380637785934	f	f	Дворянская
15	$12.00	2015-09-23	1	380637732484	f	f	Дворянская
9	-$20.00	2016-09-05	1	380637783493	t	f	Дворянская
10	-$2.00	2011-05-23	3	38093955516	t	f	Дворянская
16	-$20.00	2016-09-05	1	380637783493	t	f	Дворянская
17	$45.00	2017-09-05	5	380637875493	f	f	Дворянская
11	$0.00	2016-09-07	4	380639589096	f	t	Дворянская
14	$55.00	2017-06-08	2	380634583411	f	t	Дворянская
\.


--
-- Data for Name: tariff_mm_region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tariff_mm_region (tariff_plan_id, tariff_region) FROM stdin;
4	3
4	4
3	2
\.


--
-- Data for Name: tariff_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tariff_plan (tariff_plan_id, price, expires_date, region, tariff_name, tariff_description) FROM stdin;
1	$11.00	2018-09-24	Kiev	Little Boy	If you have a kids, this tariff plan for them
2	$22.00	2025-11-11	All	Middle Boy	A really good offer for you
3	$55.00	2025-11-29	All	Big Boy	Time to talk about big things
4	$55.00	2022-03-01	All	Big Boss	You know what to do with this
5	$25.00	2018-09-24	Крут	Студентский	Государственный льготный тариф до 2016.12.05
\.


--
-- Data for Name: tariff_plan_mm_service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tariff_plan_mm_service (tariff_plan_id, service_id) FROM stdin;
1	1
1	5
1	7
2	2
2	5
2	6
2	7
3	2
3	6
3	8
4	4
4	9
4	5
\.


--
-- Data for Name: tariff_region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tariff_region (region_id, region_name) FROM stdin;
1	Одесская
2	Киевская
3	Тернопольская
4	Львовская
\.


--
-- Data for Name: tech_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tech_request (tech_reqeust_id, problem_description, date_id, subscriber_id, tech_support_user_id) FROM stdin;
1	I cant call to police!!!	1	9	1
2	Please call to police, they thief my dog!!	1	9	2
3	Internet connection might be faseter, please review my internet type	4	11	8
4	I want to dissallow all services from my accout, and turn money back, thank you.	2	9	6
5	If my wife asks you to provide my phone calls, please refuse her	6	14	7
6	Hello, pleace provide me a list of recent calls, i miss one important number, thank`s	7	14	7
7	Hello	10	14	7
8	Whats up?)	8	13	4
9	Bye	8	14	7
10	I am sad(	10	12	4
\.


--
-- Data for Name: tech_request_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tech_request_date (date_id, request_date) FROM stdin;
1	2017-08-12
2	2017-08-15
3	2017-08-17
4	2017-08-18
5	2017-08-28
6	2017-09-01
7	2017-09-02
8	2017-12-08
9	2017-12-01
10	2017-12-09
\.


--
-- Data for Name: tech_support_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tech_support_user (tech_support_user_id, first_name, last_name, phone_number) FROM stdin;
1	Freddie	Mercury	380636690294
2	Jimi	Hendricks	380687920123
3	Chris	Cornell	380745698745
4	Curt	Cobain	380781231239
5	Michael	Jackson	380935782354
6	Bob	Marley	380543245345
7	Marilyn	Monroe	380543645648
8	Elvis	Presley	380594502348
\.


--
-- Data for Name: testentity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testentity (id, content, description) FROM stdin;
1	sometimes	Its sometimes her behaviour are contented.
2	listening	DO listening am eagerness oh objection
3	collected	collected.Together gay feelings
4	CONTINUE	CONTINUE juvenile had OFF one.
\.


--
-- Name: service_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('service_service_id_seq', 9, true);


--
-- Name: subscriber_subscriber_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('subscriber_subscriber_id_seq', 17, true);


--
-- Name: tariff_plan_tariff_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tariff_plan_tariff_plan_id_seq', 5, true);


--
-- Name: tariff_region_region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tariff_region_region_id_seq', 4, true);


--
-- Name: tech_request_date_date_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tech_request_date_date_id_seq', 10, true);


--
-- Name: tech_request_tech_reqeust_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tech_request_tech_reqeust_id_seq', 10, true);


--
-- Name: tech_support_user_tech_support_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tech_support_user_tech_support_user_id_seq', 8, true);


--
-- Name: testentity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('testentity_id_seq', 4, true);


--
-- Name: blocked_subscriber blocked_subscriber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY blocked_subscriber
  ADD CONSTRAINT blocked_subscriber_pkey PRIMARY KEY (subscriber_id);


--
-- Name: service_mm_subscriber service_mm_subscriber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_mm_subscriber
  ADD CONSTRAINT service_mm_subscriber_pkey PRIMARY KEY (service_id, subscriber_id);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service
  ADD CONSTRAINT service_pkey PRIMARY KEY (service_id);


--
-- Name: subscriber subscriber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subscriber
  ADD CONSTRAINT subscriber_pkey PRIMARY KEY (subscriber_id);


--
-- Name: tariff_mm_region tariff_mm_region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_mm_region
  ADD CONSTRAINT tariff_mm_region_pkey PRIMARY KEY (tariff_plan_id, tariff_region);


--
-- Name: tariff_plan_mm_service tariff_plan_mm_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_plan_mm_service
  ADD CONSTRAINT tariff_plan_mm_service_pkey PRIMARY KEY (tariff_plan_id, service_id);


--
-- Name: tariff_plan tariff_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_plan
  ADD CONSTRAINT tariff_plan_pkey PRIMARY KEY (tariff_plan_id);


--
-- Name: tariff_region tariff_region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_region
  ADD CONSTRAINT tariff_region_pkey PRIMARY KEY (region_id);


--
-- Name: tech_request_date tech_request_date_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request_date
  ADD CONSTRAINT tech_request_date_pkey PRIMARY KEY (date_id);


--
-- Name: tech_request tech_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request
  ADD CONSTRAINT tech_request_pkey PRIMARY KEY (tech_reqeust_id);


--
-- Name: tech_support_user tech_support_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_support_user
  ADD CONSTRAINT tech_support_user_pkey PRIMARY KEY (tech_support_user_id);


--
-- Name: testentity testentity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testentity
  ADD CONSTRAINT testentity_pkey PRIMARY KEY (id);


--
-- Name: blocked_subscriber blocked_subscriber_subscriber_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY blocked_subscriber
  ADD CONSTRAINT blocked_subscriber_subscriber_id_fkey FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id);


--
-- Name: service_mm_subscriber service_mm_subscriber_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_mm_subscriber
  ADD CONSTRAINT service_mm_subscriber_service_id_fkey FOREIGN KEY (service_id) REFERENCES service(service_id);


--
-- Name: service_mm_subscriber service_mm_subscriber_subscriber_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_mm_subscriber
  ADD CONSTRAINT service_mm_subscriber_subscriber_id_fkey FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id);


--
-- Name: subscriber subscriber_tariff_plan_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subscriber
  ADD CONSTRAINT subscriber_tariff_plan_id_fkey FOREIGN KEY (tariff_plan_id) REFERENCES tariff_plan(tariff_plan_id);


--
-- Name: tariff_mm_region tariff_mm_region_tariff_plan_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_mm_region
  ADD CONSTRAINT tariff_mm_region_tariff_plan_id_fkey FOREIGN KEY (tariff_plan_id) REFERENCES tariff_plan(tariff_plan_id);


--
-- Name: tariff_mm_region tariff_mm_region_tariff_region_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_mm_region
  ADD CONSTRAINT tariff_mm_region_tariff_region_fkey FOREIGN KEY (tariff_region) REFERENCES tariff_region(region_id);


--
-- Name: tariff_plan_mm_service tariff_plan_mm_service_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_plan_mm_service
  ADD CONSTRAINT tariff_plan_mm_service_service_id_fkey FOREIGN KEY (service_id) REFERENCES service(service_id);


--
-- Name: tariff_plan_mm_service tariff_plan_mm_service_tariff_plan_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff_plan_mm_service
  ADD CONSTRAINT tariff_plan_mm_service_tariff_plan_id_fkey FOREIGN KEY (tariff_plan_id) REFERENCES tariff_plan(tariff_plan_id);


--
-- Name: tech_request tech_request_date_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request
  ADD CONSTRAINT tech_request_date_id_fkey FOREIGN KEY (date_id) REFERENCES tech_request_date(date_id);


--
-- Name: tech_request tech_request_subscriber_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request
  ADD CONSTRAINT tech_request_subscriber_id_fkey FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id);


--
-- Name: tech_request tech_request_tech_support_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tech_request
  ADD CONSTRAINT tech_request_tech_support_user_id_fkey FOREIGN KEY (tech_support_user_id) REFERENCES tech_support_user(tech_support_user_id);


--
-- PostgreSQL database dump complete
--
