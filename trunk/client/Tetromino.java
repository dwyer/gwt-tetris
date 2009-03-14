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

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

public final class Tetromino {

  private static final int TETROMINO[][][][] = {
    // I-block
    {
      {
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 }
      },
      {
        { 1, 1, 1, 1 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 },
        { 1, 0, 0, 0 }
      },
      {
        { 1, 1, 1, 1 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // J-block
    {
      {
        { 2, 2, 0, 0 },
        { 2, 0, 0, 0 },
        { 2, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 2, 0, 0, 0 },
        { 2, 2, 2, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 2, 0, 0 },
        { 0, 2, 0, 0 },
        { 2, 2, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 2, 2, 2, 0 },
        { 0, 0, 2, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // L-block
    {
      {
        { 3, 0, 0, 0 },
        { 3, 0, 0, 0 },
        { 3, 3, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 0, 3, 0 },
        { 3, 3, 3, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 3, 3, 0, 0 },
        { 0, 3, 0, 0 },
        { 0, 3, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 3, 3, 3, 0 },
        { 3, 0, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // O-block
    {
      {
        { 0, 0, 0, 0 },
        { 4, 4, 0, 0 },
        { 4, 4, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 0, 0, 0 },
        { 4, 4, 0, 0 },
        { 4, 4, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 0, 0, 0 },
        { 4, 4, 0, 0 },
        { 4, 4, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 0, 0, 0 },
        { 4, 4, 0, 0 },
        { 4, 4, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // S-block
    {
      {
        { 5, 0, 0, 0 },
        { 5, 5, 0, 0 },
        { 0, 5, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 5, 5, 0 },
        { 5, 5, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 5, 0, 0, 0 },
        { 5, 5, 0, 0 },
        { 0, 5, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 5, 5, 0 },
        { 5, 5, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // T-block
    {
      {
        { 6, 0, 0, 0 },
        { 6, 6, 0, 0 },
        { 6, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 6, 0, 0 },
        { 6, 6, 6, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 6, 0, 0 },
        { 6, 6, 0, 0 },
        { 0, 6, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 6, 6, 6, 0 },
        { 0, 6, 0, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
    // Z-block
    {
      {
        { 0, 7, 0, 0 },
        { 7, 7, 0, 0 },
        { 7, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 7, 7, 0, 0 },
        { 0, 7, 7, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 0, 7, 0, 0 },
        { 7, 7, 0, 0 },
        { 7, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
      {
        { 7, 7, 0, 0 },
        { 0, 7, 7, 0 },
        { 0, 0, 0, 0 },
        { 0, 0, 0, 0 }
      },
    },
  };
  
  private static int x;
  private static int y;
  private static int z;
  private static int type;
  private static Timer timer = new Timer() {
    public void run() {
      move(0, 1, 0);
    }
  };
  
  private static boolean collisionCheck(int x, int y, int z) {
    for (int i = 0; i != 4; ++i) {
      for (int j = 0; j != 4; ++j) {
        int tile = TETROMINO[type][z][i][j];
        if (tile != 0) {
          if (Tilemap.collisionCheck(x+i, y+j)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private static void clear() {
    for (int i = 0; i != 4; ++i) {
      for (int j = 0; j != 4; ++j) {
        int tile = TETROMINO[type][z][i][j];
        if (tile != 0) {
          Tilemap.drawTile(x+i, y+j, 0);
        }
      }
    }
  }
  
  private static void draw() {
    for (int i = 0; i != 4; ++i) {
      for (int j = 0; j != 4; ++j) {
        int tile = TETROMINO[type][z][i][j];
        if (tile != 0) {
          Tilemap.drawTile(x+i, y+j, tile);
        }
      }
    }
  }
  
  private static void move(int xOff, int yOff, int zOff) {
    if (collisionCheck(x+xOff, y+yOff, (z+zOff)%4) == false) {
      clear();
      x += xOff;
      y += yOff;
      z += zOff;
      z %= 4;
      draw();
    }
    else if (yOff > 0) {
      for (int i = 0; i != 4; ++i) {
        for (int j = 0; j != 4; ++j) {
          int tile = TETROMINO[type][z][i][j];
          if (tile != 0) {
            Tilemap.setTile(x+i, y+j, tile);
          }
        }
      }
      int lines = 0;
      for (int i = 0; i != 4; ++i) {
        if (Tilemap.isLine(y+i)) {
          Tilemap.clearLine(y+i);
          lines++;
        }
      }
      if (lines != 0) {
        Score.incLines(lines);
      }
      newTetro();
    }
  }
  
  public static void moveDown() {
    move(0, 1, 0);
    startTimer();
  }
  
  public static void moveDrop() {
    while (collisionCheck(x, y+1, z) == false) {
      move(0, 1, 0);
    }
    move(0, 1, 0);
  }
  
  public static void moveLeft() {
    move(-1, 0, 0);
  }
  
  public static void moveRight() {
    move(1, 0, 0);
  }
  
  public static void rotate() {
    move(0, 0, 1);
  }
  
  public static void newTetro() {
    x = Tilemap.getWidth() / 2 - 2;
    y = 0;
    z = 0;
    type = Random.nextInt(7);
    draw();
    if (collisionCheck(x, y, z)) {
      timer.cancel();
      Tetris.gameOver();
    }
    else {
      startTimer();
    }
  }
  
  private static void startTimer() {
    timer.cancel();
    timer.scheduleRepeating(Score.getSpeed());
  }
}
