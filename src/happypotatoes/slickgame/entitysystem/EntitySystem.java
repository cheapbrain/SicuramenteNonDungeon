package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.entitysystem.entity.ItemEntity;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class EntitySystem {
	private static EntitySystem instance;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private LinkedBlockingQueue<Entity> toAdd = new LinkedBlockingQueue<Entity>();
	private LinkedBlockingQueue<Entity> toRemove = new LinkedBlockingQueue<Entity>();
	private long nextid = 1;
	
	private EntitySystem() {}
	
	public static EntitySystem getInstance() {
		if (instance==null)
			instance = new EntitySystem();
		return instance;
	}
	
	public long getFreeID() {
		return nextid++;
	}
	
	public Entity getEntity(int id) {
		for(Entity e:entities)
			if (e.id == id)
				return e;
		return null;
	}
	
	public List<Entity> getAll() {
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity> getEntities(Class<?>... components) {
		List<Entity> list = new ArrayList<Entity>();
		for (Entity e:entities) {
			boolean hasComponents = true;
			for (Class<?> c:components)
				if (e.getComponent((Class<? extends Component>) c)==null) {
					hasComponents = false;
					break;
				}
			if (hasComponents)
				list.add(e);
		}
			
		return list;
	}

	public void addEntity(Entity e){
		toAdd.add(e);
	}
	
	public void removeEntity(Entity e){
		toRemove.add(e);
	}
	
	public void update(World w, long delta) {
		while (!toAdd.isEmpty())
			entities.add(toAdd.poll());
		
		while (!toRemove.isEmpty())
			entities.remove(toRemove.poll());
		
		for (Entity entity : entities)
			entity.update(w, delta);
	}

	public void clear() {
		entities.clear();
		toAdd.clear();
		toRemove.clear();
	}

	public void destroy(Entity focus) {
		Inventory inventory = focus.getComponent(Inventory.class);
		Equip equip = focus.getComponent(Equip.class);
		if(inventory!=null){
			for(int i=0;i<inventory.width;i++)
				for(int j=0;j<inventory.height;j++)
					if(!inventory.getSlot(i, j).isFree())
						addEntity(ItemEntity.create(inventory.getSlot(i, j).getItemId(), focus.x, focus.y));
		}
		if(equip!=null){
			for(int i=0;i<equip.width;i++)
				for(int j=0;j<equip.height;j++)
					if(!equip.getSlot(i, j).isFree())
						addEntity(ItemEntity.create(equip.getSlot(i, j).getItemId(), focus.x, focus.y));
		}
		removeEntity(focus);
		
	}
	
}
