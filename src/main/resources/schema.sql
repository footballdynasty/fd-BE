CREATE TABLE IF NOT EXISTS team
(
    id UUID default gen_random_uuid (),
    "name" VARCHAR NOT NULL,
    coach VARCHAR,
    username VARCHAR,
    conference VARCHAR,
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

CREATE TABLE IF NOT EXISTS week
(
    id UUID DEFAULT gen_random_uuid (),
    year INTEGER NOT NULL,
    week_number INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS game
(
    id UUID DEFAULT gen_random_uuid (),
    game_id VARCHAR NOT NULL,
    home_team_id UUID references team(id),
    away_team_id UUID references team(id),
    home_score INTEGER NOT NULL DEFAULT 0,
    away_score INTEGER NOT NULL DEFAULT 0,
    date DATE NOT NULL,
    week_id UUID references week(id),
    PRIMARY KEY (id)
);