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
        x = Gdx.graphics.getWidth()/2 - 10;
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
        batch.setColor(rgb(191, 54, 4, opacity));
        batch.draw(texture, x, y, 30, 30);
        batch.setColor(rgb(242, 187, 19, opacity));
        batch.draw(texture, x, y, 25, 25);
        batch.setColor(rgb(242, 163, 15, opacity));
        batch.draw(texture, x, y, 20, 20);
        batch.setColor(rgb(242, 120, 12, opacity));
        batch.draw(texture, x, y, 15, 15);
        batch.setColor(rgb(13, 13, 13, opacity));
        batch.draw(texture, x, y, 10, 10);

        if(!subFireworks.isEmpty()) {
            for(SubFirework subFirework : subFireworks) {
                subFirework.update();
                subFirework.draw(batch);
            }
        }
    }

    public Color rgb(int r, int g, int b, float opacity) {
        return new Color(r/255f, g/255f, b/255f, opacity);
    }

    public float getHideTimer() {
        return subFireworks.get(0).getHideTimer();
    }
}
