package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.ExternalClient;
import me.carina.rpg.client.InternalClient;
import me.carina.rpg.packets.C2SMessagePacket;
import me.carina.rpg.packets.S2CMessagePacket;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.InternalServer;
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
		client = new ExternalClient();
		server = externalServer;
		((AbstractExternalServer) server).open(18273);
		((ExternalClient) client).connect("localhost",18273);
	}

	@Override
	public void render () {
		client.send(new C2SMessagePacket("trololol"));
		server.send(new S2CMessagePacket("lmaoooo"));
	}
	
	@Override
	public void dispose () {
		((ExternalClient) client).disconnect("bye");
		((AbstractExternalServer) server).close("bye");
	}
}
