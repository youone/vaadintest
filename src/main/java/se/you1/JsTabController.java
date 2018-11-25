package se.you1;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import se.you1.TabSheetMod;

import java.util.UUID;

@JavaScript({
        "vaadin://javascript/components/jsTabController.js",
})
@StyleSheet({
})
public class JsTabController extends AbstractJavaScriptComponent {

//    public JsTabController(UUID tabId, String caption, TabSheetMod tabSheet) {
//    }

    public JsTabController(UUID tabId, String caption, TabSheetMod tabSheet) {

        getState().tabId = tabId.toString();
        getState().title = caption;

        addFunction("addPendingTab", new JavaScriptFunction() {
            @Override
            public void call(JsonArray jsonArray) {
                System.out.println(jsonArray.toJson());
                tabSheet.addPendingTab(
                        UUID.fromString(jsonArray.getObject(0).getString("id")),
                        jsonArray.getObject(0).getString("caption"));
            }
        });

        addFunction("changeTab", new JavaScriptFunction() {
            @Override
            public void call(JsonArray jsonArray) {
                System.out.println(jsonArray.toJson());
                tabSheet.setTab(UUID.fromString(jsonArray.getObject(0).getString("id")));
            }
        });

        addFunction("closeTab", new JavaScriptFunction() {
            @Override
            public void call(JsonArray jsonArray) {
                System.out.println(jsonArray.toJson());
                tabSheet.closeTab(UUID.fromString(jsonArray.getObject(0).getString("id")));
            }
        });

    }

    public void addNewTab(UUID tabId, String caption) {
        this.callFunction("addNewTab", tabId.toString(), caption);
    }

    @Override
    protected JsTabControllerState getState() {
        return (JsTabControllerState) super.getState();
    }

}

