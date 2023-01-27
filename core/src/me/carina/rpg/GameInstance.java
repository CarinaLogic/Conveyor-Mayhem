package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import me.carina.rpg.client.ExternalClient;
import me.carina.rpg.server.AbstractExternalServer;

public class GameInstance extends ApplicationAdapter {
	ExternalClient client;
	AbstractExternalServer server;
	public GameInstance(AbstractExternalServer server){
		this.server = server;
	}
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		server.open(18273);
		client = new ExternalClient();
		client.connect("localhost",18273);
	}

	@Override
	public void render () {
		if (client.isConnected()){
			Gdx.app.debug("Main","Client has reported active connection");
			client.send("LMAOOOOOO");
			server.send("Hello world!");
		}
	}
	
	@Override
	public void dispose () {

	}
}
