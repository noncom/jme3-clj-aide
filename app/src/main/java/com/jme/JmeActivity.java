package com.jme;
import com.jme3.app.*;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;
import android.content.pm.*;

public class JmeActivity extends AndroidHarness {

    int k = 9;

	public JmeActivity() {
        com.ncm.krmt.main.Game.setActivity(this);
		appClass = "com.ncm.main.Game";
		eglConfigType = ConfigType.BEST;

		exitDialogTitle = "Quit?";
		exitDialogMessage = "Rly?";

		screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
	}
}
