package com.alpha.message.dao.impl.email;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.alpha.message.common.utils.CollectionToStringUtils;
import com.alpha.message.dao.entiy.email.EmailMessageTemplate;
import com.alpha.message.dao.mapper.email.EmailMessageTemplateMapper;
import com.bmo.ibackend.persistence.SQLQuery;

@Repository
public class EmailMessageTemplateDao implements EmailMessageTemplateMapper {

	@Override
	public EmailMessageTemplate selectByPrimaryKey(Long id) {
		return EmailMessageTemplate.findById(id);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		EmailMessageTemplate.deleteById(id);
	}

	@Override
	public void insertSelective(EmailMessageTemplate template) {
		template.saveOrUpdate();
	}

	@Override
	public void updateByPrimaryKeySelective(EmailMessageTemplate template) {
		template.saveOrUpdate();
	}

	public void changeDeleted(Long id, int flag, String deletedBy) {
		EmailMessageTemplate template = this.selectByPrimaryKey(id);
		if (template != null) {
			template.setDeleted(flag);
			template.setDeletedBy(deletedBy);
			template.setDeletedTime(new Date());
			template.saveOrUpdate();
		}
	}

	public boolean checkDeleted(Long id, int deleted) {
		EmailMessageTemplate template = EmailMessageTemplate.where(" id =? and deleted=?   ", id, deleted).first();
		return template == null ? false : true;
	}

	@Override
	public void changeState(Long id, int state, String updatedBy) {
		EmailMessageTemplate template = this.selectByPrimaryKey(id);
		if (template != null) {
			template.setState(state);
			template.setUpdatedBy(updatedBy);
			template.setUpdatedTime(new Date());
			template.saveOrUpdate();
		}
	}

	@Override
	public boolean checkState(Long id, int state) {
		EmailMessageTemplate template = EmailMessageTemplate.where(" id =? and state=?   ", id, state).first();
		return template == null ? false : true;
	}

	@Override
	public List<EmailMessageTemplate> listEmailTemplate(String name, Set<Integer> states) {

		StringBuffer sql = new StringBuffer();
		sql.append("   SELECT   *   FROM email_message_template  t  ");
		if (!StringUtils.isEmpty(name) || CollectionUtils.isNotEmpty(states)) {
			sql.append("  WHERE   ");
			if (StringUtils.hasText(name)) {
				sql.append("     t.name like '%").append(name).append("%'");
				if (CollectionUtils.isNotEmpty(states)) {
					sql.append("  AND  ");
				}
			}
			if (CollectionUtils.isNotEmpty(states)) {
				sql.append(" t.state in ( ");
				sql.append(CollectionToStringUtils.transform(states));
				sql.append(")");
			}
		}
		sql.append(" ORDER BY  UPPER(t.created_time)  ASC ");
		return SQLQuery.sql(EmailMessageTemplate.class, sql.toString()).all();
	}

	@Override
	public EmailMessageTemplate selectByUniqueName(String name) {
		return EmailMessageTemplate.where(" name =?  ", name).first();
	}

}
