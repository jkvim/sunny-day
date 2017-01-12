CREATE TABLE `exam` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `logic_q_count` integer NOT NULL,
  `prog_q_count` integer NOT NULL,
  `status_id` tinyint DEFAULT NULL,
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_exam_status FOREIGN KEY (status_id) REFERENCES exam_status(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;