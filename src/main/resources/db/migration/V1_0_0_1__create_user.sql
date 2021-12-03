CREATE TABLE IF NOT EXISTS users
(
    id          bigserial PRIMARY KEY,
    login       varchar(50)     NOT NULL,
    password    varchar(255)    NOT NULL,
    enable      boolean         NOT NULL,
    created     date,
    modified    date
    );

CREATE UNIQUE INDEX users_login_idx ON users (login);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id     int8            NOT NULL,
    role        varchar(20)     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT user_role_role_unique UNIQUE (user_id, role)
    );
