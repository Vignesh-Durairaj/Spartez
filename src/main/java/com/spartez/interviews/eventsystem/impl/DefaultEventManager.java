package com.spartez.interviews.eventsystem.impl;

import com.spartez.interviews.eventsystem.EventManager;
import com.spartez.interviews.eventsystem.Event;
import com.spartez.interviews.eventsystem.EventListener;

import java.util.*;

/**
 * Manages the firing and receiving of events.
 *
 * <p>Any event passed to {@link #publishEvent} will be passed through to "interested" listeners.
 *
 * <p>Event listeners can register to receive events via
 * {@link #registerListener(String, EventListener)}
 */
public class DefaultEventManager implements EventManager
{
    private Map<String, EventListener> listeners = new HashMap<>();
    private Map<Class<?>, List<EventListener>> listenersByClass = new HashMap<>();

    @Override
    public void publishEvent(Event event)
    {
        if (event == null)
        {
            System.err.println("Null event fired?");
            return;
        }

        sendEventTo(event, calculateListeners(event.getClass()));
    }

    private Collection<EventListener> calculateListeners(Class eventClass)
    {
    	Collection<EventListener> allListeners = new ArrayList<>();
    	
    	// Adding listeners monitoring all the events without any filter
    	if (listenersByClass.get(null) != null)
    		allListeners.addAll(listenersByClass.get(null));
    	
    	// Adding listeners monitoring only the target event class
    	if (listenersByClass.get(eventClass) != null) 
    		allListeners.addAll(listenersByClass.get(eventClass));
        
    	return allListeners;
    }

    @Override
    public void registerListener(String listenerKey, EventListener listener)
    {
        if (listenerKey == null || listenerKey.equals(""))
            throw new IllegalArgumentException("Key for the listener must not be null: " + listenerKey);

        if (listener == null)
            throw new IllegalArgumentException("The listener must not be null");

        if (listeners.containsKey(listenerKey))
            unregisterListener(listenerKey);

        Class[] classes = listener.getHandledEventClasses();

        // Changes made to add a listener monitoring all the events without filter, with NULL as a key
        // Since event class was unknown while registering
        if (classes.length == 0) {
        	addToListenerList(null, listener);
        } else {
        	for (final Class aClass : classes)
            {
                addToListenerList(aClass, listener);
            }
        }

        listeners.put(listenerKey, listener);
    }

    @Override
    public void unregisterListener(String listenerKey)
    {
        EventListener listener = listeners.get(listenerKey);

        for (List<EventListener> list : listenersByClass.values())
        {
            list.remove(listener);
        }

        listeners.remove(listenerKey);
    }

    private void sendEventTo(Event event, Collection<EventListener> listeners)
    {
        if (listeners == null || listeners.size() == 0)
            return;

        for (EventListener eventListener : listeners) {
            eventListener.handleEvent(event);
        }
    }

    private void addToListenerList(Class aClass, EventListener listener)
    {
        if (!listenersByClass.containsKey(aClass))
            listenersByClass.put(aClass, new ArrayList<>());

        listenersByClass.get(aClass).add(listener);
    }

    public Map<String, EventListener> getListeners()
    {
        return listeners;
    }
}
