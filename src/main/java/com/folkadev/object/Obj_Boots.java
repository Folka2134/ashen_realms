package com.folkadev.object;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class Obj_Boots extends SuperObject {

  GamePanel gp;

  public Obj_Boots(GamePanel gp) {
    name = "Boots";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
