package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder {

	private static final StaticLoggerBinder instance = new StaticLoggerBinder();
	private static final LoggerFactory factory = new LoggerFactory();

	public static StaticLoggerBinder getSingleton() {
		return instance;
	}

	@Override
	public ILoggerFactory getLoggerFactory() {
		return factory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return factory.getClass().getName();
	}
}
