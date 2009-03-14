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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * NewGameDialog
 */
public final class NewGameDialog extends DialogBox implements ClickListener {
  public NewGameDialog() {
    String gameOverText = "You just lost <a href='http://www.google.com/" +
                                "search?q=lost+the+game'>The Game</a>";
    this.setHTML("Tetris");
    this.setAnimationEnabled(true);
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

    button.addClickListener(this);

    // Set the contents of the Widget
    this.setWidget(dialogVPanel);
    this.center();
  }
  
  public void onClick(Widget sender) {
    this.hide();
    Tetris.newGame();
  }
}
