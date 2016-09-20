package com.epam.chuikov.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImageCreator {
	private int WIDTH = 128;
	private int HEIGHT = 48;
	private float FONT_SIZE = 28f;

	public BufferedImage create(String captcha) {
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) (bufferedImage.getGraphics());
		configGraphics(graphics);
		graphics.drawString(captcha, 16, 28);
		return bufferedImage;
	}

	private void configGraphics(Graphics2D graphics) {
		graphics.setBackground(Color.lightGray);
		graphics.clearRect(0, 0, WIDTH, HEIGHT);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.BLACK);
		graphics.setFont(graphics.getFont().deriveFont(FONT_SIZE));
	}
}
