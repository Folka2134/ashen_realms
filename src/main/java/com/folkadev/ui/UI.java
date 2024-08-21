package com.folkadev.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.folkadev.GamePanel;

public class UI {
  GamePanel gp;
  Graphics2D g2;
  Font arial_40, arial_60B;
  int messageDuration = 0;
  public boolean messageOn = false;
  public String message = "";
  public boolean gameFinished = false;

  public UI(GamePanel gp) {
    this.gp = gp;
    arial_40 = new Font("Arial", Font.PLAIN, 40);
    arial_60B = new Font("Arial", Font.BOLD, 60);
  }

  public void displayMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;

    g2.setFont(arial_40);
    g2.setColor(Color.white);

    if (gp.gameState == gp.playState) {
      // Play
    }
    if (gp.gameState == gp.pauseState) {
      drawPauseScreen();
    }
  }

  public void drawPauseScreen() {
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
    String text = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight / 2;

    g2.drawString(text, x, y);
  }

  public int getXforCenteredText(String text) {
    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth / 2 - length / 2;
    return x;
  }
}
