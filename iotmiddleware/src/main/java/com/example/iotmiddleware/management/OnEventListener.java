package com.example.iotmiddleware.management;

public interface OnEventListener {
	void onAttributeSet(String key, String value);
	void onAttributeUnset(String key);
}
