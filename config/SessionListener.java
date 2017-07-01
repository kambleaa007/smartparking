package com.techmahindra.smartparking.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(15 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		//Nothing to do when session is destroyed
	}

}
