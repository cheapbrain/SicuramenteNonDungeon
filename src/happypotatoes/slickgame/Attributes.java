package happypotatoes.slickgame;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.items.ItemArmor;


public class Attributes {


	private Entity Owner;
	
	private float forza; //da 0 a 25 -- 8 è un punteggio normale/di base
	private float destrezza;
	private float costituzione;
	private float intelligenza;
	private float tenacia;
	private float carisma;
	private float tecnologia;
	
	private float maestriataglio; //da 0 a 100
	private float maestriacontundente;
	private float maestriaarco;
	private float maestriaarmadafuoco;
	private float maestriaarcana;
	
	private float salute;
	
	private float esperienza;
	private int livello; //da 1 a 25 //calcolare livello quando?
	
	private float mitigazione; //da 0 a 100
	
	
	public void calcolaMitigazione(ItemArmor a){
		Float mit=(float) 0;
		mit=(float) ((Math.sqrt(costituzione/8)*10)-10)*3; //ci abbiamo perso un'ora
		//mit=mit+a.getmitigation();
		if (mit>100) mit=(float) 100;
	}
	
	public void calcolaMitigazione(){ //se non ha armatura
		Float mit=(float) 0;
		mit=(float) ((Math.sqrt(costituzione/8)*10)-10)*3;
		if (mit>100) mit=(float) 100;
	}
	
	public void incrementamaestria(int tipo){ //tipo: 1=taglio, 2=contundente, 3=arco, 4=armadafuoco; 5=arcana;
		switch (tipo){
		
		case 1:{
						if (maestriataglio<100)
							maestriataglio=(float) (maestriataglio+(Math.cos(maestriataglio/36)+1)/32);
							//modificare l'ultima divisione per aumentare il numero di attacchi necessari
							incrementaforza();
						break;
	                  }
		case 2:{
						if (maestriacontundente<100)
							maestriacontundente=(float) (maestriacontundente+(Math.cos(maestriacontundente/36)+1)/32);
							incrementaforza();
						break;
					       }
			
		case 3:{
						if (maestriaarco<100)
							 maestriaarco=(float) (maestriaarco+(Math.cos(maestriaarco/36)+1)/32);
							incrementadestrezza();
						break;
		}
		
		case 4:{
						if (maestriaarmadafuoco<100)
							maestriaarmadafuoco=(float) (maestriaarmadafuoco+(Math.cos(maestriaarmadafuoco/36)+1)/32);
							incrementadestrezza();
						break;
		}
			
		case 5:{
						if (maestriaarcana<100)
							maestriaarcana=(float) (maestriaarcana+(Math.cos(maestriaarcana/36)+1)/32);
							incrementaintelligenza();
						break;		
		}
		}
		
	}

	private void incrementaforza() {
		if (forza<25){

		}
		
	}
	
	private void incrementadestrezza() {
		if (destrezza<25){

		}
		
	}

	private void incrementaintelligenza() {
		if (intelligenza<25){
			intelligenza++;
		}
	}
		
	private void incrementatenacia() {
		if (tenacia<25){
			tenacia++;	
		}
	
}
	
}
