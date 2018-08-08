package com.spartez.interviews.eventsystem;

public interface EventManager
{
    /**
     * Publish an {@link Event} that will be consumed by any listener which has
     * registered to receive it.
     *
     * @param event the event to publish
     */
    void publishEvent(Event event);

    /**
     * Register a listener to receive {@link Event}s. If you register a listener with the
     * same key as an existing listener, the previous listener with that key will be unregistered.
     *
     * @param listenerKey A unique key for this listener
     * @param listener The listener that is being registered
     */
    void registerListener(String listenerKey, EventListener listener);

    /**
     * Unregister the listener so that it will no longer receive events. If no listener has been
     * registered under this key, nothing will happen.
     *
     * @param listenerKey the key under which the listener was registered.
     */
    void unregisterListener(String listenerKey);
}
