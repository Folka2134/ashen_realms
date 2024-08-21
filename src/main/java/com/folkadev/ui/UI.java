package com.folkadev.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.folkadev.GamePanel;
import com.folkadev.object.Obj_Key;

public class UI {
  // inventory
  // health
  // mana
  // loot text
  // narative text
  // map

  GamePanel gp;
  Font arial_40, arial_60B;
  BufferedImage keyImage;
  int messageDuration = 0;
  public boolean messageOn = false;
  public String message = "";
  public boolean gameFinished = false;

  public UI(GamePanel gp) {
    this.gp = gp;
    arial_40 = new Font("Arial", Font.PLAIN, 40);
    arial_60B = new Font("Arial", Font.BOLD, 60);
    Obj_Key key = new Obj_Key(gp);
    keyImage = key.image;
  }

  public void displayMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {

    if (gameFinished == true) {

      String text;
      int textLength;
      int x;
      int y;

      g2.setFont(arial_60B);
      g2.setColor(Color.green);

      text = "Mission Completed!";
      textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      x = gp.screenWidth / 2 - textLength / 2;
      y = gp.screenHeight / 2 + (gp.tileSize * 3);
      g2.drawString(text, x, y);

      gp.gameThread = null;

    } else {
      g2.setFont(arial_40);
      g2.setColor(Color.white);
      g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
      g2.drawString("x " + gp.player.keys, 74, 65);

      if (messageOn == true) {
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

        messageDuration++;

        if (messageDuration > 120) {
          messageDuration = 0;
          messageOn = false;
        }
      }
    }
  }
}
