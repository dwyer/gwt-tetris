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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tetris implements EntryPoint, EventPreview {
  private Tilemap tilemap = new Tilemap(10, 20);
  
  public static void gameOverEx() {
    // Create the dialog box
    final DialogBox dialogBox = new DialogBox();
    final String gameOverText = "You just lost <a href='http://www.google.com/" +
                                "search?q=lost+the+game'>The Game</a>";
    dialogBox.setHTML(gameOverText);
    dialogBox.setAnimationEnabled(true);
    HTML html = new HTML("<u>Controls</u><br/>" + 
                         "<b>Right Left Down</b>: move around<br/>" +
                         "<b>Up</b>: rotate clockwise<br/>" +
                         "<b>Space</b>: drop");
    Button button = new Button("New Game");
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.setWidth("100%");
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    dialogVPanel.add(html);
    dialogVPanel.add(button);

    button.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        dialogBox.hide();
        newGame();
      }
    });

    // Set the contents of the Widget
    dialogBox.setWidget(dialogVPanel);
    dialogBox.center();
    dialogBox.show();
  }

  public static void gameOver() {
    NewGameDialog dialog = new NewGameDialog();
    dialog.show();
  }
  
  public static void newGame() {
    Tilemap.clear();
    Tetromino.newTetro();
  }
  
  public boolean onEventPreview(Event event) {
    if (event.getTypeInt() == Event.ONKEYPRESS) {
      int keyCode = event.getKeyCode();
      if (keyCode == KeyboardListener.KEY_RIGHT) {
        Tetromino.moveRight();
      }
      else if (keyCode == KeyboardListener.KEY_LEFT) {
        Tetromino.moveLeft();
      }
      else if (keyCode == KeyboardListener.KEY_DOWN) {
        Tetromino.moveDown();
      }
      else if (keyCode == KeyboardListener.KEY_UP) {
        Tetromino.rotate();
      }
      else if (keyCode == KeyboardListener.KEY_END || keyCode == ' ') {
        Tetromino.moveDrop();
      }
      else if (keyCode == KeyboardListener.KEY_DELETE) {
        Tilemap.clear();
      }
    }
    return true;
  }
  
  public void onModuleLoad() {
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setWidth("100%");
    vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    vPanel.add(tilemap);
    vPanel.add(Score.getPanel());
    
    RootPanel.get().add(vPanel);
    DOM.addEventPreview(this);
    //Tetromino.newTetro();
    gameOver();
  }
}
