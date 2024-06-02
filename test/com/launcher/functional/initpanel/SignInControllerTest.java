package com.launcher.functional.initpanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.replication.PGReplicationConnectionImpl;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SignInControllerTest {
    private SignInController signInController;
    private JButton button;

    @BeforeEach
    public void setUp() {
        signInController = new SignInController();
        button = new JButton();
    }

    @Test
    public void getCurrentUser_returnsCurrentUser() {
        assertNotNull(SignInController.getCurrentUser());
    }

    @Test
    public void getPurple_returnsPurpleColor() {
        assertEquals(new Color(80, 65, 165), SignInController.getPurple());
    }

}