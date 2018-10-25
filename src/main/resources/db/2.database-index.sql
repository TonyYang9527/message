USE `message`;
-- --------------------------------------------------------
-- Host:                         192.168.48.110
-- Server version:               10.1.32-MariaDB-1~xenial - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------
-- ----------------------------
-- Table structure for email_message_property
-- ----------------------------

CREATE INDEX  index_email_message_id ON `email_message_property`(email_message_id);

-- ----------------------------
-- Table structure for email_message_attachment
-- ----------------------------
CREATE INDEX  index_email_message_id ON `email_message_attachment`(email_message_id);

-- ----------------------------
-- Table structure for email_message
-- ----------------------------
CREATE INDEX  index_email_message_template_id ON `email_message`(email_message_template_id);
-- ----------------------------
-- Table structure for sms_message_property
-- ----------------------------

CREATE INDEX  index_sms_message_id ON `sms_message_property`(sms_message_id);

-- ----------------------------
-- Table structure for sms_message
-- ----------------------------

CREATE INDEX  index_sms_message_template_id ON `sms_message`(sms_message_template_id);
-- ----------------------------
-- Table structure for push_message_property
-- ----------------------------

CREATE INDEX  index_push_message_id ON `push_message_property`(push_message_id);

-- ----------------------------
-- Table structure for push_message
-- ----------------------------


CREATE INDEX  index_push_message_template_id ON `push_message`(push_message_template_id);
-- ----------------------------
-- Table structure for site_message_content
-- ----------------------------

CREATE INDEX  index_site_message_template_id ON `site_message_content`(site_message_template_id);
-- ----------------------------
-- Table structure for site_message_property
-- ----------------------------

CREATE INDEX  index_site_message_id ON `site_message_property`(site_message_id);

-- ----------------------------
-- Table structure for site_message
-- ----------------------------

CREATE INDEX  index_site_message_content_id ON `site_message`(site_message_content_id);

-- ----------------------------
-- Table structure for user_device
-- ----------------------------

CREATE UNIQUE INDEX index_user_device_regist_user_id ON `user_device`(user_id,registration_id);

-- ----------------------------
-- Table structure for user_channel_setting
-- ----------------------------

CREATE INDEX  index_user_channel_user_id ON `user_channel_setting`(user_id);
