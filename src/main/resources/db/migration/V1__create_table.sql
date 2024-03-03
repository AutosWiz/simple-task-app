CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL
);

CREATE TABLE authorities (
    id INT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(id) REFERENCES users(id)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (id, authority);

CREATE TABLE tasks (
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(1500) NOT NULL,
    due_date DATE,
    task_status VARCHAR(100) NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE tasks_sequence (
    id INT NOT NULL
);

CREATE TABLE users_sequence (
    id INT NOT NULL
);
