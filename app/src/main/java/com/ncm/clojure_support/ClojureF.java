package com.ncm.clojure_support;

import clojure.lang.*;
import java.io.StringReader;
import java.net.*;
import android.util.*;

public class ClojureF {

	class FakeFn extends AFn {
		@Override
		public Object invoke(Object o, Object oo) {
			return fakePath;
		}
	}

	class FakeSeq extends ASeq {
		public ISeq next() {return null;}
		public ISeq first() {return null;}
		public Obj withMeta(IPersistentMap m) {return null;}
	}

	RT rt = new RT();
	String fakePath = "/mnt/sdcard/_Storage/programming/clojure/temp-compile-path";
	String fake = "/mnt/sdcard/_Storage/programming/clojure/fake.clj";
	String nsf = "NO_SOURCE_FILE";
    
    public void addURL(String url){
        try {
            rt.addURL(url);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public RT getRT() {
        return rt;
    }

	public Object eval(String s) {
        Log.d("_-----------==== ","baseloader=" + rt.baseLoader());
        Log.d("====------------","compiler loader=" + clojure.lang.Compiler.LOADER.getRawRoot());
		RT.var("clojure.core", "*compile-path*").alterRoot(new FakeFn(), new FakeSeq());
		Object res = clojure.lang.Compiler.load(new StringReader(s), fake, nsf);
		return res;
	}
}
