package com.ncm.clojure_support;
import java.util.concurrent.*;
import android.os.*;
import android.util.*;

public class ClojureLoader implements Runnable
{

	Executor executor;
	ClojureLoaderAsyncTask clat;
	ClojureF clojure;
	String[] targets;
	
	public ClojureLoader(ClojureF c){
		clojure = c;
	}
	
	public void run(){
		executor = Executors.newSingleThreadExecutor(new ThreadFactory(){
				public Thread newThread(Runnable r){
					return new Thread(Thread.currentThread().getThreadGroup(),
									  r,
									  "clojure_loader",
									  32 * 1024);
				}
			});

		clojure = new ClojureF();
		
		clat = new ClojureLoaderAsyncTask(clojure);
		Log.d("999999", "_-----------getting ready to load clojure core clj ------");
		clat.executeOnExecutor(executor, "clj/core.clj");
	}
	
	public AsyncTask.Status getStatus(){
		if(clat != null) return clat.getStatus();
		else return AsyncTask.Status.PENDING;
	}
	
}
