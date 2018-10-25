
-- --------------------------------------------------------
-- Host:                                   192.168.48.110
-- Server version:               10.1.32-MariaDB-1~xenial - mariadb.org binary distribution
-- Server OS:                         debian-linux-gnu
-- HeidiSQL Version:          9.4.0.5125
-- --------------------------------------------------------

 -- Dumping database structure for message
 DROP   DATABASE  IF EXISTS  `message`;
 CREATE DATABASE message CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 USE `message`;

  /************************Email****************************/
  -- Dumping structure for table  bmo_event
 /************************Email****************************/
 
DROP TABLE IF EXISTS  `bmo_event` ;
CREATE TABLE IF NOT EXISTS  `bmo_event` (
  `id`        bigint(18)      NOT NULL PRIMARY KEY,
  `name`      varchar(64)     DEFAULT NULL,
  `data`      text,
  `status`    varchar(16)     DEFAULT NULL,
  `retry`     int(11)         DEFAULT NULL,
  `created`   timestamp       NULL DEFAULT NULL,
  `updated`   timestamp       NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /************************Email****************************/
  -- Dumping structure for table  email_message_template
 /************************Email****************************/
DROP TABLE IF EXISTS  `email_message_template` ;
CREATE TABLE IF NOT EXISTS  `email_message_template` (
  `id`            bigint(18)     NOT NULL PRIMARY KEY ,
  `name`          varchar(50)    UNIQUE COMMENT 'template name',
  `title`         varchar(100)   DEFAULT NULL COMMENT 'title',
  `content`       text           NOT NULL COMMENT 'content',
  `type`          smallint(6)    NOT NULL DEFAULT '0'  COMMENT 'type',
  `state`         tinyint(4)     NOT NULL DEFAULT '0'   COMMENT 'state  (0:enable ,1:disable)' ,
  `priority`      tinyint(4)     NOT NULL DEFAULT '99'   COMMENT 'priority: 1-99,1:Top priority',
  `from_address`  varchar(50)    DEFAULT NULL COMMENT 'from address',
  `sender_name`   varchar(100)   DEFAULT NULL COMMENT 'sender  name',
  `created_time`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by`    varchar(36)    NOT NULL,
  `updated_time`  timestamp      NULL ,
  `updated_by`    varchar(36)    DEFAULT NULL ,
  `deleted_time`  timestamp      NULL ,
  `deleted_by`    varchar(36)    DEFAULT NULL ,
  `deleted`       tinyint(4)     NOT NULL  DEFAULT '0' COMMENT 'Deleted(0:enable,1:disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 /************************Email****************************/
  -- Dumping structure for table  email_message_property
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message_property`;
CREATE TABLE IF NOT EXISTS `email_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY ,
  `email_message_id`   bigint(18)    NOT NULL COMMENT 'email message id ',
  `prop_key`           varchar(50)   DEFAULT NULL COMMENT ' prop  key',
  `prop_value`         text          NOT NULL COMMENT     ' prop  value',
  `created_time`       timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /************************Email****************************/
  -- Dumping structure for table  email_message_attachment
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message_attachment`;
CREATE TABLE IF NOT EXISTS `email_message_attachment` (
  `id`                  bigint(18)   NOT NULL PRIMARY KEY ,
  `email_message_id`    bigint(18)   NOT NULL COMMENT 'email  message id ',
  `name`                varchar(50)  NOT NULL COMMENT 'attachment name',
  `path`                text         DEFAULT NULL COMMENT ' attachment path ',
  `created_time`        timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /************************Email****************************/
  -- Dumping structure for table  email_message
 /************************Email****************************/
DROP TABLE IF EXISTS `email_message`;
CREATE TABLE IF NOT EXISTS `email_message` (
  `id`                         bigint(18)      NOT NULL PRIMARY KEY ,
  `email_message_template_id`  bigint(18)      NOT NULL COMMENT 'email  template id ',
  `to_address`                 text            NOT NULL COMMENT '  to  email address , multiple  address  with  ''|''separation',
  `cc_address`                 text            DEFAULT NULL COMMENT ' cc  email address ,multiple  address  with  ''|''separation',
  `bcc_address`                text            DEFAULT NULL COMMENT 'bcc  email address,multiple  address  with  ''|''separation',
  `priority`                   tinyint(4)      NOT NULL  DEFAULT '99' COMMENT 'priority: 1-99,1:Top priority',
  `state`                      tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'state ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,40:Readed 99:Send Failed)',
  `immediate`                  tinyint(4)      NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`              timestamp       NULL COMMENT 'Expected delivery time',
  `expired_time`               timestamp       NULL COMMENT 'expired time',
  `sent_time`                  timestamp       NULL COMMENT 'sent time',
  `created_time`               timestamp       NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  `created_by`                 varchar(36)     NOT NULL,
  `updated_time`               timestamp       NULL,
  `updated_by`                 varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 /************************SMS****************************/
  -- Dumping structure for table sms_message_template
/************************SMS****************************/
DROP TABLE IF EXISTS  `sms_message_template` ;
CREATE TABLE IF NOT EXISTS  `sms_message_template` (
  `id`              bigint(18)       PRIMARY KEY ,
  `name`            varchar(50)      UNIQUE    COMMENT 'sms name message',
  `content`         text             NOT NULL  COMMENT 'sms message content ',
  `priority`        tinyint(4)       NOT NULL   DEFAULT '99'   COMMENT 'priority:1-99,1:Top priority',
  `type`            smallint(6)      NOT NULL   DEFAULT '0' COMMENT 'sms name message type ',
  `state`           tinyint(4)       NOT NULL   DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `created_time`    timestamp        NOT NULL   DEFAULT CURRENT_TIMESTAMP,
  `created_by`      varchar(36)      NOT NULL,
  `updated_time`    timestamp        NULL,
  `updated_by`      varchar(36)      DEFAULT NULL,
  `deleted_time`    timestamp        NULL,
  `deleted_by`      varchar(36)      DEFAULT NULL,
  `deleted`         tinyint(4)       NOT NULL DEFAULT '0' COMMENT 'Deleted ( 0:enable,1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 /************************SMS****************************/
  -- Dumping structure for table  sms_message_property
 /************************SMS****************************/
DROP TABLE IF EXISTS `sms_message_property`;
CREATE TABLE IF NOT EXISTS  `sms_message_property` (
  `id`              bigint(18)    NOT NULL PRIMARY KEY ,
  `sms_message_id`  bigint(18)    NOT NULL COMMENT 'sms_message_id',
  `prop_key`        varchar(50)   DEFAULT NULL,
  `prop_value`      text          DEFAULT NULL,
  `created_time`    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /************************SMS****************************/
  -- Dumping structure for table sms_message
 /************************SMS****************************/
DROP TABLE IF EXISTS  `sms_message`;
CREATE TABLE IF NOT EXISTS  `sms_message` (
 ` id`                        bigint(18)      NOT NULL PRIMARY KEY ,
  `sms_message_template_id`   bigint(18)      NOT NULL COMMENT 'sms_message_template_id',
  `mobiles`                   text            NOT NULL COMMENT 'mobiles',
  `priority`                  tinyint(4)      NOT NULL  DEFAULT '99'  COMMENT 'priority:1-99,1:Top priority',
  `state`                     tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,40:Readed ,99:Send Failed)',
  `immediate`                 tinyint(4)      NOT NULL COMMENT 'instatnt message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`             timestamp       NULL COMMENT 'Expected delivery time',
  `expired_time`              timestamp       NULL COMMENT 'Expired Time',
  `sent_time`                 timestamp       NULL COMMENT 'Sent Time',
  `sent_result`               varchar(50)     DEFAULT NULL,
  `created_time`              timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)     NOT NULL,
  `updated_time`              timestamp       NULL,
  `updated_by`                varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


  /************************PUSH****************************/
   -- Dumping structure for table  push_message_template
 /************************PUSH****************************/
DROP TABLE IF EXISTS  `push_message_template`;
CREATE TABLE IF NOT EXISTS  `push_message_template` (
  `id`             bigint(18)     NOT NULL PRIMARY KEY ,
  `name`           varchar(50)    UNIQUE COMMENT 'template name',
  `title`          varchar(50)    DEFAULT NULL COMMENT 'template  title ',
  `content`        text           NOT NULL COMMENT 'template  title  content',
  `type`           smallint(6)    NOT NULL DEFAULT '0'  COMMENT 'type',
  `priority`       tinyint(4)     NOT NULL DEFAULT '99'  COMMENT 'priority:1-99,1:Top priority',
  `state`          tinyint(4)     NOT NULL  DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `addition`       text           DEFAULT NULL COMMENT 'addition',
  `created_time`   timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`     varchar(36)    NOT NULL,
  `updated_time`   timestamp      NULL,
  `updated_by`     varchar(36)    DEFAULT NULL,
  `deleted_time`   timestamp      NULL,
  `deleted_by`     varchar(36)    DEFAULT NULL,
  `deleted`        tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'Deleted( 0:enable,1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  /************************PUSH****************************/
   -- Dumping structure for table  push_message_property
 /************************PUSH****************************/
DROP TABLE IF EXISTS `push_message_property`;
CREATE TABLE IF NOT EXISTS  `push_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY ,
  `push_message_id`    bigint(18)    NOT NULL COMMENT 'push_message_template id',
  `prop_key`           varchar(50)   DEFAULT NULL ,
  `prop_value`         text          DEFAULT NULL ,
  `created_time`       timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  /************************PUSH****************************/
  -- Dumping structure for table  push_message
 /************************PUSH****************************/
DROP TABLE IF EXISTS `push_message`;
CREATE TABLE IF NOT EXISTS `push_message` (
  `id`                          bigint(18)      NOT NULL PRIMARY KEY ,
  `push_message_template_id`    bigint(18)      NOT NULL         COMMENT 'push_message_template id',
  `receivers`                     text          DEFAULT NULL     COMMENT 'receiver userIds  ',
  `tags`                          text          DEFAULT NULL     COMMENT  'tags',
  `alias`                         text          DEFAULT NULL     COMMENT  'alias',
  `audience_type`               tinyint(4)      NOT NULL         COMMENT  'Audience Type,(0:registrationId,1:alias,2:tag,3:all 4:multi)',
  `application`                 varchar(100)    NOT NULL         COMMENT  'mobile application',
  `content_type`                varchar(100)    DEFAULT NULL     COMMENT  'jpush message content_type',
  `extras`                        text          DEFAULT NULL     COMMENT 'Device Type ,1:IOS,2:Android',
  `priority`                    tinyint(4)      DEFAULT '99'     COMMENT 'Priority,1-99,1:Top priority',
  `state`                       tinyint(4)      NOT NULL  DEFAULT '0' COMMENT 'State ,(0: Init State 10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,40:Readed,99:Send Failed)',
  `immediate`                   tinyint(4)      NOT NULL         COMMENT 'Instatnt Message  flag ( 0:  Instant  Message  , 1 : Schedule message) ',
  `schedule_time`               timestamp       NULL             COMMENT 'Expected delivery time',
  `expire_time`                 timestamp       NULL             COMMENT 'Expired Time',
  `sent_time`                   timestamp       NULL             COMMENT 'Sent Time',
  `sent_result`                 varchar(50)     DEFAULT NULL     COMMENT 'Sent Result',
  `created_time`                timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                  varchar(36)     NOT NULL,
  `updated_time`                timestamp       NULL,
  `updated_by`                  varchar(36)     DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  /************************SITE****************************/
  -- Dumping structure for table  site_message_template
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message_template`;
CREATE TABLE IF NOT EXISTS   `site_message_template` (
  `id`            bigint(18)    NOT NULL PRIMARY KEY ,
  `name`          varchar(50)   UNIQUE COMMENT 'Template Name ',
  `title`         varchar(50)   DEFAULT NULL COMMENT 'Template Title',
  `content`       text          DEFAULT NULL COMMENT 'Template Content',
  `addition`      text          DEFAULT NULL COMMENT 'Template Addition ',
  `type`          smallint(6)   NOT NULL  DEFAULT '0' COMMENT 'Template  Type',
  `state`         tinyint(4)    NOT NULL  DEFAULT '0' COMMENT 'state (0:enable ,1:disable)' ,
  `priority`      tinyint(4)    NOT NULL   DEFAULT '99' COMMENT 'Priority, 1-99,1:Top priority',
  `sender`        varchar(50)   NOT NULL COMMENT ' Sender',
  `created_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by`    varchar(36)   NOT NULL ,
  `updated_time`  timestamp     NULL,
  `updated_by`    varchar(36)   DEFAULT NULL,
  `deleted_time`  timestamp     NULL,
  `deleted_by`    varchar(36)   DEFAULT NULL,
  `deleted`       tinyint(4)    NOT NULL  DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  /************************SITE****************************/
   -- Dumping structure for table  site_message_content
 /************************SITE****************************/ 
DROP TABLE IF EXISTS `site_message_content`;
CREATE TABLE IF NOT EXISTS  `site_message_content` (
  `id`                          bigint(18)    NOT NULL PRIMARY KEY ,
  `site_message_template_id`    bigint(18)    DEFAULT NULL COMMENT 'site_message_template  id',
  `title`                       varchar(50)   DEFAULT NULL COMMENT 'Template Title',
  `content`                     text          DEFAULT NULL COMMENT 'Template  Content',
  `addition`                    text          DEFAULT NULL COMMENT 'Template  Addition',
  `type`                        smallint(6)   DEFAULT NULL COMMENT 'Template Type',
  `created_time`                timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                  varchar(36)   DEFAULT NULL ,
  `updated_time`                timestamp     NULL ,
  `updated_by`                  varchar(36)   DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


  /****************************************************/
   -- Dumping structure for table  site_message_property
 /****************************************************/ 
DROP TABLE IF EXISTS `site_message_property`;
CREATE TABLE IF NOT EXISTS  `site_message_property` (
  `id`                 bigint(18)    NOT NULL PRIMARY KEY ,
  `site_message_id`    bigint(18)    NOT NULL COMMENT 'site_message  id',
  `prop_key`           varchar(50)   DEFAULT NULL COMMENT 'prop key',
  `prop_value`         text          DEFAULT NULL COMMENT 'prop value',
  `created_time`       timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  /****************************************************/
   -- Dumping structure for table  site_message
 /****************************************************/ 
DROP TABLE IF EXISTS `site_message`;
CREATE TABLE IF NOT EXISTS  `site_message` (
  `id`                        bigint(18)    NOT NULL PRIMARY KEY ,
  `site_message_content_id`   bigint(18)    DEFAULT NULL COMMENT 'site_message_content  id',
  `receiver_id`               varchar(50)   DEFAULT NULL COMMENT 'receiver user_Id',
  `sender`                    varchar(50)   DEFAULT NULL COMMENT 'sender  ',
  `type`                      smallint(6)   DEFAULT '0'   COMMENT 'Site Message Type',
  `priority`                  tinyint(4)    DEFAULT '99'  COMMENT 'Priority,1-99,1:Top priority',
  `state`                     tinyint(4)    NOT NULL COMMENT 'State ,(10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,40:Readed,99:Send Failed)',
  `immediate`                 tinyint(4)    DEFAULT NULL COMMENT 'Instatnt Message  flag (0:Instant  Message, 1:Schedule message) ',
  `schedule_time`             timestamp     NULL COMMENT 'Expected delivery time ',
  `expired_time`              timestamp     NULL COMMENT 'Expired Time ',
  `sent_time`                 timestamp     NULL COMMENT 'Sent Time',
  `read_time`                 timestamp     NULL COMMENT 'Read Time',
  `deleted_time`              timestamp     NULL ,
  `deleted`                   tinyint(4)    NOT NULL DEFAULT '0'  COMMENT 'deleted   ( 0:enable,  1: disable)',
  `created_time`              timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)   NOT NULL,
  `updated_time`              timestamp     NULL,
  `updated_by`                varchar(36)   DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /****************************************************/
   -- Dumping structure for table  user_device
 /****************************************************/ 
DROP TABLE IF EXISTS `user_device`;
CREATE TABLE IF NOT EXISTS  `user_device` (
  `id`                        bigint(18)     NOT NULL PRIMARY KEY ,
  `user_id`                   bigint(18)     NOT NULL ,
  `platform`                  varchar(50)    NOT NULL       COMMENT  'platform : android, ios,winphone ',
  `device_id`                 varchar(100)   DEFAULT NULL   COMMENT  'mobile device Id',
  `registration_id`           varchar(100)   NOT NULL       COMMENT  'JPush AudienceTarget registrationId',
  `application`               varchar(100)   NOT NULL       COMMENT  'mobile application',
  `created_time`              timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)    NOT NULL,
  `updated_time`              timestamp      NULL,
  `updated_by`                varchar(36)    DEFAULT NULL,
  `deleted_time`              timestamp      NULL ,
  `deleted`                   tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'deleted ( 0:enable, 1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 /****************************************************/
   -- Dumping structure for table  user_channel_setting
 /****************************************************/ 
DROP TABLE IF EXISTS `user_channel_setting`;
CREATE TABLE IF NOT EXISTS  `user_channel_setting` (
  `id`                        bigint(18)     NOT NULL PRIMARY KEY ,
  `user_id`                   bigint(18)     NOT NULL ,
  `sms`                       tinyint(1)     DEFAULT 0  NOT NULL COMMENT  'sms   channel',
  `email`                     tinyint(1)     DEFAULT 1  NOT NULL COMMENT  'email channel',
  `push`                      tinyint(1)     DEFAULT 1  NOT NULL COMMENT  'push  channel',
  `inbox`                     tinyint(1)     DEFAULT 1  NOT NULL COMMENT  'inbox channel',
  `created_time`              timestamp      NOT NULL   DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)    NOT NULL,
  `updated_time`              timestamp      NULL,
  `updated_by`                varchar(36)    DEFAULT NULL,
  `deleted_time`              timestamp      NULL ,
  `deleted`                   tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'deleted ( 0:enable, 1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 /****************************************************/
   -- Dumping structure for table  user_email_whitelist
 /****************************************************/ 
DROP TABLE IF EXISTS `user_email_whitelist`;
CREATE TABLE IF NOT EXISTS  `user_email_whitelist` (
  `id`                        bigint(18)     NOT NULL PRIMARY KEY ,
  `user_id`                   bigint(18)     DEFAULT NULL ,
  `email`                     varchar(100)   NOT NULL ,
  `created_time`              timestamp      NOT NULL  DEFAULT CURRENT_TIMESTAMP ,
  `created_by`                varchar(36)    NOT NULL  DEFAULT 'admin',
  `updated_time`              timestamp      NULL,
  `updated_by`                varchar(36)    DEFAULT NULL,
  `deleted_time`              timestamp      NULL ,
  `deleted`                   tinyint(4)     NOT NULL DEFAULT '0'  COMMENT 'deleted(0:enable, 1: disable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

