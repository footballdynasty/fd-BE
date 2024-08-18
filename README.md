pg_dump -U u206ti558u4gvl -h caij57unh724n3.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com d4bretudoh1e1l >> fd.sql

docker exec -i docker-compose-postgres-1 /bin/bash -c "PGPASSWORD=mysecretpassword psql --username fd_user fd_db" < fd.sql

Change the owner u206ti558u4gvl to fd_user in the file before running the above command

--
-- Name: achievements; Type: TABLE; Schema: public; Owner: fd_user
--

CREATE TABLE public.achievements (
    id uuid DEFAULT gen_random_uuid(),
    description character varying NOT NULL,
    reward character varying NOT NULL,
    date_completed bigint
);


ALTER TABLE public.achievements OWNER TO fd_user;

--
-- Name: game; Type: TABLE; Schema: public; Owner: fd_user
--

CREATE TABLE public.game (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    game_id character varying NOT NULL,
    home_team_id uuid,
    away_team_id uuid,
    home_score integer DEFAULT 0 NOT NULL,
    away_score integer DEFAULT 0 NOT NULL,
    date date NOT NULL,
    week_id uuid,
    home_team_rank integer DEFAULT 0,
    away_team_rank integer DEFAULT 0
);


ALTER TABLE public.game OWNER TO fd_user;

--
-- Name: standings; Type: TABLE; Schema: public; Owner: fd_user
--

CREATE TABLE public.standings (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    team_id uuid,
    year integer NOT NULL,
    wins integer NOT NULL,
    losses integer NOT NULL,
    rank integer,
    receiving_votes integer
);


ALTER TABLE public.standings OWNER TO fd_user;

--
-- Name: team; Type: TABLE; Schema: public; Owner: fd_user
--

CREATE TABLE public.team (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name character varying NOT NULL,
    coach character varying,
    username character varying,
    conference character varying,
    is_human boolean DEFAULT false,
    image_url character varying
);


ALTER TABLE public.team OWNER TO fd_user;

--
-- Name: week; Type: TABLE; Schema: public; Owner: fd_user
--

CREATE TABLE public.week (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    year integer NOT NULL,
    week_number integer NOT NULL
);


ALTER TABLE public.week OWNER TO fd_user;

--
-- Data for Name: achievements; Type: TABLE DATA; Schema: public; Owner: fd_user
--

COPY public.achievements (id, description, reward, date_completed) FROM stdin;
\.


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: fd_user
--

