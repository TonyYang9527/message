package com.bmo.message.driver.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AiliDriver {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtpdm-ap-southeast-1.aliyun.com");
		// props.put("mail.smtp.port", ALIDM_SMTP_PORT);

		// 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");
		// 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com

		props.put("mail.user", "no-reply-usercenter@email.brightoilmarine.com");
		// 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
		props.put("mail.password", "MailService20170814");
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// mailSession.setDebug(true);

		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		try {
			// 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
			InternetAddress from = new InternetAddress("no-reply-usercenter@email.brightoilmarine.com",
					"Marine Online  Singapore");
			message.setFrom(from);

			// 设置收件人邮件地址，比如yyy@yyy.com
			InternetAddress to = new InternetAddress("tony.yang@bwoil.com");
			InternetAddress cc = new InternetAddress("yangxiangjiang19880805@gmail.com");
			InternetAddress bcc = new InternetAddress("yangxiangjiang19880805@gmail.com");
			message.setRecipient(MimeMessage.RecipientType.TO, to);
			message.setRecipient(MimeMessage.RecipientType.CC, cc);
			message.setRecipient(MimeMessage.RecipientType.BCC, bcc);
			// 如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
			// InternetAddress[] adds = new InternetAddress[2];
			// adds[0] = new InternetAddress("xxxxx@qq.com");
			// adds[1] = new InternetAddress("xxxxx@qq.com");
			// message.setRecipients(Message.RecipientType.TO, adds);
			// 设置邮件标题
			message.setSubject("测试邮件");
			// 设置邮件的内容体
			message.setContent(
					"<div style=\"background-color: #ececec; padding: 35px;\"><table cellpadding=\"0\" align=\"center\" style=\"width: 100%; margin: 0px auto; text-align: left; position: relative; border-radius: 5px; font-size: 14px; font-family: 'Microsoft YaHei', SimHei; line-height: 1.5; box-shadow: #999999 0px 0px 5px; border-collapse: collapse; background: #ffffff;\" height=\"100%\"><tbody><tr style=\"height: 24px;\"><th valign=\"middle\" style=\"height: 24px; line-height: 25px; padding: 15px 35px; border-bottom: 1px solid #2276d2; background-color: #2276d2; border-radius: 5px 5px 0px 0px; width: 1168px;\"><span style=\"color: #ffffff; font-family: Microsoft YaHei; font-size: x-large;\">Your Order with Order ID number {{orderId}}&nbsp;is now Partially Paid</span></th></tr><tr style=\"height: 488px;\"><td style=\"width: 1168px; height: 488px;\"><div style=\"padding: 25px 35px 40px; background-color: #fff;\"><h2 style=\"margin: 5px 0px;\"><span style=\"line-height: 20px; color: #333333;\"><span style=\"line-height: 22px; font-size: large;\">Dear {{companyName}}:</span></span></h2><p>&nbsp;</p><p>Your Order with Order ID number {{orderId}} is now Partially Paid.<br /><br /> Please click <a href=\"{{link}}\">link to Buyer - Order Management - My Enquiry - Appointed tab -{{orderId}} with status: Partially Paid&nbsp;</a>to view the details online.</p><p>If you require assistance, please contact our Customer Service from 8am to 9:30pm, Monday to Friday (except Saturday, Sunday and Public Holidays) at 6571 5888 or +65 6571 5888 (from overseas). Alternatively, you can email us at customercare@emarineonline.com.</p><p>We look forward to serving you again.</p><br /><p>Marine Online Pte Ltd</p><p><a href=\"http://www.emarineonline.com\">www.emarineonline.com</a></p><br /><p align=\"left\">This is an automated email. Please do not reply.</p></div></td></tr></tbody></table></div>",
					"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			String err = e.getMessage();
			// 在这里处理message内容， 格式是固定的
			System.out.println(err);
		}
	}
}
