import com.silconsystem.gensokyo.GensokyoGame;

//import gdx packs
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame
{

		public static void main (String[] args)
		{			
			// listener to receive application events
			ApplicationListener listener = new GensokyoGame();
			
			// window title
			String title = "GensokyoGame";
			
			// window size
			int width = 800;
			int height = 480;
			
			// openGles 2.0
			boolean useOpenGLES2 = false;
			
			// create the game
			new LwjglApplication(listener, title, width, height, useOpenGLES2);
		}
	
}
