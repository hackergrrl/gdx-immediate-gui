package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class Window extends Widget {
    public final int TITLE_HEIGHT = 17;
    public String title;
    public Vector2 preferredSize = new Vector2();
    private List<Widget> contents;

    public Window() {
        contents = new LinkedList<Widget>();
    }

    public void addChild(Widget widget) {
        contents.add(widget);
        widget.parent = this;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {

        // Convert to GL coords
        Vector2 pos = new Vector2(this.pos.x, Gdx.graphics.getHeight() - this.pos.y - size.y);

        // Body
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
        shapeRenderer.end();

        // Titlebar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.rect(pos.x, pos.y + size.y - TITLE_HEIGHT, size.x, TITLE_HEIGHT);
        shapeRenderer.end();

        // Title
        batch.getTransformMatrix().idt();
        batch.begin();
        font.draw(batch, title, pos.x + 3, pos.y + size.y - 4);
        font.draw(batch, title, 0, 0);
        batch.end();

        // Contents
        for (Widget widget : contents) {
            widget.draw(batch, shapeRenderer, font);
        }
    }

    public void end(BitmapFont font) {
        size.x = Math.max(font.getBounds(title).width + 10, preferredSize.x);

        float y = pos.y + TITLE_HEIGHT + marginTop;
        for (Widget widget : contents) {
            widget.pos.y = y;
            widget.pos.x = pos.x;
            size.x = Math.max(widget.size.x, Math.max(size.x, preferredSize.x));
            y += widget.computeSize().y;
        }

        size.y = y - pos.y + marginTop + marginBottom;
    }
}