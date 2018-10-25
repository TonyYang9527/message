package com.alpha.message.dao.impl.site;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.bmo.ibackend.persistence.SQLQuery;
import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.SQLOperators;
import com.alpha.message.common.utils.SQLWhereBuilder;
import com.alpha.message.dao.entiy.site.SiteMessage;
import com.alpha.message.dao.entiy.site.SiteMessageRecord;
import com.alpha.message.dao.mapper.site.SiteMessageMapper;

@Repository
public class SiteMessageDao implements SiteMessageMapper {

	@Override
	public void insertSelective(SiteMessage message) {
		message.saveOrUpdate();
	}

	@Override
	public SiteMessage selectByPrimaryKey(Long id) {
		return SiteMessage.findById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Long id, Integer state) {
		SiteMessage siteMessage = this.selectByPrimaryKey(id);
		if (siteMessage != null) {
			siteMessage.setState(state);
			siteMessage.setSentTime(new Date());
			siteMessage.saveOrUpdate();
		}
	}

	@Override
	public void updateByPrimaryKeySelective(SiteMessage message) {
		SiteMessage siteMessage = this.selectByPrimaryKey(message.getId());
		if (siteMessage != null) {
			message.saveOrUpdate();
		}
	}

	@Override
	public List<SiteMessage> listSiteMessage(Set<Integer> states, Set<Integer> types) {
		StringBuffer sql = new StringBuffer();
		sql.append("   SELECT   *   FROM  site_message  t  ");
		SQLWhereBuilder sqlBuilder = SQLWhereBuilder.instance();
		sqlBuilder.and("  t.state ", SQLOperators.In, states);
		sqlBuilder.and("  t.type  ", SQLOperators.In, types);
		sql.append(sqlBuilder.toString());
		sql.append(" ORDER BY  UPPER(t.created_time)  DESC ");
		return SQLQuery.sql(SiteMessage.class, sql.toString()).all();
	}

	@Override
	public List<SiteMessage> getUserSiteMessage(Long userId, Set<Integer> states) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  site_message  t  ");

		SQLWhereBuilder sqlBuilder = SQLWhereBuilder.instance();
		sqlBuilder.and("  t.receiver_id  ", SQLOperators.EqualTo, String.valueOf(userId));
		sqlBuilder.and("  t.state ", SQLOperators.In, states);
		sqlBuilder.and("  t.deleted  ", SQLOperators.EqualTo, String.valueOf(DeleteFlag.SURVIVOR.getValue()));

		sql.append(sqlBuilder.toString());

		sql.append(" ORDER BY  UPPER (t.created_time)  DESC ");

		return SQLQuery.sql(SiteMessage.class, sql.toString()).all();
	}

	@Override
	public SiteMessageRecord countUserSiteMsgTotal(Long userId, Set<Integer> states) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(1) total FROM  site_message  t  ");
		
		SQLWhereBuilder sqlBuilder = SQLWhereBuilder.instance();
		sqlBuilder.and("  t.receiver_id  ", SQLOperators.EqualTo, String.valueOf(userId));
		sqlBuilder.and("  t.state ", SQLOperators.In, states);
		sqlBuilder.and("  t.deleted  ", SQLOperators.EqualTo, String.valueOf(DeleteFlag.SURVIVOR.getValue()));
		sql.append(sqlBuilder.toString());
		sql.append(" ORDER BY  UPPER(t.created_time)  DESC ");
		
		return SQLQuery.sql(SiteMessageRecord.class, sql.toString()).first();
	}
}
