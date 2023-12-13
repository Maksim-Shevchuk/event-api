CREATE TABLE events
(
    id          SERIAL,
    subject     VARCHAR(255) NOT NULL,
    description TEXT,
    organizer   VARCHAR(255) NOT NULL,
    event_date  TIMESTAMP    NOT NULL,
    event_place VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);