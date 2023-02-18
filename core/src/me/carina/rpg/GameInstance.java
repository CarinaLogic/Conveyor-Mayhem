package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.ExternalClient;
import me.carina.rpg.client.InternalClient;
import me.carina.rpg.client.scenes.WorldScreen;
import me.carina.rpg.common.world.Block;
import me.carina.rpg.packets.C2SMessagePacket;
import me.carina.rpg.packets.S2CMessagePacket;
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
