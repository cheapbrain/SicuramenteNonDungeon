package entity2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MobFactory {
	public static final String folder = "./res/";
	public static Entity newMob(String path){
		File f;
		Properties props = new Properties();
	    InputStream is = null;
		Entity mob = new Entity(0);
		if(path.equals(null)) return null;		
		f= new File(folder+path+".mob");
		if(!f.exists()) return null;
	    try {
	        is = new FileInputStream(f.getPath());
	        props.load( is );
	    }
	    catch ( Exception e ) { }
	    try{
	    	/*name = props.getProperty("Name");*/
	    	mob.addComponent(new Health( mob, 1, Float.parseFloat(props.getProperty("Health")), Float.parseFloat(props.getProperty("HealthRegeneration"))));
	    	/*spritePath=props.getProperty("SpritePath");*/
	    } catch(Exception e){
	    	System.out.println("dati nel file corrotti");
	    	return null;
	    }		
		return mob;
	}
}
