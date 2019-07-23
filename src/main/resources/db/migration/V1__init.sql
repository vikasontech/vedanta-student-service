CREATE TABLE `revinfo` (
  `rev` int(11) NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `combo_master_data` (
  `id` bigint(20) NOT NULL,
  `combo_name` varchar(255) DEFAULT NULL,
  `combo_key` varchar(100) DEFAULT NULL,
  `combo_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE `fee_details` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `standard` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE `fee_details_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `standard` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKphxs9kv9n12ffey0aut8rkljl` (`rev`),
  CONSTRAINT `FKphxs9kv9n12ffey0aut8rkljl` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `fine_master` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fine_type` varchar(255) DEFAULT NULL,
  `standard` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `fine_master_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fine_type` varchar(255) DEFAULT NULL,
  `standard` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKrptmpis26ut31lo7iytslb0bo` (`rev`),
  CONSTRAINT `FKrptmpis26ut31lo7iytslb0bo` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `fine_payment` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) NOT NULL,
  `fine_id` bigint(20) NOT NULL,
  `instrument_no` varchar(255) NOT NULL,
  `transaction_mode`varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fine_record` (
  `id` bigint(20) NOT NULL primary key,
`created_by` varchar(50) not NULL,
`created_date` datetime not NULL,
`last_modified_by` varchar(50) default NULL,
`last_modified_date` datetime default NULL,
`amount` decimal(19,2) not NULL,
`description` varchar(255) not NULL,
`enrolment_no` bigint(20) not NULL,
`fine_type` varchar(255) not NULL,
`status` varchar(255) not NULL
) ;



CREATE TABLE `fine_payment_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `fine_id` bigint(20) DEFAULT NULL,
  `instrument_no` varchar(255) DEFAULT NULL,
  `transaction_mode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKs2prcfsyyn54japvany3ichbh` (`rev`),
  CONSTRAINT `FKs2prcfsyyn54japvany3ichbh` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `fine_record_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enrolment_no` bigint(20) DEFAULT NULL,
  `fine_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKsmeb9qfoypusdeqha1k2bp1lc` (`rev`),
  CONSTRAINT `FKsmeb9qfoypusdeqha1k2bp1lc` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;



CREATE TABLE `jhi_authority` (
`name` varchar(50) NOT NULL,
PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_persistent_audit_event` (
`event_id` bigint(20) NOT NULL AUTO_INCREMENT,
`event_date` datetime DEFAULT NULL,
`event_type` varchar(255) DEFAULT NULL,
`principal` varchar(255) NOT NULL,
PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_persistent_audit_evt_data` (
`event_id` bigint(20) NOT NULL,
`value` varchar(255) DEFAULT NULL,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`event_id`,`name`),
CONSTRAINT `FK2ehnyx2si4tjd2nt4q7y40v8m` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_user` (
`id` varchar(255) NOT NULL,
`created_by` varchar(50) NOT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`activated` bit(1) NOT NULL,
`email` varchar(254) DEFAULT NULL,
`first_name` varchar(50) DEFAULT NULL,
`image_url` varchar(256) DEFAULT NULL,
`lang_key` varchar(6) DEFAULT NULL,
`last_name` varchar(50) DEFAULT NULL,
`login` varchar(50) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_9y0frpqnmqe7y6mk109vw3246` (`login`),
UNIQUE KEY `UK_bycanyquvi09q7fh5pgxrqnku` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_user_authority` (
`user_id` varchar(255) NOT NULL,
`authority_name` varchar(50) NOT NULL,
PRIMARY KEY (`user_id`,`authority_name`),
KEY `FK4psxl0jtx6nr7rhqbynr6itoc` (`authority_name`),
CONSTRAINT `FK290okww5jujghp4el5i7mgwu0` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`),
CONSTRAINT `FK4psxl0jtx6nr7rhqbynr6itoc` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `job_scheduler_information` (
`id` bigint(20) NOT NULL,
`created_by` varchar(50) NOT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`end_time` datetime DEFAULT NULL,
`job_status` varchar(255) DEFAULT NULL,
`process_name` varchar(255) NOT NULL,
`schedule_status` varchar(255) NOT NULL,
`start_time` datetime NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








CREATE TABLE `student_detail` (
`id` bigint(20) NOT NULL,
`created_by` varchar(50) NOT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`address1` varchar(255) NOT NULL,
`address2` varchar(255) DEFAULT NULL,
`admission_class` int(11) NOT NULL,
`admission_status` varchar(255) NOT NULL,
`area_of_interest` varchar(255) DEFAULT NULL,
`blood_group` varchar(255) DEFAULT NULL,
`board` varchar(255) DEFAULT NULL,
`caste` varchar(255) NOT NULL,
`chechak` varchar(255) DEFAULT NULL,
`conduct` varchar(255) DEFAULT NULL,
`current_class` int(11) DEFAULT NULL,
`date_of_admission` datetime NOT NULL,
`date_of_birth` datetime NOT NULL,
`date_of_termination` datetime DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`father_name` varchar(255) NOT NULL,
`gender` varchar(255) NOT NULL,
`instrument_no` varchar(255) NOT NULL,
`is_fee_due` bit(1) not NULL,
`is_withdrawal` bit(1) NOT NULL,
`last_class` int(11) DEFAULT NULL,
`last_class_year` int(11) DEFAULT NULL,
`last_rank` int(11) DEFAULT NULL,
`last_school_mark_sheet` varchar(255) DEFAULT NULL,
`last_school_name` varchar(255) DEFAULT NULL,
`mobile` varchar(255) DEFAULT NULL,
`mother_name` varchar(255) DEFAULT NULL,
`mother_tongue` varchar(255) DEFAULT NULL,
`name` varchar(255) NOT NULL,
`nationality` varchar(255) DEFAULT NULL,
`number_of_siblings` int(11) DEFAULT NULL,
`occupation` varchar(255) DEFAULT NULL,
`phone1` varchar(255) DEFAULT NULL,
`phone2` varchar(255) DEFAULT NULL,
`pin1` varchar(255) NOT NULL,
`pin2` varchar(255) DEFAULT NULL,
`reason_for_leave` varchar(255) DEFAULT NULL,
`relation` varchar(255) DEFAULT NULL,
`religion` varchar(255) NOT NULL,
`scored` int(11) DEFAULT NULL,
`subcaste` varchar(255) DEFAULT NULL,
`total_marks` int(11) DEFAULT NULL,
`transfer_certificate` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `student_detail_aud` (
`id` bigint(20) NOT NULL,
`rev` int(11) NOT NULL,
`revtype` tinyint(4) DEFAULT NULL,
`created_by` varchar(50) DEFAULT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`address1` varchar(255) DEFAULT NULL,
`address2` varchar(255) DEFAULT NULL,
`admission_class` int(11) DEFAULT NULL,
`admission_status` varchar(255) DEFAULT NULL,
`area_of_interest` varchar(255) DEFAULT NULL,
`blood_group` varchar(255) DEFAULT NULL,
`board` varchar(255) DEFAULT NULL,
`caste` varchar(255) DEFAULT NULL,
`chechak` varchar(255) DEFAULT NULL,
`conduct` varchar(255) DEFAULT NULL,
`current_class` int(11) DEFAULT NULL,
`date_of_admission` datetime DEFAULT NULL,
`date_of_birth` datetime DEFAULT NULL,
`date_of_termination` datetime DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`father_name` varchar(255) DEFAULT NULL,
`gender` varchar(255) DEFAULT NULL,
`instrument_no` varchar(255) DEFAULT NULL,
`is_fee_due` bit(1) DEFAULT NULL,
`is_withdrawal` bit(1) DEFAULT NULL,
`last_class` int(11) DEFAULT NULL,
`last_class_year` int(11) DEFAULT NULL,
`last_rank` int(11) DEFAULT NULL,
`last_school_mark_sheet` varchar(255) DEFAULT NULL,
`last_school_name` varchar(255) DEFAULT NULL,
`mobile` varchar(255) DEFAULT NULL,
`mother_name` varchar(255) DEFAULT NULL,
`mother_tongue` varchar(255) DEFAULT NULL,
`name` varchar(255) DEFAULT NULL,
`nationality` varchar(255) DEFAULT NULL,
`number_of_siblings` int(11) DEFAULT NULL,
`occupation` varchar(255) DEFAULT NULL,
`phone1` varchar(255) DEFAULT NULL,
`phone2` varchar(255) DEFAULT NULL,
`pin1` varchar(255) DEFAULT NULL,
`pin2` varchar(255) DEFAULT NULL,
`reason_for_leave` varchar(255) DEFAULT NULL,
`relation` varchar(255) DEFAULT NULL,
`religion` varchar(255) DEFAULT NULL,
`scored` int(11) DEFAULT NULL,
`subcaste` varchar(255) DEFAULT NULL,
`total_marks` int(11) DEFAULT NULL,
`transfer_certificate` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`,`rev`),
KEY `FKcd2e9inqyc9fm6h2aofe17o8d` (`rev`),
CONSTRAINT `FKcd2e9inqyc9fm6h2aofe17o8d` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
