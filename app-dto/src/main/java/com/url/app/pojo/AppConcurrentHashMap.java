package com.url.app.pojo;

import java.util.concurrent.ConcurrentHashMap;

public class AppConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 1L;

	@Override
	public V put(K key, V value) {
		if (key == null || value == null) {
			return null;
		}

		return super.put(key, value);
	}
}