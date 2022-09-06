package src.engine;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Entity {
    // Appearence
    public boolean alive=true, colliding=false;

    public String name;

    public Rectangle rect = new Rectangle();
    
    // Methods
    public boolean is_colliding(Rectangle rect) {
        return this.rect.intersects(rect);
    }

    public void update() {}

    public void render(Graphics2D graphics) {}

    public void destroy() {
        alive = false;
    }
}