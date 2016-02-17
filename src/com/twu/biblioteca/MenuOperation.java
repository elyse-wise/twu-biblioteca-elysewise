package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public abstract class MenuOperation {

    private String trigger;
    private String description;

    public MenuOperation(String trigger, String description) {
        this.trigger = trigger;
        this.description = description;
    }

    String getTrigger() {
        return trigger;
    }

    String getDescription() {
        return description;
    }

    Boolean isTriggeredBy(String s) {
        return s.equalsIgnoreCase(trigger);
    }

    abstract void execute(Library library, Console console);

}
