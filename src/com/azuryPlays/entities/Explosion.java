package com.azuryPlays.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.azuryPlays.main.Game;

public class Explosion extends Entity{
	
	private int frames =0;
	private int maxFrames =4;
	
	private int curAnimin=0; 
	private int maxAnimin = 3;
	
	private BufferedImage[] Animation = new BufferedImage[maxAnimin];

	public Explosion(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		Animation[0] = Game.spritesheet.getSprite(48, 32, 32, 32);
		Animation[1] = Game.spritesheet.getSprite(80, 32, 32, 32);
		Animation[2] = Game.spritesheet.getSprite(112, 32, 32, 32);
	}
	
	public void tick() {
		
		frames++;
		if(frames == maxFrames) {
			frames =0;
			curAnimin++;
			if(curAnimin >= maxAnimin) {
				Game.entities.remove(this);
				return;
			}
									
		}
		
	}
	

	public void render(Graphics g) {
		g.drawImage(Animation[curAnimin], this.getX(), this.getY(), null);
	}

}