COPY public.game (id, game_id, home_team_id, away_team_id, home_score, away_score, date, week_id, home_team_rank, away_team_rank) FROM stdin;
d51daf5b-78db-4b53-b3e2-28e1b00d5289	JacksonvilleStateBowlingGreen20244	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	b65b4323-c82f-4634-b6ec-3f98f8b5c600	22	41	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
39e5fe68-cf5e-4c12-bb25-4aa2dd409ef2	KentuckyOhio20244	e970983c-fa25-4a75-9bb9-568b64d81736	24a95425-d89c-494f-956d-73580ba251d1	7	10	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
e1906d09-514a-4e85-a476-c67d853eb2ed	MarshallWesternMichigan20245	dbd3d0f7-9085-42bb-a99e-5dd0e6e07883	c80180cc-808b-46d3-a1a8-e12ce74fd376	0	0	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
f0f6cf2e-d020-4dee-ac2b-d631dfbdb9a2	UConnBuffalo20245	74e5cad1-a98f-49ff-a9b2-a4863d1547e3	e93cfd20-77df-4c16-9827-00057f12098f	0	0	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
9aed1750-e0d5-40e4-a403-ac6caedab482	AkronUTEP20245	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	16b1aa37-54ce-4c98-849f-252589f81cfe	0	0	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
a83260ab-8052-401c-99f6-8284b36de544	NCStateNorthernIllinois20245	3dda27d1-2ee5-49eb-b5ea-16f3ba5af681	a824c605-3ee2-495c-8a62-c4d3c1868ed2	0	0	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	6	0
f8498b88-700a-4576-8a8f-7cf36c57b99d	ToledoWesternKentucky20244	983c76d0-6afa-4a17-88a1-b1327a1d3f43	783e41ee-6de7-473c-9779-158933554f5a	42	35	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
f9de0e12-fef4-4629-b4ff-1ae4e6bf1e36	CentralMichiganBallState20244	ae313b41-8332-47de-af3b-1b6d68e8b8cb	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	31	22	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
d451bfd8-8aac-480c-bf8b-95a5e217a1e4	CentralMichiganSanDiegoState20245	ae313b41-8332-47de-af3b-1b6d68e8b8cb	c46e9bc4-f15f-474f-bf4f-1913e360e50e	30	42	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
3972edbb-c786-44da-a859-1a667df16beb	JacksonvilleStateToledo20245	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	983c76d0-6afa-4a17-88a1-b1327a1d3f43	0	0	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
654f5330-f3e1-4882-8196-1714b5297e12	BowlingGreenTennessee20241	b65b4323-c82f-4634-b6ec-3f98f8b5c600	65683297-ed46-4e81-9404-cd2cd16d58b3	7	35	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
668b78fc-5366-41f2-a0e4-e2b6293dd71a	SyracuseOhio20241	3babf710-f7ec-4b9a-82bf-2529bcfb27a1	24a95425-d89c-494f-956d-73580ba251d1	49	0	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
328d62db-66bc-4086-ae80-86a641065168	KentStateToledo20241	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	983c76d0-6afa-4a17-88a1-b1327a1d3f43	42	21	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
215b65ff-1d92-42f2-a574-57560d250ba0	OregonBallState20241	10992486-e4ab-4d0b-b96c-94c365a7f55a	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	56	3	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
7680b189-46b6-422f-8280-23cd77185af4	UMassEasternMichigan20241	b03a4d76-f742-4029-a62c-50ebe3b1a785	5a9ac517-4494-44fb-8c69-02bebeac5259	45	17	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
cb342e3b-e4f0-4eaf-a4c3-75a936dd8696	CentralMichiganOklahomaState20241	ae313b41-8332-47de-af3b-1b6d68e8b8cb	e2fffe92-2b53-47f7-8895-74de1474eee7	16	28	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
03976a4e-9895-46c2-9ff8-136ec4f34dd3	JacksonvilleStateWesternMichigan20241	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	c80180cc-808b-46d3-a1a8-e12ce74fd376	14	31	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
7ab30cdf-63d2-4473-8fa3-c1f77f760e3d	Miami-OHAkron20241	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	35	0	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
52f337df-20fe-44a8-9bfe-8f883994531c	BuffaloIowa20241	e93cfd20-77df-4c16-9827-00057f12098f	4a52ab32-fd42-4edc-856c-6cc3b7beed15	7	45	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
bab13b87-a47c-4473-82d0-ae12ab2af499	NorthernIllinoisUtah20241	a824c605-3ee2-495c-8a62-c4d3c1868ed2	c1c4eff8-020c-4819-9537-097da9dca492	36	31	2024-07-21	90cf7ecd-0582-4eca-8fec-2153cd802f22	0	0
911e570a-e33b-4217-a463-b136a87418e7	OhioSouthAlabama20242	24a95425-d89c-494f-956d-73580ba251d1	6c23b2ed-1022-40d7-94e2-f03761a85624	31	20	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
409cab45-87b3-44be-9e21-592a1d570776	ToledoUMass20242	983c76d0-6afa-4a17-88a1-b1327a1d3f43	b03a4d76-f742-4029-a62c-50ebe3b1a785	30	3	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
826cf4cf-a9f6-499b-8e28-a219d7e89880	JacksonvilleStateBallState20242	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	7	30	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
50d386c6-168f-4a29-b5ce-8a691d8075d2	WashingtonEasternMichigan20242	dd26d024-ec35-43ea-a236-f331f92be0e6	5a9ac517-4494-44fb-8c69-02bebeac5259	21	28	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
82580291-485d-4683-8fcb-faa6f999a943	FloridaInternationalCentralMichigan20242	2b7e664f-5edb-46af-a798-c386899e1794	ae313b41-8332-47de-af3b-1b6d68e8b8cb	24	14	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
a5acc492-7692-4860-a9d2-92d1157f73a2	WisconsinWesternMichigan20242	782f2c4f-dbe7-4047-ac3b-78c3c24c220f	c80180cc-808b-46d3-a1a8-e12ce74fd376	49	7	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
0c56dfb1-1dc2-4456-b999-a3e29020cfd9	NorthwesternMiami-OH20242	6d4d9fde-05d8-4a1a-aa59-eddc3c9fb3f9	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	28	7	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
4454126b-fc68-4285-8ee1-d92d09b07264	ToledoMississippiState20243	983c76d0-6afa-4a17-88a1-b1327a1d3f43	ce18f9d1-374e-4d70-ae70-9ba68848320b	28	24	2024-07-29	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
3dfc0bdf-a9f2-4d30-92a4-8bd967ba15c2	JacksonvilleStateEasternMichigan20243	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	5a9ac517-4494-44fb-8c69-02bebeac5259	16	21	2024-07-29	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
8a87c66b-d440-4c7c-acb2-5573daa4b8b7	CincinnatiMiami-OH20243	9896f1eb-a985-4f83-9913-b1e24243e5a4	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	41	17	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
8bac0297-7100-408b-8db8-b6c1446db824	Miami(FL)BallState20243	64481cb1-8860-4ff0-8a36-3783f51f8edd	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	14	21	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
1b88eed2-2328-4773-a46c-a4d027c2286f	IllinoisCentralMichigan20243	5cadb4a5-0c97-4ef4-93f0-b47789377bdd	ae313b41-8332-47de-af3b-1b6d68e8b8cb	38	7	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
7910fd62-f28a-4f5a-92e1-763d7645e676	OhioStateWesternMichigan20243	c79c844d-d2c5-4b79-9469-8e2872e357a7	c80180cc-808b-46d3-a1a8-e12ce74fd376	38	7	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
13afb1bc-4dd8-4bf1-9adc-a7953579527a	TennesseeKentState20243	65683297-ed46-4e81-9404-cd2cd16d58b3	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	27	14	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
a1f52bc5-6639-49b7-9e97-9ac505761191	UMassBuffalo20243	b03a4d76-f742-4029-a62c-50ebe3b1a785	e93cfd20-77df-4c16-9827-00057f12098f	10	20	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
fba786f9-c73f-436a-bd70-a02bd0bade97	RutgersAkron20243	df2d658f-84e4-4db6-abed-329dde76f396	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	38	24	2024-07-28	c576b0b2-464d-4f4a-9d70-191dd2c1d644	0	0
683d59b9-5679-4992-a628-9f5c019eaeb5	PennStateBowlingGreen20242	ce851a8d-6657-4a5d-915c-ccf90e8f403e	b65b4323-c82f-4634-b6ec-3f98f8b5c600	37	30	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
864162a0-49ca-4860-a77c-b04bb3a53ec6	PittsburghKentState20242	4f02aabc-bf3b-4920-9de1-a28d05bfed35	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	39	14	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
ac1bb8e8-7e31-466c-892a-3c8065b74c23	NotreDameNorthernIllinois20242	1e06d7f5-c100-448d-8e6e-37574d6cfa7f	a824c605-3ee2-495c-8a62-c4d3c1868ed2	24	17	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
be35a6ed-2d4f-4ee0-a1d4-ab71b12dfce7	MissouriBuffalo20242	b396e80f-4de1-455f-bc83-8ffa695a795d	e93cfd20-77df-4c16-9827-00057f12098f	34	7	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
97080b58-cfa0-4869-9727-f2a71471b9d6	OhioStateAkron20242	c79c844d-d2c5-4b79-9469-8e2872e357a7	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	48	20	2024-07-25	5e3d19d4-a892-4f98-85ab-dcfda1584a7c	0	0
95d684df-4b28-4fed-8f03-74081fc92e59	EasternMichiganFCSWest20244	5a9ac517-4494-44fb-8c69-02bebeac5259	e66b737c-9a21-44b6-b781-25bc42045f90	45	41	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
157426ce-a1bf-436b-a8d6-a20593f43379	SouthCarolinaAkron20244	8036b8ce-5619-4aa8-8f1f-57c29ca3366e	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	35	14	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
2140d059-b881-417d-b567-de7d8ef2ce7a	PennStateKentState20244	ce851a8d-6657-4a5d-915c-ccf90e8f403e	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	44	3	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
4dedf439-98fd-406e-990a-d6f5971e69f8	NotreDameMiami-OH20244	1e06d7f5-c100-448d-8e6e-37574d6cfa7f	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	35	28	2024-08-01	5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	0	0
0fa3b823-7ae4-4524-a8a7-211cc71aa67f	Miami-OHUMass20245	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	b03a4d76-f742-4029-a62c-50ebe3b1a785	14	35	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
b9a148f9-8d37-488c-8674-a6f1cac865e1	BowlingGreenOldDominion20245	b65b4323-c82f-4634-b6ec-3f98f8b5c600	8dec5236-aa24-4fcd-9f20-3944166bfcff	34	11	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
c8782d20-3f6a-4256-964c-b13d86e1e225	EasternMichiganKentState20245	5a9ac517-4494-44fb-8c69-02bebeac5259	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	51	3	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
4765d515-2ff0-4232-92b3-74c792168b83	JamesMadisonBallState20245	ceae4eab-1e1b-46f6-a49c-08ca672cbe0a	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	17	21	2024-08-04	8cba82cb-3fee-403b-8927-ff03570af728	0	0
\.


