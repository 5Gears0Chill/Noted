package com.fivegearszerochill.noted.editor.utils;

import android.view.View;

import com.fivegearszerochill.noted.editor.MarkDEditor;
import com.fivegearszerochill.noted.editor.components.HorizontalDividerComponentItem;
import com.fivegearszerochill.noted.editor.components.ImageComponentItem;
import com.fivegearszerochill.noted.editor.components.TextComponentItem;
import com.fivegearszerochill.noted.editor.datatypes.DraftDataItemModel;
import com.fivegearszerochill.noted.editor.models.ComponentTag;
import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.editor.models.TextComponentModel;

import java.util.ArrayList;

import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_OL;
import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_PLAIN;
import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_UL;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.NORMAL;

public class DraftManager {
  public DraftManager() {

  }

  /**
   * Traverse through each item and prepares the draft item list.
   * @param markDEditor editor object.
   * @return a list of Draft Item types.
   */
  public DraftModel processDraftContent(MarkDEditor markDEditor) {
    ArrayList<DraftDataItemModel> drafts = new ArrayList<>();
    int childCount = markDEditor.getChildCount();
    View view;
    int textStyle;
    ComponentTag componentTag;
    for (int i = 0; i < childCount; i++) {
      view = markDEditor.getChildAt(i);
      if (view instanceof TextComponentItem) {
        //check mode
        int mode = ((TextComponentItem) view).getMode();
        if (mode == MODE_PLAIN) {
          //check for styles {H1-H5 Blockquote Normal}
          componentTag = (ComponentTag) view.getTag();
          textStyle = ((TextComponentModel) componentTag.getComponent()).getHeadingStyle();
          drafts.add(getPlainModel(textStyle, ((TextComponentItem) view).getContent()));
        } else if (mode == MODE_UL) {
          drafts.add(getUlModel(((TextComponentItem) view).getContent()));
        } else if (mode == MODE_OL) {
          drafts.add(getOlModel(((TextComponentItem) view).getContent()));
        }
      } else if (view instanceof HorizontalDividerComponentItem) {
        drafts.add(getHRModel());
      } else if (view instanceof ImageComponentItem) {
        drafts.add(getImageModel(
         ((ImageComponentItem) view).getDownloadUrl(),
         ((ImageComponentItem) view).getCaption()
        ));
      }
    }
    return new DraftModel(drafts);
  }

  /**
   * Models Text information.
   * @param textStyle style associated with the text (NORMAL,H1-H5,BLOCKQUOTE)
   * @param content   text content
   * @return          a Generic TextType Object containing information.
   */
  private DraftDataItemModel getPlainModel(int textStyle, String content) {
    DraftDataItemModel textType = new DraftDataItemModel();
    textType.setItemType(DraftModel.ITEM_TYPE_TEXT);
    textType.setContent(content);
    textType.setMode(MODE_PLAIN);
    textType.setStyle(textStyle);
    return textType;
  }

  /**
   * Models UnOrdered list text information.
   * @param content text content.
   * @return a UL type model object.
   */
  private DraftDataItemModel getUlModel(String content) {
    DraftDataItemModel textType = new DraftDataItemModel();
    textType.setItemType(DraftModel.ITEM_TYPE_TEXT);
    textType.setContent(content);
    textType.setMode(MODE_UL);
    textType.setStyle(NORMAL);
    return textType;
  }

  /**
   * Models Ordered list text information.
   * @param content text content.
   * @return a OL type model object.
   */
  private DraftDataItemModel getOlModel(String content) {
    DraftDataItemModel textType = new DraftDataItemModel();
    textType.setItemType(DraftModel.ITEM_TYPE_TEXT);
    textType.setContent(content);
    textType.setMode(MODE_OL);
    textType.setStyle(NORMAL);
    return textType;
  }

  /**
   * Models Horizontal rule object.
   * @return a HR type model object.
   */
  private DraftDataItemModel getHRModel() {
    DraftDataItemModel hrType = new DraftDataItemModel();
    hrType.setItemType(DraftModel.ITEM_TYPE_HR);
    return hrType;
  }

  /**
   * Models Image type object item.
   * @param downloadUrl  url of the image.
   * @param caption      caption of the image(if any)
   * @return             a Image Model object type.
   */
  private DraftDataItemModel getImageModel(String downloadUrl, String caption) {
    DraftDataItemModel imageType = new DraftDataItemModel();
    imageType.setItemType(DraftModel.ITEM_TYPE_IMAGE);
    imageType.setCaption(caption);
    imageType.setDownloadUrl(downloadUrl);
    return imageType;
  }

}
