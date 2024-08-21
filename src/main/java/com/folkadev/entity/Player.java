package com.folkadev.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;
import com.folkadev.KeyHandler;
import com.folkadev.UtilityTool;
import com.folkadev.object.Obj_Chest_Open;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;
  public final int screenX;
  public final int screenY;
  public int keys = 0;
  int idleCounter = 0;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    // PLAYER POSITION
    screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

    // OBJECT COLLISION
    collisionArea = new Rectangle();
    collisionArea.x = 8;
    collisionArea.y = 20;
    collisionAreaDefaultX = collisionArea.x;
    collisionAreaDefaultY = collisionArea.y;
    collisionArea.width = 24;
    collisionArea.height = 24;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage() {
    up1 = setup("boy_up_1");
    up2 = setup("boy_up_2");
    down1 = setup("boy_down_1");
    down2 = setup("boy_down_2");
    left1 = setup("boy_left_1");
    left2 = setup("boy_left_2");
    right1 = setup("boy_right_1");
    right2 = setup("boy_right_2");
  }

  public BufferedImage setup(String imageName) {
    UtilityTool uTool = new UtilityTool();
    BufferedImage image = null;

    try {
      image = ImageIO
          .read(getClass().getResourceAsStream("/images/player/walking_sprites/" + imageName + ".png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }

  public void update() {
    // Player movement
    if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
      if (keyH.upPressed == true) {
        direction = "up";
      } else if (keyH.downPressed == true) {
        direction = "down";
      } else if (keyH.leftPressed == true) {
        direction = "left";
      } else if (keyH.rightPressed == true) {
        direction = "right";
      }

      // Check tile collision
      collisionOn = false;
      gp.collisionChecker.checkTile(this);

      // Check object collision
      int objIndex = gp.collisionChecker.checkObject(this, true);
      pickUpObject(objIndex);

      if (collisionOn == false) {
        switch (direction) {
          case "up":
            worldY -= speed;
            break;
          case "down":
            worldY += speed;
            break;
          case "left":
            worldX -= speed;
            break;
          case "right":
            worldX += speed;
            break;
        }
      }

      spriteCounter++;
      if (spriteCounter > 14) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    } else {
      idleCounter++;
      if (idleCounter == 20) {
        spriteNum = 1;
        idleCounter = 0;
      }
    }
  }

  public void pickUpObject(int index) {
    if (index != 999) {
      String objectName = gp.obj[index].name;
      switch (objectName) {
        case "Key":
          keys++;
          gp.obj[index] = null;
          gp.ui.displayMessage("Rusty key found");
          break;
        case "Iron Door":
          if (keys > 0) {
            gp.obj[index] = null;
            keys--;
            gp.ui.displayMessage("Key used");
          } else {
            gp.ui.displayMessage("It's locked");
          }
          break;
        case "Boots":
          speed += 2;
          gp.obj[index] = null;
          gp.ui.displayMessage("Speed Boots equipped");
          break;
        case "Chest_Closed":
          gp.obj[index] = null;
          gp.obj[3] = new Obj_Chest_Open(gp);
          gp.obj[3].worldX = 10 * gp.tileSize;
          gp.obj[3].worldY = 7 * gp.tileSize;
          gp.ui.gameFinished = true;
          break;
      }
    }
  }

  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (spriteNum == 1) {
          image = up1;
        }
        if (spriteNum == 2) {
          image = up2;
        }
        break;
      case "down":
        if (spriteNum == 1) {
          image = down1;
        }
        if (spriteNum == 2) {
          image = down2;
        }
        break;
      case "left":
        if (spriteNum == 1) {
          image = left1;
        }
        if (spriteNum == 2) {
          image = left2;
        }
        break;
      case "right":
        if (spriteNum == 1) {
          image = right1;
        }
        if (spriteNum == 2) {
          image = right2;
        }
        break;
    }

    g2.drawImage(image, screenX, screenY, null);
  }
}
