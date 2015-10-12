package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

class Window extends Widget {
    public final int TITLE_HEIGHT = 17;
    public String title;
    public Color titleColour = Color.TEAL;

    private Vector2 cursor = new Vector2();
    private boolean usedGlobalPositioning = false;

    public Window(Vector2 size) {
        super(size);

        pos = new Vector2();
        pos.x = Gui.globalX;

        usedGlobalPositioning = true;

        cursor = new Vector2(pos.x + horizontalMargin, pos.y + verticalMargin + TITLE_HEIGHT);
    }

    public Window(Vector2 pos, Vector2 size) {
        super(size);
        this.pos = pos;
        cursor = new Vector2(pos.x + horizontalMargin, pos.y + verticalMargin + TITLE_HEIGHT);
    }

    public Vector2 getCursorPos() {
        return cursor.cpy();
    }

    public Vector2 addChild(Widget widget) {
        cursor.y += widget.computeSize().y;
        size.x = Math.max(widget.size.x, size.x);
        return new Vector2(size.x, widget.size.y);
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {

        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y - size.y);

        // Body + Outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY.cpy().mul(0.5f));
        shapeRenderer.rect(pos.x, pos.y, size.x + horizontalMargin * 2, size.y - TITLE_HEIGHT);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(pos.x + 1, pos.y + 1, size.x + horizontalMargin * 2 - 2, size.y - TITLE_HEIGHT - 1);
        shapeRenderer.end();

        // Titlebar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(titleColour.cpy().mul(0.5f));
        shapeRenderer.rect(pos.x, pos.y + size.y - TITLE_HEIGHT, size.x + horizontalMargin * 2, TITLE_HEIGHT);
        shapeRenderer.setColor(titleColour);
        shapeRenderer.rect(pos.x + 1, pos.y + size.y - TITLE_HEIGHT + 1, size.x + horizontalMargin * 2 - 2, TITLE_HEIGHT - 2);
        shapeRenderer.end();

        // Title
        if (title != null) {
            batch.getTransformMatrix().idt();
            batch.begin();
            font.draw(batch, title, pos.x + 3, pos.y + size.y - 4);
            font.draw(batch, title, 0, 0);
            batch.end();
        }

        // Contents
        for (Widget widget : contents) {
            widget.draw(batch, shapeRenderer, font);
        }
    }

    public void end(BitmapFont font) {
        if (title != null) {
            size.x = Math.max(font.getBounds(title).width + horizontalMargin * 2, size.x);
        }

        float y = pos.y + TITLE_HEIGHT + verticalMargin;
        for (Widget widget : contents) {
//            widget.pos.y = y;
//            widget.pos.x = pos.x;
            y += widget.computeSize().y;
        }

        size.y = y - pos.y;

        // HACK: fix me
        if (usedGlobalPositioning) {
            Gui.globalX += size.x + 20;
        }
    }
}