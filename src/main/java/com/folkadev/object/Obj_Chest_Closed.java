package com.folkadev.object;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class Obj_Chest_Closed extends SuperObject {

  GamePanel gp;

  public Obj_Chest_Closed(GamePanel gp) {
    name = "Chest_Closed";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
