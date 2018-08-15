package com.spartez.interviews.eventsystem;

/**
 * A simple event listener.
 */
public interface EventListener
{
    /**
     * Perform some action as a response to an Event.
     *
     * @param event an event published using {@link EventManager#publishEvent(Event)}
     */
    void handleEvent(Event event);

    /**
     * Optionally limit which event classes this listener is interested in.
     * 
     * <p>The EventManager performs rudimentary filtering of events by their class. If
     * you want to receive only a subset of events passing through the system, return
     * an array of the Classes you wish to listen for from this method.
     *
     * <p>For the sake of efficiency, only exact class matches are performed. Sub/superclassing
     * is not taken into account.
     *
     * @return An array of the event classes that this event listener is only interested in,
     *         or an empty array if the listener should receive all events without filtering.
     *         <b>Must not</b> return null.
     */
    Class[] getHandledEventClasses();
}
