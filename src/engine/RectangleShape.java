package src.engine;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class RectangleShape extends Entity {
    // Self
    public Color color;

    // Constructor
    public RectangleShape() {
        // Self
        rect = new Rectangle(0, 0, 50, 50);
        color = new Color(0, 0, 0);
    }

    // Methods
    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
}
