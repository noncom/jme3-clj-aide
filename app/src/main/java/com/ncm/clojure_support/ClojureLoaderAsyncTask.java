package com.ncm.clojure_support;
import android.os.*;
import java.io.*;

public class ClojureLoaderAsyncTask extends AsyncTask<String, String, String> {

	ClojureF clojure;
	String defaultPath = "/mnt/sdcard/AppProjects/MyApp2/app/src/main";
//	System.setProperty("clojure.compile.path", path + "clj/");


	public ClojureLoaderAsyncTask(ClojureF c) {
		clojure = c;
	}

    public void log(String s) {
        System.out.println("+============= Clj Async Task: " + s);
    }

	// loads all passed clojure code locations
	@Override
	protected String doInBackground(String[] locations) {
        FileInputStream fis;

        log("begin");

		for (String location : locations) {
			System.setProperty("user.dir", defaultPath);
            try {
                fis = new FileInputStream(new File(defaultPath + "/" + location));
                StringBuilder sb = new StringBuilder();
                while (fis.available() > 0) {
                    sb.append((char)fis.read());
                }
                //  sb = new StringBuffer(fis);
                log("eval " + sb.toString());
                clojure.eval(sb.toString());//currently accepts clojure commands, not locations
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //	clojure.eval(Gdx.files.internal(location).readString());
		}
        log("done!");
		return "complete";
	}


}
