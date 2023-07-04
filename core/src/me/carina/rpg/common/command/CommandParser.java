package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

import java.util.Arrays;

public class CommandParser {
    Map<String,Object> values = new Map<>();
    Map<String,Integer> labels = new Map<>();
    Map<String,Class<? extends Command>> commandClass = new Map<>();
    Queue<String> commandQueue = new Queue<>();
    Array<String> currentCommands = new Array<>();
    int cursor = 0;
    boolean isInit = true;
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
        if (commands.length == 0) return;
        StringBuilder c = new StringBuilder();
        for (String command : commands) {
            if (command.endsWith("\n")) command = command.replaceFirst("\\n$","");
            c.append(command.trim());
            c.append("\n");
        }
        commandQueue.addLast(c.toString());
    }
    public void tick() throws CommandException {
        if (currentCommands.isEmpty() && commandQueue.isEmpty()) return;
        while (true){
            if (currentCommands.isEmpty() && !commandQueue.isEmpty()){
                reset();
                if (!commandQueue.isEmpty()){
                    currentCommands.addAll(commandQueue.first().split("\\n"));
                }
            }
            else if ((cursor < 0 || cursor >= currentCommands.size) && isInit){
                isInit = false;
                cursor = 0;
            }
            else if (cursor < 0 || cursor >= currentCommands.size){
                reset();
                if (!commandQueue.isEmpty()){
                    currentCommands.addAll(commandQueue.first().split("\\n"));
                }
                return;
            }
            String cmd = currentCommands.get(cursor);
            String[] args = cmd.split(" ");
            if (isInit){
                getCommand(cmd).init(this,Arrays.copyOfRange(args,1,args.length));
            }
            else {
                getCommand(cmd).run(this,Arrays.copyOfRange(args,1,args.length));
            }

        }
    }
    public void reset(){
        currentCommands.clear();
        labels.clear();
        cursor = 0;
        isInit = true;
    }
    public Command getCommand(String cmd) throws CommandException {
        String[] args = cmd.split(" ");
        if (args.length == 0){
            Game.getInstance().getLogger().error("Command too short ("+cmd+")");
            throw new CommandException();
        }
        Class<? extends Command> c = commandClass.get(args[0]);
        if (c == null){
            Game.getInstance().getLogger().error("Could not find command "+args[0]);
            throw new CommandException();
        }
        Command command;
        try {
            command = ClassReflection.newInstance(c);
        } catch (ReflectionException e) {
            Game.getInstance().getLogger().error("Could not init command ("+ClassReflection.getSimpleName(c)+")");
            throw new CommandException();
        }
        return command;
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
    public String getString(String arg) {
        if (arg.startsWith("$")) return (String) getValue(arg);
        return arg;
    }
}
