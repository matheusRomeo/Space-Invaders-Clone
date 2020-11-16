package com.azuryPlays.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.azuryPlays.main.Game;

public class Enemy extends Entity{
	
	public double speed =2;
	
	public int life = 3;
	
	public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick(){
		depth = 1;
		y+=speed;
		for(int i=0; i<Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				if(Entity.isColidding(e, this)) {
					Game.entities.remove(e);
					life--;					
					if(life <=0) {
						Explosion explosion = new Explosion(x, y, 32, 32, null);
						Game.entities.add(explosion);
						Game.score++;
						Game.entities.remove(this);
						return;
					}
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
}
