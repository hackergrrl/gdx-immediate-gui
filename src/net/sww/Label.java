package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

class Label extends Widget {

    public static final int LINE_PADDING = 8;
    String[] lines;
    private String text;

    public Label(String text) {
        super(new Vector2(100, 24));
        this.text = text;
        lines = text.split("\n");
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y);

        batch.begin();
        float h = font.getBounds(text).height + LINE_PADDING;
        for (int i=0; i < lines.length; i++) {
            font.draw(batch, lines[i], pos.x + horizontalMargin, pos.y - verticalMargin - h * i);
        }
        batch.end();
    }
}
