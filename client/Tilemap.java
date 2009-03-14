/*
 * Copyright 2009 Casey Dwyer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

/**
 * Tilemap
 */
public final class Tilemap extends Composite {
  private static final int TILE_SIZE = 16;
  private static final String TILE_SET = "8blocks-gw-16.png";
  private static Tile tiles[][];
  private static Grid grid;
  
  public Tilemap(int width, int height) {
    grid = new Grid(height, width);
    tiles = new Tile[getWidth()][getHeight()];
    for (int i = 0; i != getWidth(); ++i) {
      for (int j = 0; j != getHeight(); ++j) {
        tiles[i][j] = new Tile();
        grid.setWidget(j, i, tiles[i][j]);
      }
    }
    grid.setCellPadding(0);
    grid.setCellSpacing(0);
    grid.setStyleName("field");
    clear();
    initWidget(grid);
  }
  
  public static void clear() {
    for (int i = 0; i != getWidth(); ++i) {
      for (int j = 0; j != getHeight(); ++j) {
        tiles[i][j].setValue(0);
        drawTile(i, j, 0);
      }
    }
  }
  
  public static void clearLine(int y) {
    for (int i = 0; i != y; ++i) {
      for (int j = 0; j != getWidth(); ++j) {
        int tile = tiles[j][y-i-1].getValue();
        if (tiles[j][y-i].getValue() != tile) {
          tiles[j][y-i].setValue(tile);
          drawTile(j, y-i, tile);
        }
        if (y-i-1 == 0 && tile != 0) {
          tiles[j][0].setValue(0);
          drawTile(j, 0, 0);
        }
      }
    }
  }
  
  public static boolean collisionCheck(int x, int y) {
    if (x < 0 || x >= getWidth() ||
        y < 0 || y >= getHeight() ||
        tiles[x][y].getValue() != 0) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public static void drawTile(int x, int y, int n) {
    tiles[x][y].draw(n);
  }
  
  public static int getHeight() {
    return grid.getRowCount();
  }
  
  public static int getWidth() {
    return grid.getColumnCount();
  }
  
  public static boolean isLine(int y) {
    if (y < getHeight()) {
      for (int i = 0; i != getWidth(); ++i) {
        if (tiles[i][y].getValue() == 0) {
          return false;
        }
      }
      return true;
    }
    else {
      return false;
    }
  }
  
  public static void setTile(int x, int y, int tile) {
    tiles[x][y].setValue(tile);
  }
}
