package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import MobsManagers.AIManager;

public class Mob extends Npc implements IsEnemy{
	private String name, spritePath;
	private int health;
	private int direction;
	private int timer=3000;
	private boolean toDx, toUp;
	public static final String path = "./res/Mobs/";
	public Mob(String race) {
		super(true);
		speed=0.001f;
		File f;
		f= new File(path+race+".mob");
		loadParams(f);
		try {
			Image texture = new Image(spritePath);
			texture.setFilter(Image.FILTER_NEAREST);
			setSize(96, 54, 2);
			setAnimations(texture, 0, 1, 1);
			setAnimations(texture, 1, 10, 100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void render() {
		super.render();
	}
	public void loadParams(File f) {
	    Properties props = new Properties();
	    InputStream is = null;
	    try {
	        is = new FileInputStream( f );
	    }
	    catch ( Exception e ) { is = null; }
	    try {
	        if ( is == null ) {
	        	 is = new FileInputStream(f.getPath());
	  	      }
	        props.load( is );
	    }
	    catch ( Exception e ) { }
	    try{
	    	name = props.getProperty("Name");
	    	health = Integer.parseInt(props.getProperty("Health"));
	    	spritePath=props.getProperty("SpritePath");
	    } catch(Exception e){
	    	System.out.println("dati nel file corrotti");
	    }
	}
	public void update(GameContainer container, World world, int delta) {
		super.update(container, world, delta);
		AIManager.update(this, delta, world);
		
	}
	public void collideWith(Entity entity){
		if(entity instanceof WalkEntity){
			stop();
			if((this.x-entity.getX())>=0) speedx=speed; else speedx=-speed;
			if((this.y-entity.getY())>=0) speedy=speed; else speedy=-speed;
		}
		if(entity instanceof Player){
			stop();
		}
	}
	private void die(World world) {
		world.remove(this);
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void follow(Entity e){
		int x=50;
		if(this.getX()>e.getX()+x*speed) speedx = -speed;
		else if(this.getX()<e.getX()-x*speed) speedx = speed;
		else speedx=0;
		if(this.getY()>e.getY()+x*speed) speedy = -speed;
		else if(this.getY()<e.getY()-x*speed) speedy = speed;
		else speedy=0;
	}
	public void wander(int delta){
		Random r = new Random();
		if(timer<=0){
			timer=3000;
			if(r.nextInt()%2==0) toDx=true; else toDx=false;
			if(r.nextInt()%2==0) toUp=true; else toUp=false;
		}
		if(toDx){
			speedx=speed;
		} else speedx=-speed;

		if(toUp){
			speedy=speed;
		} else speedy=-speed;
		timer-=delta;
		if(toUp){ facing =0; state=1;}
		if(toUp&&!toDx){ facing =1; state=1;}
		/*if(!toDx){ facing =2; state=1;}
		if(!toUp&&!toDx) facing =1;
		if(!toUp) facing =4;
		if(!toUp&&toDx) facing =5;
		if(toDx) facing =6;
		if(toUp&&toDx) facing =7;
	*/}
	public void stop(){
		speedx=0;
		speedy=0;
	}
}
