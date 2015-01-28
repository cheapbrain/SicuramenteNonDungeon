package happypotatoes.slickgame;

import happypotatoes.slickgame.entity.Entity;

public class MonsterAttributes {

	
	private Entity Owner;
	
	private String tipo; //Animal, Antropomorph, Construct, Undead, Demon, Elemental, Xenomorph
	private int livello;
	private String pericolosità; //Normal, Strong, Elite, Menace, Grand Menace
	private int puntiferita;
	private float mitigazione;
	private int[] danni;
	
	private int[][] drop;  //servono gli id, ma di che tipo sono?
	
	private int xp;
	
	private int status; //morto, vivo ecc..
	
	public int givexp(){
		//when he dies
		return xp;
	}
	
	

}
