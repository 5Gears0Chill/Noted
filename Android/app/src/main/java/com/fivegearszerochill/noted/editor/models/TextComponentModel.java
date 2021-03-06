package com.fivegearszerochill.noted.editor.models;


import androidx.annotation.NonNull;

import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.NORMAL;

public class TextComponentModel extends BaseComponentModel {
  private int headingStyle = NORMAL;

  public int getHeadingStyle() {
    return headingStyle;
  }

  public void setHeadingStyle(int headingStyle) {
    this.headingStyle = headingStyle;
  }

  @NonNull
  @Override
  public String toString() {
    return "TextComponentModel{" +
     "headingStyle=" + headingStyle +
     '}';
  }
}
