package com.mycompany.myapp2;
import com.jme3.app.*;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;
import android.content.pm.*;
import com.ncm.main.*;

public class JmeActivity extends AndroidHarness
{
    
    
	public JmeActivity(){
        com.ncm.main.Game.setActivity(this);
		appClass = "com.ncm.main.Game";
		eglConfigType = ConfigType.BEST;
		
		exitDialogTitle = "Quit?";
		exitDialogMessage = "Rly?";
		
		screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
	}
}
