package net.sww;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

abstract class Widget {

    final Widget parent;
    Vector2 pos;
    Vector2 size;
    float horizontalMargin, verticalMargin;
    List<Widget> contents = new LinkedList<Widget>();

    public Widget(Vector2 size) {
        this.parent = Gui.getCurrentWindow();
        if (parent != null) {
            parent.contents.add(this);
        }

        this.size = size;
        this.horizontalMargin = 5;
        this.verticalMargin = 5;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {}

    public Vector2 computeSize() {
        return new Vector2(size.x + horizontalMargin * 2, size.y + verticalMargin * 2);
    }
}
