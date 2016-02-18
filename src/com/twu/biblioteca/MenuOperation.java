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

    public String getTrigger() {
        return trigger;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isTriggeredBy(String s) {
        return s.equalsIgnoreCase(trigger);
    }

    public Boolean needsLogin() {
        return false;
    }

    abstract void execute(Library library, Console console);

}
