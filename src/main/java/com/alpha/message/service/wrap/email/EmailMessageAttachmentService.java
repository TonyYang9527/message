package com.alpha.message.service.wrap.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.dao.mapper.email.EmailMessageAttachmentMapper;

@Service
public class EmailMessageAttachmentService {
    @Autowired
    protected EmailMessageAttachmentMapper  attachmentMapper;
}
