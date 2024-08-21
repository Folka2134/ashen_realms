package com.folkadev.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.folkadev.GamePanel;

public abstract class SuperObject {
  public BufferedImage image;
  public String name;
  public boolean collision = false;
  public int worldX, worldY;
  public Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
  public int collisionAreaDefaultX = 0;
  public int collisionAreaDefaultY = 0;

  public void draw(Graphics2D g2, GamePanel gp) {
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
  }
}
