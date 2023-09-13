package me.carina.conveyor;

import com.github.czyzby.websocket.CommonWebSockets;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate{
    @Override
    protected IOSApplication createApplication() {
        CommonWebSockets.initiate();
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new Game(new IOSPlatform()), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}
