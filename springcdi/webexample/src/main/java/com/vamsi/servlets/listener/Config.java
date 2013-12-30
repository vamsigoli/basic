package com.vamsi.servlets.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.vamsi.spring.jpa.types.ParkingLocation;

@WebListener
public class Config implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("parkingLotLocations", ParkingLocation.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP
    }

}