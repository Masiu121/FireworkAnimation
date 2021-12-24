package com.oxology.firework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.math.MathUtils.*;

public class SubFirework {
    float xVelocity;
    float yVelocity;
    float x, y;
    Texture texture;
    float opacity;
    Color color;
    float opacityDecreaseSpeed;
    float hideTimer;

    public SubFirework(float x, float y, Texture texture) {
        float angle = random()*360;
        float divide = random()*2;
        xVelocity = (sin(angle)*3)*divide;
        yVelocity = (cos(angle)*3)*divide;
        opacityDecreaseSpeed = ((random()*2)+1)/100;
        this.x = x;
        this.y = y;
        this.opacity = 1;
        this.texture = FireworkAnimation.smallFirework;
        color = FireworkAnimation.getRandomColor();
        hideTimer = 1;
    }

    public void update() {
        this.x = this.x + xVelocity;
        this.y = this.y + yVelocity;
        opacity = opacity-opacityDecreaseSpeed;

        xVelocity = xVelocity/1.03f;
        yVelocity = yVelocity/1.03f;
        yVelocity -= 0.1f;
        hideTimer -= 0.01f;
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(color.r, color.g, color.b, opacity);
        batch.draw(texture, x, y);
    }

    public float getHideTimer() {
        return hideTimer;
    }
}
