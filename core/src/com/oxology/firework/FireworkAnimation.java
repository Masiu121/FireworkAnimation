package com.oxology.firework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

public class FireworkAnimation extends ApplicationAdapter {
	SpriteBatch batch;
	Texture firework;
	List<Firework> fireworks;
	static Texture smallFirework;
	static int subFireworkCount;
	List<Firework> fireworksToRemove;

	static List<Color> colors;

	@Override
	public void create () {
		batch = new SpriteBatch();
		firework = new Texture("Firework.png");
		smallFirework = new Texture("FireworkSmall.png");
		fireworks = new ArrayList<>();
		colors = new ArrayList<>();
		//colors.add(Color.BLUE);
		//colors.add(Color.RED);
		//colors.add(Color.GREEN);
		//colors.add(Color.WHITE);
		colors.add(Color.WHITE);
		colors.add(Color.RED);
		colors.add(new Color(0.96f, 0.72f, 0.85f, 1));
		subFireworkCount = 200;
		fireworksToRemove = new ArrayList<>();
	}

	public static Color getRandomColor() {
		int rand = (int) (random()*colors.size());

		return colors.get(rand);
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for(Firework firework : fireworks) {
			firework.draw(batch);
		}
		batch.end();
	}

	public void update() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
			spawnFirework();

		for(Firework firework : fireworks) {
			firework.update();
			if(firework.subFireworks.size()>0 && firework.getHideTimer() < 0)
				fireworksToRemove.add(firework);
	 	}

		if(fireworksToRemove.size() > 0) {
			for(Firework firework : fireworksToRemove) {
				fireworks.remove(firework);
			}
			fireworksToRemove.clear();
		}
	}

	public void spawnFirework() {
		fireworks.add(new Firework(firework));
	}

	@Override
	public void dispose () {
		batch.dispose();
		firework.dispose();
	}
}
