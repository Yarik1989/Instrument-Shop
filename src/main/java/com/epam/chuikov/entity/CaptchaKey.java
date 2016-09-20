package com.epam.chuikov.entity;

public class CaptchaKey {
	private final Object id;
	private final long timelife;

	public CaptchaKey(Object id, long timeout) {
		this.id = id;
		this.timelife = System.currentTimeMillis() + timeout;
	}

	public CaptchaKey(Object id) {
		this.id = id;
		timelife = 0;
	}

	public boolean isLive(long currentTimeMillis) {
		return currentTimeMillis < timelife;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CaptchaKey other = (CaptchaKey) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

}
