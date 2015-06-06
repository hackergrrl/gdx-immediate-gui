package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Label extends Widget {

    private String text;

    public Label(String text) {
        this.text = text;
        marginLeft = 4;
        marginTop = marginBottom = 4;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y);

        batch.begin();
        font.draw(batch, text, pos.x + marginLeft, pos.y - marginBottom);
        batch.end();
    }
}
