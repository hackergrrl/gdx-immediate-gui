package net.sww;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Gui {

    private static ShapeRenderer shapeRenderer;

    public static void init(ShapeRenderer shapeRenderer) {
        Gui.shapeRenderer = shapeRenderer;
    }

	public static void beginFrame() {
	}

	public static void endFrame() {
	}

    public static void beginWindow(String title) {
    }

    public static void endWindow() {
    }
}
