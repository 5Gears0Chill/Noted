package com.fivegearszerochill.noted.editor.utils;

import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.BLOCK_QUOTE;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H1;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H2;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H3;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H4;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H5;

public class MarkDownFormat {

  public static String getTextFormat(int heading, String content) {
    String pref;
    switch (heading) {
      case H1:
        pref = "# ";
        break;
      case H2:
        pref = "## ";
        break;
      case H3:
        pref = "### ";
        break;
      case H4:
        pref = "#### ";
        break;
      case H5:
        pref = "##### ";
        break;
      case BLOCK_QUOTE:
        pref = "> ";
        break;
      default:
        pref = "";
    }
    return String.format("\\n%s%s\\n", pref, content);
  }

  public static String getImageFormat(String url) {
    return String.format("\\n<center>![Image](%s)</center>", url);
  }

  public static String getCaptionFormat(String caption) {
    return caption != null ? String.format("<center>%s</center>\\n\\n\\n", caption) : "\\n\\n\\n";
  }

  public static String getLineFormat() {
    return "\\n\\n---\\n\\n";
  }

  public static String getULFormat(String content) {
    return String.format("  - %s\\n", content);
  }

  public static String getOLFormat(String indicator, String content) {
    return String.format("  %s %s\\n", indicator, content);
  }
}
