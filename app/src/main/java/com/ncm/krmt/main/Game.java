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

public class Game extends SimpleApplication {
    
    static Game game;
    
    // -- clojure
    //clojure.java.api.Clojure b;
    ClojureF clj;
    ClojureLoader cl;
    Context ctx;
    static JmeActivity activity;
    
    // -- input
    TouchInputListener touchListener;

    public static void setActivity(JmeActivity a) {
        activity = a;
    }

    private void initClojure() {
        //b = new clojure.java.api.Clojure();
        clj = new ClojureF();
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

        IFn require = Clojure.var("clojure.core", "require");

        //     require.invoke(Clojure.read("clj.core"));
        //     Clojure.var("clj.core", "hello").invoke();
        System.out.println("[[[[[[[[[[!!!]]]]]]]]]]]");
        //  clj.eval("(println (seq (.getURLs (java.lang.ClassLoader/getSystemClassLoader))))");     

        var("clojure.core", "require").invoke(Clojure.read("clj.core"));
        var("clj.core", "hello").invoke();
    }

	@Override
	public void simpleInitApp() {
        Log.d("aaaaaaaa", "----- begin!");
        game = this;
        initClojure();
        initTouchInput();
        initGame();
		// TODO: Implement this method

        Log.d("bbbbbbbb", " ----- end!");
	}

    private void initGame() {
        var("clj.core", "init").invoke();
    }

    private void initTouchInput() {
        IFn inputf = var("clj.input", "listener-f");
        touchListener = new TouchInputListener(inputf);
        inputManager.addListener(touchListener);
    }

    private IFn var(String ns, String sym) {
        return clj.getRT().var(ns, sym);
    }

    public Game getInstance(){
        return game;
    }

}
