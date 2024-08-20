package com.folkadev.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject {

  public Obj_Key() {
    name = "Key";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
    } catch (IOException e) {
      // TODO: handle exception
    }
  }
}
