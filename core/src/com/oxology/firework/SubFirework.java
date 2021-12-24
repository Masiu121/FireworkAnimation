package com.oxology.firework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.math.MathUtils.random;

public class SubFirework {
    float xVelocity;
    float yVelocity;
    float x, y;
    Texture texture;
    float opacity;
    Color color;

    public SubFirework(float x, float y, Texture texture) {
        xVelocity = random()*10-5f;
        yVelocity = random()*10-5f;
        this.x = x;
        this.y = y;
        this.opacity = 1;
        this.texture = FireworkAnimation.smallFirework;
        color = FireworkAnimation.getRandomColor();
    }

    public void update() {
        this.x = this.x + xVelocity;
        this.y = this.y + yVelocity;
        opacity = opacity-0.02f;
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(color.r, color.g, color.b, opacity);
        batch.draw(texture, x, y);
    }

    public float getOpacity() {
        return opacity;
    }
}
