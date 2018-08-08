package com.spartez.interviews.eventsystem;

import com.spartez.interviews.eventsystem.mockevents.BaseTestEvent;
import com.spartez.interviews.eventsystem.mockevents.SpecificTestEvent;
import com.spartez.interviews.eventsystem.impl.DefaultEventManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultEventManagerTest
{
    private EventManager eventManager = new DefaultEventManager();

    @Test
    public void testPublishNullEvent()
    {
        eventManager.publishEvent(null);
    }

    @Test
    public void testRegisterListenerAndPublishEvent()
    {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SpecificTestEvent.class});
        eventManager.registerListener("some.key", mockEventListener);
        eventManager.publishEvent(new SpecificTestEvent());
        assertTrue(mockEventListener.isCalled());
    }

    @Test
    public void testListenerWithoutMatchingEventClass()
    {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{BaseTestEvent.class});
        eventManager.registerListener("some.key", mockEventListener);
        eventManager.publishEvent(new SpecificTestEvent());
        assertFalse(mockEventListener.isCalled());
    }

    @Test
    public void testUnregisterListener()
    {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SpecificTestEvent.class});
        MockEventListener mockEventListener2 = new MockEventListener(new Class[]{SpecificTestEvent.class});

        eventManager.registerListener("some.key", mockEventListener);
        eventManager.registerListener("another.key", mockEventListener2);
        eventManager.unregisterListener("some.key");

        eventManager.publishEvent(new SpecificTestEvent());
        assertFalse(mockEventListener.isCalled());
        assertTrue(mockEventListener2.isCalled());
    }


    /**
     * Check that registering and unregistering listeners behaves properly.
     */
    @Test
    public void testRemoveNonexistentListener()
    {
        DefaultEventManager dem = (DefaultEventManager)eventManager;
        assertEquals(0, dem.getListeners().size());
        eventManager.registerListener("some.key", new MockEventListener(new Class[]{SpecificTestEvent.class}));
        assertEquals(1, dem.getListeners().size());
        eventManager.unregisterListener("this.key.is.not.registered");
        assertEquals(1, dem.getListeners().size());
        eventManager.unregisterListener("some.key");
        assertEquals(0, dem.getListeners().size());
    }

    /**
     * Registering duplicate keys on different listeners should only fire the most recently added.
     */
    @Test
    public void testDuplicateKeysForListeners()
    {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SpecificTestEvent.class});
        MockEventListener mockEventListener2 = new MockEventListener(new Class[]{SpecificTestEvent.class});

        eventManager.registerListener("some.key", mockEventListener);
        eventManager.registerListener("some.key", mockEventListener2);

        eventManager.publishEvent(new SpecificTestEvent());

        assertTrue(mockEventListener2.isCalled());
        assertFalse(mockEventListener.isCalled());

        mockEventListener.resetCalled();
        mockEventListener2.resetCalled();

        eventManager.unregisterListener("some.key");
        eventManager.publishEvent(new SpecificTestEvent());

        assertFalse(mockEventListener2.isCalled());
        assertFalse(mockEventListener.isCalled());
    }

    /**
     * Attempting to register a null with a valid key should result in an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddValidKeyWithNullListener() {
        eventManager.registerListener("bogus.key", null);
    }
    
    @Test
    public void testUnfilteredEventListening() {
    	SpecificTestEvent testEventOne = new SpecificTestEvent();
    	MockEventListener allEventsListener = new MockEventListener(new Class[] {});
    	MockEventListener allEventsListener2 = new MockEventListener(new Class[] {});
    	
    	eventManager.registerListener("key.one", allEventsListener);
    	eventManager.registerListener("key.two", allEventsListener2);
    	
    	eventManager.publishEvent(testEventOne);
    	assertTrue(allEventsListener.isCalled());
    	assertTrue(allEventsListener2.isCalled());
    	
    }
}
