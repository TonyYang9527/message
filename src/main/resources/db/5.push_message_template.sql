
-- --------------------------------------------------------
-- Table :                                  Push  Message Template
-- --------------------------------------------------------
USE `message`;
-- Base Template
INSERT INTO push_message_template (id, name, title, content, `type`, priority, state, addition, created_time, created_by, updated_time, updated_by, deleted_time, deleted_by, deleted) VALUES(3000, 'base', NULL, '{{content}}', 0, 1, 0, NULL, '2018-10-05 04:27:15.000', 'admin', NULL, NULL, NULL, NULL, 0);
-- crew.share.post Template
INSERT INTO push_message_template (id, name, title, content, `type`, priority, state, addition, created_time, created_by, updated_time, updated_by, deleted_time, deleted_by, deleted) VALUES(3001, 'crew.share.post', NULL, '{{crewName}} shared your post {{#opt}}:{{content}}{{/opt}}.', 0, 1, 0, NULL, '2018-10-05 04:27:15.000', 'admin', NULL, NULL, NULL, NULL, 0);
-- crew.follow Template
INSERT INTO push_message_template (id, name, title, content, `type`, priority, state, addition, created_time, created_by, updated_time, updated_by, deleted_time, deleted_by, deleted) VALUES(3002, 'crew.follow', NULL, '{{crewName}} has followed you.', 0, 1, 0, NULL, '2018-10-05 04:27:15.000', 'admin', NULL, NULL, NULL, NULL, 0);
-- Crew CV Shortlist Template
INSERT INTO push_message_template (id, name, title, content, `type`, priority, state, addition, created_time, created_by, updated_time, updated_by, deleted_time, deleted_by, deleted) VALUES(3003, 'crew.shortlist.cv', NULL, '{{companyName}}  has shorlisted your CV.', 0, 1, 0, NULL, '2018-10-05 04:27:15.000', 'admin', NULL, NULL, NULL, NULL, 0);
-- Crew CV View Template
INSERT INTO push_message_template (id, name, title, content, `type`, priority, state, addition, created_time, created_by, updated_time, updated_by, deleted_time, deleted_by, deleted) VALUES(3004, 'crew.view.cv', NULL, '{{companyName}} has viewed your CV.', 0, 1, 0, NULL, '2018-10-05 04:27:15.000', 'admin', NULL, NULL, NULL, NULL, 0);
