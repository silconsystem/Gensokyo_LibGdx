package com.silconsystem.gensokyo.utils;

//import gdx packs
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class DefaultActorListener extends InputListener
{
	@Override
	public boolean touchDown(
			InputEvent event,
			float x,
			float y,
			int pointer,
			int button) {
		return true;
	}
}
