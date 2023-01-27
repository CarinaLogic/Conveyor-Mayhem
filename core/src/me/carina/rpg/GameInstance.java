package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.ExternalClient;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.Server;

public class GameInstance extends ApplicationAdapter {
	Client client;
	Server server;
	AbstractExternalServer externalServer;
	public GameInstance(AbstractExternalServer server){
		this.externalServer = server;
	}
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

	}
}
