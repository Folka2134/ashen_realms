package com.folkadev.object;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class Obj_Chest_Open extends SuperObject {

  GamePanel gp;

  public Obj_Chest_Open(GamePanel gp) {
    name = "Chest_Open";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_opened.png"));
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
