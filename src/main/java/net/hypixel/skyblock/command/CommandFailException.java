package net.hypixel.skyblock.command;

public class CommandFailException extends RuntimeException {
    public CommandFailException(String message) {
        super(message);
    }
}