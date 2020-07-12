package com.fivegearszerochill.noted.editor.utils;

import com.fivegearszerochill.noted.editor.models.ComponentTag;

public class ComponentMetadataHelper {
  public static ComponentTag getNewComponentTag(int index) {
    ComponentTag componentTag = new ComponentTag();
    componentTag.setComponentIndex(index);
    return componentTag;
  }
}
