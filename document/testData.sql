-- Test data generation script
-- Four teachers (role teacher), fifty students (role student), twenty courses (5 per teacher)
-- Each student selects 15 courses using a rotating window to balance distribution.
-- Password hash provided by user is reused for all accounts.
-- Idempotent: removes previously generated teachers/students/courses/enrollments before reinserting.
-- Uses dynamic lookup of teacher_id to avoid depending on fixed auto-increment values.

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
START TRANSACTION;

-- 1. Ensure roles exist
INSERT IGNORE INTO role (name, description)
VALUES ('teacher', 'Teacher Role'),
       ('student', 'Student Role'),
       ('admin', 'Admin Role');

-- 2. Remove previously generated test users (teachers teacher1..4, students student01..student50) and their enrollments/courses if re-running.

-- 3. Insert teachers (auto IDs; will be looked up dynamically later)
INSERT INTO user (username, password, email, real_name, nick_name, create_time, is_enabled, is_delete, is_admin)
VALUES ('teacher1', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'teacher1@example.com', 'Teacher One', 'Teacher1', NOW(), 1, 0,
        0),
       ('teacher2', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'teacher2@example.com', 'Teacher Two', 'Teacher2', NOW(), 1, 0,
        0),
       ('teacher3', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'teacher3@example.com', 'Teacher Three', 'Teacher3', NOW(), 1,
        0, 0),
       ('teacher4', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'teacher4@example.com', 'Teacher Four', 'Teacher4', NOW(), 1, 0,
        0),
       ('admin', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'teacher4@example.com', 'Teacher Four', 'Teacher4', NOW(), 1, 0,
        0);

-- 4. Teacher role mappings
INSERT IGNORE INTO user_roles (username, role_name)
VALUES ('teacher1', 'teacher'),
       ('teacher2', 'teacher'),
       ('teacher3', 'teacher'),
       ('teacher4', 'teacher'),
       ('admin', 'admin');

