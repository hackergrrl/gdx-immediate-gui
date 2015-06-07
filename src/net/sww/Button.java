package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Button extends Widget {

    private Color colour;
    private String caption;

    public Button(String caption, Color colour) {
        this.caption = caption;
        this.colour = colour;
        marginLeft = marginRight = 8;
        marginTop = marginBottom = 8;
        size.y = 24;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y);
        size.x = parent.size.x;

        float w = size.x - marginLeft - marginRight;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(colour.cpy().mul(1.75f));
        shapeRenderer.rect(pos.x + marginLeft, pos.y - marginBottom - size.y, w, size.y);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(colour);
        shapeRenderer.rect(pos.x + marginLeft + 1, pos.y - marginBottom - size.y + 1, w - 2, size.y - 2);
        shapeRenderer.end();

        float xPos = (pos.x + marginLeft + w/2) - font.getBounds(caption).width / 2;
        batch.begin();
        font.draw(batch, caption, xPos, pos.y - marginTop - 1 - size.y/2 + font.getBounds(caption).height/2);
        batch.end();
    }
}
