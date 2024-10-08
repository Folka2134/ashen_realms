package com.folkadev;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.folkadev.entity.Player;
import com.folkadev.object.SuperObject;
import com.folkadev.tile.TileManager;
import com.folkadev.ui.UI;

public class GamePanel extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int originalTileSize = 16; // 16 * 16
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol;
  public final int screenHeight = tileSize * maxScreenRow;

  // WORLD SETTINGS
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;

  // FPS
  final int FPS = 60;

  public Thread gameThread;

  TileManager tileManager = new TileManager(this);
  KeyHandler keyH = new KeyHandler(this);
  public AssetSetter assetSetter = new AssetSetter(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  public SuperObject obj[] = new SuperObject[10];
  public UI ui = new UI(this);
  public Player player = new Player(this, keyH);

  // GAME STATE
  public int gameState;
  public final int playState = 1;
  public final int pauseState = 2;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void setupGame() {
    assetSetter.setObject();
    gameState = playState;
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // Game loop
  /// Refactor: Inefficient method
  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    // long timer = 0;
    // long drawCount = 0;

    while (gameThread != null) {

      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      // timer += (currentTime - lastTime);
      lastTime = currentTime;
      if (delta >= 1) {
        update(); // UPDATE GAMESTATE
        repaint(); // DRAW GAMESTATE
        delta--;
        // drawCount++;
      }

      // if (timer >= 1000000000) {
      // // display fps
      // System.out.println("FPS:" + drawCount);
      // drawCount = 0;
      // timer = 0;
      // }
    }

  }

  public void update() {

    if (gameState == playState) {
      player.update();
    }
    if (gameState == pauseState) {
      // TODO
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    // DEBUG
    long drawStart = 0;
    if (keyH.checkDrawTime == true) {
      drawStart = System.nanoTime();
    }

    // Draw tiles
    tileManager.draw(g2);
    // Draw object
    for (int i = 0; i < obj.length; i++) {
      if (obj[i] != null) {
        obj[i].draw(g2, this);
      }
    }
    // Draw player
    player.draw(g2);

    // Draw UI
    ui.draw(g2);

    // DEBUG

    if (keyH.checkDrawTime == true) {
      long drawEnd = System.nanoTime();
      long passed = drawEnd - drawStart;
      g2.setColor(Color.white);
      g2.drawString("Draw Time: " + passed, 10, 400);
      System.out.println("Draw Time: " + passed);
    }
    g2.dispose();
  }
}