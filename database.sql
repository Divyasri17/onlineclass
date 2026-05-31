CREATE TABLE `teacher` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `password` varchar(255) NOT NULL,
   `timezone` varchar(255) DEFAULT NULL,
   `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `phone_number` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `email` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `parent` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `password` varchar(255) NOT NULL,
   `timezone` varchar(255) DEFAULT NULL,
   `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `phone_number` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `email` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `offering` (
   `id` int NOT NULL AUTO_INCREMENT,
   `course_id` int NOT NULL,
   `teacher_id` int NOT NULL,
   `batch_name` varchar(255) DEFAULT NULL,
   `status` varchar(255) DEFAULT NULL,
   `price` double DEFAULT NULL,
   `mode` varchar(255) DEFAULT NULL,
   `level` varchar(255) DEFAULT NULL,
   `duration_type` varchar(255) DEFAULT NULL,
   `duration_count` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `course_id` (`course_id`),
   KEY `teacher_id` (`teacher_id`),
   CONSTRAINT `offering_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
   CONSTRAINT `offering_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 
 CREATE TABLE `offering` (
   `id` int NOT NULL AUTO_INCREMENT,
   `course_id` int NOT NULL,
   `teacher_id` int NOT NULL,
   `batch_name` varchar(255) DEFAULT NULL,
   `status` varchar(255) DEFAULT NULL,
   `price` double DEFAULT NULL,
   `mode` varchar(255) DEFAULT NULL,
   `level` varchar(255) DEFAULT NULL,
   `duration_type` varchar(255) DEFAULT NULL,
   `duration_count` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `course_id` (`course_id`),
   KEY `teacher_id` (`teacher_id`),
   CONSTRAINT `offering_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
   CONSTRAINT `offering_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 
 
 CREATE TABLE `session` (
   `id` int NOT NULL AUTO_INCREMENT,
   `offering_id` int NOT NULL,
   `start_time_utc` timestamp NOT NULL,
   `end_time_utc` timestamp NOT NULL,
   PRIMARY KEY (`id`),
   KEY `offering_id` (`offering_id`),
   CONSTRAINT `session_ibfk_1` FOREIGN KEY (`offering_id`) REFERENCES `offering` (`id`) ON DELETE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 
 
 
 
 
 CREATE TABLE `booking` (
   `id` int NOT NULL AUTO_INCREMENT,
   `parent_id` int NOT NULL,
   `course_id` int NOT NULL,
   `offering_id` int NOT NULL,
   `session_id` int NOT NULL,
   `booked_start_time` timestamp NOT NULL,
   `booked_end_time` timestamp NOT NULL,
   `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 