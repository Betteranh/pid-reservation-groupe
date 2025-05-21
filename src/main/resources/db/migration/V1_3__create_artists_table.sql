CREATE TABLE `artists`
(
    `id`        int(11)                                NOT NULL AUTO_INCREMENT,
    `firstname` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
    `lastname`  varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
    `troupe_id` BIGINT                                 NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_artist_troupe FOREIGN KEY (troupe_id)
        REFERENCES troupes (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
