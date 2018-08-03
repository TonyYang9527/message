
-- --------------------------------------------------------
-- Host:                                   192.168.48.110
-- Server version:               10.1.32-MariaDB-1~xenial - mariadb.org binary distribution
-- Server OS:                         debian-linux-gnu
-- HeidiSQL Version:          9.4.0.5125
-- --------------------------------------------------------

 -- Dumping database structure for message
 DROP   DATABASE  IF EXISTS  `message`;
 CREATE DATABASE  IF NOT EXISTS  `message`  ;
 USE `message`;

 /************************Email****************************/
  -- Dumping structure for table  email_message_template
 /************************Email****************************/
DROP TABLE IF EXISTS  `email_message_template` ;
CREATE TABLE IF NOT EXISTS  `email_message_template` (
  `id`            bigint(18)     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`          varchar(50)    UNIQUE COMMENT 'template name',
  `title`         varchar(100)   DEFAULT NULL COMMENT 'title',
  `content`       blob           NOT NULL COMMENT 'content',
  `type`          smallint(6)    NOT NULL DEFAULT '0'  COMMENT 'type',
  `state`         tinyint(4)     NOT NULL DEFAULT '0'   COMMENT 'state  (0:enable ,1:disable)' ,
  `priority`      tinyint(4)     NOT NULL DEFAULT '99'   COMMENT 'priority: 1-99,1:Top priority',
  `from_address`  varchar(50)    DEFAULT NULL COMMENT 'from address',
  `sender_name`   varchar(100)   DEFAULT NULL COMMENT 'sender  name',
  `created_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by`    varchar(36)    NOT NULL,
  `updated_time`  datetime       DEFAULT NULL ,
  `updated_by`    varchar(36)    DEFAULT NULL ,
  `deleted_time`  datetime       DEFAULT NULL ,
  `deleted_by`    varchar(36)    DEFAULT NULL ,
  `deleted`       tinyint(4)     NOT NULL  DEFAULT '0' COMMENT 'Deleted(0:enable,1:disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************Email****************************/
  -- Dumping structure for table  email_message_property
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message_property`;
CREATE TABLE IF NOT EXISTS `email_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_id`   bigint(18)    NOT NULL COMMENT 'email message id ',
  `prop_key`           varchar(50)   DEFAULT NULL COMMENT ' prop  key',
  `prop_value`         blob          NOT NULL COMMENT     ' prop  value',
  `created_time`       datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


 /************************Email****************************/
  -- Dumping structure for table  email_message_attachment
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message_attachment`;
CREATE TABLE IF NOT EXISTS `email_message_attachment` (
  `id`                  bigint(18)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_id`    bigint(18)   NOT NULL COMMENT 'email  message id ',
  `name`                varchar(50)  NOT NULL COMMENT 'attachment name',
  `path`                blob         DEFAULT NULL COMMENT ' attachment path ',
  `created_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


 /************************Email****************************/
  -- Dumping structure for table  email_message
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message`;
CREATE TABLE IF NOT EXISTS `email_message` (
  `id`                         bigint(18)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email_message_template_id`  bigint(18)      NOT NULL COMMENT 'email  template id ',
  `to_address`                 blob            NOT NULL COMMENT '  to  email address , multiple  address  with  ''|''separation',
  `cc_address`                 blob            DEFAULT NULL COMMENT ' cc  email address ,multiple  address  with  ''|''separation',
  `bcc_address`                blob            DEFAULT NULL COMMENT 'bcc  email address,multiple  address  with  ''|''separation',
  `priority`                   tinyint(4)      NOT NULL  DEFAULT '99' COMMENT 'priority: 1-99,1:Top priority',
  `state`                      tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'state ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate`                  tinyint(4)      NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`              datetime        NOT NULL COMMENT 'Expected delivery time',
  `expired_time`               datetime        NOT NULL COMMENT 'expired time',
  `sent_time`                  datetime        DEFAULT NULL COMMENT 'sent time',
  `created_time`               datetime        NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  `created_by`                 varchar(36)     NOT NULL,
  `updated_time`               datetime        DEFAULT NULL,
  `updated_by`                 varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************SMS****************************/
  -- Dumping structure for table sms_message_template
/************************SMS****************************/
DROP TABLE IF EXISTS  `sms_message_template` ;
CREATE TABLE IF NOT EXISTS  `sms_message_template` (
  `id`              bigint(18)       PRIMARY KEY AUTO_INCREMENT,
  `name`            varchar(50)      UNIQUE  COMMENT 'sms name message',
  `content`         blob             NOT NULL  COMMENT 'sms message content ',
  `priority`        tinyint(4)       NOT NULL DEFAULT '99'   COMMENT 'priority:1-99,1:Top priority',
  `type`            smallint(6)      NOT NULL   DEFAULT '0' COMMENT 'sms name message type ',
  `state`           tinyint(4)       NOT NULL  DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `created_time`    datetime         NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  `created_by`      varchar(36)      NOT NULL,
  `updated_time`    datetime         DEFAULT NULL,
  `updated_by`      varchar(36)      DEFAULT NULL,
  `deleted_time`    datetime         DEFAULT NULL,
  `deleted_by`      varchar(36)      DEFAULT NULL,
  `deleted`         tinyint(4)       NOT NULL DEFAULT '0' COMMENT 'Deleted ( 0:enable,1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

 /************************SMS****************************/
  -- Dumping structure for table  sms_message_property
 /************************SMS****************************/
DROP TABLE IF EXISTS ` sms_message_property`;
CREATE TABLE IF NOT EXISTS  `sms_message_property` (
  `id`              bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `sms_message_id`  bigint(18)    NOT NULL COMMENT 'sms_message_id',
  `prop_key`        varchar(50)   DEFAULT NULL,
  `prop_value`      blob          DEFAULT NULL,
  `created_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


 /************************SMS****************************/
  -- Dumping structure for table sms_message
 /************************SMS****************************/
DROP TABLE IF EXISTS  `sms_message`;
CREATE TABLE IF NOT EXISTS  `sms_message` (
 ` id`                        bigint(18)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `sms_message_template_id`   bigint(18)      NOT NULL COMMENT 'sms_message_template_id',
  `mobiles`                   blob            NOT NULL COMMENT 'mobiles',
  `priority`                  tinyint(4)      NOT NULL  DEFAULT '99'  COMMENT 'priority:1-99,1:Top priority',
  `state`                     tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate`                 tinyint(4)      NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`             datetime        DEFAULT NULL COMMENT 'Expected delivery time',
  `expired_time`              datetime        NOT NULL COMMENT 'Expired Time',
  `sent_time`                 datetime        DEFAULT NULL COMMENT 'Sent Time',
  `sent_result`               varchar(50)     DEFAULT NULL,
  `created_time`              datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)     NOT NULL,
  `updated_time`              datetime        DEFAULT NULL,
  `updated_by`                varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


  /************************PUSH****************************/
   -- Dumping structure for table  push_message_template
 /************************PUSH****************************/
DROP TABLE IF EXISTS  `push_message_template`;
CREATE TABLE IF NOT EXISTS  `push_message_template` (
  `id`             bigint(18)     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`           varchar(50)    UNIQUE COMMENT 'template name',
  `title`          varchar(50)    DEFAULT NULL COMMENT 'template  title ',
  `content`        blob           NOT NULL COMMENT 'template  title  content',
  `type`           smallint(6)    NOT NULL DEFAULT '0'  COMMENT 'type',
  `priority`       tinyint(4)     NOT NULL DEFAULT '99'  COMMENT 'priority:1-99,1:Top priority',
  `state`          tinyint(4)     NOT NULL  DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `addition`       blob           DEFAULT NULL COMMENT 'addition',
  `created_time`   datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`     varchar(36)    NOT NULL,
  `updated_time`   datetime       DEFAULT NULL,
  `updated_by`     varchar(36)    DEFAULT NULL,
  `deleted_time`   datetime       DEFAULT NULL,
  `deleted_by`     varchar(36)    DEFAULT NULL,
  `deleted`        tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'Deleted( 0:enable,1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************PUSH****************************/
   -- Dumping structure for table  push_message_property
 /************************PUSH****************************/
DROP TABLE IF EXISTS `push_message_property`;
CREATE TABLE IF NOT EXISTS  `push_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `push_message_id`    bigint(18)    NOT NULL COMMENT 'push_message_template id',
  `prop_key`           varchar(50)   NOT NULL ,
  `prop_value`         blob          DEFAULT NULL ,
  `created_time`       datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************PUSH****************************/
  -- Dumping structure for table  push_message
 /************************PUSH****************************/
DROP TABLE IF EXISTS `push_message`;
CREATE TABLE IF NOT EXISTS `push_message` (
  `id`                          bigint(18)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `push_message_template_id`    bigint(18)      NOT NULL COMMENT 'push_message_template id',
  `receiver_id`                 varchar(50)     DEFAULT NULL COMMENT 'receiver user id',
  `device_id`                   varchar(120)    NOT NULL COMMENT 'DeviceId',
  `device_type`                 tinyint(4)      NOT NULL COMMENT 'Device Type ,1:IOS,2:Android',
  `priority`                    tinyint(4)      DEFAULT '99' COMMENT 'Priority,1-99,1:Top priority',
  `state`                       tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate`                   tinyint(4)      NOT NULL COMMENT 'Instatnt Message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`               datetime        NOT NULL COMMENT 'Expected delivery time',
  `expire_time`                 datetime        NOT NULL COMMENT 'Expired Time',
  `sent_time`                   datetime        DEFAULT NULL COMMENT 'Sent Time',
  `sent_result`                 varchar(50)     DEFAULT NULL COMMENT 'Sent Result',
  `created_time`                datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                  varchar(36)     NOT NULL,
  `updated_time`                datetime        DEFAULT NULL,
  `updated_by`                  varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************SITE****************************/
  -- Dumping structure for table  site_message_template
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message_template`;
CREATE TABLE IF NOT EXISTS   `site_message_template` (
  `id`            bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`          varchar(50)   UNIQUE COMMENT 'Template Name ',
  `title`         varchar(50)   DEFAULT NULL COMMENT 'Template Title',
  `content`       blob          DEFAULT NULL COMMENT 'Template Content',
  `addition`      blob          DEFAULT NULL COMMENT 'Template Addition ',
  `type`          smallint(6)   NOT NULL  DEFAULT '0' COMMENT 'Template  Type',
  `state`         tinyint(4)    NOT NULL  DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `priority`      tinyint(4)    NOT NULL   DEFAULT '99' COMMENT 'Priority, 1-99,1:Top priority',
  `sender`        varchar(50)   NOT NULL COMMENT ' Sender',
  `created_time`  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by`    varchar(36)   NOT NULL ,
  `updated_time`  datetime      DEFAULT NULL,
  `updated_by`    varchar(36)   DEFAULT NULL,
  `deleted_time`  datetime      DEFAULT NULL,
  `deleted_by`    varchar(36)   DEFAULT NULL,
  `deleted`       tinyint(4)    NOT NULL  DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************SITE****************************/
   -- Dumping structure for table  site_message_content
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message_content`;
CREATE TABLE IF NOT EXISTS  `site_message_content` (
  `id`                          bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_template_id`    bigint(18)    DEFAULT NULL COMMENT 'site_message_template  id',
  `title`                       varchar(50)   DEFAULT NULL COMMENT 'Template Title',
  `content`                     blob          DEFAULT NULL COMMENT 'Template  Content',
  `addition`                    blob          DEFAULT NULL COMMENT 'Template  Addition',
  `type`                        smallint(6)   DEFAULT NULL COMMENT 'Template Type',
  `created_time`                datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                  varchar(36)   DEFAULT NULL ,
  `updated_time`                datetime      DEFAULT NULL ,
  `updated_by`                  varchar(36)   DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


  /************************SITE****************************/
   -- Dumping structure for table  site_message_property
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message_property`;
CREATE TABLE IF NOT EXISTS  `site_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_id`    bigint(18)    NOT NULL COMMENT 'site_message  id',
  `prop_key`           varchar(50)   NOT NULL COMMENT 'prop key',
  `prop_value`         blob          DEFAULT NULL COMMENT 'prop value',
  `created_time`       datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************SITE****************************/
   -- Dumping structure for table  site_message
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message`;
CREATE TABLE IF NOT EXISTS  `site_message` (
  `id`                        bigint(18)    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `site_message_content_id`   bigint(18)    DEFAULT NULL COMMENT 'site_message_content  id',
  `receiver_id`               varchar(50)   DEFAULT NULL COMMENT 'receiver user_Id',
  `sender`                    varchar(50)   DEFAULT NULL COMMENT 'sender  ',
  `type`                      smallint(6)   DEFAULT '0'   COMMENT 'Site Message Type',
  `priority`                  tinyint(4)    DEFAULT '99'  COMMENT 'Priority,1-99,1:Top priority',
  `state`                     tinyint(4)    NOT NULL COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,99:Send Failed)',
  `immediate`                 tinyint(4)    DEFAULT NULL COMMENT 'Instatnt Message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`             datetime      DEFAULT NULL COMMENT 'Expected delivery time ',
  `expired_time`              datetime      DEFAULT NULL COMMENT 'Expired Time ',
  `sent_time`                 datetime      DEFAULT NULL COMMENT 'Sent Time',
  `read_time`                 datetime      DEFAULT NULL COMMENT 'Read Time',
  `deleted_time`              datetime      DEFAULT NULL ,
  `deleted`                   tinyint(4)    NOT NULL DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)',
  `created_time`              datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)   NOT NULL,
  `updated_time`              datetime      DEFAULT NULL,
  `updated_by`                varchar(36)   DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

  /************************send_request****************************/
    -- Dumping structure for table  send_request
  /************************Email****************************/ 
DROP TABLE IF EXISTS `send_request`;
CREATE TABLE IF NOT EXISTS  `send_request` (
   `id`                          bigint(18)     NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `user_id`                     bigint(18)     DEFAULT NULL COMMENT 'user_id',
   `email_message_template_id`   bigint(18)     DEFAULT NULL COMMENT 'email_message_template    id',
   `to_addresses`                blob           DEFAULT NULL COMMENT 'to_addresses',
   `cc_addresses`                blob           DEFAULT NULL COMMENT 'cc_addresses',
   `bcc_addresses`               blob           DEFAULT NULL COMMENT 'bcc_addresses',
   `schedule_time`               datetime       DEFAULT NULL COMMENT 'schedule time',
   `expired_time`                datetime       DEFAULT NULL COMMENT 'Expired Time ',
   `properties`                  blob           DEFAULT NULL COMMENT 'properties',
   `attachments`                 blob           DEFAULT NULL COMMENT 'attachments',
   `receiver`                    blob           DEFAULT NULL COMMENT 'receiver',
   `status`                      tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'status  (0: Created ,1 Succeed ,20:Failed)',
   `created_time`                datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ,
   `created_by`                  varchar(36)    NOT NULL,
   `updated_time`                datetime       DEFAULT NULL,
   `updated_by`                  varchar(36)    DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
