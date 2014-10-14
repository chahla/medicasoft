package com.rsoft.medicasoft.client.toolsbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.rsoft.medicasoft.client.view.MainView.MenuActionCallBack;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ButtonGroup;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class MainBarPane extends ContentPanel {

    private int elemCount = 1;
    private int currentPosX = 0;
    private int currentPosY = 0;
    private VerticalLayoutContainer con = new VerticalLayoutContainer();
    private ToolBar toolBar = new ToolBar();
    private MenuActionCallBack mnActionCallback;

    public MainBarPane(MenuActionCallBack mnActionCallback, Menu menu) {
        this.mnActionCallback = mnActionCallback;
        this.toolBar.setSpacing(5);
        this.con.add(toolBar, new VerticalLayoutData(0, 0));
        this.add(con);
        createMixed(menu);
    }

    private void cleanCells(Element elem) {
        NodeList<Element> tds = elem.<XElement>cast().select("td");
        for (int i = 0; i < tds.getLength(); i++) {
            Element td = tds.getItem(i);

            if (!td.hasChildNodes() && td.getClassName().equals("")) {
                td.removeFromParent();
            }
        }
    }

    public void addMenuField(FlexTable table, final Menu menu) {
        if (table != null && menu != null) {
            Menu.MenuCoordonate coordonate = menu.getCoordonate();
            int x = currentPosX;
            int y = currentPosY;
            int rowSpan = 1;
            int colSpan = 1;
            ButtonScale buttonScale = ButtonScale.NONE;
            IconAlign iconAlign = IconAlign.BOTTOM;
            ButtonArrowAlign buttonArrowAlign = ButtonArrowAlign.BOTTOM;
            if (coordonate != null) {
                if (coordonate.getX() != null) {
                    x = coordonate.getX();
                }
                if (coordonate.getY() != null) {
                    y = coordonate.getY();
                }
                rowSpan = coordonate.getRowSpan() != null ? coordonate
                        .getRowSpan() : rowSpan;
                colSpan = coordonate.getColSpan() != null ? coordonate
                        .getColSpan() : colSpan;
                buttonScale = coordonate.getButtonScale() != null ? coordonate
                        .getButtonScale() : buttonScale;
                iconAlign = iconAlign != null ? coordonate.getIconAlign()
                        : iconAlign;
                buttonArrowAlign = coordonate.getButtonArrowAlign() != null ? coordonate
                        .getButtonArrowAlign() : buttonArrowAlign;
            }
            TextButton button;
            if (menu.getImageName() == null) {
                button = new TextButton(menu.getMenuName());
            } else {
                ImageResource imr = getMenuImage(menu.getImageName());
                if (imr != null) {
                    button = new TextButton(menu.getMenuName(), imr);
                } else {
                    button = new TextButton(menu.getMenuName());
                }
            }
            button.addSelectHandler(new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    mnActionCallback.showPage(menu);
                }
            });
            if (buttonScale != null) {
                button.setScale(buttonScale);
            }
            if (iconAlign != null) {
                button.setIconAlign(iconAlign);
            }
            if (buttonArrowAlign != null) {
                button.setArrowAlign(buttonArrowAlign);
            }
            table.getFlexCellFormatter().setRowSpan(x, y, rowSpan);
            table.getFlexCellFormatter().setColSpan(x, y, colSpan);
            if (menu.getMenuInfo() != null) {
                button.setToolTip(menu.getMenuInfo());
            }
            button.setBorders(false);
            button.setStyleName("mainToolBarButton");
            table.setWidget(x, y, button);
            if (coordonate == null || coordonate.getX() == null) {
                if (x == 0) {
                    x = 1;
                } else {
                    x = 0;
                    if (elemCount % 2 == 0) {
                        y++;
                    }
                }
            }
            this.currentPosX = x;
            this.currentPosY = y;
            elemCount++;
        }
    }

    private void createMixed(Menu menu) {
        setHeaderVisible(false);
        ButtonGroup group = new ButtonGroup();
        group.setHeadingText(menu.getMenuName());
        toolBar.add(group);
        FlexTable table = new FlexTable();
        group.add(table);
        if (menu.getMenuFields() != null) {
            for (Menu m : menu.getMenuFields()) {
                if (m.getMenuFields() == null) {
                    addMenuField(table, m);
                } else {
                    currentPosX = 0;
                    currentPosY = 0;
                    createMixed(m);
                }
            }
        }
        cleanCells(table.getElement());
    }

    private ImageResource getMenuImage(String name) {
        if ("add16".equalsIgnoreCase(name)) {
            return MainToolsBarImage.INSTANCE.add16();
        }
        if ("add24".equalsIgnoreCase(name)) {
            return MainToolsBarImage.INSTANCE.add24();
        }
        if ("add32".equalsIgnoreCase(name)) {
            return MainToolsBarImage.INSTANCE.add32();
        }
        return null;
    }

    public interface MainToolsBarImage extends ClientBundle {

        public MainToolsBarImage INSTANCE = GWT.create(MainToolsBarImage.class);

        @Source("com/rsoft/medicasoft/client/images/page-first.gif")
        ImageResource first();

        @Source("com/rsoft/medicasoft/client/images/page-prev.gif")
        ImageResource prev();

        @Source("com/rsoft/medicasoft/client/images/page-next.gif")
        ImageResource next();

        @Source("com/rsoft/medicasoft/client/images/page-last.gif")
        ImageResource last();

        @Source("com/rsoft/medicasoft/client/images/refresh.gif")
        ImageResource refresh();

        @Source("com/rsoft/medicasoft/client/images/refresh.gif")
        ImageResource loading();

        @Source("com/rsoft/medicasoft/client/images/saveIcon.gif")
        ImageResource save();// Update and insert

        @Source("com/rsoft/medicasoft/client/images/first.gif")
        ImageResource remove();// Delete

        @Source("com/rsoft/medicasoft/client/images/plusIcon.gif")
        ImageResource criteria();// For Criteria

        @Source("com/rsoft/medicasoft/client/images/searchIcon.gif")
        ImageResource search();// search

        @Source("com/rsoft/medicasoft/client/images/upIcon.gif")
        ImageResource load();// load csv data

        @Source("com/rsoft/medicasoft/client/images/downIcon.gif")
        ImageResource extract();// extract csv data

        @Source("com/rsoft/medicasoft/client/images/printIcon.gif")
        ImageResource print();// print

        @Source("com/rsoft/medicasoft/client/images/add16.gif")
        ImageResource add16();// add

        @Source("com/rsoft/medicasoft/client/images/add24.gif")
        ImageResource add24();// add

        @Source("com/rsoft/medicasoft/client/images/add32.gif")
        ImageResource add32();// add
    }
}
