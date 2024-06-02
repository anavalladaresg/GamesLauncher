package com.launcher.functional.initpanel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UIControllerTest {
        @Test
        public void uiControllerCanBeInstantiated() {
            assertDoesNotThrow(() -> {
                UIController controller = new UIController();
            });
        }

        @Test
        public void uiControllerDoesNotThrowExceptionOnDisplayMainMenu() {
            assertDoesNotThrow(() -> {
                UIController controller = new UIController();
                controller.displayMainMenu();
            });
        }
    }

