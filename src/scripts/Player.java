package src.scripts;

import src.engine.*;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.event.KeyEvent;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Color;

public class Player extends Entity {
    // Self and Motion
    float attributes[];

    public int dx, dy, speed;

    Image sprite;
    BufferedImage texture;

    // Animation
    int hframes, vframes, frame;
    boolean hflip, vflip, show_rect;

    // Functions
    public void update_frame() {
        sprite = get_sprite();
    }

    public Image get_sprite() {
        return texture.getSubimage (
            frame*texture.getWidth()/hframes, 0, texture.getWidth(null)/hframes,
            texture.getHeight(null)/vframes
        );
    }

    // Constructor
    public Player() {
        name = "Player";
        
        // Instances
        attributes = new float[]{10, 25, 0, 0, 0, 0, 0};

        rect = new Rectangle(750/2, 460, 48, 48);
        speed = 4;

        hframes = 4;
        vframes = 1;
        frame = 0;
        hflip = false;
        vflip = false;
        show_rect = true;

        // Load
        try {
            texture = ImageIO.read(new File("data\\player-spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        update_frame();
        System.out.println("Player initialized...");
    }

    // Functions
    void receive_attributes(float att[]) {
        /* Array Order
        * 0 - frame
        * 1 - +intelligence
        * 2 - +life
        * 3 - +fault
        * 4 - +stress
        * 5 - +happy
        * 6 - +problems
        * 7 - +money */
    }

    // Methods
    @Override
    public void update() {
        // Physics
        rect.x += dx;
        rect.y += dy;

        if (rect.x > 750 - rect.width) {
            rect.x = 750 - rect.width;
        } else if (rect.x < 300) {
            rect.x = 300;
        }

        // Intelligence

        // Life
        attributes[1] += attributes[4]; // When you happy: +life

        // Stress
        attributes[3] += (attributes[2]/attributes[2])/2; // Stress increase when your fault increase

        // Happy
        attributes[5] -= (attributes[3]/attributes[3])/2;
    }

    @Override
    public void render(Graphics2D graphics) {
        if (show_rect) {
            graphics.setColor(Color.BLUE);
        }
        graphics.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
    }

    public void key_pressed(int key) {
        if (key == KeyEvent.VK_RIGHT)
            dx = speed;
        else if (key == KeyEvent.VK_LEFT)
            dx = -speed;
    }

    public void key_released(int key) {
        if (key == KeyEvent.VK_RIGHT)
            dx = 0;
        else if (key == KeyEvent.VK_LEFT)
            dx = 0;
    }
}