--
-- Data for Name: standings; Type: TABLE DATA; Schema: public; Owner: fd_user
--

COPY public.standings (id, team_id, year, wins, losses, rank, receiving_votes) FROM stdin;
30a3cbac-a239-497a-941b-b0bcae5132e6	34ea5de2-79d3-45ed-9c1b-e06b8f2de728	2024	0	4	\N	\N
4ec47a3f-0a9d-449f-ac2a-048d5a50f921	c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	2024	1	1	\N	\N
3dacdb20-36d9-4e42-8042-cb8e4705b707	5a9ac517-4494-44fb-8c69-02bebeac5259	2024	2	0	\N	\N
d6b4e59a-5793-4aed-9a1d-2df046e1b820	322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	2024	0	4	\N	\N
5b3ba3f0-2063-423b-b488-6240fcefb01b	ae313b41-8332-47de-af3b-1b6d68e8b8cb	2024	1	2	\N	\N
7bd769ad-803d-4549-bbc6-07f902799ccc	e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	2024	3	2	\N	\N
dc3f2f14-2532-4ee3-934d-fd59a4ba4724	c80180cc-808b-46d3-a1a8-e12ce74fd376	2024	1	2	\N	\N
0a5d9e67-4c92-48c8-8d7b-f941881ae020	a824c605-3ee2-495c-8a62-c4d3c1868ed2	2024	0	1	\N	\N
a94b8579-0f28-4307-99e9-16f4a55f49a9	e93cfd20-77df-4c16-9827-00057f12098f	2024	1	1	\N	\N
6c95f038-cbfb-4e29-b5fd-c9e2ced61c1f	a6873c5c-41f9-49a5-bf62-46d9719ccdb2	2024	0	4	\N	\N
49deaf6e-5d90-40d0-b6aa-54803e36a5f5	b65b4323-c82f-4634-b6ec-3f98f8b5c600	2024	1	1	\N	\N
950030e4-e9e6-4052-b8c3-d5129579f9d5	24a95425-d89c-494f-956d-73580ba251d1	2024	1	1	\N	\N
308a6eea-5fa9-424a-8c97-61ec15dd0a42	983c76d0-6afa-4a17-88a1-b1327a1d3f43	2024	3	0	\N	\N
\.


