package com.ncm.main;

import clojure.lang.*;
import java.io.StringReader;

public class ClojureF
{
	
	class FakeFn extends AFn{
		@Override
		public Object invoke(Object o, Object oo){
			return fakePath;
		}
	}
	
	class FakeSeq extends ASeq{
		public ISeq next(){return null;}
		public ISeq first(){return null;}
		public Obj withMeta(IPersistentMap m){return null;}
	}
	
	RT rt = new RT();
	String fakePath = "/mnt/sdcard/_Storage/programming/clojure/temp-compile-path";
	String fake = "/mnt/sdcard/_Storage/programming/clojure/fake.clj";
	String nsf = "NO_SOURCE_FILE";
	
	public Object eval(String s){
		RT.var("clojure.core", "*compile-path*").alterRoot(new FakeFn(), new FakeSeq());
		Object res = clojure.lang.Compiler.load(new StringReader(s), fake, nsf);
		return res;
	}
}
