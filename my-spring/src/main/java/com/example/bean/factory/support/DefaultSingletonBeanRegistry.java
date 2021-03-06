package com.example.bean.factory.support;

import com.example.bean.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64);

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		Object oldObject = singletonObjects.get(beanName);
		if (oldObject != null) {
			throw new IllegalStateException("Could not register object [" + singletonObject + "]"
				+ "under bean name '"+ beanName + "' there is already object [" + oldObject + "]");
		}
		this.singletonObjects.put(beanName, singletonObject);
	}

	@Override
	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}
}
