DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content text,
  post_id bigint,
  CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts(id)
);