--
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: fd_user
--

COPY public.team (id, name, coach, username, conference, is_human, image_url) FROM stdin;
983c76d0-6afa-4a17-88a1-b1327a1d3f43	Toledo	Brody Critchlow	Nightraptor360	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/utrockets.com/images/responsive_2021/main-logo.svg
b65b4323-c82f-4634-b6ec-3f98f8b5c600	Bowling Green	Kyle-Jay Critchlow	cloaked_revenant	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/bgsufalcons.com/images/nextgen_2022/logo_main.svg
24a95425-d89c-494f-956d-73580ba251d1	Ohio	Tyler Critchlow	ShinyWasSeE	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/ohiobobcats.com/images/responsive_2021/logo-primary.svg
e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e	Ball State	Darin Critchlow	NthDemolisher	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/bsu.sidearmsports.com/images/responsive_2019/ballstate_logo_cardinal.svg
ae313b41-8332-47de-af3b-1b6d68e8b8cb	Central Michigan	Todd Critchlow	tcritch3	MAC	t	https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/centralmich_on-primary.svg
322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e	Kent State	Cameron Kempff	Nerdvana_93	MAC	t	https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/kentst_on-primary.svg
a824c605-3ee2-495c-8a62-c4d3c1868ed2	Northern Illinois	Ilan Hurtado	passionphruit	MAC	t	https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/niu_on-primary.svg
34ea5de2-79d3-45ed-9c1b-e06b8f2de728	Akron	Eddie Jeppson	FastEddie06	MAC	t	https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/akron_on-primary.svg
a6873c5c-41f9-49a5-bf62-46d9719ccdb2	Jacksonville State	Jayden Jeppson	jjfire5	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/jsugamecocksports.com/images/responsive_2023/JaxState_state.png
8dec5236-aa24-4fcd-9f20-3944166bfcff	Old Dominion	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
ceae4eab-1e1b-46f6-a49c-08ca672cbe0a	James Madison	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
c46e9bc4-f15f-474f-bf4f-1913e360e50e	San Diego State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
dbd3d0f7-9085-42bb-a99e-5dd0e6e07883	Marshall	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
b03a4d76-f742-4029-a62c-50ebe3b1a785	UMass	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
3dda27d1-2ee5-49eb-b5ea-16f3ba5af681	NC State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
74e5cad1-a98f-49ff-a9b2-a4863d1547e3	UConn	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
16b1aa37-54ce-4c98-849f-252589f81cfe	UTEP	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a	Miami-OH	Bradley Critchlow	apple_lucky144	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/miamiredhawks.com/images/nav_logo.svg
e93cfd20-77df-4c16-9827-00057f12098f	Buffalo	Eric Critchlow	The-Eric-C	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/ubbulls.com/images/responsive_2019/main_logo.svg
c80180cc-808b-46d3-a1a8-e12ce74fd376	Western Michigan	Spencer Critchlow	KMBAPPEPANDA5	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/wmich.sidearmsports.com/images/responsive_2024/logo_main.svg
5a9ac517-4494-44fb-8c69-02bebeac5259	Eastern Michigan	Will Myers	myerswj163	MAC	t	https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/emueagles.com/images/responsive_2021/nav_main_logo.svg
65683297-ed46-4e81-9404-cd2cd16d58b3	Tennessee	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
3babf710-f7ec-4b9a-82bf-2529bcfb27a1	Syracuse	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
10992486-e4ab-4d0b-b96c-94c365a7f55a	Oregon	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
e2fffe92-2b53-47f7-8895-74de1474eee7	Oklahoma State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
4a52ab32-fd42-4edc-856c-6cc3b7beed15	Iowa	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
c1c4eff8-020c-4819-9537-097da9dca492	Utah	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
ce851a8d-6657-4a5d-915c-ccf90e8f403e	Penn State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
6c23b2ed-1022-40d7-94e2-f03761a85624	South Alabama	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
dd26d024-ec35-43ea-a236-f331f92be0e6	Washington	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
2b7e664f-5edb-46af-a798-c386899e1794	Florida International	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
782f2c4f-dbe7-4047-ac3b-78c3c24c220f	Wisconsin	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
6d4d9fde-05d8-4a1a-aa59-eddc3c9fb3f9	Northwestern	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
ce18f9d1-374e-4d70-ae70-9ba68848320b	Mississippi State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
9896f1eb-a985-4f83-9913-b1e24243e5a4	Cincinnati	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
64481cb1-8860-4ff0-8a36-3783f51f8edd	Miami (FL)	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
5cadb4a5-0c97-4ef4-93f0-b47789377bdd	Illinois	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
c79c844d-d2c5-4b79-9469-8e2872e357a7	Ohio State	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
df2d658f-84e4-4db6-abed-329dde76f396	Rutgers	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
4f02aabc-bf3b-4920-9de1-a28d05bfed35	Pittsburgh	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
1e06d7f5-c100-448d-8e6e-37574d6cfa7f	Notre Dame	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
b396e80f-4de1-455f-bc83-8ffa695a795d	Missouri	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
e970983c-fa25-4a75-9bb9-568b64d81736	Kentucky	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
783e41ee-6de7-473c-9779-158933554f5a	Western Kentucky	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
e66b737c-9a21-44b6-b781-25bc42045f90	FCS West	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
8036b8ce-5619-4aa8-8f1f-57c29ca3366e	South Carolina	\N	\N	\N	f	https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg
\.


