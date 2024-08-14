package com.folkadev;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public boolean upPressed, downPressed, leftPressed, rightPress;

  @Override
  public void keyTyped(KeyEvent e) {
    // Unused
  };

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    // Movement keys
    if (code == KeyEvent.VK_W) {
      upPressed = true;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = true;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = true;
    }
    if (code == KeyEvent.VK_D) {
      rightPress = true;
    }
  };

  @Override
  public void keyReleased(KeyEvent e) {

    int code = e.getKeyCode();

    // Movement Keys
    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_D) {
      rightPress = false;
    }
  };

}
