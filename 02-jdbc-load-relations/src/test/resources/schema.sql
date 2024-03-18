DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
	user_id  BIGINT PRIMARY KEY,
	username VARCHAR
);

CREATE TABLE messages
(
	message_id BIGINT PRIMARY KEY,
	sender_id  BIGINT,
	receiver   VARCHAR,
	content    VARCHAR
);

ALTER TABLE messages
	add foreign key (sender_id) REFERENCES users (user_id);

