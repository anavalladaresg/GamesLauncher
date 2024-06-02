package com.launcher.styles;

import org.junit.jupiter.api.Test;
import javax.swing.text.PlainDocument;
import static org.junit.jupiter.api.Assertions.*;

public class PlaceholderTextFieldTest {

    @Test
    void placeholderTextFieldCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            PlaceholderTextField field = new PlaceholderTextField();
        });
    }

    @Test
    void placeholderTextFieldCanBeInstantiatedWithDocumentTextAndColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderTextField field = new PlaceholderTextField(new PlainDocument(), "text", 10);
        });
    }

    @Test
    void placeholderTextFieldCanBeInstantiatedWithColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderTextField field = new PlaceholderTextField(10);
        });
    }

    @Test
    void placeholderTextFieldCanBeInstantiatedWithText() {
        assertDoesNotThrow(() -> {
            PlaceholderTextField field = new PlaceholderTextField("text");
        });
    }

    @Test
    void placeholderTextFieldCanBeInstantiatedWithTextAndColumns() {
        assertDoesNotThrow(() -> {
            PlaceholderTextField field = new PlaceholderTextField("text", 10);
        });
    }

    @Test
    void promptCanBeSetAndRetrieved() {
        PlaceholderTextField field = new PlaceholderTextField();
        field.setPrompt("test");
        assertEquals("test", field.getPrompt());
    }
}