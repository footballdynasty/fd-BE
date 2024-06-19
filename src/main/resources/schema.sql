CREATE TABLE standings
(
    id UUID DEFAULT gen_random_uuid (),
    team_id UUID NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE team
(
    id UUID default gen_random_uuid (),
    team_name VARCHAR NOT NULL,
    PRIMARY KEY (id)
);