-- 5. Insert students
INSERT INTO user (username, password, email, real_name, nick_name, create_time, is_enabled, is_delete, is_admin)
VALUES ('student01', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student01@example.com', 'Student 01', 'Student01', NOW(), 1,
        0, 0),
       ('student02', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student02@example.com', 'Student 02', 'Student02', NOW(), 1,
        0, 0),
       ('student03', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student03@example.com', 'Student 03', 'Student03', NOW(), 1,
        0, 0),
       ('student04', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student04@example.com', 'Student 04', 'Student04', NOW(), 1,
        0, 0),
       ('student05', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student05@example.com', 'Student 05', 'Student05', NOW(), 1,
        0, 0),
       ('student06', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student06@example.com', 'Student 06', 'Student06', NOW(), 1,
        0, 0),
       ('student07', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student07@example.com', 'Student 07', 'Student07', NOW(), 1,
        0, 0),
       ('student08', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student08@example.com', 'Student 08', 'Student08', NOW(), 1,
        0, 0),
       ('student09', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student09@example.com', 'Student 09', 'Student09', NOW(), 1,
        0, 0),
       ('student10', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student10@example.com', 'Student 10', 'Student10', NOW(), 1,
        0, 0),
       ('student11', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student11@example.com', 'Student 11', 'Student11', NOW(), 1,
        0, 0),
       ('student12', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student12@example.com', 'Student 12', 'Student12', NOW(), 1,
        0, 0),
       ('student13', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student13@example.com', 'Student 13', 'Student13', NOW(), 1,
        0, 0),
       ('student14', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student14@example.com', 'Student 14', 'Student14', NOW(), 1,
        0, 0),
       ('student15', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student15@example.com', 'Student 15', 'Student15', NOW(), 1,
        0, 0),
       ('student16', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student16@example.com', 'Student 16', 'Student16', NOW(), 1,
        0, 0),
       ('student17', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student17@example.com', 'Student 17', 'Student17', NOW(), 1,
        0, 0),
       ('student18', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student18@example.com', 'Student 18', 'Student18', NOW(), 1,
        0, 0),
       ('student19', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student19@example.com', 'Student 19', 'Student19', NOW(), 1,
        0, 0),
       ('student20', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student20@example.com', 'Student 20', 'Student20', NOW(), 1,
        0, 0),
       ('student21', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student21@example.com', 'Student 21', 'Student21', NOW(), 1,
        0, 0),
       ('student22', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student22@example.com', 'Student 22', 'Student22', NOW(), 1,
        0, 0),
       ('student23', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student23@example.com', 'Student 23', 'Student23', NOW(), 1,
        0, 0),
       ('student24', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student24@example.com', 'Student 24', 'Student24', NOW(), 1,
        0, 0),
       ('student25', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student25@example.com', 'Student 25', 'Student25', NOW(), 1,
        0, 0),
       ('student26', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student26@example.com', 'Student 26', 'Student26', NOW(), 1,
        0, 0),
       ('student27', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student27@example.com', 'Student 27', 'Student27', NOW(), 1,
        0, 0),
       ('student28', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student28@example.com', 'Student 28', 'Student28', NOW(), 1,
        0, 0),
       ('student29', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student29@example.com', 'Student 29', 'Student29', NOW(), 1,
        0, 0),
       ('student30', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student30@example.com', 'Student 30', 'Student30', NOW(), 1,
        0, 0),
       ('student31', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student31@example.com', 'Student 31', 'Student31', NOW(), 1,
        0, 0),
       ('student32', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student32@example.com', 'Student 32', 'Student32', NOW(), 1,
        0, 0),
       ('student33', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student33@example.com', 'Student 33', 'Student33', NOW(), 1,
        0, 0),
       ('student34', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student34@example.com', 'Student 34', 'Student34', NOW(), 1,
        0, 0),
       ('student35', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student35@example.com', 'Student 35', 'Student35', NOW(), 1,
        0, 0),
       ('student36', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student36@example.com', 'Student 36', 'Student36', NOW(), 1,
        0, 0),
       ('student37', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student37@example.com', 'Student 37', 'Student37', NOW(), 1,
        0, 0),
       ('student38', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student38@example.com', 'Student 38', 'Student38', NOW(), 1,
        0, 0),
       ('student39', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student39@example.com', 'Student 39', 'Student39', NOW(), 1,
        0, 0),
       ('student40', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student40@example.com', 'Student 40', 'Student40', NOW(), 1,
        0, 0),
       ('student41', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student41@example.com', 'Student 41', 'Student41', NOW(), 1,
        0, 0),
       ('student42', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student42@example.com', 'Student 42', 'Student42', NOW(), 1,
        0, 0),
       ('student43', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student43@example.com', 'Student 43', 'Student43', NOW(), 1,
        0, 0),
       ('student44', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student44@example.com', 'Student 44', 'Student44', NOW(), 1,
        0, 0),
       ('student45', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student45@example.com', 'Student 45', 'Student45', NOW(), 1,
        0, 0),
       ('student46', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student46@example.com', 'Student 46', 'Student46', NOW(), 1,
        0, 0),
       ('student47', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student47@example.com', 'Student 47', 'Student47', NOW(), 1,
        0, 0),
       ('student48', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student48@example.com', 'Student 48', 'Student48', NOW(), 1,
        0, 0),
       ('student49', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student49@example.com', 'Student 49', 'Student49', NOW(), 1,
        0, 0),
       ('student50', 'f38a9bcfa7b985ea9fb8f4e09282ae93', 'student50@example.com', 'Student 50', 'Student50', NOW(), 1,
        0, 0);

-- 6. Student role mappings
INSERT IGNORE INTO user_roles (username, role_name)
SELECT username, 'student'
FROM user
WHERE username LIKE 'student%';

-- 7. Insert 20 courses (5 per teacher) dynamically resolving teacher_id
INSERT INTO course (name, course_num, course_time, course_hours, credit, teacher_id, teacher_name, capacity, class_size)
VALUES ('Course 1', 101, 'Mon 08:00-10:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher1'), 'teacher1', 60,
        0),
       ('Course 2', 102, 'Mon 10:00-12:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher1'), 'teacher1', 60,
        0),
       ('Course 3', 103, 'Tue 08:00-10:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher1'), 'teacher1', 60,
        0),
       ('Course 4', 104, 'Tue 10:00-12:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher1'), 'teacher1', 60,
        0),
       ('Course 5', 105, 'Wed 08:00-10:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher1'), 'teacher1', 60,
        0),
       ('Course 6', 106, 'Mon 13:00-15:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher2'), 'teacher2', 60,
        0),
       ('Course 7', 107, 'Mon 15:00-17:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher2'), 'teacher2', 60,
        0),
       ('Course 8', 108, 'Tue 13:00-15:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher2'), 'teacher2', 60,
        0),
       ('Course 9', 109, 'Tue 15:00-17:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher2'), 'teacher2', 60,
        0),
       ('Course 10', 110, 'Wed 10:00-12:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher2'), 'teacher2', 60,
        0),
       ('Course 11', 111, 'Wed 13:00-15:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher3'), 'teacher3', 60,
        0),
       ('Course 12', 112, 'Wed 15:00-17:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher3'), 'teacher3', 60,
        0),
       ('Course 13', 113, 'Thu 08:00-10:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher3'), 'teacher3', 60,
        0),
       ('Course 14', 114, 'Thu 10:00-12:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher3'), 'teacher3', 60,
        0),
       ('Course 15', 115, 'Thu 13:00-15:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher3'), 'teacher3', 60,
        0),
       ('Course 16', 116, 'Fri 08:00-10:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher4'), 'teacher4', 60,
        0),
       ('Course 17', 117, 'Fri 10:00-12:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher4'), 'teacher4', 60,
        0),
       ('Course 18', 118, 'Fri 13:00-15:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher4'), 'teacher4', 60,
        0),
       ('Course 19', 119, 'Fri 15:00-17:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher4'), 'teacher4', 60,
        0),
       ('Course 20', 120, 'Fri 18:00-20:00', 32, 2, (SELECT id FROM user WHERE username = 'teacher4'), 'teacher4', 60,
        0);

