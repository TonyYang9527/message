package com.alpha.message.service.concurrent.callback;

import com.alpha.message.service.concurrent.event.NotfiyEvent;
import com.google.common.util.concurrent.FutureCallback;

public class NotifyFutureCallback implements FutureCallback<NotfiyEvent>{
	private StringBuilder builder = new StringBuilder();
	
	public void onFailure(Throwable t) {
		 builder.append(t.toString());
		   System.out.println("Failure");
	}

	public void onSuccess(NotfiyEvent result) {
		 builder.append(result).append(" successfully");
	    System.out.println("successfully");
	}
	public String getCallbackResult() {
		return builder.toString();
	}

}
