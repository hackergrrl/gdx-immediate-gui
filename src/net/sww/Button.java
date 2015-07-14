package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

class Button extends Widget {

    public boolean hovering = false;
    private Color colour;
    private String caption;

    public Button(String caption, Color colour) {
        super(new Vector2(100, 24));
        this.caption = caption;
        this.colour = colour;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y);

        Color outline = colour.cpy().mul(1.15f);
        Color bg = colour.cpy();
        if (hovering) {
            bg.mul(1.25f);
            outline.mul(1.6f);
        }

        float w = size.x - horizontalMargin * 2;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(outline);
        shapeRenderer.rect(pos.x + horizontalMargin, pos.y - verticalMargin - size.y, w, size.y);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(bg);
        shapeRenderer.rect(pos.x + horizontalMargin + 1, pos.y - verticalMargin - size.y + 1, w - 2, size.y - 2);
        shapeRenderer.end();

        float xPos = (pos.x + horizontalMargin + size.x/2) - font.getBounds(caption).width / 2;
        batch.begin();
        font.draw(batch, caption, xPos, pos.y - horizontalMargin - 1 - size.y/2 + font.getBounds(caption).height/2);
        batch.end();
    }
}
