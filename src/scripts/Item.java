package src.scripts;

import java.awt.image.BufferedImage;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import src.engine.Entity;

public class Item extends Entity {
    // Variables
    float speed;

    BufferedImage texture;
    Image sprite;
    int frame, hframes, vframes;

    ArrayList<Object[]> items_array;

    // Constructor
    public Item() {
        // Load Images And Initialize Some Objects
        rect = new Rectangle(300, 0, 48, 48);
        speed = 1.5f + new Random().nextFloat(2);

        try {
            texture = ImageIO.read(new File("data\\items-spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hframes = 9;
        vframes = 1;

        // Items Start
        items_array = new ArrayList<Object[]>();

        // Init
        /* Array Order
        * 0 - frame
        * 1 - +intelligence
        * 2 - +life
        * 3 - +fault
        * 4 - +stress
        * 5 - +happy
        * 6 - +problems
        * 7 - +money */

        // Book - id 0
        items_array.add(new Object[]{0, 10, 5, 0, -5, 0, 0, 0});
        
        // Cocaine - id 1
        items_array.add(new Object[]{1, 0, -5, 10, 0, 50, 10, 0});

        // Bible - id 2
        items_array.add(new Object[]{2, 10, 10, 0, -10, 20, -10, 0});

        // Gun - id 3
        items_array.add(new Object[]{3, 2, -10, 20, 20, 0, 10, 200});
        
        // Heart - id 4
        items_array.add(new Object[]{4, 5, -5, -10, -10, 20, 10, -50});

        // Money - id 5
        items_array.add(new Object[]{5, 0, 0, 5, -5, 20, 0, 20});

        // Weed - id 6
        items_array.add(new Object[]{6, -2, -5, 5, -20, 10, 10, 10});

        // Work - id 7
        items_array.add(new Object[]{7, 15, 10, -10, 0, 0, -10, 50});

        // Sleep - id 8
        items_array.add(new Object[]{8, 0, 10, 0, -15, 5, 0, 0});
        
        // Items End

        // Change Item Type
        this.rect.x += new Random().nextInt(402);

        int id = new Random().nextInt(3);

        frame = (int) items_array.get(id)[0];
        sprite = get_sprite();
    }

    // Functions
    public Image get_sprite() {
        return texture.getSubimage (
            frame*texture.getWidth()/hframes, 0, texture.getWidth(null)/hframes,
            texture.getHeight(null)/vframes
        );
    }

    // Methods
    @Override
    public void update() {
        rect.y += speed;

        // Dead Control
        if (this.rect.y > 550 || colliding) {
            destroy();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
    }
}
