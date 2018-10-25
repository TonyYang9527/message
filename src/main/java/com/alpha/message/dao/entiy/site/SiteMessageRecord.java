package com.alpha.message.dao.entiy.site;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Record;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Record
public class SiteMessageRecord {
	@Column
	Integer total;
}
