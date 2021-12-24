package com.oxology.firework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

public class Firework {
    float opacity;
    float fireworkXSpeed;
    float fireworkYSpeed;
    float x, y;
    Texture texture;
    List<SubFirework> subFireworks;
    boolean subFireworksCreated;
    Color color;

    public Firework(Texture texture) {
        this.texture = texture;
        opacity = 1;
        fireworkXSpeed = random()*5-2.5f;
        fireworkYSpeed = 11;
        x = Gdx.graphics.getWidth()/2 - this.texture.getWidth()/2;
        subFireworks = new ArrayList<>();
        subFireworksCreated = false;
        color = FireworkAnimation.getRandomColor();
    }

    public void update() {
        opacity = opacity-0.01f;
        x = x+fireworkXSpeed;
        y = y+fireworkYSpeed;
        fireworkYSpeed -= 0.15f;

        if(opacity < 0.1 && !subFireworksCreated) {
            createSubFireworks();
            subFireworksCreated = true;
        }
    }

    public void createSubFireworks() {
        for(int i = 0; i < FireworkAnimation.subFireworkCount; i++) {
            subFireworks.add(new SubFirework(this.x, this.y, texture));
        }
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(color.r, color.g, color.b, opacity);
        batch.draw(texture, x, y);
        if(!subFireworks.isEmpty()) {
            for(SubFirework subFirework : subFireworks) {
                subFirework.update();
                subFirework.draw(batch);
            }
        }
    }

    public float getHideTimer() {
        return subFireworks.get(0).getHideTimer();
    }
}
