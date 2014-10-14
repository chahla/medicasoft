package com.rsoft.medicasoft.shared.menu;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import java.io.Serializable;
import java.util.List;

public class BaseTreeItem implements Serializable, TreeStore.TreeNode<BaseTreeItem> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Menu menu;

    protected BaseTreeItem() {
    }

    public BaseTreeItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BaseTreeItem getData() {
        return this;
    }

    @Override
    public List<? extends TreeNode<BaseTreeItem>> getChildren() {
        return null;
    }

    @Override
    public String toString() {
        return name != null ? name : super.toString();
    }
}