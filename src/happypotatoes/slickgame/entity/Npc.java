package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Npc extends WalkEntity{
	
	private String tipo; //Animal, Antropomorph, Construct, Undead, Demon, Elemental, Xenomorph
	private int livello;
	private String pericolosità; //Normal, Strong, Elite, Menace, Grand Menace
	private int puntiferita;
	private float mitigazione;
	private int[] danni;
	
	//private int[][] drop;  //servono gli id, ma di che tipo sono?
	
	private int xp;
	
	private int status; //morto, vivo ecc..
	
	public int givexp(){
		//when he dies
		return xp;
	}

	public Npc(boolean doesCollide) {
		super(doesCollide);
	}
	public void update(GameContainer container, World world, int delta) {
		super.update(container, world, delta);
	}
	public void render() {
		super.render();
	}
	
}
