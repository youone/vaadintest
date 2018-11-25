package se.you1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import elemental.json.JsonArray;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@com.vaadin.annotations.JavaScript({
        "vaadin://static/3rdparty/jqueryui/1.12.1/external/jquery/jquery.js",
        "vaadin://static/3rdparty/jqueryui/1.12.1/jquery-ui.js",
        "https://cdnjs.cloudflare.com/ajax/libs/qtip2/3.0.3/basic/jquery.qtip.min.js",
//        "vaadin://javascript/tabs.js"
})
@StyleSheet({
        "vaadin://javascript/tabs.css",
        "vaadin://static/3rdparty/jqueryui/1.12.1/jquery-ui.css",
        "https://cdnjs.cloudflare.com/ajax/libs/qtip2/3.0.3/basic/jquery.qtip.min.css",
})
@Theme("mytheme")
public class MyUI extends UI {

    class TabComponent extends Label {

        TabComponent(String value) {
            super(value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setId("tabs");
        getPage().setTitle("tabs");

//        Navigator navigator = new Navigator();

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        VerticalLayout tabHolder = new VerticalLayout();

        PersistentTabSheet ts = new PersistentTabSheet(tabHolder);

        ts.setTabCaptionsAsHtml(true);

        //        ts.addSelectedTabChangeListener(selectedTabChangeEvent -> {
//            Component selTab = selectedTabChangeEvent.getTabSheet().getSelectedTab();
//            System.out.println("TAB " +  selTab.getStyleName());
//            System.out.println("TAB " +  selectedTabChangeEvent.getSource());
//        });

//        ts.addTab(new Label("tab0"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 0").setClosable(true);
//        ts.addTab(new Label("tab1"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 1").setClosable(true);
//        ts.addTab(new Label("tab2"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 2").setClosable(true);
//        ts.addTab(new Label("tab3"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 3").setClosable(true);
//        ts.addTab(new Label("tab4"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 4").setClosable(true);
//        ts.addTab(new Label("tab5"), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab 5").setClosable(true);

        Button addTab = new Button("add tab");
        addTab.addClickListener(clickEvent -> {
//            ts.addTab(new Label("tab" + ts.getComponentCount()), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab"  + ts.getComponentCount()).setClosable(true);
            ts.addNewTab(new TabComponent("tab" + ts.getComponentCount()), "looooooooooooooooooong taaaaaaaaaaaaaaaaaab"  + ts.getComponentCount());
        });

        layout.addComponent(addTab);
        layout.addComponent(ts);

//        tabHolder.setVisible(false);
        layout.addComponent(tabHolder);

        JavaScript.getCurrent().addFunction("selectTab", new JavaScriptFunction() {

            @Override
            public void call(JsonArray jsonArray) {
                ts.setSelectedTab(0);
                System.out.println(jsonArray);
            }
        });



    //        final VerticalLayout layout = new VerticalLayout();
//
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
//        Button button = new Button("Click Me");
//        button.addClickListener(e -> {
//            layout.addComponent(new Label("Thanks " + name.getValue()
//                    + ", it works!"));
//        });
//
//        layout.addComponents(name, button);
//
//        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
