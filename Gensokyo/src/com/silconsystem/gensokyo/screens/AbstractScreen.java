package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

// import my gdx packs
import com.silconsystem.gensokyo.GensokyoGame;

public abstract class AbstractScreen implements Screen
{
		public static final int GAME_VIEWPORT_WIDTH = 500, GAME_VIEWPORT_HEIGHT = 400;
		public static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT = 480;
	
		protected final GensokyoGame gensokyogame;
		protected final Stage stage;
		
		private BitmapFont font;
		private SpriteBatch spritebatch;
		private TextureAtlas menuatlas;
		private TextureAtlas gameAtlas;
		private TextureAtlas atlas2;
		private Table table;
		private Skin skin;
		
		public AbstractScreen(GensokyoGame gensokyogame)
		{			
				this.gensokyogame = gensokyogame;
				
				int width = (isGameScreen() ? GAME_VIEWPORT_WIDTH:MENU_VIEWPORT_WIDTH);
				int height = (isGameScreen() ? GAME_VIEWPORT_HEIGHT:MENU_VIEWPORT_HEIGHT);
				

				this.stage = new Stage(width, height, true);
		}
		
		protected String getName()
		{
			return getClass().getSimpleName();
		}
		
		protected boolean isGameScreen()
		{
			return false;
		}
		/* ---		lazy loaded helper methods		--- */
		public BitmapFont getFont()
		{
			if (font == null)
			{
				font = new BitmapFont();
			}
			return font;
		}
		
		public SpriteBatch getBatch()
		{
			if (spritebatch == null)
			{
				spritebatch = new SpriteBatch();
			}
			return spritebatch;
		}
		
		public TextureAtlas getAtlas()
		{
			if (menuatlas == null)
			{
				menuatlas = new TextureAtlas(Gdx.files.internal("menu/textures/textures.pack"));
			}
			return menuatlas;
		}
		
		public TextureAtlas getAtlasTwo()
		{
			if (atlas2 == null)
			{
				atlas2 = new TextureAtlas(Gdx.files.internal("screens/startgame/atlas/textures.pack"));
			}
			return atlas2;
		}
		
		public TextureAtlas getGameAtlas()
		{
			if (gameAtlas == null)
			{
				gameAtlas = new TextureAtlas(Gdx.files.internal("screens/gamescreen/atlas/textures.pack"));
			}
			return gameAtlas;
		}
		
		protected Skin getSkin()
		{
			if(skin ==  null)
			{
				FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
				skin = new Skin(skinFile);
			}
			return skin;
		}
		
		protected Table getTable()
		{
			if(table == null)
			{
				table = new Table(getSkin());
				table.setFillParent(true);				
				if (GensokyoGame.DEV)
				{
					table.debug();
				}
				
				stage.addActor(table);
			}
			return table;
		}
				
		/* ---		implement screens		--- */
		@Override
		public void show()
		{
			Gdx.app.log(GensokyoGame.LOG, "Showing screen: " + getName());
			
			// init stage to be used as inputprocessor
			Gdx.input.setInputProcessor(stage);
		}
		
		@Override
		public void resize(int width, int height)
		{
			Gdx.app.log(GensokyoGame.LOG, "Resizing screen: " + getName() + "to: " + width + " x " + height);
			
			// resize stage
			stage.setViewport(width, height, true);
		}
		
		@Override
		public void render(float delta)
		{
			// clear and fill screen with black color
			Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			// update and draw the stage actors
			stage.act(delta);
			stage.draw();
		}
		
		@Override
		public void hide()
		{
			Gdx.app.log(GensokyoGame.LOG, "Hiding screen: " + getName());
			
			// dispose of screen
			dispose();
		}
		
		@Override
		public void pause()
		{
			Gdx.app.log(GensokyoGame.LOG, "Pauzing screen: " + getName());
		}
		
		@Override
		public void resume()
		{
			Gdx.app.log(GensokyoGame.LOG, "Resume screen: " + getName());
		}
		
		@Override
		public void dispose()
		{
			Gdx.app.log(GensokyoGame.LOG, "Disposing screen: " + getName());
			
			// dispose of stage assets
			//stage.dispose();		MAY CAUSE PROBLEMS !!!
			if (spritebatch != null) spritebatch.dispose();
			if (font != null) font.dispose();
		}

}//