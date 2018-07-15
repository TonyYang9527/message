package com.alpha.message.service.impl.notify;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

@Service("pushNotifyServices")
public class PushNotifyService {

	final static ListeningExecutorService runTaskservice = MoreExecutors
			.listeningDecorator(Executors.newCachedThreadPool());
	
	final static ListeningExecutorService callbackService = MoreExecutors
			.listeningDecorator(Executors.newCachedThreadPool());
	
	public <V> void execute(Callable<V> call, FutureCallback<V> callback)
			throws InterruptedException, ExecutionException {
		ListenableFuture<V> future = runTaskservice.submit(call);
		Futures.addCallback(future, callback, callbackService);
	}
}
