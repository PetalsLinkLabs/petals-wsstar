package com.ebmwebsourcing.wsstar.topics.datatypes.api.utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestrictedAccessList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger; 	
	
	public RestrictedAccessList(Logger logger) {
		this.logger = logger;
	}
	@Override
	public final boolean add(E newObj){
		this.logger.log(Level.WARNING, "\n\t/!\\ WARNING /!\\\n" +
				"The \"add(...)\" method can not be called directly" +
				" to add new object to the List. Please use the provided" +
				"\"add[ObjectType](...)\" method instead in order to not" +
				"break the model implementation logic. \n\t/!\\ WARNING /!\\\n");
		return false;	
	}
	
}


