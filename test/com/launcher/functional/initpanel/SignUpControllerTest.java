package com.launcher.functional.initpanel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SignUpControllerTest {

    @Test
    public void testSignUpController() {
        assertDoesNotThrow(() -> {
            SignUpController signUpController = new SignUpController();
        });
    }

    @Test
    public void testGetCurrentUser() {
        assertDoesNotThrow(() -> {
            SignUpController.getCurrentUser();
        });
    }
}
