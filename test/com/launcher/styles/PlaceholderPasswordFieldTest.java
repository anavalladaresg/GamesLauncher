package com.launcher.styles;

import org.junit.jupiter.api.Test;
import javax.swing.text.PlainDocument;
import static org.junit.jupiter.api.Assertions.*;

public class PlaceholderPasswordFieldTest {

    @Test
    void placeholderPasswordFieldCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            PlaceholderPasswordField field = new PlaceholderPasswordField();
        });
    }

    @Test
    void placeholderPasswordFieldCanBeInstantiatedWithDocumentTextAndColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderPasswordField field = new PlaceholderPasswordField(new PlainDocument(), "text", 10);
        });
    }

    @Test
    void placeholderPasswordFieldCanBeInstantiatedWithColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderPasswordField field = new PlaceholderPasswordField(10);
        });
    }

    @Test
    void placeholderPasswordFieldCanBeInstantiatedWithText() {
        assertDoesNotThrow(() -> {
            PlaceholderPasswordField field = new PlaceholderPasswordField("text");
        });
    }

    @Test
    void placeholderPasswordFieldCanBeInstantiatedWithTextAndColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderPasswordField field = new PlaceholderPasswordField("text", 10);
        });
    }

    @Test
    void promptCanBeSetAndRetrieved() {
        PlaceholderPasswordField field = new PlaceholderPasswordField();
        field.setPrompt("test");
        assertEquals("test", field.getPrompt());
    }
}