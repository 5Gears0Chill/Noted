package com.fivegearszerochill.noted.editor.utils;


import com.fivegearszerochill.noted.editor.styles.TextComponentStyle;

import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H1;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H2;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H3;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H4;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H5;

public class FontSize {
  public static final int H1_SIZE = 32;
  public static final int H2_SIZE = 28;
  public static final int H3_SIZE = 24;
  public static final int H4_SIZE = 22;
  public static final int H5_SIZE = 20;
  public static final int NORMAL = 20;

  public static int getFontSize(int heading) {
    switch (heading) {
      case H1:
        return H1_SIZE;
      case H2:
        return H2_SIZE;
      case H3:
        return H3_SIZE;
      case H4:
        return H4_SIZE;
      case H5:
        return H5_SIZE;
      case TextComponentStyle.NORMAL:
        return NORMAL;
    }
    return H5_SIZE;
  }
}
