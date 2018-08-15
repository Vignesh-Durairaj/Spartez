package com.spartez.interviews.eventsystem.impl;


import com.spartez.interviews.eventsystem.Event;
import com.spartez.interviews.eventsystem.EventListener;
import com.spartez.interviews.eventsystem.EventManager;

/**
 * Simplistic implementation for project structure illustration only.
 *
 * <p>This is <b>not</b> the code you will be working on.
 *
 * <p>When the test starts you will receive a different copy of the project that will contain a much more advanced
 * implementation of the EventManager.
 * It will still be relatively simple (under 100 lines of code in the class) but in contrast to this example class
 * it will actually implement the contract.
 *
 */
public class SimpleEventManager implements EventManager {

	private EventListener listener;

	@Override
	public void publishEvent(Event event) {
		if (listener != null) {
			listener.handleEvent(event);
		}
	}

	@Override
	public void registerListener(String listenerKey, EventListener listener) {
		this.listener = listener;
	}

	@Override
	public void unregisterListener(String listenerKey) {
		listener = null;
	}
}