-- 8. Generate 15 course selections per student (50 * 15 = 750 rows)
INSERT INTO student_course (student_name, course_id, semester, status, select_time, score, remark)
SELECT CONCAT('student', LPAD(s.n + 1, 2, '0')) AS student_name,
       ((s.n + o.o) % 20) + 1                   AS course_id,
       '2025-Fall'                              AS semester,
       0                                        AS status,
       NOW()                                    AS select_time,
       NULL                                     AS score,
       NULL                                     AS remark
FROM (SELECT 0 n
      UNION ALL
      SELECT 1
      UNION ALL
      SELECT 2
      UNION ALL
      SELECT 3
      UNION ALL
      SELECT 4
      UNION ALL
      SELECT 5
      UNION ALL
      SELECT 6
      UNION ALL
      SELECT 7
      UNION ALL
      SELECT 8
      UNION ALL
      SELECT 9
      UNION ALL
      SELECT 10
      UNION ALL
      SELECT 11
      UNION ALL
      SELECT 12
      UNION ALL
      SELECT 13
      UNION ALL
      SELECT 14
      UNION ALL
      SELECT 15
      UNION ALL
      SELECT 16
      UNION ALL
      SELECT 17
      UNION ALL
      SELECT 18
      UNION ALL
      SELECT 19
      UNION ALL
      SELECT 20
      UNION ALL
      SELECT 21
      UNION ALL
      SELECT 22
      UNION ALL
      SELECT 23
      UNION ALL
      SELECT 24
      UNION ALL
      SELECT 25
      UNION ALL
      SELECT 26
      UNION ALL
      SELECT 27
      UNION ALL
      SELECT 28
      UNION ALL
      SELECT 29
      UNION ALL
      SELECT 30
      UNION ALL
      SELECT 31
      UNION ALL
      SELECT 32
      UNION ALL
      SELECT 33
      UNION ALL
      SELECT 34
      UNION ALL
      SELECT 35
      UNION ALL
      SELECT 36
      UNION ALL
      SELECT 37
      UNION ALL
      SELECT 38
      UNION ALL
      SELECT 39
      UNION ALL
      SELECT 40
      UNION ALL
      SELECT 41
      UNION ALL
      SELECT 42
      UNION ALL
      SELECT 43
      UNION ALL
      SELECT 44
      UNION ALL
      SELECT 45
      UNION ALL
      SELECT 46
      UNION ALL
      SELECT 47
      UNION ALL
      SELECT 48
      UNION ALL
      SELECT 49) s
         CROSS JOIN (SELECT 0 o
                     UNION ALL
                     SELECT 1
                     UNION ALL
                     SELECT 2
                     UNION ALL
                     SELECT 3
                     UNION ALL
                     SELECT 4
                     UNION ALL
                     SELECT 5
                     UNION ALL
                     SELECT 6
                     UNION ALL
                     SELECT 7
                     UNION ALL
                     SELECT 8
                     UNION ALL
                     SELECT 9
                     UNION ALL
                     SELECT 10
                     UNION ALL
                     SELECT 11
                     UNION ALL
                     SELECT 12
                     UNION ALL
                     SELECT 13
                     UNION ALL
                     SELECT 14) o;

-- 9. Update class_size for each course based on actual enrollments
UPDATE course c
SET class_size = (SELECT COUNT(*) FROM student_course sc WHERE sc.course_id = c.id);

COMMIT;
SET FOREIGN_KEY_CHECKS = 1;


-- Distribution note:
-- Resulting class_size values will range roughly 35-40 providing a balanced load across the 20 courses.

