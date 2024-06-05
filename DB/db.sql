--
-- PostgreSQL database dump
--

-- Dumped from database version 13.5
-- Dumped by pg_dump version 13.5

-- Started on 2023-04-24 03:04:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 203 (class 1259 OID 33240)
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer (
    id integer NOT NULL,
    id_question integer NOT NULL,
    content text NOT NULL,
    is_picture boolean DEFAULT false NOT NULL,
    is_right boolean NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.answer OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 33286)
-- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.answer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 202 (class 1259 OID 33212)
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    id integer NOT NULL,
    content text NOT NULL,
    is_picture boolean DEFAULT false NOT NULL,
    id_quez integer NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.question OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 33284)
-- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 201 (class 1259 OID 33176)
-- Name: quez; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quez (
    id integer NOT NULL,
    name text NOT NULL,
    owner_id integer NOT NULL,
    tag text,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.quez OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 33282)
-- Name: quez_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.quez ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.quez_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 33257)
-- Name: user_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_answer (
    id_question integer NOT NULL,
    id_answer integer NOT NULL,
    id_quez integer NOT NULL,
    id_user integer,
    "created_at " timestamp without time zone NOT NULL
);


ALTER TABLE public.user_answer OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 33165)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying NOT NULL,
    role integer DEFAULT 0 NOT NULL,
    created_at timestamp without time zone NOT NULL,
    password character varying NOT NULL,
    first_name character varying,
    second_name character varying,
    last_name character varying,
    birth_date date
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 33280)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3029 (class 0 OID 33240)
-- Dependencies: 203
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.answer (id, id_question, content, is_picture, is_right, created_at) FROM stdin;
\.


--
-- TOC entry 3028 (class 0 OID 33212)
-- Dependencies: 202
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.question (id, content, is_picture, id_quez, created_at) FROM stdin;
\.


--
-- TOC entry 3027 (class 0 OID 33176)
-- Dependencies: 201
-- Data for Name: quez; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quez (id, name, owner_id, created_at) FROM stdin;
\.


--
-- TOC entry 3030 (class 0 OID 33257)
-- Dependencies: 204
-- Data for Name: user_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_answer (id_question, id_answer, id_quez, id_user, "created_at ") FROM stdin;
\.


--
-- TOC entry 3026 (class 0 OID 33165)
-- Dependencies: 200
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, role, created_at, password, first_name, second_name, last_name, burth_date) FROM stdin;
\.


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 208
-- Name: answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id_seq', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 207
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id_seq', 1, false);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 206
-- Name: quez_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quez_id_seq', 1, false);


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 205
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- TOC entry 2888 (class 2606 OID 33248)
-- Name: answer answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- TOC entry 2886 (class 2606 OID 33220)
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- TOC entry 2884 (class 2606 OID 33183)
-- Name: quez quez_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quez
    ADD CONSTRAINT quez_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 33175)
-- Name: users username is uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "username is uniq" UNIQUE (username);


--
-- TOC entry 2882 (class 2606 OID 33173)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2891 (class 2606 OID 33249)
-- Name: answer answer_id_question_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_id_question_fkey FOREIGN KEY (id_question) REFERENCES public.question(id);


--
-- TOC entry 2890 (class 2606 OID 33221)
-- Name: question question_id_quez_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_id_quez_fkey FOREIGN KEY (id_quez) REFERENCES public.quez(id);


--
-- TOC entry 2889 (class 2606 OID 33184)
-- Name: quez quez_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quez
    ADD CONSTRAINT quez_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES public.users(id);


--
-- TOC entry 2893 (class 2606 OID 33265)
-- Name: user_answer user_answer_id_answer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_answer
    ADD CONSTRAINT user_answer_id_answer_fkey FOREIGN KEY (id_answer) REFERENCES public.answer(id);


--
-- TOC entry 2892 (class 2606 OID 33260)
-- Name: user_answer user_answer_id_question_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_answer
    ADD CONSTRAINT user_answer_id_question_fkey FOREIGN KEY (id_question) REFERENCES public.question(id);


--
-- TOC entry 2894 (class 2606 OID 33270)
-- Name: user_answer user_answer_id_quez_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_answer
    ADD CONSTRAINT user_answer_id_quez_fkey FOREIGN KEY (id_quez) REFERENCES public.quez(id);


--
-- TOC entry 2895 (class 2606 OID 33275)
-- Name: user_answer user_answer_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_answer
    ADD CONSTRAINT user_answer_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id);


-- Completed on 2023-04-24 03:04:09

--
-- PostgreSQL database dump complete
--

