CREATE TABLE events (
    id             SERIAL,
    description    VARCHAR(255) NOT NULL,
    organizer      VARCHAR(255) NOT NULL,
    time_of_event  DATE         NOT NULL,
    place_of_event VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);