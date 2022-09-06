package src.engine;

import java.awt.Graphics2D;

import java.util.ArrayList;

public class Group extends ArrayList<Entity>
{
    // Constructor
    public Group() {}
    
    // Methods
    public void render(Graphics2D graphics) {
        for (int i=0; i < this.size(); i++)
            this.get(i).render(graphics);
    }

    public void update() {
        for (int i=0; i < this.size(); i++) {
            Entity ce = this.get(i);
            
            if (!ce.alive) {
                this.remove(i);
                return;
            }

            ce.update();
        }
    }
}