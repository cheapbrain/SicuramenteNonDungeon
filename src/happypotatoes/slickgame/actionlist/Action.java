package happypotatoes.slickgame.actionlist;

public abstract class Action {
	public boolean blocking;
	public float elapsed;
	public float duration;
	
	public Action(float duration, boolean blocking){
		this.duration = duration;
	}
	
	public void update(float delta){
		elapsed += delta;
		update();
	}
	public abstract void update();
	
	public boolean isBlocking(){
		return blocking;
	}
}