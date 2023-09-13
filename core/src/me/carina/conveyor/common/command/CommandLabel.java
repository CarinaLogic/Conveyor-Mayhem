package me.carina.conveyor.common.command;

public class CommandLabel {
    String labelName;
    public CommandLabel(String name){
        this.labelName = name;
    }

    public String getLabelName() {
        return labelName;
    }

    public boolean matches(String command){
        if (!command.startsWith("@")) return false;
        return command.substring(1).equals(labelName);
    }
}
