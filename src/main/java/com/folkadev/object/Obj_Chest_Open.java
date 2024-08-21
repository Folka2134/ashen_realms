package com.folkadev.object;

import javax.imageio.ImageIO;

public class Obj_Chest_Open extends SuperObject {

  public Obj_Chest_Open() {
    name = "Chest_Open";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_opened.png"));
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
