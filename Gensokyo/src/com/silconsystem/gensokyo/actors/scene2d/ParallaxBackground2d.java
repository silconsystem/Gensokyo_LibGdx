package com.silconsystem.gensokyo.actors.scene2d;

// import gdx packs
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxBackground2d {
	
	private ParallaxLayer2d[] layers;
	private Camera camera;
	private SpriteBatch spriteBatch;
	private Vector2 speed = new Vector2();
	
	public ParallaxBackground2d(ParallaxLayer2d[] layers, float width, float height, Vector2 speed)
	{
		this.layers = layers;
		this.speed.set(speed);
		camera = new OrthographicCamera(width, height);
		spriteBatch = new SpriteBatch();
	}
	
	public void render(float delta)
	{
		this.camera.position.add(speed.x * delta, speed.y * delta, 0);
		for (ParallaxLayer2d layer:layers)
		{
			spriteBatch.setProjectionMatrix(camera.projection);
			spriteBatch.begin();
			float currentX = -camera.position.x * layer.parallaxRatio.x % (layer.region.getRegionWidth()
					+ layer.padding.x);
			if (speed.x < 0) currentX += (layer.region.getRegionWidth() + layer.padding.x);
			do
			{
				float currentY = - camera.position.y * layer.parallaxRatio.y % (layer.region.getRegionHeight()
						+ layer.padding.y);
				if (speed.y < 0) currentY += (layer.region.getRegionHeight() + layer.padding.y);
				do
				{
					spriteBatch.draw(layer.region,
							-this.camera.viewportWidth / 2 + currentX + layer.startPosition.x,
							-this.camera.viewportHeight / 2 + currentY + layer.startPosition.y);
					currentY += (layer.region.getRegionHeight() + layer.padding.y);
				} while (currentY < camera.viewportHeight);
					
				currentX += (layer.region.getRegionWidth() + layer.padding.x);
			} while (currentX < camera.viewportWidth);
			
			spriteBatch.end();
		}
	}

}
