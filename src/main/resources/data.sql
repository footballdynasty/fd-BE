-- WITH v_id AS (
-- INSERT
-- INTO team(team_name)
-- values ('test') RETURNING id
-- );

INSERT into team(id, team_name) values('177c56c8-d596-4477-8ee1-11e77e06ab9f', 'test')
-- SELECT ID INTO grabbed_id FROM team WHERE team_name = 'test';
INSERT INTO standings(team_id) values('177c56c8-d596-4477-8ee1-11e77e06ab9f');