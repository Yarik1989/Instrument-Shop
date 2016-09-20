package com.epam.chuikov.storage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.epam.chuikov.entity.CaptchaKey;

public class CaptchaStorages {
	public static ConcurrentHashMap<CaptchaKey, String> globalMap = new ConcurrentHashMap<>();
	public static long default_timeout;
	public static AtomicInteger integer = new AtomicInteger(1);

}
