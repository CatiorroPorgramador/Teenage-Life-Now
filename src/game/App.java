package src.game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.FontFormatException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import src.engine.Scene;
import src.scenes.Gameplay;

class App extends Canvas implements Runnable, KeyListener, MouseListener {
    // Variables
    long last_time;
    double fps;

    public static Dimension dimension = new Dimension(750, 550);
    
    Scene cur_sce;

    // Render
    Font font;

    boolean show_fps;

    // Constructor
    public App() {
        setFocusable(true);
        setPreferredSize(dimension);
        
        addKeyListener(this);
        addMouseListener(this);
        
        cur_sce = new Gameplay();

        // Load
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data\\Font.ttf")).deriveFont(24f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App app = new App();

        // Window
        JFrame window = new JFrame("Survive Vania");
        window.add(app);
        window.setSize(dimension);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        window.pack();

        // Thread
        new Thread(app).start();
    }

    // Methods
    public void update() {
        cur_sce.update();

        show_fps = (boolean) cur_sce.contact[0];
    }

    public void render() {
        if (getBufferStrategy() == null) {
            System.out.println("'App->BufferStrategy' not exist!");
            this.createBufferStrategy(2);
            System.out.println("'App->BufferStrategy' Created!");
            return;
        }

        Graphics2D graphics = (Graphics2D) getBufferStrategy().getDrawGraphics();

        graphics.setFont(font);
        graphics.setStroke(new BasicStroke(5));

        cur_sce.render(graphics);

        if (show_fps) {
            graphics.setColor(Color.WHITE);
            graphics.drawString("FPS: "+(int)fps, 650, 30);
        }
        getBufferStrategy().show();
        graphics.dispose();
    }
    
    @Override
    public void run() {
        while (true) {
            last_time = System.nanoTime();

            update();
            render();

            try {
                Thread.sleep((long) 9);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fps = 1000000000.0 / (System.nanoTime() - last_time);
            last_time = System.nanoTime();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        cur_sce.key_pressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        cur_sce.key_released(key);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cur_sce.mouse_clicked(e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cur_sce.mouse_pressed(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        cur_sce.mouse_released(e.getButton());
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
}