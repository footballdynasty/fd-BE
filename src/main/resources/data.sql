INSERT INTO team(id, name, coach, conference, is_human, image_url) values('177c56c8-d596-4477-8ee1-11e77e06ab9f', 'Toledo', 'Brody Critchlow', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/utrockets.com/images/responsive_2021/main-logo.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('606e6bce-13de-4eff-aea9-bda3c8daf043','177c56c8-d596-4477-8ee1-11e77e06ab9f', 2024, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, conference, is_human, image_url) values('6268f8eb-e68a-48a7-85ba-bc8f48a6505b', 'Duquesne', 'Jerry Schmitt', 'NEC', false, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/goduquesne.com/images/responsive_2021/header-logo.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('5b760801-1e57-428a-9cb5-ad6182daa92e','6268f8eb-e68a-48a7-85ba-bc8f48a6505b', 2024, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, conference, is_human, image_url) values('88260b66-3576-476c-b02c-92f4a58c531b', 'Ohio', 'Tyler Critchlow', 'MAC', true, 'https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/ohiobobcats.com/images/responsive_2021/logo-primary.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('ac9c807e-69fb-43ae-a2a6-ba8505c2a157','88260b66-3576-476c-b02c-92f4a58c531b', 2023, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, conference, is_human, image_url) values('eb431ace-bce9-48a6-97cb-00819b4eaaab', 'Syracuse', 'Fran Brown', 'ACC', false, 'https://d3d4f9a2nwdz4m.cloudfront.net/images/sng_2022/main_nav_logo.svg') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('eb431ace-bce9-48a6-97cb-00819b4eaaab','88260b66-3576-476c-b02c-92f4a58c531b', 2023, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO schedule(id, year) values('dffdd39a-1416-4816-9b54-dff3506f0ca0', 2024) ON CONFLICT DO NOTHING;
INSERT INTO game(id, home_team_id, away_team_id, home_score, away_score, date, schedule_id) values('7acd60a8-9da4-4adb-9abb-9f6901dabfce', '177c56c8-d596-4477-8ee1-11e77e06ab9f', '6268f8eb-e68a-48a7-85ba-bc8f48a6505b', 0, 0, '2024-07-21', 'dffdd39a-1416-4816-9b54-dff3506f0ca0') ON CONFLICT DO NOTHING;
INSERT INTO game(id, home_team_id, away_team_id, home_score, away_score, date, schedule_id) values('7686bcfd-e06d-4a23-93c0-6e6f1efc6717', 'eb431ace-bce9-48a6-97cb-00819b4eaaab', '88260b66-3576-476c-b02c-92f4a58c531b', 0, 0, '2024-07-21', 'dffdd39a-1416-4816-9b54-dff3506f0ca0') ON CONFLICT DO NOTHING;