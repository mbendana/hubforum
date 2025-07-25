CREATE TABLE IF NOT EXISTS topics
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(100) NOT NULL,
    creation_date DATETIME NOT NULL,
    status TINYINT NOT NULL,
    author_id BIGINT NOT NULL,
    course_name VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)
);
