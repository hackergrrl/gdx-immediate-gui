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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class Gui {

    private static ShapeRenderer shapeRenderer;
    private static SpriteBatch spriteBatch;
    private static BitmapFont font;

    private static Window currentWindow;

    private static List<Window> windows = new LinkedList<Window>();

    private static int buttonPressedFrameCount;

    public static void init(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Gui.shapeRenderer = shapeRenderer;
        Gui.spriteBatch = spriteBatch;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/VeraMono.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public static Window getCurrentWindow() {
        return currentWindow;
    }

	public static void beginFrame() {
        currentWindow = null;
        windows.clear();

        if (Gdx.input.isButtonPressed(0)) {
            buttonPressedFrameCount++;
        } else {
            buttonPressedFrameCount = 0;
        }
	}

	public static void endFrame() {
        for (Window window : windows) {
            window.draw(spriteBatch, shapeRenderer, font);
        }
	}

    public static void beginWindow(String title) {
        // TODO(sww): remove this hack?
        int x = 10;
        for (Window win : windows) {
            x += win.size.x + 20;
        }
        beginWindow(title, new Vector2(x, 10));
    }

    public static void beginWindow(String title, Vector2 pos) {
        Window window = new Window(pos, new Vector2(100, 20));
        window.title = title;
        currentWindow = window;
    }

    public static void endWindow() {
        currentWindow.end(font);
        windows.add(currentWindow);

        currentWindow = null;
    }

    public static void setWindowWidth(int w) {
        currentWindow.size.x = w;
    }

    public static void setWindowHeight(int h) {
        currentWindow.size.y = h;
    }

    public static void setWindowTitleColour(Color colour) {
        currentWindow.titleColour = colour;
    }

    public static void label(String text) {
        Label label = new Label(text);
        for (int i=0; i < label.lines.length; i++) {
            label.size.x = Math.max(label.size.x, font.getBounds(label.lines[i]).width + 10);
        }
        label.size.y = font.getBounds(text).height * label.lines.length + Label.LINE_PADDING * (label.lines.length - 1) + 5;
        label.pos = currentWindow.getCursorPos();
        label.size = currentWindow.addChild(label);
    }

    public static void progressBar(String caption, Color colour, float value, float max, boolean showPercent) {
        ProgressBar bar = new ProgressBar(caption, colour, value, max, showPercent);
        bar.size.x = currentWindow.size.x;
        bar.pos = currentWindow.getCursorPos();
        currentWindow.addChild(bar);
    }

    public static boolean button(String caption, Color colour) {
        Button btn = new Button(caption, colour);
        btn.pos = currentWindow.getCursorPos();
        btn.size = currentWindow.addChild(btn);

        Rectangle rect = new Rectangle(
                btn.pos.x,
                btn.pos.y,
                btn.size.x,
                btn.size.y);
        btn.hovering = rect.contains(Gdx.input.getX(), Gdx.input.getY());
        return buttonPressedFrameCount == 1 && btn.hovering;
    }
}
