CREATE TABLE IF NOT EXISTS team
(
    id UUID default gen_random_uuid (),
    "name" VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS standings
(
    id UUID DEFAULT gen_random_uuid (),
    team_id UUID references team(id),
    year Integer NOT NULL,
    PRIMARY KEY (id)
);