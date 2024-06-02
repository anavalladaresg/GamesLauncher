package com.launcher.functional.mainpanel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryControllerTest {

    @Test
    void libraryControllerCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            LibraryController controller = new LibraryController("testUser");
        });
    }

    @Test
    void getImagePathReturnsNullForInvalidPath() {
        LibraryController controller = new LibraryController("testUser");
        assertNull(LibraryController.getImagePath("invalid/path"));
    }

    @Test
    void getImagePathReturnsCorrectPathForValidPath() {
        LibraryController controller = new LibraryController("testUser");
        assertEquals("C:\\Users\\anxor\\Downloads\\wl.png", LibraryController.getImagePath("C:\\Users\\anxor\\Downloads\\wl.png"));
    }
}