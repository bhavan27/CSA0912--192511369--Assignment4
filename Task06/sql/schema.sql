CREATE DATABASE IF NOT EXISTS campus_events;
USE campus_events;

CREATE TABLE IF NOT EXISTS registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    event_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_student_event UNIQUE (student_id, event_name)
);

INSERT INTO registrations(student_id,student_name,event_name,email)
VALUES
('STU1001','Aarav Kumar','TechFest','aarav@example.com'),
('STU1002','Diya Raman','Cultural Night','diya@example.com'),
('STU1003','Kavin Raj','Sports Meet','kavin@example.com');
