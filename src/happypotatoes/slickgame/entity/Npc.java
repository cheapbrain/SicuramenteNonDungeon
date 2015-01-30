package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Npc extends WalkEntity{
	
	protected String tipo; //Animal, Antropomorph, Construct, Undead, Demon, Elemental, Xenomorph
	protected int livello;
	protected String pericolosità; //Normal, Strong, Elite, Menace, Grand Menace
	protected int puntiferita;
	protected float mitigazione;
	protected int[] danni;
	
	//private int[][] drop;  //servono gli id, ma di che tipo sono?
	
	protected int xp;
	
	protected int status; //morto, vivo ecc..
	
	public int givexp(){
		//if status=died{
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
