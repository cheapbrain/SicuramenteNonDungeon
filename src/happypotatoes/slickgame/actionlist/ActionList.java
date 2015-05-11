package happypotatoes.slickgame.actionlist;

import java.util.List;

public class ActionList {
	private List<Action> actions; // can be a vector or linked list
	
	public void update( float dt ){
		
	}
	
	public void pushHead( Action action ){
		actions.add(0, action);
	}
	
	public void pushTail( Action action ){
		actions.add(action);
	}
	
	public Action popHead( Action action ){
		Action t = actions.get(0);
		actions.remove(0);
		return t;
	} 
	
	public Action popBack( Action action ){
		Action t = actions.get(actions.size()-1);
		actions.remove(actions.size()-1);
		return t;
	}
		
	public boolean isEmpty(){
		return actions.isEmpty();
		
	}
		
}
