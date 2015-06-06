package net.sww;

import com.badlogic.gdx.Gdx;
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

        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y - size.y);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.rect(pos.x, pos.y + size.y - 17, size.x, 17);
        shapeRenderer.end();

        batch.getTransformMatrix().idt();
        batch.begin();
        font.draw(batch, title, pos.x + 2, pos.y + size.y - 4);
        font.draw(batch, title, 0, 0);
        batch.end();
    }

    public void end(BitmapFont font) {
        size.x = Math.max(size.x, font.getBounds(title).width + 10);
    }
}
