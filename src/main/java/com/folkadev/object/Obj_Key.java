package com.folkadev.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class Obj_Key extends SuperObject {

  GamePanel gp;

  public Obj_Key(GamePanel gp) {
    name = "Key";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      // TODO: handle exception
    }
  }
}
