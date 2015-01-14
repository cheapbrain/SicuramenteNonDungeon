package happypotatoes.slickgame;

import happypotatoes.slickgame.entity.Armour;
import happypotatoes.slickgame.entity.Entity;


public class Attributes {

	private Entity Owner;
	
	private Float forza; //da 0 a 25 -- 8 è un punteggio normale/di base
	private Float destrezza;
	private Float costituzione;
	private Float intelligenza;
	private Float tenacia;
	private Float carisma;
	private Float tecnologia;
	
	private Float maestriataglio; //da 0 a 100
	private Float maestriacontundente;
	private Float maestriaarco;
	private Float maestriaarmadafuoco;
	private Float maestriaarcana;
	
	private Float salute;
	
	private Float mitigazione; //da 0 a 100
	
	
	public void calcolaMitigazione(Armour a){
		Float mit=(float) 0;
		mit=(float) ((Math.sqrt(costituzione/8)*10)-10)*3; //ci abbiamo perso un'ora
		mit=mit+a.getmitigation();
		if (mit>100) mit=(float) 100;
	}
	
	public void calcolaMitigazione(){ //se non ha armatura
		Float mit=(float) 0;
		mit=(float) ((Math.sqrt(costituzione/8)*10)-10)*3;
		if (mit>100) mit=(float) 100;
	}
	
	public void incrementamaestria(String tipo){
		switch (tipo){
		
		case "taglio": maestriataglio=(float) (maestriataglio+(Math.cos(maestriataglio/36)+1)/32); //modificare l'ultima divisione per aumentare il numero di attacchi necessari
			
		case "contundente": maestriacontundente=(float) (maestriacontundente+(Math.cos(maestriacontundente/36)+1)/32);
			
		case "arco": maestriaarco=(float) (maestriaarco+(Math.cos(maestriaarco/36)+1)/32);
		
		case "armadafuoco": maestriaarmadafuoco=(float) (maestriaarmadafuoco+(Math.cos(maestriaarmadafuoco/36)+1)/32);
			
		case "arcana": maestriaarcana=(float) (maestriaarcana+(Math.cos(maestriaarcana/36)+1)/32);
		}
	}
	
}
