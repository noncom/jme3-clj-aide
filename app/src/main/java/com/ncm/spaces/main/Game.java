package com.ncm.spaces.main;
import com.jme3.app.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import clojure.lang.*;
import clojure.java.api.*;
import com.ncm.clojure_support.*;
import com.jme.*;
import com.jme3.input.controls.*;
import java.net.*;
import dalvik.system.*;

public class Game extends SimpleApplication {
    
    static Game game;
    static JmeActivity activity;
    
    // -- clojure
    ClojureF clj;
    ClojureLoader cl;
    Context ctx;
    IFn require;
    
    // -- input
    TouchInputListener touchListener;
    TouchTrigger touchTrigger;

    public static void setActivity(JmeActivity a) {
        activity = a;
    }

    private void initClojure() {
        String url = "file:///mnt/sdcard/AppProjects/spaces/app/src/main/clj";
        //b = new clojure.java.api.Clojure();
        clj = new ClojureF();
  //      clj.addURL(url);
        cl = new ClojureLoader(clj);
        activity.runOnUiThread(cl);
        int counter = 0;
        while (cl.getStatus() != AsyncTask.Status.FINISHED) {
            try {
                Thread.sleep(10);
                System.out.println(counter);
                counter++;
            }
            catch (InterruptedException e) {}
        }
      //  clojure.lang.Compiler.LOADER.alterRoot();
        require = var("clojure.core", "require");
  /*      try {
            ((PathClassLoader)RT.baseLoader()).addURL(new URL(url));
            for(URL u : ((DalvikDynamicClassLoader)RT.baseLoader()).getURLs()){
                Log.d("-- = ---", "url : " + url.toString());
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        //     require.invoke(Clojure.read("clj.core"));
        //     Clojure.var("clj.core", "hello").invoke();
        System.out.println("[[[[[[[[[[!!!]]]]]]]]]]]");
        //  clj.eval("(println (seq (.getURLs (java.lang.ClassLoader/getSystemClassLoader))))");     

        require.invoke(Clojure.read("clj.core"));
        var("clj.core", "hello").invoke();
    }

	@Override
	public void simpleInitApp() {
        Log.d("aaaaaaaa", "----- begin!");
        game = this;
        initClojure();
        Log.d("spaces", " begin init touch");
        initTouchInput();
        Log.d("spaces", " end init touch");
        
        initGame();
		// TODO: Implement this method

        Log.d("bbbbbbbb", " ----- end!");
	}

    private void initGame() {
        var("clj.core", "init").invoke();
    }

    private void initTouchInput() {
        Log.d("=== init input ===", "[A]");
        touchTrigger = new TouchTrigger(0);
        Log.d("=== init input ===", "[B]");
        inputManager.addMapping("all touch", touchTrigger);
        
        Log.d("=== init input ===", "[C]");
        
        IFn inputf = var("clj.input", "touch-input-jme-listener-f");
        Log.d("spaces", "----------- input f = " + inputf);
        touchListener = new TouchInputListener(inputf);
        Log.d("=== init input ===", "[D]");
        inputManager.addListener(touchListener, "all touch");
        Log.d("=== init input ===", "[E]");
        
    }

    private IFn var(String ns, String sym) {
  //      return Clojure.var(ns, sym);
        return clj.getRT().var(ns, sym);
    }

    public Game getInstance(){
        return game;
    }

}
