package me.carina.rpg;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.czyzby.websocket.CommonWebSockets;
import me.carina.rpg.GameInstance;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		CommonWebSockets.initiate();
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GameInstance(new CommonExternalServer()), config);
	}
}
