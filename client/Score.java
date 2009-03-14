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

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class Score {
  private static Integer bonus = 0;
  private static Integer chain = 0;
  private static Integer time = 0;
  private static Integer lines = 0;
  private static Integer score = 0;
  private static Integer speed = 1000;
  private static VerticalPanel panel = new VerticalPanel();
  private static HTML chainHTML = new HTML();
  private static HTML scoreHTML = new HTML();
  private static Timer timer = new Timer() {
    public void run() {
      time--;
      if (time > 0) {
        schedule(1000);
      }
      else {
        bonus = chain = 0;
      }
      setHTML();
    }
  };
  
  private static int fibonacci(int n) {
    if (n > 1) {
      return fibonacci(n-1) + fibonacci(n-2);
    }
    else {
      return n;
    }
  }
  
  public static VerticalPanel getPanel() {
    setHTML();
    panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    panel.add(scoreHTML);
    panel.add(chainHTML);
    return panel;
  }
  
  public static int getSpeed() {
    return speed;
  }
  
  public static void incLines(int n) {
    chain += n;
    bonus += n * fibonacci(chain) * (lines+10);
    score += n * fibonacci(chain) * (lines+10);
    lines += n;
    if (time == 0) {
      timer.schedule(1000);
    }
    time += 3;
    setHTML();
  }
  
  private static void setHTML() {
    scoreHTML.setHTML("Lines: " + lines.toString() +
                      " | Score: " + score.toString());
    if (time > 0) {
      chainHTML.setHTML("<b>Chain</b>: " + chain.toString() + " lines, +" +
                        bonus.toString() + " points (<font color=red>" +
                        time.toString() + "</font>)");
    }
    else {
      chainHTML.setHTML("");
    }
  }
}
