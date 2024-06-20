CREATE TABLE IF NOT EXISTS team
(
    id UUID default gen_random_uuid (),
    "name" VARCHAR NOT NULL,
    coach VARCHAR NOT NULL,
    conference VARCHAR NOT NULL,
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