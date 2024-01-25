CREATE TABLE avatars (
  id SERIAL PRIMARY KEY,
  file_name VARCHAR(255),
  file_path VARCHAR(255)
);

INSERT INTO avatars (file_name, file_path) VALUES ('default', 'images/default.png');

ALTER TABLE users
ADD COLUMN avatar_id BIGINT;

ALTER TABLE users
ADD CONSTRAINT fk_avatar
FOREIGN KEY (avatar_id)
REFERENCES avatars(id);