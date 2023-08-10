package me.carina.rpg.common.command;

public class CommandException extends RuntimeException{
    String cmd;
    int index;
    ExceptionType type;

    public CommandException(ExceptionType type){
        this.type = type;
    }

    public CommandException(String cmd, int index, ExceptionType type){
        this.cmd = cmd;
        this.index = index;
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (cmd == null) return "Command exception of "+ type.toString();
        if (index >= cmd.length()) return "Command exception of "+ type.toString() + " upon parsing \"" + cmd + "\"";
        return "Command exception of "+ type.toString() + " upon parsing \"" + cmd + "\" at index " + index + " \""
                + cmd.charAt(index) + "\"";
    }

    public enum ExceptionType{
        bracket_no_match, quotation_no_match, command_not_found, zero_division, type_mismatch, label_not_found
    }
}
