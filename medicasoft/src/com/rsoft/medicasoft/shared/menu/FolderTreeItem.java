package com.rsoft.medicasoft.shared.menu;

import java.util.List;

@SuppressWarnings("serial")
public class FolderTreeItem extends BaseTreeItem {

	private List<BaseTreeItem> children;

	protected FolderTreeItem() {

	}

	public FolderTreeItem(Integer id, String name) {
		super(id, name);
	}

	public List<BaseTreeItem> getChildren() {
		return children;
	}

	public void setChildren(List<BaseTreeItem> children) {
		this.children = children;
	}

	public void addChild(BaseTreeItem child) {
		getChildren().add(child);
	}
}
