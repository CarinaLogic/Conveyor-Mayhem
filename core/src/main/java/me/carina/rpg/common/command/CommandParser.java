package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.Annotation;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.common.command.commands.*;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

public class CommandParser {
    Map<String,Object> data = new Map<>();
    Array<Command> commands = new Array<>();
    Array<Script> scripts = new Array<>();
    int cursor = 0;
    Script immediateRunningScript = null;
    public CommandParser(){
        commands.add(new AnimationCommand());
        commands.add(new ArrayCommand());
        commands.add(new BooleanCommand());
        commands.add(new BranchCommand());
        commands.add(new DataCommand());
        commands.add(new IOCommand());
        commands.add(new MathCommand());
        commands.add(new ResourceCommand());
        commands.add(new WaitCommand());
    }
    public void tick(){
        while (cursor < scripts.size) {
            if (scripts.get(cursor).tick()) {
                scripts.removeIndex(cursor);
            } else {
                cursor++;
            }
        }
        cursor = 0;
    }

    public synchronized void immediateRun(Script script){
        immediateRunningScript = script;
        script.tick();
        immediateRunningScript = null;
    }

    public Script getScript(){
        if (immediateRunningScript != null) return immediateRunningScript;
        return scripts.get(cursor);
    }

    public void queueScript(Script script){
        scripts.add(script);
    }

    public Object parseCommand(String command){
        //Surrounded by () = InlineCommand
        //Quoted string = String
        //Starts with $ = CommandData that is registered on its name
        //Starts with @ = CommandLabel
        //Number~Number = DataRange
        //Number = double
        //true/false = boolean
        //Anything else = Argument
        //if @ is at the beginning, treat it as a label and ignore
        if (command.startsWith("@")) return null;
        Array<Object> args = new Array<>();
        int argBegin = 0;
        int argEnd = 0;
        while (true){
            if (argEnd >= command.length()){
                if (argBegin == argEnd) break;
                args.add(parseArg(command.substring(argBegin,argEnd)));
                break;
            }
            if (command.charAt(argEnd) == ' '){
                args.add(parseArg(command.substring(argBegin,argEnd)));
                argBegin = argEnd + 1;
            }
            else if (command.charAt(argEnd) == '('){
                int i = argEnd + 1;
                int bracket = 0;
                while (true){
                    if (i >= command.length()){
                        throw new CommandException(command,argEnd, CommandException.ExceptionType.bracket_no_match);
                    }
                    if (command.charAt(i) == ')' && bracket == 0){
                        args.add(new InlineCommand(command.substring(argEnd+1,i),this));
                        break;
                    }
                    //bracket != 0
                    if (command.charAt(i) == ')'){
                        bracket--;
                    }
                    if (command.charAt(i) == '('){
                        bracket++;
                    }
                    i++;
                }
                argBegin = i + 2;
                argEnd = i + 1;
            }
            else if (command.charAt(argEnd) == '"'){
                int i = argEnd;
                while (true){
                    if (i >= command.length()){
                        throw new CommandException(command,argEnd, CommandException.ExceptionType.quotation_no_match);
                    }
                    if (command.charAt(i) == '"'){
                        args.add(command.substring(argEnd+1,i));
                        break;
                    }
                    i++;
                }
                argBegin = i + 2;
                argEnd = i + 1;
            }
            else if (command.charAt(argEnd) == '\''){
                int i = argEnd;
                while (true){
                    if (i >= command.length()){
                        throw new CommandException(command,argEnd, CommandException.ExceptionType.quotation_no_match);
                    }
                    if (command.charAt(i) == '\''){
                        args.add(command.substring(argEnd+1,i));
                        break;
                    }
                    i++;
                }
                argBegin = i + 2;
                argEnd = i + 1;
            }
            argEnd++;
        }
        try{
            return parseArgArray(args);
        } catch (CommandException e){
            throw new CommandException(command,argEnd,e.type);
        }
    }

