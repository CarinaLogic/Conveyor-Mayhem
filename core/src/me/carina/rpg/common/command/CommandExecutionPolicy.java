package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.command.commands.*;
import me.carina.rpg.common.util.Array;

public class CommandExecutionPolicy {
    Array<Class<? extends Command>> allowed = new Array<>();
    Array<Class<? extends Command>> skipped = new Array<>();
    Array<Class<? extends Command>> blocked = new Array<>();
    @SafeVarargs
    public final void allow(Class<? extends Command>... allowedCommands){
        allowed.addAll(allowedCommands);
        skipped.remove(allowedCommands);
        blocked.remove(allowedCommands);
    }
    @SafeVarargs
    public final void skip(Class<? extends Command>... skippedCommands){
        skipped.addAll(skippedCommands);
        allowed.remove(skippedCommands);
        blocked.remove(skippedCommands);
    }
    @SafeVarargs
    public final void block(Class<? extends Command>... blockedCommands){
        blocked.addAll(blockedCommands);
        allowed.remove(blockedCommands);
        skipped.remove(blockedCommands);
    }
    public <T extends Command> boolean isAllowed(T command){
        for (Class<? extends Command> cls : allowed) {
            if (ClassReflection.isInstance(cls,command)) return true;
        }
        return false;
    }
    public <T extends Command> boolean isSkipped(T command){
        for (Class<? extends Command> cls : skipped) {
            if (ClassReflection.isInstance(cls,command)) return true;
        }
        return false;
    }
    public <T extends Command> boolean isBlocked(T command){
        for (Class<? extends Command> cls : blocked) {
            if (ClassReflection.isInstance(cls,command)) return true;
        }
        return false;
    }
    public static CommandExecutionPolicy clientPolicy(){
        CommandExecutionPolicy policy = new CommandExecutionPolicy();
        policy.allow(AnimationCommand.class, ArrayCommand.class, BranchCommand.class, BooleanCommand.class, DataCommand.class,
                IOCommand.class, MathCommand.class, WaitCommand.class);
        return policy;
    }
    public static CommandExecutionPolicy skillEvaluationPolicy(){
        CommandExecutionPolicy policy = new CommandExecutionPolicy();
        policy.allow(ArrayCommand.class, BooleanCommand.class, BranchCommand.class, DataCommand.class,
                IOCommand.class, MathCommand.class);
        policy.skip(AnimationCommand.class, WaitCommand.class);
        return policy;
    }
}
