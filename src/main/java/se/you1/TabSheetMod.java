package se.you1;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.UUID;

public class TabSheetMod extends TabSheet {

    private HashMap<UUID, Component> tabList = new HashMap<>();
    private VerticalLayout tabHolder;
    private JsTabController previousTab = null;

    public TabSheetMod(VerticalLayout tabHolder) {
        super();
        this.tabHolder = tabHolder;
        this.tabHolder.setStyleName("tab-holder");

//        this.addSelectedTabChangeListener(selectedTabChangeEvent -> {
//            System.out.println("CHANGING TABS");
////            if (this.previousTab != null) this.previousTab.setTab(UUID.randomUUID(), "hej");
//            this.previousTab = (JsTabController) selectedTabChangeEvent.getTabSheet().getSelectedTab();
//        });
    }

    @Override
    public Tab addTab(Component c, String caption) {

        UUID tabId = UUID.randomUUID();
        c.addStyleName("tab_" + tabId.toString());
        tabList.put(tabId, c);
        tabHolder.addComponent(c);

        JsTabController tabController = new JsTabController(tabId, caption, this);
        Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\">" + caption + "</span>");
        newTab.setClosable(true);
        this.setSelectedTab(newTab);
        this.previousTab = tabController;

        return newTab;
    }

    public void addNewTab(Component c, String caption) {

        UUID tabId = UUID.randomUUID();
        c.addStyleName("tab_" + tabId.toString());
        tabList.put(tabId, c);
        tabHolder.addComponent(c);

        System.out.println("PREVIOUS TAB: " + this.previousTab);
        if (this.previousTab != null) {
            this.previousTab.addNewTab(tabId, caption);
        }
        else {
            addTab(c, caption);
        }
    }

    public void addPendingTab(UUID tabId, String caption) {
        System.out.println("ADDING PENDING TAB " + tabId);
        JsTabController tabController = new JsTabController(tabId, caption, this);
        Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\">" + caption + "</span>");
        newTab.setClosable(true);
        this.setSelectedTab(newTab);
        this.previousTab = tabController;
    }
}
