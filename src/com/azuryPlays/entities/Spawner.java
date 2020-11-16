package com.azuryPlays.entities;

import java.awt.image.BufferedImage;

import com.azuryPlays.main.Game;

public class Spawner extends Entity{
	
	private int timer = 60 *2;
	private int curTime = 0;

	public Spawner(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	}
	
	public void tick() {
		curTime++;
		
		if(curTime == timer) {
			curTime =0;
			int yy = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH - 32);
			
			Enemy meteoro = new Enemy(xx, yy, 32,32, Entity.METEOR);
			Game.entities.add(meteoro);
			
		}
		
	}
}