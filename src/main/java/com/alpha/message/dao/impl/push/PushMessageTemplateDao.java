package com.alpha.message.dao.impl.push;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bmo.ibackend.persistence.SQLQuery;
import com.alpha.message.common.utils.CollectionToStringUtils;
import com.alpha.message.dao.entiy.push.PushMessageTemplate;
import com.alpha.message.dao.mapper.push.PushMessageTemplateMapper;

@Repository
public class PushMessageTemplateDao implements PushMessageTemplateMapper {

	@Override
	public PushMessageTemplate selectByPrimaryKey(Long id) {
		return PushMessageTemplate.findById(id);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		PushMessageTemplate.deleteById(id);
	}

	@Override
	public void insertSelective(PushMessageTemplate template) {
		template.saveOrUpdate();

	}

	@Override
	public void updateByPrimaryKeySelective(PushMessageTemplate template) {
		template.saveOrUpdate();
	}

	@Override
	public void changeDeleted(Long id, int flag, String deletedBy) {
		PushMessageTemplate template = this.selectByPrimaryKey(id);
		if (template != null) {
			template.setDeleted(flag);
			template.setDeletedBy(deletedBy);
			template.setDeletedTime(new Date());
			template.saveOrUpdate();
		}

	}

	@Override
	public boolean checkDeleted(Long id, int deleted) {
		PushMessageTemplate template = PushMessageTemplate.where(" id =? and deleted=?   ", id, deleted).first();
		return template == null ? false : true;
	}

	@Override
	public void changeState(Long id, int state, String updatedBy) {
		PushMessageTemplate template = this.selectByPrimaryKey(id);
		if (template != null) {
			template.setState(state);
			template.setUpdatedBy(updatedBy);
			template.setUpdatedTime(new Date());
			template.saveOrUpdate();
		}

	}

	@Override
	public boolean checkState(Long id, int state) {
		PushMessageTemplate template = PushMessageTemplate.where(" id =? and state=?   ", id, state).first();
		return template == null ? false : true;
	}

	@Override
	public List<PushMessageTemplate> listPushTemplate(String name, Set<Integer> states) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  push_message_template  t  ");
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
		return SQLQuery.sql(PushMessageTemplate.class, sql.toString()).all();
	}

	@Override
	public PushMessageTemplate selectByUniqueName(String name) {
		return PushMessageTemplate.where(" name =?  ", name).first();
	}

}
