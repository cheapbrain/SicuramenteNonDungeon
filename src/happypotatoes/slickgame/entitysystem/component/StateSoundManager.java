package happypotatoes.slickgame.entitysystem.component;

import java.util.LinkedList;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class StateSoundManager extends Component{
	Walker walker;
	int state;
	
	List<StateSound> sounds = new LinkedList<StateSound>();

	public StateSoundManager(Entity owner, float priority, Walker walker, StateSound ... sounds) {
		super(owner, priority);
		this.walker = walker;
		
		for (StateSound s:sounds)
			this.sounds.add(s);
	}

	@Override
	public void update(World w, long delta) {
		int news = walker.getState();
		if (state!=news) {
			for (StateSound s:sounds) {
				s.trigger(state, StateSound.LEAVE);
				s.trigger(news, StateSound.ENTER);
			}
			state = news;
		}
		for (StateSound s:sounds) {
			s.update(delta);
		}
	}
}
