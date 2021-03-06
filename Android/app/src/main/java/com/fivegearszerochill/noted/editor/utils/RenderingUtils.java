package com.fivegearszerochill.noted.editor.utils;

import com.fivegearszerochill.noted.editor.MarkDEditor;
import com.fivegearszerochill.noted.editor.datatypes.DraftDataItemModel;
import com.fivegearszerochill.noted.editor.models.DraftModel;

import java.util.ArrayList;

import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_OL;
import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_PLAIN;
import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_UL;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.BLOCK_QUOTE;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H1;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H2;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H3;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H4;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.H5;
import static com.fivegearszerochill.noted.editor.utils.FontSize.NORMAL;

public class RenderingUtils {
  private MarkDEditor markDEditor;

  public void setEditor(MarkDEditor markDEditor) {
    this.markDEditor = markDEditor;
  }

  public void render(ArrayList<DraftDataItemModel> contents) {
    //visit each item type
    for (int i = 0; i < contents.size(); i++) {
      DraftDataItemModel item = contents.get(i);
      //identify item of data
      if (item.getItemType() == DraftModel.ITEM_TYPE_TEXT) {
        //identify mode of text item
        switch (item.getMode()) {
          case MODE_PLAIN:
            //includes NORMAL, H1-H5, Blockquote
            renderPlainData(item);
            break;
          case MODE_OL:
            //renders orderedList
            renderOrderedList(item);
            break;
          case MODE_UL:
            //renders unorderedList
            renderUnOrderedList(item);
            break;
          default:
            //default goes to normal text
            renderPlainData(item);
        }
      } else if (item.getItemType() == DraftModel.ITEM_TYPE_HR) {
        renderHR();
      } else if (item.getItemType() == DraftModel.ITEM_TYPE_IMAGE) {
        renderImage(item);
      }
    }
  }

  /**
   * Sets mode to plain and insert a a text component.
   *
   * @param item model of text data item
   */
  private void renderPlainData(DraftDataItemModel item) {
    markDEditor.setCurrentInputMode(MODE_PLAIN);
    switch (item.getStyle()) {
      case NORMAL:
      case H1:
      case H2:
      case H3:
      case H4:
      case H5:
      case BLOCK_QUOTE:
        markDEditor.addTextComponent(getInsertIndex(), item.getContent());
        markDEditor.setHeading(item.getStyle());
        break;
      default:
        markDEditor.addTextComponent(getInsertIndex(), item.getContent());
        markDEditor.setHeading(NORMAL);
    }
  }

  /**
   * Sets mode to ordered-list and insert a a text component.
   *
   * @param item model of text data item.
   */
  private void renderOrderedList(DraftDataItemModel item) {
    markDEditor.setCurrentInputMode(MODE_OL);
    markDEditor.addTextComponent(getInsertIndex(), item.getContent());
  }

  /**
   * Sets mode to unordered-list and insert a a text component.
   *
   * @param item model of text data item.
   */
  private void renderUnOrderedList(DraftDataItemModel item) {
    markDEditor.setCurrentInputMode(MODE_UL);
    markDEditor.addTextComponent(getInsertIndex(), item.getContent());
  }

  /**
   * Adds Horizontal line.
   */
  private void renderHR() {
    markDEditor.insertHorizontalDivider(false);
  }

  /**
   * @param item model of image item.
   *             Inserts image.
   *             Sets caption
   */
  private void renderImage(DraftDataItemModel item) {
    markDEditor.insertImage(getInsertIndex(),item.getDownloadUrl(), true, item.getCaption());
  }

  /**
   * Since childs are going to be arranged in linear fashion, child count can act as insert index.
   *
   * @return insert index.
   */
  private int getInsertIndex() {
    int index = markDEditor.getChildCount();
    return index;
  }
}
