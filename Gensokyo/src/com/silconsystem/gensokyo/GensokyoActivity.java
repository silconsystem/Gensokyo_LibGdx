package com.silconsystem.gensokyo;

// import android packs
import android.os.Bundle;

// import gdx packs
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class GensokyoActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
			
	AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGL20 = true;
		config.useAccelerometer = true;
		config.useCompass = false;
				
		initialize(new GensokyoGame(), config);
			
	}
	
}
