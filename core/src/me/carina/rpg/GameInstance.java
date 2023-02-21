package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Logger;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.InternalClient;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.InternalServer;
import me.carina.rpg.server.Server;

public class GameInstance extends ApplicationAdapter{
	Client client;
	Server server;
	AbstractExternalServer externalServer;
	public GameInstance(AbstractExternalServer server){
		this.externalServer = server;
	}
	@Override
	public void create () {
		Box2D.init();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		client = new InternalClient();
		server = new InternalServer((InternalClient) client);
		client.create();
		server.create();
		client.getLogger().setLevel(Logger.DEBUG);
		server.getLogger().setLevel(Logger.DEBUG);
		//((AbstractExternalServer) server).open(18273);
		//((ExternalClient) client).connect("localhost",18273);
	}

	@Override
	public void render() {
		server.render();
		client.render();
	}

	@Override
	public void resize(int width, int height) {
		client.resize(width, height);
	}

	@Override
	public void pause() {
		client.pause();
		server.pause();
	}

	@Override
	public void resume() {
		client.resume();
		server.resume();
	}

	@Override
	public void dispose() {
		client.dispose();
		server.dispose();
	}
}
