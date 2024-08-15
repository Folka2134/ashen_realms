package com.folkadev.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;

public class TileManager {
  GamePanel gp;
  Tile[] tile;
  int mapData[][];
  private String[] tilePaths = {
      // 0
      "/images/tiles/earth.png",
      // 1
      "/images/tiles/floor01.png",
      // 2
      "/images/tiles/grass00.png",
      // 3
      "/images/tiles/grass01.png",
      // 4
      "/images/tiles/hut.png",
      // 5
      "/images/tiles/road01.png",
      // 6
      "/images/tiles/road02.png",
      // 7
      "/images/tiles/road03.png",
      // 8
      "/images/tiles/road04.png",
      // 9
      "/images/tiles/road05.png",
      // 10
      "/images/tiles/road06.png",
      // 11
      "/images/tiles/road07.png",
      // 12
      "/images/tiles/road08.png",
      // 13
      "/images/tiles/road09.png",
      // 14
      "/images/tiles/road10.png",
      // 15
      "/images/tiles/road11.png",
      // 16
      "/images/tiles/road12.png",
      // 17
      "/images/tiles/tree.png",
      // 18
      "/images/tiles/wall.png",
      // 19
      "/images/tiles/water00.png",
      // 20
      "/images/tiles/water01.png",
      // 21
      "/images/tiles/water02.png",
      // 22
      "/images/tiles/water03.png",
      // 23
      "/images/tiles/water04.png",
      // 24
      "/images/tiles/water05.png",
      // 25
      "/images/tiles/water06.png",
      // 26
      "/images/tiles/water07.png",
      // 27
      "/images/tiles/water08.png",
      // 28
      "/images/tiles/water09.png",
      // 29
      "/images/tiles/water10.png",
      // 30
      "/images/tiles/water11.png",
      // 31
      "/images/tiles/water12.png",
      // 32
      "/images/tiles/water13.png"
  };

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[tilePaths.length];
    mapData = new int[gp.maxScreenCol][gp.maxScreenRow];

    getTileImage();
    loadMapData("/map/mapData1.txt");
  }

  public void getTileImage() {
    for (int i = 0; i < tilePaths.length; i++) {
      try {
        tile[i] = new Tile();
        tile[i].image = ImageIO.read(getClass().getResourceAsStream(tilePaths[i]));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void loadMapData(String filePath) {
    try {
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
        String line = br.readLine();
        while (col < gp.maxScreenCol) {
          String numbers[] = line.split(" ");

          int num = Integer.parseInt(numbers[col]);
          mapData[col][row] = num;
          col++;
        }
        if (col == gp.maxScreenCol) {
          col = 0;
          row++;
        }
      }
      br.close();

    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void draw(Graphics2D g2) {
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    // draw environment
    while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

      int tileNum = mapData[col][row];

      g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
      col++;
      x += gp.tileSize;

      if (col == gp.maxScreenCol) {
        col = 0;
        x = 0;
        row++;
        y += gp.tileSize;
      }
    }
  }
}
