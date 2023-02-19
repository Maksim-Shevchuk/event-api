CREATE TABLE events(
	id SERIAL, 
	description VARCHAR(255) NOT NULL,
	organizer VARCHAR(255) NOT NULL,
	time_of_event DATE NOT NULL,
	place_of_event VARCHAR(255) NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO events(
  	description, 
	organizer, 
	time_of_event, 
  	place_of_event
) 
VALUES 
  	('sport event', 'Darya Domracheva', '2003-01-01', 'China'), 
  	('team-building', 'HR department', '2020-07-29', 'Belarus'),
  	('activity', 'random guy', '2023-08-30', 'Anywhere');
