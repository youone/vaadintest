package se.you1;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.UUID;

public class PersistentTabSheet extends TabSheet {

    private HashMap<UUID, Component> tabComponents = new HashMap<>();
    private VerticalLayout tabPool;
    private JsTabController currentTabController = null;

    public PersistentTabSheet(VerticalLayout tp) {
        super();

        this.tabPool = tp;
        this.tabPool.setStyleName("tab-pool");
        this.tabPool.setSpacing(false);
        this.tabPool.setMargin(false);

        this.addSelectedTabChangeListener(selectedTabChangeEvent -> {
            System.out.println("CHANGING TABS");
            this.currentTabController = (JsTabController) selectedTabChangeEvent.getTabSheet().getSelectedTab();
        });
    }

    public void addNewTab(Component c, String caption) {

        UUID tabId = UUID.randomUUID();
        c.addStyleName("tab_" + tabId.toString());
        ((Label) c).setValue(tabId.toString().split("-")[0]);

        tabPool.addComponent(c);

        caption = tabId.toString();

        System.out.println("PREVIOUS TAB: " + this.currentTabController);
        if (this.currentTabController != null) {
            this.currentTabController.addNewTab(tabId, caption);
        }
        else {
            addTabController(tabId, caption);
        }
    }

    public void addTabController(UUID tabId, String caption) {
        System.out.println("ADDING TAB CONTROLLER " + tabId);
        JsTabController tabController = new JsTabController(tabId, caption, this);
        Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\" class=\"tab-caption\">" + caption + "</span>");
        newTab.setClosable(true);
        this.setSelectedTab(newTab);
        tabComponents.put(tabId, tabController);
        this.currentTabController = tabController;
    }

    public void setTab(UUID id) {
        System.out.println("SETTING TAB " + id);
        this.setSelectedTab(this.getTab(tabComponents.get(id)));
    }

    public void closeTab(UUID id) {
        System.out.println("CLOSING TAB " + id + " " + tabComponents.get(id).getCaption());
    }
}
