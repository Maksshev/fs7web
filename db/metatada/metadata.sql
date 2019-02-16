CREATE TABLE history (
    id INTEGER DEFAULT nextval('history_id_seq1'::regclass) PRIMARY KEY NOT NULL,
    a INTEGER,
    op VARCHAR,
    b INTEGER,
    r INTEGER,
    at TIMESTAMP DEFAULT now()
    user_id INTEGER
);
CREATE TABLE users (
    id INTEGER DEFAULT nextval('users_id_seq'::regclass) PRIMARY KEY NOT NULL,
    name VARCHAR(32)
);
