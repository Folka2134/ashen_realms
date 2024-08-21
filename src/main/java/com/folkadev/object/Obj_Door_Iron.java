package com.folkadev.object;

import javax.imageio.ImageIO;

public class Obj_Door_Iron extends SuperObject {

  public Obj_Door_Iron() {
    name = "Iron Door";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
    } catch (Exception e) {
      // TODO: handle exception
    }
    collision = true;
  }
}
