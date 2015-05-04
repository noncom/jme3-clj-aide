package com.ncm.krmt.main;
import com.jme3.app.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import clojure.lang.*;
import clojure.java.api.*;
import com.ncm.clojure_support.*;
import com.jme.*;

public class Game extends SimpleApplication
{
    //clojure.java.api.Clojure b;
    ClojureF clj;
    ClojureLoader cl;
    Context ctx;
    static JmeActivity activity;
    
    public static void setActivity(JmeActivity a)
    {
        activity = a;
    }
    
    private void initClojure(){
        //b = new clojure.java.api.Clojure();
        clj = new ClojureF();
        cl = new ClojureLoader(clj);
        activity.runOnUiThread(cl);
        int counter = 0;
        while(cl.getStatus() != AsyncTask.Status.FINISHED) {
            try
            {
                Thread.sleep(10);
                System.out.println(counter);
                counter++;
            }
            catch (InterruptedException e)
            {}
        }
 
        IFn require = Clojure.var("clojure.core", "require");
        
   //     require.invoke(Clojure.read("clj.core"));
   //     Clojure.var("clj.core", "hello").invoke();
        System.out.println("[[[[[[[[[[!!!]]]]]]]]]]]");
      //  clj.eval("(println (seq (.getURLs (java.lang.ClassLoader/getSystemClassLoader))))");     
   
        clj.getRT().var("clojure.core", "require").invoke(Clojure.read("clj.core"));
        clj.getRT().var("clj.core", "hello").invoke();
    }
    
	@Override
	public void simpleInitApp()
	{
        Log.d("aaaaaaaa", "----- begin!");

        initClojure();
		// TODO: Implement this method
        
        Log.d("bbbbbbbb", " ----- end!");
	}

	
}
