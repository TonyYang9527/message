package com.alpha.message.model.queue;

import java.util.Comparator;
import java.util.Date;

import com.alpha.message.common.utils.DateUtils;

public class QueueComparator implements Comparator<QueuePriorityVo> {
	/**
	 * order by priority && schedule_time
	 */
	public int compare(QueuePriorityVo s1, QueuePriorityVo s2) {
		Date scheduleTm1 = s1.getScheduleTime();
		Date scheduleTm2 = s2.getScheduleTime();
		Integer priority1 = s1.getPriority();
		Integer  priority2 = s2.getPriority();
		int retVal = 0;
		if (priority1.compareTo(priority2) <= -1) {
			retVal = -1;
		} else if (priority1.compareTo(priority2) == 0) {
			int retVals = DateUtils.compareDate(scheduleTm1, scheduleTm2);
			if (retVals == 1) {
				retVal = 1;
			} else if (retVals == 0) {
				retVal = 0;
			} else if (retVals == -1) {
				retVal = -1;
			}
		} else if (priority1.compareTo(priority2) >= 1) {
			retVal = 1;
		}
		return retVal;
	}

}
