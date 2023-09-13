package me.carina.conveyor;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.Logger;
import me.carina.conveyor.client.Client;
import me.carina.conveyor.client.InternalClient;
import me.carina.conveyor.common.AbstractGameInstance;
import me.carina.conveyor.common.command.Script;
import me.carina.conveyor.packets.connection.C2SInternalConnection;
import me.carina.conveyor.packets.connection.S2CInternalConnection;
import me.carina.conveyor.server.InternalServer;
import me.carina.conveyor.server.Server;

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
