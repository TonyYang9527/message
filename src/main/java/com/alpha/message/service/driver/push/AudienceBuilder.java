package com.alpha.message.service.driver.push;

import java.util.Set;

import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

public class AudienceBuilder {

	public static Audience alias(Set<String> alias) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build();
	}

	public static Audience alias(String alias) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build();
	}

	public static Audience tags(String tags) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tags)).build();
	}

	public static Audience tags(Set<String> tags) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tags)).build();
	}

	public static Audience tags_alias(Set<String> tags, Set<String> alias) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tags))
				.addAudienceTarget(AudienceTarget.alias(alias)).build();
	}

	public static Audience registrationId(String registrationId) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationId)).build();
	}

	public static Audience registrationIds(Set<String> registrationIds) {
		return Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationIds)).build();
	}

	public static Audience all() {
		return Audience.all();
	}
}
