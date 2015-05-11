package com.ncm.krmt.main;
import com.jme3.input.controls.*;
import com.jme3.input.event.*;
import com.jme3.input.event.TouchEvent.Type;
import clojure.lang.*;

public class TouchInputListener implements TouchListener
{
    
    IFn f;
    
    public TouchInputListener(IFn f){
        this.f = f;
    }

    @Override
    public void onTouch(String mapping, TouchEvent event, float tpf) {
        f.invoke(mapping, event, tpf);
   //     TouchEvent.Type t = event.getType();
   //     if(t == Type.DOWN){
            
   //   }
    }

}
