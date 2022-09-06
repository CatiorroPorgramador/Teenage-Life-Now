package src.engine;

import java.awt.Graphics2D;

public abstract class Scene
{
    public String name;
    public boolean paused = false;

    public Object contact[];

    public int window_width, window_height;

    public int mouse_position_x, mouse_position_y;

    // Methods
    public void update() {}

    public void render(Graphics2D graphics)  {}

    public void key_typed(int key) {}

    public void key_pressed(int key) {}

    public void key_released(int key) {}

    public void mouse_clicked(int button) {}

    public void mouse_pressed(int button) {}
    
    public void mouse_released(int button) {}
}
