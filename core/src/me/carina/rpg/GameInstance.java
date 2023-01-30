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

public class GameInstance extends Game{
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
		client = new ExternalClient();
		server = externalServer;
		client.getWorld().addComponent(new Block(),0,0);
		client.getWorld().addComponent(new Block(),1,0);
		client.getWorld().addComponent(new Block(),0,1);
		setScreen(new WorldScreen(client));
		//((AbstractExternalServer) server).open(18273);
		//((ExternalClient) client).connect("localhost",18273);
	}
}