--
-- Data for Name: week; Type: TABLE DATA; Schema: public; Owner: fd_user
--

COPY public.week (id, year, week_number) FROM stdin;
8cba82cb-3fee-403b-8927-ff03570af728	2024	5
90cf7ecd-0582-4eca-8fec-2153cd802f22	2024	1
5e3d19d4-a892-4f98-85ab-dcfda1584a7c	2024	2
c576b0b2-464d-4f4a-9d70-191dd2c1d644	2024	3
5f352e5d-101b-4b2e-b1d5-55c8faa9ffbf	2024	4
\.


--
-- Name: game game_pkey; Type: CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);


--
-- Name: standings standings_pkey; Type: CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.standings
    ADD CONSTRAINT standings_pkey PRIMARY KEY (id);


--
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- Name: week week_pkey; Type: CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.week
    ADD CONSTRAINT week_pkey PRIMARY KEY (id);


--
-- Name: game game_away_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_away_team_id_fkey FOREIGN KEY (away_team_id) REFERENCES public.team(id);


--
-- Name: game game_home_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_home_team_id_fkey FOREIGN KEY (home_team_id) REFERENCES public.team(id);


--
-- Name: game game_week_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_week_id_fkey FOREIGN KEY (week_id) REFERENCES public.week(id);


--
-- Name: standings standings_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fd_user
--

ALTER TABLE ONLY public.standings
    ADD CONSTRAINT standings_team_id_fkey FOREIGN KEY (team_id) REFERENCES public.team(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: fd_user
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;

--
-- PostgreSQL database dump complete
--

