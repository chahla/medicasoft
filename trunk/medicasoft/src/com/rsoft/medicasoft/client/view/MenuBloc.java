package com.rsoft.medicasoft.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.MainView.MenuActionCallBack;
import com.rsoft.medicasoft.shared.menu.BaseTreeItem;
import com.rsoft.medicasoft.shared.menu.FolderTreeItem;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class MenuBloc implements IsWidget {

    private Menu userMenu;
    private MenuActionCallBack mnActionCallback;

    public MenuBloc(MenuActionCallBack mnActionCallback, Menu userMenu) {

        this.mnActionCallback = mnActionCallback;
        if (userMenu == null) {
            throw new IllegalArgumentException(
                    "Internal error menu object is null. Please call your administrator.");
        }
        this.userMenu = userMenu;
    }

    class KeyProvider implements ModelKeyProvider<BaseTreeItem> {

        @Override
        public String getKey(BaseTreeItem item) {
            return (item instanceof FolderTreeItem ? "f-" : "m-")
                    + item.getId().toString();
        }
    }
    private static int autoId;

    private FolderTreeItem makeFolder(String name) {
        FolderTreeItem theReturn = new FolderTreeItem(++autoId, name);
        theReturn
                .setChildren((List<BaseTreeItem>) new ArrayList<BaseTreeItem>());
        return theReturn;
    }

    private void addMenuChild(FolderTreeItem fti, Menu menu) {
        if (menu != null) {
            if (menu.getMenuFields() != null) { // a menu
                FolderTreeItem menuFolder = makeFolder(menu.getMenuName());
                menuFolder.setMenu(menu);
                fti.addChild(menuFolder);
                for (Menu field : menu.getMenuFields()) {
                    addMenuChild(menuFolder, field);
                }
            } else {// a sub menu
                BaseTreeItem btm = new BaseTreeItem(++autoId,
                        menu.getMenuName());
                btm.setMenu(menu);
                fti.addChild(btm);
            }
        }
    }

    public FolderTreeItem getMenuRootFolder() {
        FolderTreeItem root = makeFolder("Root");
        FolderTreeItem menu = makeFolder(userMenu.getMenuName());
        menu.setMenu(userMenu);
        List<BaseTreeItem> children = new ArrayList<BaseTreeItem>();
        children.add(menu);
        root.setChildren(children);
        if (userMenu != null && userMenu.getMenuFields() != null) {
            for (Menu menuChild : userMenu.getMenuFields()) {
                addMenuChild(menu, menuChild);
            }
        }
        return root;
    }

    public Widget asWidget() {
//        II18NMessages messages = I18NMessages
//                .getMessages();
        TreeStore<BaseTreeItem> store = new TreeStore<BaseTreeItem>(
                new KeyProvider());

        FolderTreeItem root = getMenuRootFolder();
        for (BaseTreeItem base : root.getChildren()) {
            store.add(base);
            if (base instanceof FolderTreeItem) {
                processFolder(store, (FolderTreeItem) base);
            }
        }

        StoreFilterField<BaseTreeItem> filter = new StoreFilterField<BaseTreeItem>() {
            @Override
            protected boolean doSelect(Store<BaseTreeItem> store,
                    BaseTreeItem parent, BaseTreeItem item, String filter) {
                if (item instanceof FolderTreeItem) {
                    return false;
                }

                String name = item.getName();
                name = name.toLowerCase();
                if (name.startsWith(filter.toLowerCase())) {
                    return true;
                }
                return false;
            }
        };
        filter.bind(store);

        final Tree<BaseTreeItem, Menu> tree = new Tree<BaseTreeItem, Menu>(
                store, new ValueProvider<BaseTreeItem, Menu>() {
            @Override
            public Menu getValue(BaseTreeItem object) {
                return object.getMenu();
            }

            @Override
            public void setValue(BaseTreeItem object, Menu value) {
            }

            @Override
            public String getPath() {
                return "name";
            }
        });
        tree.setWidth(300);

        // tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());
        ToolBar buttonBar = new ToolBar();

//        buttonBar.add(new TextButton(messages.expandAll(), new SelectHandler() {
//            @Override
//            public void onSelect(SelectEvent event) {
//                tree.expandAll();
//            }
//        }));
//        buttonBar.add(new TextButton(messages.collapseAll(),
//                new SelectHandler() {
//                    @Override
//                    public void onSelect(SelectEvent event) {
//                        tree.collapseAll();
//                    }
//                }));
        buttonBar.setLayoutData(new MarginData(0));
        AccordionLayoutAppearance appearance = GWT
                .<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class);
        ContentPanel cp = new ContentPanel(appearance);
        cp.setAnimCollapse(true);
        cp.setHeadingText(userMenu.getMenuName());
        cp.setHeight(250);
        FlowLayoutContainer flc = new FlowLayoutContainer();
        flc.addStyleName("margin-10");
        flc.add(filter);
        flc.add(buttonBar);
        // cp.add(flc);
        SafeHtmlRenderer<Menu> renderer = MenuHtmlRenderer.getInstance();
        SimpleSafeHtmlCell<Menu> cell = new SimpleSafeHtmlCell<Menu>(renderer,
                "click") {
            @Override
            public void onBrowserEvent(Context context, Element parent,
                    Menu value, NativeEvent event,
                    ValueUpdater<Menu> valueUpdater) {
                super.onBrowserEvent(context, parent, value, event,
                        valueUpdater);
                if ("click".equals(event.getType())) {
                    mnActionCallback.showPage(value);
                }
            }
        };

        tree.setCell(cell);

        cp.add(tree);
        return cp;
    }

    public void onModuleLoad() {
        RootPanel.get().add(asWidget());
    }

    private void processFolder(TreeStore<BaseTreeItem> store,
            FolderTreeItem folder) {
        for (BaseTreeItem child : folder.getChildren()) {
            store.add(folder, child);
            if (child instanceof FolderTreeItem) {
                processFolder(store, (FolderTreeItem) child);
            }
        }
    }
}

class MenuHtmlRenderer implements SafeHtmlRenderer<Menu> {

    private static MenuHtmlRenderer instance;

    public static MenuHtmlRenderer getInstance() {
        if (instance == null) {
            instance = new MenuHtmlRenderer();
        }
        return instance;
    }

    private MenuHtmlRenderer() {
    }

    public SafeHtml render(Menu object) {
        return (object == null) ? SafeHtmlUtils.EMPTY_SAFE_HTML : SafeHtmlUtils
                .fromString(object.getMenuName());
    }

    public void render(Menu object, SafeHtmlBuilder appendable) {
        appendable.append(SafeHtmlUtils.fromString(object.getMenuName()));
    }
}
