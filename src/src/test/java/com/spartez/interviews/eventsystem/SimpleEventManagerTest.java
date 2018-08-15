package com.spartez.interviews.eventsystem;

import com.spartez.interviews.eventsystem.impl.SimpleEventManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Simplistic implementation for project structure illustration only.
 *
 * <p>This is <b>not</b> the code you will be working on.
 *
 * <p>When the test starts you will receive a different copy of the project that will contain a much more advanced
 * implementation of the EventManager and a more complete set of tests reflecting the contract of EventListener.
 */
public class SimpleEventManagerTest
{
    private EventManager eventManager = new SimpleEventManager();

    @Test
    public void testRegisterListenerAndPublishEvent()
    {
        final SomeEventListener someEventListener = new SomeEventListener();
        eventManager.registerListener("some.key", someEventListener);

        eventManager.publishEvent(new SomeEventClass());
        assertEquals(1, someEventListener.count);
    }

    @Test
    public void testUnregisterListener()
    {
        final SomeEventListener someEventListener = new SomeEventListener();
        eventManager.registerListener("some.key", someEventListener);
        eventManager.unregisterListener("some.key");

        eventManager.publishEvent(new SomeEventClass());
        assertEquals(0, someEventListener.count);
    }

}

class SomeEventClass implements Event {
}

class SomeEventListener implements EventListener {
    int count;

    @Override
    public void handleEvent(Event event) {
        count++;
    }

    @Override
    public Class[] getHandledEventClasses() {
        return new Class[] { SomeEventClass.class };
    }
}