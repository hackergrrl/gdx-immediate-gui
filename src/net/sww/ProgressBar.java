package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

class ProgressBar extends Widget {

    private float value, max;
    private boolean showPercent;
    private Color colour;
    private String caption;

    public ProgressBar(String caption, Color colour, float value, float max, boolean showPercent) {
        super(new Vector2(16, 16));
        this.caption = caption;
        this.colour = colour;
        this.value = value;
        this.showPercent = showPercent;
        this.max = max;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y);
        size.x = parent.size.x;

        float fontHeight = font.getBounds(caption).height;

        float x = 0;
        if (caption != null && !caption.isEmpty()) {
            batch.begin();
            font.draw(batch, caption, pos.x + horizontalMargin, pos.y - verticalMargin - size.y/2 + fontHeight/2);
            batch.end();
            x += font.getBounds(caption).width + 16;
        }

        float w = size.x - verticalMargin * 2 - x;
        w *= (value / max);
        shapeRenderer.setColor(colour);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x + pos.x + horizontalMargin, pos.y - verticalMargin - size.y, w, size.y);
        shapeRenderer.end();

        if (showPercent) {
            String percent = ((int)(value / max * 100)) + "%";
            float xPos = (x + pos.x + horizontalMargin + w/2) - font.getBounds(percent).width / 2;
            batch.begin();
            font.draw(batch, percent, xPos, pos.y - verticalMargin - size.y/2 + fontHeight/2);
            batch.end();
        }
    }
}
