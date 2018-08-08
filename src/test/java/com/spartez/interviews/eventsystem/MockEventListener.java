package com.spartez.interviews.eventsystem;

/**
 * An implementation of EventListener used for tests.
 */
class MockEventListener implements EventListener
{
    private final Class[] handledClasses;
    private boolean called;
    public int count;

    public MockEventListener(Class[] handledClasses)
    {
        this.handledClasses = handledClasses;
    }

    @Override
    public void handleEvent(Event event)
    {
        called = true;
        count++;
    }

    public void resetCalled()
    {
        called = false;
    }

    public boolean isCalled()
    {
        return called;
    }

    @Override
    public Class[] getHandledEventClasses()
    {
        return handledClasses;
    }
}