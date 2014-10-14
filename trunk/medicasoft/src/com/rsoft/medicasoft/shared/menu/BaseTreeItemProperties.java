package com.rsoft.medicasoft.shared.menu;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface BaseTreeItemProperties extends PropertyAccess<BaseTreeItem> {
  
  public final ModelKeyProvider<BaseTreeItem> key = new ModelKeyProvider<BaseTreeItem>() {
    @Override
    public String getKey(BaseTreeItem item) {
      return (item instanceof FolderTreeItem ? "f-" : "m-") + item.getId().toString();
    }
  };
  ValueProvider<BaseTreeItem, String> name();
}
