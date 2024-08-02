INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('983c76d0-6afa-4a17-88a1-b1327a1d3f43', 'Toledo', 'Brody Critchlow', 'Nightraptor360', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/utrockets.com/images/responsive_2021/main-logo.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('308a6eea-5fa9-424a-8c97-61ec15dd0a42','983c76d0-6afa-4a17-88a1-b1327a1d3f43', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('b65b4323-c82f-4634-b6ec-3f98f8b5c600', 'Bowling Green', 'Kyle-Jay Critchlow', 'cloaked_revenant', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/bgsufalcons.com/images/nextgen_2022/logo_main.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('49deaf6e-5d90-40d0-b6aa-54803e36a5f5','b65b4323-c82f-4634-b6ec-3f98f8b5c600', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('24a95425-d89c-494f-956d-73580ba251d1', 'Ohio', 'Tyler Critchlow', 'ShinyWasSeE', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/ohiobobcats.com/images/responsive_2021/logo-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('950030e4-e9e6-4052-b8c3-d5129579f9d5','24a95425-d89c-494f-956d-73580ba251d1', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e', 'Ball State', 'Darin Critchlow', 'NthDemolisher', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/bsu.sidearmsports.com/images/responsive_2019/ballstate_logo_cardinal.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('7bd769ad-803d-4549-bbc6-07f902799ccc','e9fcb5d1-1c68-4d35-b49f-fe7f90e6320e', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('5a9ac517-4494-44fb-8c69-02bebeac5259', 'Eastern Michigan', 'Will Myers', 'myerswj163', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/emich_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('3dacdb20-36d9-4e42-8042-cb8e4705b707','5a9ac517-4494-44fb-8c69-02bebeac5259', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('ae313b41-8332-47de-af3b-1b6d68e8b8cb', 'Central Michigan', 'Todd Critchlow', 'tcritch3', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/centralmich_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('5b3ba3f0-2063-423b-b488-6240fcefb01b','ae313b41-8332-47de-af3b-1b6d68e8b8cb', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('c80180cc-808b-46d3-a1a8-e12ce74fd376', 'Western Michigan', 'Spencer Critchlow', 'KMBAPPEPANDA5', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/wmichigan_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('dc3f2f14-2532-4ee3-934d-fd59a4ba4724','c80180cc-808b-46d3-a1a8-e12ce74fd376', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a', 'Miami-OH', 'Bradley Critchlow', 'apple_lucky144', 'MAC', true, 'https://miamioh.edu/_hannonhill/_files/svgs/logo-2021.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('4ec47a3f-0a9d-449f-ac2a-048d5a50f921','c0f2932f-f2f9-4fbd-a5d7-1038e6fcb45a', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e', 'Kent State', 'Cameron Kempff', 'Nerdvana_93', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/kentst_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('d6b4e59a-5793-4aed-9a1d-2df046e1b820','322c71c6-ffd4-4bb7-a9c3-2be15ff6bc2e', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('a824c605-3ee2-495c-8a62-c4d3c1868ed2', 'Northern Illinois', 'Ilan Hurtado', 'passionphruit', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/niu_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('0a5d9e67-4c92-48c8-8d7b-f941881ae020','a824c605-3ee2-495c-8a62-c4d3c1868ed2', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('e93cfd20-77df-4c16-9827-00057f12098f', 'Buffalo', 'Eric Critchlow', 'The-Eric-C', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/buffalo_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('a94b8579-0f28-4307-99e9-16f4a55f49a9','e93cfd20-77df-4c16-9827-00057f12098f', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('34ea5de2-79d3-45ed-9c1b-e06b8f2de728', 'Akron', 'Eddie Jeppson', 'FastEddie06', 'MAC', true, 'https://dbukjj6eu5tsf.cloudfront.net/sidearm.sites/mac-sports/images/responsive_2022/members/on-primary/akron_on-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('30a3cbac-a239-497a-941b-b0bcae5132e6','34ea5de2-79d3-45ed-9c1b-e06b8f2de728', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, username, conference, is_human, image_url) values('a6873c5c-41f9-49a5-bf62-46d9719ccdb2', 'Jacksonville State', 'Jayden Jeppson', 'jjfire5', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/jsugamecocksports.com/images/responsive_2023/JaxState_state.png') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('6c95f038-cbfb-4e29-b5fd-c9e2ced61c1f','a6873c5c-41f9-49a5-bf62-46d9719ccdb2', 2024, 0, 0) ON CONFLICT DO NOTHING;