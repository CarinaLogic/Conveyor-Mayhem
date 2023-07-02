package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

import java.util.Arrays;

public class CommandParser {
    Map<String,Object> values = new Map<>();
    Map<String,Class<? extends Command>> commandClass = new Map<>();
    @SafeVarargs
    public CommandParser(Class<? extends Command>... cls){
        for (Class<? extends Command> cl : cls) {
            try {
                Command c = ClassReflection.newInstance(cl);
                commandClass.put(c.getPrefix(),cl);
            } catch (ReflectionException e) {
                Game.getInstance().getLogger().error("Could not register command ("+ClassReflection.getSimpleName(cl)+")");
            }
        }
    }
    public void run(String... commands){
        for (String command : commands) {
            String[] args = command.split(" ");
            if (args.length == 0){
                Game.getInstance().getLogger().error("Command too short ("+command+")");
                return;
            }
            Class<? extends Command> c = commandClass.get(args[0]);
            if (c == null){
                Game.getInstance().getLogger().error("Could not find command "+args[0]);
                return;
            }
            Command cmd;
            try {
                cmd = ClassReflection.newInstance(c);
            } catch (ReflectionException e) {
                Game.getInstance().getLogger().error("Could not init command ("+ClassReflection.getSimpleName(c)+")");
                return;
            }
            try{
                cmd.run(this,Arrays.copyOfRange(args,1,args.length));
            } catch (Exception e){
                Game.getInstance().getLogger().error("Error parsing command "+command+" ("+e.getMessage()+")");
            }
        }
    }
    public void setValue(String tag, Object value){
        if (tag == null || value == null) throw new IllegalArgumentException();
        values.put(tag, value);
    }
    public Object getValue(String tag){
        if (!tag.startsWith("$")) throw new IllegalArgumentException();
        return values.get(tag);
    }
    public int getInt(String arg){
        if (arg.startsWith("$")) return (int) getValue(arg);
        return Integer.getInteger(arg);
    }
    public String getLiteral(String target, String arg){
        if (!target.equals(arg)) throw new IllegalArgumentException();
        return target;
    }
    public String getString(String arg){
        if (arg.startsWith("$")) return (String) getValue(arg);
        return arg;
    }

}
