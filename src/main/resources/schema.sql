CREATE TABLE IF NOT EXISTS team
(
    id UUID default gen_random_uuid (),
    "name" VARCHAR NOT NULL,
    coach VARCHAR NOT NULL,
    username VARCHAR NOT NULL,
    conference VARCHAR NOT NULL,
    is_human BOOLEAN DEFAULT false,
    image_url VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS standings
(
    id UUID DEFAULT gen_random_uuid (),
    team_id UUID references team(id),
    year INTEGER NOT NULL,
    wins INTEGER NOT NULL,
    losses INTEGER NOT NULL,
    rank INTEGER,
    receiving_votes INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS achievements
(
    id UUID DEFAULT gen_random_uuid (),
    description VARCHAR NOT NULL,
    reward VARCHAR NOT NULL,
    date_completed BIGINT
);

CREATE TABLE IF NOT EXISTS schedule
(
    id UUID DEFAULT gen_random_uuid (),
    year INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS game
(
    id UUID DEFAULT gen_random_uuid (),
    home_team_id UUID references team(id),
    away_team_id UUID references team(id),
    home_score INTEGER NOT NULL DEFAULT 0,
    away_score INTEGER NOT NULL DEFAULT 0,
    date DATE NOT NULL,
    schedule_id UUID references schedule(id),
    PRIMARY KEY (id)
);