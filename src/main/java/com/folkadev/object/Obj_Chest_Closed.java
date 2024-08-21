package com.folkadev.object;

import javax.imageio.ImageIO;

public class Obj_Chest_Closed extends SuperObject {

  public Obj_Chest_Closed() {
    name = "Chest_Closed";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
