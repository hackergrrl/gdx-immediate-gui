package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class Gui {

    private static ShapeRenderer shapeRenderer;

    private static Window currentWindow;

    private static List<Window> windows = new LinkedList<Window>();


    public static void init(ShapeRenderer shapeRenderer) {
        Gui.shapeRenderer = shapeRenderer;
    }

	public static void beginFrame() {
        currentWindow = null;
        windows.clear();
	}

	public static void endFrame() {
        for (Window window : windows) {
            window.draw(shapeRenderer);
        }
	}

    public static void beginWindow(String title) {
        Window window = new Window();
        window.title = title;
        int x = 10;
        for (Window win : windows) {
            x += win.size.x + 10;
        }
        window.size = new Vector2(100, 300);
        window.pos = new Vector2(x, Gdx.graphics.getHeight() - 10 - window.size.y);

        currentWindow = window;
    }

    public static void endWindow() {
        windows.add(currentWindow);
    }
}
