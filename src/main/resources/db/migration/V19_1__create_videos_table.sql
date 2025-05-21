CREATE TABLE videos
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    video_url   VARCHAR(255) NOT NULL UNIQUE,
    show_id     int(11)      NOT NULL,
    artist_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB;
