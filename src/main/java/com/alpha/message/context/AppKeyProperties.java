package com.alpha.message.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alpha.message.common.enums.SysApp;

@Component
@ConfigurationProperties(prefix = "security")
public class AppKeyProperties {

	private Credential crewc;
	private Credential mol;
	private Credential mo;

	public Credential getAppCredential(String app) {
		if (SysApp.Crewc == SysApp.fromValue(app)) {
			return this.crewc;
		} else if (SysApp.Mol == SysApp.fromValue(app)) {
			return this.mol;
		} else if (SysApp.Mo == SysApp.fromValue(app)) {
			return this.mo;
		}
		return null;
	}

	public Credential getCrewc() {
		return crewc;
	}

	public void setCrewc(Credential crewc) {
		this.crewc = crewc;
	}

	public Credential getMol() {
		return mol;
	}

	public void setMol(Credential mol) {
		this.mol = mol;
	}

	public Credential getMo() {
		return mo;
	}

	public void setMo(Credential mo) {
		this.mo = mo;
	}

	public static class Credential {
		private String masterSecret;
		private String appKey;

		public String getMasterSecret() {
			return masterSecret;
		}

		public void setMasterSecret(String masterSecret) {
			this.masterSecret = masterSecret;
		}

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}
	}
}
