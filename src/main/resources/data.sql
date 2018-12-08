-- Insert data into Users
INSERT INTO `users` (`emp_id`, `first_nm`, `last_nm`, `user_project_id`, `user_task_id`) VALUES (5001,'Jose','Joseph',NULL,NULL);
INSERT INTO `users` (`emp_id`, `first_nm`, `last_nm`, `user_project_id`, `user_task_id`) VALUES (5002,'Tommy','Yadav',NULL,NULL);
INSERT INTO `users` (`emp_id`, `first_nm`, `last_nm`, `user_project_id`, `user_task_id`) VALUES (5003,'Xavier','Chopra',NULL,NULL);
-- Insert data into Project
INSERT INTO `project` (`end_dt`, `priority`, `project`, `start_dt`, `manager_id`) VALUES ('2018-12-31 19:00:00.000000',30,'Mission-Mars','2018-11-30 19:00:00.000000',1);
INSERT INTO `project` (`end_dt`, `priority`, `project`, `start_dt`, `manager_id`) VALUES ('2018-12-10 19:00:00.000000',15,'Mission-Moon','2018-11-30 19:00:00.000000',1);
INSERT INTO `project` (`end_dt`, `priority`, `project`, `start_dt`, `manager_id`) VALUES ('2018-12-15 19:00:00.000000',25,'Mission-Uranus','2018-11-30 19:00:00.000000',2);
-- Insert data into Task
INSERT INTO `task` (`end_dt`, `priority`, `start_dt`, `status`, `task`, `parent_task_id`, `parent_project_id`, `assoc_user_id`) VALUES ('2018-12-05 19:00:00.000000',9,'2018-12-01 19:00:00.000000',NULL,'Analysis',NULL,1,1);
INSERT INTO `task` (`end_dt`, `priority`, `start_dt`, `status`, `task`, `parent_task_id`, `parent_project_id`, `assoc_user_id`) VALUES ('2018-12-15 19:00:00.000000',3,'2018-12-06 19:00:00.000000',NULL,'Development',1,1,1);
INSERT INTO `task` (`end_dt`, `priority`, `start_dt`, `status`, `task`, `parent_task_id`, `parent_project_id`, `assoc_user_id`) VALUES ('2018-12-25 19:00:00.000000',10,'2018-12-16 19:00:00.000000',NULL,'Deploy',2,1,1);
INSERT INTO `task` (`end_dt`, `priority`, `start_dt`, `status`, `task`, `parent_task_id`, `parent_project_id`, `assoc_user_id`) VALUES ('2018-12-05 19:00:00.000000',5,'2018-12-01 19:00:00.000000',NULL,'Analysis',NULL,2,2);
INSERT INTO `task` (`end_dt`, `priority`, `start_dt`, `status`, `task`, `parent_task_id`, `parent_project_id`, `assoc_user_id`) VALUES ('2018-12-05 19:00:00.000000',20,'2018-12-01 19:00:00.000000',NULL,'Analysis',NULL,3,3);

commit;