    public Object parseArgArray(Array<Object> args){
        //Nested hell
        CommandExecutionPolicy policy = getScript().executionPolicy;
        for (Command command : commands) {
            if (policy.isAllowed(command)) {
                for (Method method : ClassReflection.getDeclaredMethods(command.getClass())) {
                    Annotation annotation = method.getDeclaredAnnotation(CommandFunction.class);
                    if (annotation != null) {
                        Array<String> names = new Array<>();
                        if (!annotation.getAnnotation(CommandFunction.class).suppressOriginalName()){
                            names.add(method.getName());
                        }
                        names.addAll(annotation.getAnnotation(CommandFunction.class).altNames());
                        @SuppressWarnings("rawtypes")
                        Class[] paramTypes = method.getParameterTypes();
                        for (String name : names) {
                            String[] dclArgs = name.split("_");
                            int keys = 0;
                            for (String dclArg : dclArgs) {
                                if (!dclArg.equals("$")) keys++;
                            }
                            if (args.size != keys + paramTypes.length) continue;
                            boolean success = true;
                            Object[] passedArgs = new Object[paramTypes.length];
                            int methodArgIndex = 0;
                                for (int i = 0; i < args.size; i++) {
                                    Object arg = args.get(i);
                                    if (i < dclArgs.length && !dclArgs[i].equals("$")) {
                                        //Argument comparison
                                        if (arg instanceof Argument) {
                                            //Don't have to pass arg to method
                                            if (!((Argument) arg).getName().equals(dclArgs[i])) {
                                                success = false;
                                                break;
                                            }
                                        } else {
                                            success = false;
                                            break;
                                        }
                                    } else {
                                        //Object comparison
                                        Object data = arg;
                                        boolean given = false;
                                        if (data == null && ClassReflection.isAssignableFrom(paramTypes[methodArgIndex], Object.class)) {
                                            passedArgs[methodArgIndex] = null;
                                            methodArgIndex++;
                                            given = true;
                                        }
                                        if (arg instanceof InlineCommand) {
                                            //if method wants InlineCommand, just give it unmodified
                                            if (ClassReflection.isAssignableFrom(InlineCommand.class,paramTypes[methodArgIndex])) {
                                                passedArgs[methodArgIndex] = data;
                                                methodArgIndex++;
                                                given = true;
                                            }
                                            //else, parse the inline command and use it as data
                                            else data = parseCommand(((InlineCommand) arg).command);
                                        } else if (arg instanceof Argument) {
                                            //if method wants String, give it as a string
                                            if (ClassReflection.isAssignableFrom(String.class,paramTypes[methodArgIndex])) {
                                                passedArgs[methodArgIndex] = ((Argument) arg).name;
                                                methodArgIndex++;
                                                given = true;
                                            }
                                            //if method wants enum,
                                            else if (ClassReflection.isEnum(paramTypes[methodArgIndex])){
                                                Object[] constants = ClassReflection.getEnumConstants(paramTypes[methodArgIndex]);
                                                for (Object constant : constants) {
                                                    if (((Enum<?>) constant).name().equals(((Argument) arg).name)){
                                                        passedArgs[methodArgIndex] = constant;
                                                        methodArgIndex++;
                                                        given = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        } else if (arg instanceof CommandData) {
                                            //if method wants CommandData, just give it unmodified
                                            if (ClassReflection.isAssignableFrom(CommandData.class, paramTypes[methodArgIndex])) {
                                                passedArgs[methodArgIndex] = data;
                                                methodArgIndex++;
                                                given = true;
                                            }
                                            //else, extract the value to data
                                            else data = ((CommandData) arg).getValue();
                                        }
                                        if (!given && ClassReflection.isInstance(paramTypes[methodArgIndex], data)) {
                                            passedArgs[methodArgIndex] = data;
                                            methodArgIndex++;
                                        } else if (!given) {
                                            success = false;
                                            break;
                                        }
                                    }
                                }
                                if (success) {
                                    try {
                                        command.setParser(this);
                                        if (method.getReturnType().equals(Void.TYPE)) {
                                            method.invoke(command, passedArgs);
                                            return null;
                                        }
                                        return method.invoke(command, passedArgs);
                                    } catch (ReflectionException ignored) {
                                }
                            }
                        }
                    }
                }
            }
            else if (policy.isSkipped(command)){
                //NOOP
                return null;
            }
            else if (policy.isBlocked(command)){
                throw new CommandException(CommandException.ExceptionType.command_blocked);
            }
        }
        throw new CommandException(CommandException.ExceptionType.command_not_found);
    }

    public Object parseArg(String arg){
        if (arg.equals("null")) return null;
        if (arg.equals("true")) return true;
        if (arg.equals("false")) return false;
        if (arg.startsWith("$")){
            String regName = arg.substring(1);
            return new CommandData(this,regName,data.get(regName));
        }
        if (arg.contains("~")){
            String[] num = arg.split("~");
            if (num.length == 2){
                try {
                    if (num[0].isEmpty()){
                        return DataRange.max(Double.valueOf(num[1]).floatValue());
                    }
                    else if (num[1].isEmpty()){
                        return DataRange.min(Double.valueOf(num[0]).floatValue());
                    }
                    else {
                        return DataRange.minMax(Double.valueOf(num[0]).floatValue(),Double.valueOf(num[1]).floatValue());
                    }
                } catch (Exception ignored){}
            }
        }
        if (arg.startsWith("@")){
            String labelName = arg.substring(1);
            return new CommandLabel(labelName);
        }
        try {
            return Double.valueOf(arg);
        } catch (Exception ignored){}
        return new Argument(arg);

    }

    public <T> void setData(String name, T value){
        data.put(name,value);
    }

    public <T> T getData(String name, Class<T> type){
        Object o = data.get(name);
        if (ClassReflection.isInstance(type,o)){
            //noinspection unchecked
            return (T) o;
        }
        throw new CommandException(CommandException.ExceptionType.type_mismatch);
    }
    public <T> Array<T> getDataAsArray(String name, Class<T> type){
        Object o = data.get(name);
        try {
            //noinspection unchecked
            return (Array<T>) o;
        }catch (Exception ignored){}
        throw new CommandException(CommandException.ExceptionType.type_mismatch);
    }

    public static class Argument{
        final String name;
        public Argument(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
