package com.folkadev.object;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class Obj_Door_Iron extends SuperObject {

  GamePanel gp;

  public Obj_Door_Iron(GamePanel gp) {
    name = "Iron Door";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (Exception e) {
      // TODO: handle exception
    }
    collision = true;
  }
}
