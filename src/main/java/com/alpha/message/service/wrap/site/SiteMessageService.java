package com.alpha.message.service.wrap.site;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.enums.MessageType;
import com.alpha.message.common.enums.MsgPriority;
import com.alpha.message.common.enums.SendState;
import com.alpha.message.common.enums.SiteState;
import com.alpha.message.common.utils.RegexUtil;
import com.alpha.message.dao.entiy.site.SiteMessage;
import com.alpha.message.dao.entiy.site.SiteMessageContent;
import com.alpha.message.dao.entiy.site.SiteMessageRecord;
import com.alpha.message.dao.entiy.site.SiteMessageTemplate;
import com.alpha.message.dao.mapper.site.SiteMessageContentMapper;
import com.alpha.message.dao.mapper.site.SiteMessageMapper;
import com.alpha.message.dao.mapper.site.SiteMessagePropertyMapper;
import com.alpha.message.dao.mapper.site.SiteMessageTemplateMapper;
import com.alpha.message.ifacade.request.site.DeleteSiteMsgRequest;
import com.alpha.message.ifacade.request.site.ListSiteMsgRequest;
import com.alpha.message.ifacade.request.site.ReadSiteMsgRequest;
import com.alpha.message.ifacade.request.site.SiteMsgRequest;
import com.alpha.message.ifacade.request.site.UpdateSiteMsgRequest;
import com.alpha.message.model.vo.UserSiteMessage;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SiteMessageService {

	@Autowired
	protected SiteMessageContentMapper siteMessageContentMapper;
	@Autowired
	protected SiteMessageMapper siteMessageMapper;
	@Autowired
	protected SiteMessagePropertyMapper propertyMapper;
	@Autowired
	protected SiteMessageTemplateMapper templateMapper;

	/**
	 * Get Site MessageById.
	 *
	 * @param id
	 * @return the site message by id
	 */
	public SiteMessage getSiteMessageById(Long id) {
		Preconditions.checkNotNull(id);
		SiteMessage o = siteMessageMapper.selectByPrimaryKey(id);
		if (o != null && o.getDeleted().intValue() == DeleteFlag.SURVIVOR.getValue()) {
			return o;
		}
		return null;
	}

	/**
	 * save SiteMessage
	 * 
	 * @param req
	 * @return the long
	 */
	public SiteMessage saveSiteMessageByReq(Long contentId, SiteMsgRequest req, SiteState siteState) {
		SiteMessageTemplate template = null;
		if (StringUtils.isNotBlank(req.getTemplateName())) {
			template = templateMapper.selectByUniqueName(req.getTemplateName());
		}
		SiteMessage msg = buildSiteMessage(contentId, template, req, siteState);
		siteMessageMapper.insertSelective(msg);
		return msg;
	}

	/**
	 * buildSiteMessage
	 *
	 * @param template the template
	 * @param req      the req
	 * @return the Site message
	 */
	private SiteMessage buildSiteMessage(Long contentId, SiteMessageTemplate template, SiteMsgRequest req,
			SiteState siteState) {
		SiteMessage message = new SiteMessage();
		message.setSiteMessageContentId(contentId);
		if (req.getReceiverId() != null) {
			message.setReceiverId(req.getReceiverId());
		}
		if (req.getSender() != null) {
			message.setSender(req.getSender());
			message.setCreatedBy(req.getSender());
		} else if (template != null) {
			message.setSender(template.getSender());
			message.setCreatedBy(template.getSender());
		}

		if (req.getPriority() != null) {
			message.setPriority(req.getPriority());
		} else if (template != null) {
			message.setPriority(template.getPriority());
		} else {
			message.setPriority(MsgPriority.LOW.getValue());
		}

		if (req.getType() != null) {
			message.setType(req.getType());
		} else if (template != null) {
			message.setType(template.getType());
		} else {
			message.setType(MessageType.Default.getValue());
		}
		
		message.setState(SendState.SENT.getValue());
		message.setImmediate(ImmediateType.IMMEDIATE.getValue());
		message.setScheduleTime(new Date());
		message.setSentTime(new Date());
		message.setExpiredTime(new Date());
		message.setCreatedTime(new Date());

		return message;
	}

	public void updateSiteMsg(UpdateSiteMsgRequest request) {
		SiteMessage siteMessage = getSiteMessageById(request.getId());
		if (siteMessage != null) {
			SiteMessageContent siteMessageContent = new SiteMessageContent();
			siteMessageContent.setId(siteMessage.getSiteMessageContentId());
			siteMessageContent.setTitle(request.getTitle());
			siteMessageContent.setContent(request.getContent());
			siteMessageContent.setAddition(request.getAddition());
			if (request.getType() != null) {
				siteMessageContent.setType(request.getType());
			}
			siteMessageContent.setUpdatedBy(request.getOperator());
			siteMessageContentMapper.updateByPrimaryKeySelective(siteMessageContent);
		}

		if (request.getPriority() != null) {
			siteMessage.setPriority(request.getPriority());
		}
		if (request.getType() != null) {
			siteMessage.setType(request.getType());
		}
		if (request.getScheduleUtcTime() != null) {
			siteMessage.setScheduleTime(new Date(request.getScheduleUtcTime()));
		}
		if (request.getExpiredUtcTime() != null) {
			siteMessage.setExpiredTime(new Date(request.getExpiredUtcTime()));
		}
		siteMessage.setReceiverId(request.getReceiverId());
		siteMessage.setSender(request.getSender());
		siteMessageMapper.updateByPrimaryKeySelective(siteMessage);
	}

	public void deleteSiteMsg(DeleteSiteMsgRequest request) {
		for (long id : request.getIds()) {
			SiteMessage siteMessage = getSiteMessageById(id);
			if (siteMessage == null) {
				continue;
			}
			siteMessage.setDeleted(DeleteFlag.VICTIM.getValue());
			siteMessage.setDeletedTime(new Date());
			siteMessage.setUpdatedBy(request.getOperator());
			siteMessage.setUpdatedTime(new Date());
			siteMessageMapper.updateByPrimaryKeySelective(siteMessage);
		}
	}

	public void changeSiteMessageState(Long id, Integer state) {
		siteMessageMapper.updateByPrimaryKeySelective(id, state);
	}

	/**
	 * Read Site Message
	 * 
	 * @param req
	 * @return the long
	 */
	public void readSiteMsg(ReadSiteMsgRequest request) {
		for (Long id : request.getIds()) {
			SiteMessage siteMessage = getSiteMessageById(id);
			if (siteMessage.getState() == SendState.SENT.getValue()) {
				siteMessageMapper.updateByPrimaryKeySelective(id, SendState.READ.getValue());
			}
		}
	}

	/**
	 * List Site Message
	 * 
	 * @param req
	 * @return the long
	 */
	public List<SiteMessage> listSiteMessage(ListSiteMsgRequest request) {
		return siteMessageMapper.listSiteMessage(request.getStates(), request.getTypes());
	}

	/**
	 * Get User Site Message
	 * @param req
	 * @return the long
	 */
	public List<UserSiteMessage> getUserSiteMessage(Long userId) {
		Set<Integer> states = new HashSet<Integer>();
		states.add(SendState.SENT.getValue());
		states.add(SendState.READ.getValue());
		List<SiteMessage> siteMessages = siteMessageMapper.getUserSiteMessage(userId, states);
		List<UserSiteMessage> result = new ArrayList<UserSiteMessage>();
		if (!CollectionUtils.isEmpty(siteMessages)) {
			siteMessages.stream().forEach(site -> {
				if (site != null) {
					SiteMessageContent content = siteMessageContentMapper
							.selectByPrimaryKey(site.getSiteMessageContentId());
					if (content != null) {
						UserSiteMessage message = new UserSiteMessage();
						message.setSiteMessageId(site.getId());
						message.setState(site.getState());
						message.setUserId(site.getReceiverId());
						message.setTitle(content.getTitle());
						message.setLink(RegexUtil.getLabelAttrValue(content.getContent(), RegexUtil.A, RegexUtil.HREF));
						message.setContent(RegexUtil.getLabelInnerValue(content.getContent(), RegexUtil.A));
						message.setCreateTime(content.getCreatedTime());

						result.add(message);
					}
				}
			});
		}
		return result;
	}

	public Integer countUserSiteMsgTotal(Long userId) {
		SiteMessageRecord record = null;
		if (userId == null) {
			log.error("countUserSiteMsgTotal userId is not  null");
			return Integer.getInteger("0");
		}

		Set<Integer> states = new HashSet<Integer>();
		states.add(SendState.SENT.getValue());
		states.add(SendState.READ.getValue());
		record = siteMessageMapper.countUserSiteMsgTotal(userId, states);
		return record != null ? record.getTotal() : Integer.getInteger("0");

	}

	public Integer countUserSiteMsgUnread(Long userId) {
		SiteMessageRecord record = null;
		if (userId == null) {
			log.error("countUserSiteMsgUnread userId is not  null");
			return Integer.getInteger("0");
		}
		Set<Integer> states = new HashSet<Integer>();
		states.add(SendState.SENT.getValue());
		record = siteMessageMapper.countUserSiteMsgTotal(userId, states);
		return record != null ? record.getTotal() : Integer.getInteger("0");

	}

}
