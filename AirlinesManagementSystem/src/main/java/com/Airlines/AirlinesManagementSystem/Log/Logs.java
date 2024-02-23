package com.Airlines.AirlinesManagementSystem.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {

	 private static final Logger LOGGER = Logger.getLogger(Logs.class.getName());
	 
	 public static void logInfo(String message) {
	        LOGGER.log(Level.INFO, message);
	    }

	    // Log a message at the WARNING level
	    public static void logWarning(String message) {
	        LOGGER.log(Level.WARNING, message);
	    }

	    // Log a message at the SEVERE level
	    public static void logSevere(String message) {
	        LOGGER.log(Level.SEVERE, message);
	    }

	    // Log a message at the FINE level
	    public static void logFine(String message) {
	        LOGGER.log(Level.FINE, message);
	    }
	 
}
