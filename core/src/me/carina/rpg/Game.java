package me.carina.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.Logger;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.InternalClient;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.command.Script;
import me.carina.rpg.packets.connection.C2SInternalConnection;
import me.carina.rpg.packets.connection.S2CInternalConnection;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.InternalServer;
import me.carina.rpg.server.Server;

public class Game extends ApplicationAdapter{
	static Game game;
	Client client;
	Server server;
	Platform platform;
	AbstractGameInstance gameInstance;
	public Game(Platform platform){
		this.platform = platform;
		Game.game = this;
		Collections.allocateIterators = true;
	}
	@Override
	public void create () {
		//Due to threading not supported on GWT, dirty solution
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		client = new InternalClient();
		server = new InternalServer();
//		client = new ExternalClient();
//		server = externalServer;
		client.getLogger().setLevel(Logger.DEBUG);
		server.getLogger().setLevel(Logger.DEBUG);
		//getInstance() should NEVER be called until now
		gameInstance = client;
		client.create();
		gameInstance = server;
		server.create();
		gameInstance = client;
		client.addConnection(new C2SInternalConnection((InternalClient) client, (InternalServer) server));
		gameInstance = server;
		server.addConnection(new S2CInternalConnection((InternalServer) server, (InternalClient) client));
//		server.open(18273);
//		client.addConnection(((ExternalClient) client).connect("localhost",18273));
		server.getCommandParser().queueScript(new Script(
				"$array = (range 10)",
				"<- $array",
				"$array <- 3.5",
				"100 -> $array",
				"for $i in $array @fin",
				"print $i",
				"wait 1",
				"@fin",
				"print finished"
		));
	}

	@Override
	public void render() {
		gameInstance = server;
		server.render();
		gameInstance = client;
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
	public static AbstractGameInstance getInstance(){
		return game.gameInstance;
	}
	public static Client getClient(){
		return (Client) game.gameInstance;
	}
	public static Server getServer(){
		return (Server) game.gameInstance;
	}

}
