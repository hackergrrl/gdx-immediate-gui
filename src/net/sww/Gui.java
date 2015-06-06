/*
 * Inspired by ImGui
 *
 * https://github.com/ocornut/imgui/blob/master/imgui.h
 */

package net.sww;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class Gui {

    private static ShapeRenderer shapeRenderer;
    private static SpriteBatch spriteBatch;
    private static BitmapFont font;

    private static Window currentWindow;

    private static List<Window> windows = new LinkedList<Window>();

    public static void init(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Gui.shapeRenderer = shapeRenderer;
        Gui.spriteBatch = spriteBatch;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/VeraMono.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

	public static void beginFrame() {
        currentWindow = null;
        windows.clear();
	}

	public static void endFrame() {
        for (Window window : windows) {
            window.draw(spriteBatch, shapeRenderer, font);
        }
	}

    public static void beginWindow(String title) {
        Window window = new Window();
        window.title = title;
        int x = 10;
        for (Window win : windows) {
            x += win.size.x + 20;
        }
        window.size = new Vector2(100, 20);
        window.pos = new Vector2(x, 10);

        currentWindow = window;
    }

    public static void endWindow() {
        currentWindow.end(font);
        windows.add(currentWindow);

        currentWindow = null;
    }

    public static void setWindowWidth(int w) {
        currentWindow.preferredSize.x = w;
    }

    public static void setWindowHeight(int h) {
        currentWindow.preferredSize.y = h;
    }

    public static void setWindowTitleColour(Color colour) {
        currentWindow.titleColour = colour;
    }

    public static void label(String text) {
        Label label = new Label(text);
        label.size.y = font.getBounds(text).height;
        label.size.x = font.getBounds(text).width + 6;
        currentWindow.addChild(label);
    }

    public static void progressBar(String caption, Color colour, float value, float max, boolean showPercent) {
        ProgressBar bar = new ProgressBar(caption, colour, value, max, showPercent);
        bar.size.x = currentWindow.size.x;
        bar.parent = currentWindow;
        currentWindow.addChild(bar);
    }
}
