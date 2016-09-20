package com.epam.chuikov.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {
	private static Map<String, CaptchaStrategy> map = new HashMap<String, CaptchaStrategy>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3952050662449982173L;

		{
			put("hidden", new HiddenCaptchaStrategy());
			put("cookie", new CookieCaptchaStrategy());
			put("session", new SessionCaptchaStrategy());
		}
	};

	public static CaptchaStrategy createStrategy(String strategy) {
		return map.get(strategy);
	}
}
