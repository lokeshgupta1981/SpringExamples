CREATE TABLE actor (
	id INT PRIMARY KEY,
	first_name VARCHAR(255) NULL,
	last_name VARCHAR(255) NULL,
	birth_date VARCHAR(255) NULL
);

CREATE TABLE album (
	id INT PRIMARY KEY,
	title VARCHAR(255) NULL,
	description VARCHAR(255) NULL,
	release_date VARCHAR(255) NULL
);

CREATE TABLE actor_album (
	actor_id INT,
	album_id INT
);