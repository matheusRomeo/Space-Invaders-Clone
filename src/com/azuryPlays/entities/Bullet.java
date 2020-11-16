package com.azuryPlays.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.azuryPlays.main.Game;

public class Bullet extends Entity{
	
	public int speed = 4;

	public Bullet(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	}
	
	public void tick() {
		y -=speed;
		
		if(y <= -10) {
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(this.getX(), this.getY(), 5, 5);
	}

}
