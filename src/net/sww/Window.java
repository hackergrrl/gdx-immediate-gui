package net.sww;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Window extends Widget {
    public Vector2 pos;
    public Vector2 size;
    public String title;

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.rect(pos.x, pos.y + size.y - 10, size.x, 10);
        shapeRenderer.end();

        batch.begin();
        font.draw(batch, title, pos.x, pos.y);
        font.draw(batch, title, 0, 0);
        batch.end();
    }
}
