package src.scenes;

import src.engine.*;

import java.awt.event.KeyEvent;
import java.util.Random;

import java.awt.Color;
import java.awt.Graphics2D;

import src.scripts.Player;
import src.scripts.Item;

public class Gameplay extends Scene {
    // player_group
    Group player_group, items_group;

    Player player;

    // Variables
    int day, items_index, items_add;
    
    String keys[];
    int values[];

    // Constructor
    public Gameplay() {
        // Instance
        contact = new Object[10];
        contact[0] = true;

        player_group  = new Group();
        items_group  = new Group();

        player = new Player();

        // Player
        player_group.add(player);

        // Variables
        day = 0;
        items_index = 0;
        items_add = 1;

        keys = new String[]{"intelligence", "life","fault", "stress", "happy", "money"};
        values = new int[]{0, 100, 0, 0, 50, 0};
    }

    // Functions
    void render_status(Graphics2D graphics) {
        int y = 55;
        for (int i=0; i < keys.length; i++) {
            graphics.drawString(keys[i], 10, y + (20*i));
            graphics.drawString(String.valueOf(values[i]), 170, y + (20*i));
        }
    }

    void spawn_control() {
        items_index += items_add;

        if (items_index > 60) {
            items_index = 0;

            if (new Random().nextBoolean()) {
                items_group.add(new Item());
            }
        }
    }

    // Methods
    @Override
    public void update() {
        player_group.update();
        items_group.update();

        spawn_control();

        for (int i=0; i < items_group.size(); i++) {
            Entity cur_obj = items_group.get(i);

            if (player.is_colliding(cur_obj.rect)) {

                cur_obj.destroy();
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(0, 213, 255));
        graphics.fillRect(0, 0, 750, 550);

        player_group.render(graphics);
        items_group.render(graphics);

        // UI render
        graphics.setColor(new Color(214, 214, 214));
        graphics.fillRect(0, 0, 300, 550);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(2, 3, 295, 545);

        graphics.drawString("Status:", 10, 30);
        render_status(graphics);
        graphics.drawLine(0, 165, 295, 165);

        graphics.drawString("Debug: ", 10, 190);
    }

    @Override
    public void key_pressed(int key) {
        player.key_pressed(key);
    }

    @Override
    public void key_released(int key) {
        player.key_released(key);

        if (key == KeyEvent.VK_0)
            contact[0] = !((boolean) contact[0]);
    }
}