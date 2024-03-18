DROP TABLE IF EXISTS messages;
CREATE TABLE messages
(
	message_id BIGINT PRIMARY KEY,
	sender     VARCHAR,
	receiver   VARCHAR,
	content    VARCHAR
);