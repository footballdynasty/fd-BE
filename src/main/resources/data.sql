INSERT INTO team(id, name, coach, conference) values('177c56c8-d596-4477-8ee1-11e77e06ab9f', 'Thorns', 'Brody', 'MWC') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('606e6bce-13de-4eff-aea9-bda3c8daf043','177c56c8-d596-4477-8ee1-11e77e06ab9f', 2024, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, conference) values('6268f8eb-e68a-48a7-85ba-bc8f48a6505b', 'Ty Dawgs', 'Tyler', 'MWC') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('5b760801-1e57-428a-9cb5-ad6182daa92e','6268f8eb-e68a-48a7-85ba-bc8f48a6505b', 2024, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO team(id, name, coach, conference) values('88260b66-3576-476c-b02c-92f4a58c531b', 'NthDemo', 'Darin', 'MWC') ON CONFLICT DO NOTHING;
INSERT INTO standings(id, team_id, year, wins, losses) values('ac9c807e-69fb-43ae-a2a6-ba8505c2a157','88260b66-3576-476c-b02c-92f4a58c531b', 2023, 16, 0) ON CONFLICT DO NOTHING;

INSERT INTO achievements(description, reward, date_completed) values('An example achievement', 'One schedule change', 1796929477);
INSERT INTO achievements(description, reward, date_completed) values('Another example achievement', 'One schedule change', 1719833117);