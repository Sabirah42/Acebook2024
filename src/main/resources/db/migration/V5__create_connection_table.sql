CREATE TABLE connections (
    id bigserial PRIMARY KEY,
    user_id BIGINT,
    friend_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (friend_id) REFERENCES users(id)
);