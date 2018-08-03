
-- --------------------------------------------------------
-- Table :                                  Site  Template
-- --------------------------------------------------------

-- Base Template
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4000,'base.templ','Base Template','{{content}}',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:32:33.000','admin',NULL,NULL,NULL,NULL,0);
-- New Request For Quotation public
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4001,'rfq.new.public','New Request For Quotation','<a href="{{link}}">You have received a new Request For Quotation.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:56.000','admin',NULL,NULL,NULL,NULL,0);
-- New Request For Quotation private
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4002,'rfq.new.private','New  Request For Quotation','<a href="{{link}}">You have received a new Request For Quotation from {{companyName}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:54.000','admin',NULL,NULL,NULL,NULL,0);
-- Received Quotation
INSERT INTO site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4003,'quotation.received','New Quotation','<a href="{{link}}">You have received a new Quotation from{{companyName}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:58.000','admin',NULL,NULL,NULL,NULL,0);
-- Accepted Quotation
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4004,'quotation.accepted','New Quotation','<a href="{{link}}">Your Quotation has been accepted by {{companyName}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:49:30.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Handling
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4005,'order.status.handling','Order Handling','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now on Handling.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:32:33.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Delivery
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4006,'order.status.delivery','Order Delivery','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now on Delivery.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:54.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Delivered
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4007,'order.status.delivered','Order Delivered','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now Delivered.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:56.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Partially Paid
INSERT INTO site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4008,'order.status.partially.paid','Order  Partially Paid','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now Partially Paid.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:58.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Paid
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4009,'order.status.paid','Order  Paid','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now Paid.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:49:30.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Completed
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4010,'order.status.completed','Order  Completed','<a href="{{link}}">Your Order with Order ID number {{orderId}} is now Completed.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:32:33.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Cancellation
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES (4011,'order.status.cancel','Order  Cancellation','<a href="{{link}}">{{companyName}} send a cancellation order request for Order ID number {{orderId}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:54.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Agrees
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4012,'order.status.agree','Order  Agrees','<a href="{{link}}">{{companyName}}} agrees to cancel order for Order ID number {{orderId}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:56.000','admin',NULL,NULL,NULL,NULL,0);
-- Order  Disagrees
INSERT INTO site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4013,'order.status.disagree','Order  Disagrees','<a href="{{link}}">{{companyName}} disagrees to cancel order for Order ID number {{orderId}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:37:58.000','admin',NULL,NULL,NULL,NULL,0);
-- Update Quotation
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4014,'order.status.update','Update Quotation','<a href="{{link}}">{{companyName}} updated Quotation for Order ID number{{orderId}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:49:30.000','admin',NULL,NULL,NULL,NULL,0);
-- Order updload  File
INSERT INTO  site_message_template (id,name,title,content,addition,`type`,state,priority,sender,created_time,created_by,updated_time,updated_by,deleted_time,deleted_by,deleted) VALUES(4015,'order.status.upload','Order Upload','<a href="{{link}}">{{companyName}} uploaded a file for Order ID number {{orderId}}.</a>',NULL,0,0,1,'Marine Online  Singapore','2018-07-25 11:49:30.000','admin',NULL,NULL,NULL,NULL,0);

