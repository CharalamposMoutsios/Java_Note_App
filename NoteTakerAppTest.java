package com.github.JavaNoteApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class NoteTakerAppTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    private void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * 
     */
    @Test
    public void testAddNoteAndDisplayNotes() {
        provideInput("2\nFirst note\n1\n3\n");
        NoteTakerApp.main(new String[]{});
        String output = getOutput();

        List<String> notes = NoteTakerApp.loadNotes();
        int expectedNoteCount = 1;
        String expectedNote = "First note";

        Assertions.assertTrue(notes.contains(expectedNote));
        Assertions.assertTrue(output.contains(expectedNote));
        Assertions.assertTrue(output.contains("Note added successfully!"));
        Assertions.assertTrue(output.contains("----- Your Notes -----"));
        Assertions.assertTrue(output.contains(expectedNoteCount + ". " + expectedNote));
    }

    @Test
    public void testExitOption() {
        provideInput("3\n");
        NoteTakerApp.main(new String[]{});
        String output = getOutput();

        Assertions.assertTrue(output.contains("Exiting Note Taker. Goodbye!"));
    }
}
