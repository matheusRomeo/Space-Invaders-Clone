package com.azuryPlays.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.azuryPlays.main.Game;
import com.azuryPlays.world.Camera;
import com.azuryPlays.world.World;
import com.azuryPlays.world.astar.Node;
import com.azuryPlays.world.astar.Vector2;

public class Entity {
	
	//mascara de coliz�o
		public int maskx, masky, maskw, maskh;
		
		public static final int ENTITY_SIZE =16;
	
	/* pegando Sprites da imagem para renderizar o objeto inicialmente*/
	public static BufferedImage OBJ = Game.spritesheet.getSprite(0, 0, ENTITY_SIZE, ENTITY_SIZE);
	
	public static BufferedImage PLAYER_SPRITE = Game.spritesheet.getSprite(0,0,Player.PLAYER_SIZE,Player.PLAYER_SIZE);
	public static BufferedImage METEOR = Game.spritesheet.getSprite(48, 0, 32, 32);
	
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	public int depth;

	protected List<Node> path;
	
	public boolean debug = false;
	
	protected BufferedImage sprite;
	
	public static Random rand = new Random();
	
	public Entity(double x,double y,int width,int height,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0; //offset da mascara
		this.masky = 0; //offset da mascara
		this.maskw = width;
		this.maskh = height;
	}
	
	public void tick(){}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		@Override
		public int compare(Entity n0,Entity n1) {
			if(n1.depth > n0.depth)
				return +1;
			if(n1.depth < n0.depth)
				return -1;
			return 0;
		}
		
	};
	
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public double calculateDistance(int x1,int y1,int x2,int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2 target = path.get(path.size() - 1).tile;
				/* utilize " else " para movimentos em diagonal */
				if(x < target.x * 16) 
					x++;
				else if(x > target.x * 16)
					x--;
								
				if(y < target.y * 16) 
					y++;
				else if(y > target.y * 16) 
					y--;
								
				if(x == target.x * 16 && y == target.y * 16) 
					path.remove(path.size() - 1);							
			}
		}
	}
	
	public static boolean isColidding(Entity e1,Entity e2){
		Rectangle e1Mask = new Rectangle(e1.getX(),e1.getY(),e1.getWidth(),e1.getHeight());
		Rectangle e2Mask = new Rectangle(e2.getX(),e2.getY(),e2.getWidth(),e2.getHeight());
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y,null);
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + maskx - Camera.x,this.getY() + masky - Camera.y,maskw,maskh);
	}
	
	
	
	/*GETTERS AND SETTERS*/
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}