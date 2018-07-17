package com.alpha.message.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.message.service.channel.ChannelService;
import com.alpha.message.service.channel.entity.email.EmailChannelReq;

@RestController
public class EmailController {


	@Autowired
	private ChannelService channelService;
	
	@RequestMapping("/")
	public String index() {
	
		Long id =1L ;
	   String[] target =new  	String[] {"yxj1985@126.com"};
	    String  content= "This  is test email";
	    String from ="bwecusercenter@gmail.com";
	    String title = "This  is test email";
	    String  senderName = "This  is test email";
        Boolean isHtmlText =false ;
        String[] cc =   new  	String[] {"yxj1985@126.com"};
        String[] bcc=   new  	String[] {"yxj1985@126.com"};
        List<File> attachmentFiles =null ;
       		
		EmailChannelReq   req = new EmailChannelReq(id,target, content, from, title, senderName, isHtmlText, cc, bcc,  attachmentFiles);
		channelService.request(req) ;
		return "Greetings from Spring Boot!";
	}
	

}
