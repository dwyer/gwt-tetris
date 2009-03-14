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
import com.google.gwt.user.client.ui.Image;

/**
 * Tile
 */
public final class Tile extends Composite {
  private static final int TILE_SIZE = 16;
  private static final String TILE_SET = "8blocks-gw-16.png";
  private final Image image = new Image(TILE_SET, 0, 0, TILE_SIZE, TILE_SIZE);
  private int value = 0;
  
  public Tile() {
    initWidget(image);
  }
  
  public void clear() {
    draw(0);
  }
  
  public void draw(int n) {
    image.setVisibleRect(n * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
  }
  
  public int getValue() {
    return value;
  }
  
  public void setValue(int n) {
    value = n;
  }
}
