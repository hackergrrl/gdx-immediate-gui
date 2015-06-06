package net.sww;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Widget {

    public Vector2 pos = new Vector2();
    public Vector2 size = new Vector2();
    public float marginLeft = 3, marginTop = 3, marginRight = 3,marginBottom = 3;

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {}

    public Vector2 computeSize() {
        return new Vector2(size.x + marginLeft + marginRight, size.y + marginTop + marginBottom);
    }
}
