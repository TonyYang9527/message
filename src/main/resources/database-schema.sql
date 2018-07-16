
-- --------------------------------------------------------
-- Host:                                   192.168.48.110
-- Server version:               10.1.32-MariaDB-1~xenial - mariadb.org binary distribution
-- Server OS:                         debian-linux-gnu
-- HeidiSQL Version:          9.4.0.5125
-- --------------------------------------------------------

-- Dumping database structure for message
DROP DATABASE IF EXISTS `iMessageCenter`;
CREATE DATABASE IF NOT EXISTS `iMessageCenter`  ;
USE `iMessageCenter`;

-- Dumping structure for table message.bmo_event
DROP TABLE IF EXISTS `bmo_event`;
CREATE TABLE IF NOT EXISTS `bmo_event` (
  `id` bigint(18) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `data` blob,
  `status` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `retry` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************Email****************************/
-- Dumping structure for table im.email_message_template
DROP TABLE IF EXISTS  `email_message_template` ;
CREATE TABLE IF NOT EXISTS  `email_message_template` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT 'template name',
  `title` varchar(100) DEFAULT NULL COMMENT 'title',
  `content` varchar(5000) NOT NULL COMMENT 'content',
  `type` smallint(6) NOT NULL COMMENT 'type',
   `state` tinyint(4) NOT NULL COMMENT 'state  (0:enable ,1:disable)' ,
  `priority` tinyint(4) NOT NULL COMMENT 'priority£º1-99,1:Top priority',
  `from_address` varchar(50) DEFAULT NULL COMMENT 'from address',
  `sender_name` varchar(100) DEFAULT NULL COMMENT 'sender  name',
  `created_time` datetime NOT NULL ,
  `created_by` varchar(36) NOT NULL ,
  `updated_time` datetime DEFAULT NULL ,
  `updated_by` varchar(36) DEFAULT NULL ,
  `deleted_time` datetime DEFAULT NULL ,
  `deleted_by` varchar(36) DEFAULT NULL ,
  `deleted` tinyint(4) NOT NULL  DEFAULT '0'  COMMENT 'Deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `email_message_property`;
CREATE TABLE IF NOT EXISTS `email_message_property` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_id` bigint(20) NOT NULL COMMENT 'email message id ',
  `prop_key` varchar(50) DEFAULT NULL COMMENT ' prop  key',
  `prop_value` varchar(500) NOT NULL COMMENT 'prop  key',
  `created_time` datetime NOT NULL COMMENT 'created time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `email_message_attachment`;
CREATE TABLE IF NOT EXISTS `email_message_attachment` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_id` bigint(20) NOT NULL COMMENT 'email  message id ',
  `name` varchar(50) NOT NULL COMMENT 'attachment name',
  `path` varchar(100) DEFAULT NULL COMMENT ' attachment path ',
  `created_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `email_message`;
CREATE TABLE IF NOT EXISTS `email_message` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_template_id` bigint(20) NOT NULL COMMENT 'email  template id ',
  `to_address` varchar(3000) NOT NULL COMMENT '  to  email address , multiple  address  with  ''|''separation',
  `cc_address` varchar(3000) DEFAULT NULL COMMENT ' cc  email address ,multiple  address  with  ''|''separation',
  `bcc_address` varchar(3000) DEFAULT NULL COMMENT 'bcc  email address,multiple  address  with  ''|''separation',
  `priority` tinyint(4) NOT NULL COMMENT 'priority£º1-99,1:Top priority',
  `state` tinyint(4) NOT NULL COMMENT 'state ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate` tinyint(4) NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time` datetime NOT NULL COMMENT 'Expected delivery time',
  `expired_time` datetime NOT NULL COMMENT 'expired time',
  `sent_time` datetime DEFAULT NULL COMMENT 'sent time',
  `created_time` datetime NOT NULL,
  `created_by` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


 /************************SMS****************************/
-- Dumping structure for table im.sms_message_template
DROP TABLE IF EXISTS  `sms_message_template` ;
CREATE TABLE IF NOT EXISTS  `sms_message_template` (
  `id` bigint(18) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) NOT NULL  COMMENT 'sms name message',
  `content` varchar(500) NOT NULL  COMMENT 'sms message content ',
  `priority` tinyint(4) NOT NULL  COMMENT 'priority£º1-99,1:Top priority',
  `type` smallint(6) NOT NULL  COMMENT 'sms name message type ',
   `state` tinyint(4) NOT NULL COMMENT 'state (0:enable ,1:disable)' ,
  `created_time`  datetime NOT NULL ,
  `created_by`  varchar(36) NOT NULL,
  `updated_time`  datetime,
  `updated_by`  varchar(36),
  `deleted_time` datetime,
  `deleted_by` varchar(36),
  `deleted`  tinyint(4) NOT NULL DEFAULT '0'  COMMENT 'Deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS ` sms_message_property`;
CREATE TABLE IF NOT EXISTS  `sms_message_property` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `sms_message_id`  bigint(20) NOT NULL COMMENT 'sms_message_id',
  `prop_key`  varchar(50) DEFAULT NULL,
  `prop_value`  varchar(500) DEFAULT NULL,
  `created_time`  datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS  `sms_message`;
CREATE TABLE IF NOT EXISTS  `sms_message` (
 ` id`  bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `sms_message_template_id`  bigint(20) NOT NULL COMMENT 'sms_message_template_id',
  `mobiles`  varchar(3000) NOT NULL COMMENT 'mobiles',
  `priority`  tinyint(4) NOT NULL COMMENT 'priority£º1-99,1:Top priority',
  `state` tinyint(4) NOT NULL COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate`  tinyint(4) NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`  datetime DEFAULT NULL COMMENT 'Expected delivery time',
  `expired_time`  datetime NOT NULL COMMENT 'Expired Time',
  `sent_time`  datetime DEFAULT NULL COMMENT 'Sent Time',
  `created_time`  datetime NOT NULL,
  `created_by`  varchar(255) NOT NULL,
  `sent_result`  varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************PUSH****************************/
DROP TABLE IF EXISTS  `push_message_template`;
CREATE TABLE IF NOT EXISTS  `push_message_template` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT 'template name',
  `title` varchar(50) DEFAULT NULL COMMENT 'template  title ',
  `content` varchar(500) NOT NULL COMMENT 'template  title  content',
   `type` smallint(6) NOT NULL COMMENT 'type',
  `priority` tinyint(4) NOT NULL COMMENT 'priority£º1-99,1:Top priority',
   `state` tinyint(4) NOT NULL COMMENT 'state (0:enable ,1:disable)' ,
  `addition` varchar(500) DEFAULT NULL COMMENT 'addition',
  `created_time` datetime NOT NULL  ,
  `created_by` varchar(36) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `deleted_time` datetime DEFAULT NULL,
  `deleted_by` varchar(36) DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '0'  COMMENT 'Deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `push_message_property`;
CREATE TABLE IF NOT EXISTS  `push_message_property` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `push_message_id` bigint(20) NOT NULL COMMENT 'push_message_template id',
  `prop_key` varchar(50) NOT NULL ,
  `prop_value` varchar(500) DEFAULT NULL ,
  `created_time` datetime NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `push_message`;
CREATE TABLE IF NOT EXISTS `push_message` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `push_message_template_id` bigint(20) NOT NULL COMMENT 'push_message_template id',
  `receiver_id` varchar(50) DEFAULT NULL COMMENT 'receiver user id',
  `device_id` varchar(120) NOT NULL COMMENT 'DeviceId',
  `device_type` tinyint(4) NOT NULL COMMENT 'Device Type¡£1:IOS£¬2:Android',
  `priority` tinyint(4) NOT NULL COMMENT 'Priority£º1-99,1:Top priority',
  `state` tinyint(4) NOT NULL COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate` tinyint(4) NOT NULL COMMENT 'Instatnt Message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time` datetime NOT NULL COMMENT 'Expected delivery time',
  `expire_time` datetime NOT NULL COMMENT 'Expired Time',
  `sent_time` datetime DEFAULT NULL COMMENT 'Sent Time',
  `sent_result` varchar(50) DEFAULT NULL COMMENT 'Sent Result',
  `created_time` datetime NOT NULL ,
  `created_by` varchar(36) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************SITE****************************/
DROP TABLE IF EXISTS `site_message_template`;
CREATE TABLE IF NOT EXISTS   `site_message_template` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT 'Template Name ',
  `title` varchar(50) DEFAULT NULL COMMENT 'Template Title',
  `content` varchar(500) DEFAULT NULL COMMENT 'Template Content',
  `addition` varchar(400) DEFAULT NULL COMMENT 'Template Addition ',
  `type` smallint(6) NOT NULL COMMENT 'Template  Type',
  `priority` tinyint(4) NOT NULL COMMENT 'Priority£º1-99,1:Top priority',
  `sender` varchar(50) NOT NULL COMMENT ' Sender',
  `created_time` datetime NOT NULL ,
  `created_by` varchar(36) NOT NULL ,
  `updated_time` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `deleted_time` datetime DEFAULT NULL,
  `deleted_by` varchar(36) DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL  DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `site_message_content`;
CREATE TABLE IF NOT EXISTS  `site_message_content` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_template_id` bigint(20) DEFAULT NULL COMMENT 'site_message_template  id',
  `title` varchar(50) DEFAULT NULL COMMENT 'Template Title',
  `content` varchar(500) DEFAULT NULL COMMENT 'Template  Content',
  `addition` varchar(400) DEFAULT NULL COMMENT 'Template  Addition',
  `type` smallint(6) DEFAULT NULL COMMENT 'Template Type',
  `created_time` datetime DEFAULT NULL  ,
  `created_by` varchar(36) DEFAULT NULL ,
  `updated_time` datetime DEFAULT NULL ,
  `updated_by` varchar(36) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


DROP TABLE IF EXISTS `site_message_property`;
CREATE TABLE IF NOT EXISTS  `site_message_property` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_id` bigint(20) NOT NULL COMMENT 'site_message  id',
  `prop_key` varchar(50) NOT NULL,
  `prop_value` varchar(500) DEFAULT NULL,
  `created_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


DROP TABLE IF EXISTS `site_message`;
CREATE TABLE IF NOT EXISTS  `site_message` (
  `id` bigint(18) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_content_id` bigint(20) DEFAULT NULL COMMENT 'site_message_content  id',
  `receiver_id` varchar(50) DEFAULT NULL COMMENT 'receiver user_Id',
  `sender` varchar(50) DEFAULT NULL COMMENT 'sender  ',
  `type` smallint(6) DEFAULT NULL COMMENT 'Site Message Type',
  `priority` tinyint(4) DEFAULT NULL COMMENT 'Priority£º1-99,1:Top priority',
  `state` tinyint(4) NOT NULL COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate` tinyint(4) DEFAULT NULL COMMENT 'Instatnt Message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time` datetime DEFAULT NULL COMMENT 'Expected delivery time ',
  `expired_time` datetime DEFAULT NULL COMMENT 'Expired Time ',
  `sent_time` datetime DEFAULT NULL COMMENT 'Sent Time',
  `read_time` datetime DEFAULT NULL COMMENT 'Read Time',
  `deleted_time` datetime DEFAULT NULL ,
  `deleted` tinyint(4) NOT NULL DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)',
  `created_time` datetime NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


