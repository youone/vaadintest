package se.you1;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
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
        this.tabHolder.setSpacing(false);
        this.tabHolder.setMargin(false);

        this.addSelectedTabChangeListener(selectedTabChangeEvent -> {
            System.out.println("CHANGING TABS");
//            if (this.previousTab != null) this.previousTab.setTab(UUID.randomUUID(), "hej");
            this.previousTab = (JsTabController) selectedTabChangeEvent.getTabSheet().getSelectedTab();
        });
    }

//    @Override
//    public Tab addTab(Component c, String caption) {
//
//        UUID tabId = UUID.randomUUID();
//        c.addStyleName("tab_" + tabId.toString());
//        tabHolder.addComponent(c);
//
//        JsTabController tabController = new JsTabController(tabId, caption, this);
//        Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\">" + caption + "</span>");
//        newTab.setClosable(true);
//        this.setSelectedTab(newTab);
//        this.previousTab = tabController;
//
//        tabList.put(tabId, newTab);
//
//        return newTab;
//    }

    public void addNewTab(Component c, String caption) {

        UUID tabId = UUID.randomUUID();
        c.addStyleName("tab_" + tabId.toString());
        ((Label) c).setValue(tabId.toString().split("-")[0]);
        tabHolder.addComponent(c);

        caption = tabId.toString();

        System.out.println("PREVIOUS TAB: " + this.previousTab);
        if (this.previousTab != null) {
            this.previousTab.addNewTab(tabId, caption);
        }
        else {
//            addTab(c, caption);
            JsTabController tabController = new JsTabController(tabId, caption, this);
            Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\" class=\"tab-caption\">" + caption + "</span>");
            newTab.setClosable(true);
            this.setSelectedTab(newTab);
            tabList.put(tabId, tabController);
            this.previousTab = tabController;
        }
    }

    public void addPendingTab(UUID tabId, String caption) {
        System.out.println("ADDING PENDING TAB " + tabId);
        JsTabController tabController = new JsTabController(tabId, caption, this);
        Tab newTab = super.addTab(tabController, "<span id=\"tab_" + tabId + "\" class=\"tab-caption\">" + caption + "</span>");
        newTab.setClosable(true);
        this.setSelectedTab(newTab);
        tabList.put(tabId, tabController);
        this.previousTab = tabController;
    }

    public void setTab(UUID id) {
        System.out.println("SETTING TAB " + id);
        this.setSelectedTab(this.getTab(tabList.get(id)));
    }

    public void closeTab(UUID id) {
        System.out.println("CLOSING TAB " + id + " " + tabList.get(id).getCaption());
//        System.out.println("TAB POSITION " + this.getTabPosition(tabList.get(id)));
//        this.setTabPosition(tabList.get(id), 0);
//            this.previousTab = (JsTabController) tabList.get(id);
//        this.removeTab(tabList.get(id));
//        tabList.remove(id);
    }
}
