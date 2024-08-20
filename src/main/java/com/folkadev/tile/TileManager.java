package com.folkadev.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.folkadev.GamePanel;
import com.folkadev.UtilityTool;

public class TileManager {
  public Tile[] tile;
  public int mapTileNum[][];
  GamePanel gp;

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[100];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

    getTileImage();
    loadMapData("/map/world01.txt");
    // loadMapData("/map/worldV2.txt");
  }

  public void getTileImage() {
    setup("00", "earth", false);
    setup("01", "floor01", false);
    setup("02", "grass00", false);
    setup("03", "grass01", false);
    setup("04", "hut", true);
    setup("05", "road00", false);
    setup("06", "road01", false);
    setup("07", "road02", false);
    setup("08", "road03", false);
    setup("09", "road04", false);
    setup("10", "road05", false);
    setup("11", "road06", false);
    setup("12", "road07", false);
    setup("13", "road08", false);
    setup("14", "road09", false);
    setup("15", "road10", false);
    setup("16", "road11", false);
    setup("17", "road12", false);
    setup("18", "table01", true);
    setup("19", "tree", true);
    setup("20", "wall", true);
    setup("21", "water00", true);
    setup("22", "water01", true);
    setup("23", "water02", true);
    setup("24", "water03", true);
    setup("25", "water04", true);
    setup("26", "water05", true);
    setup("27", "water06", true);
    setup("28", "water07", true);
    setup("29", "water08", true);
    setup("30", "water09", true);
    setup("31", "water10", true);
    setup("32", "water11", true);
    setup("33", "water12", true);
    setup("34", "water13", true);
  }

  public void setup(String str, String imageName, boolean collision) {
    UtilityTool uTool = new UtilityTool();
    try {
      // CONVERT STRING TO INDEX
      int num = convertStrIndex(str);
      tile[num] = new Tile();
      tile[num].image = ImageIO.read(getClass().getResourceAsStream("/images/tiles/" + imageName + ".png"));
      tile[num].image = uTool.scaleImage(tile[num].image, gp.tileSize, gp.tileSize);
      tile[num].collision = collision;

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int convertStrIndex(String str) {
    int result = 0;
    String num;

    if (str.charAt(0) == '0') {
      num = String.valueOf(str.charAt(1));
      result = Integer.parseInt(num);
    } else {
      result = Integer.parseInt(str);
    }
    return result;
  }

  public void loadMapData(String filePath) {
    try {
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
        String line = br.readLine();
        while (col < gp.maxWorldCol) {
          String numbers[] = line.split(" ");

          int num = Integer.parseInt(numbers[col]);
          mapTileNum[col][row] = num;
          col++;
        }
        if (col == gp.maxWorldCol) {
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
    int worldCol = 0;
    int worldRow = 0;

    // DRAW MAP
    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

      while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

        int tileNum = mapTileNum[worldCol][worldRow];

        int worldX = worldCol * gp.tileSize;
        int worldY = worldRow * gp.tileSize;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Stop moving camera at the edge
        if (gp.player.screenX > gp.player.worldX) {
          screenX = worldX;
        }

        if (gp.player.screenY > gp.player.worldY) {
          screenY = worldY;
        }

        int rightOffset = gp.screenWidth - gp.player.screenX;
        if (rightOffset > gp.worldWidth - gp.player.worldX) {
          screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if (bottomOffset > gp.worldHeight - gp.player.worldY) {
          screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
          g2.drawImage(tile[tileNum].image, screenX, screenY, null);
        } else if (gp.player.screenX > gp.player.worldX ||
            gp.player.screenY > gp.player.worldY ||
            rightOffset > gp.worldWidth - gp.player.worldX ||
            bottomOffset > gp.worldHeight - gp.player.worldY) {
          g2.drawImage(tile[tileNum].image, screenX, screenY, null);
        }

        worldCol++;

        if (worldCol == gp.maxWorldCol) {
          worldCol = 0;
          worldRow++;
        }
      }
    }
  }
}