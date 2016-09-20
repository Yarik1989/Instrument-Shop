package com.epam.chuikov.service;

import java.util.List;
import java.util.stream.Collectors;

import com.epam.chuikov.entity.CaptchaKey;
import com.epam.chuikov.storage.CaptchaStorages;

public class CaptchaService {
	private String DICT;
	private int CHARS;

	public CaptchaService(String DICT, int CHARS) {
		this.DICT = DICT;
		this.CHARS = CHARS;
	}

	public int insert(String captcha) {
		int id = CaptchaStorages.integer.getAndIncrement();
		CaptchaStorages.globalMap.put(new CaptchaKey(id, CaptchaStorages.default_timeout), captcha);
		return id;
	}

	public String select(Integer id) {
		return CaptchaStorages.globalMap.get(new CaptchaKey(id));
	}

	public String generateString() {
		StringBuilder captcha = new StringBuilder();
		for (int i = 0; i < CHARS; i++) {
			captcha.append(DICT.charAt((int) (Math.random() * DICT.length())));
		}
		return captcha.toString();
	}

	public List<CaptchaKey> keySet() {
		return CaptchaStorages.globalMap.keySet().stream().collect(Collectors.toList());
	}

	public void remove(CaptchaKey k) {
		CaptchaStorages.globalMap.remove(k);
	}
}
