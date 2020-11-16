package com.azuryPlays.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.azuryPlays.main.Game;
import com.azuryPlays.world.World;

public class Player extends Entity{
	
	public static final int PLAYER_SIZE = 48;	
	public double speed = 4;
	
	public boolean right, left;
	
	public boolean isShooting = false;
	
	public Player(int x, int y, int width, int height,BufferedImage sprite) {
		super(x, y, width, height,sprite);		

	}
	
	public void tick(){
		depth = 0;
		
		if(right) {
			x+=speed;
		
		}else if(left) {
			x-=speed;
		}
		
		
		if(x>=Game.WIDTH) {
			x = -(PLAYER_SIZE);
		}
		else if(x+PLAYER_SIZE <0) {
			x = Game.WIDTH;
		}
		
		if(isShooting) {
			isShooting = false;
			int xx = this.getX() +5;
			int yy = this.getY();
			
			Bullet shot = new Bullet(xx, yy, 5, 5, null);
			Game.entities.add(shot);
		}
		
		for(int i=0; i<Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColidding(e, this)) {
				
				System.exit(1);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	

	



}
