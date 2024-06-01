package com.launcher;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

/**
 * A password field with a placeholder.
 */
public class PlaceholderPasswordField extends JPasswordField {

    /**
     * The placeholder text.
     */
    private String prompt;

    /**
     * Creates a new PlaceholderPasswordField.
     */
    public PlaceholderPasswordField() {
        super();
    }

    /**
     * Creates a new PlaceholderPasswordField.
     *
     * @param pDoc The document to use.
     * @param pText The text to use.
     * @param pColumns The number of columns to use.
     */
    public PlaceholderPasswordField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    /**
     * Creates a new PlaceholderPasswordField.
     *
     * @param pColumns The number of columns to use.
     */
    public PlaceholderPasswordField(final int pColumns) {
        super(pColumns);
    }

    /**
     * Creates a new PlaceholderPasswordField.
     * @param pText The text to use.
     */
    public PlaceholderPasswordField(final String pText) {
        super(pText);
    }

    /**
     * Creates a new PlaceholderPasswordField.
     *
     * @param pText The text to use.
     * @param pColumns The number of columns to use.
     */
    public PlaceholderPasswordField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    /**
     * Paints the component.
     *
     * @param pG The graphics object.
     */
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (getPassword().length == 0 && isEnabled()) {
            final Graphics2D g = (Graphics2D) pG;
            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(getDisabledTextColor());
            g.drawString(getPrompt(), getInsets().left, pG.getFontMetrics()
                    .getMaxAscent() + getInsets().top + 11);
        }
    }

    /**
     * Gets the prompt.
     *
     * @return The prompt.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the prompt.
     *
     * @param prompt The prompt to set.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}