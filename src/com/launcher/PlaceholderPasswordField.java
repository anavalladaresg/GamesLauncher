package com.launcher;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class PlaceholderPasswordField extends JPasswordField {

    private String prompt;

    public PlaceholderPasswordField() {
        super();
    }

    public PlaceholderPasswordField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderPasswordField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderPasswordField(final String pText) {
        super(pText);
    }

    public PlaceholderPasswordField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (getPassword().length == 0 && isEnabled()) {
            final Graphics2D g = (Graphics2D) pG;
            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(getDisabledTextColor());
            g.drawString(getPrompt(), getInsets().left + 10, pG.getFontMetrics()
                    .getMaxAscent() + getInsets().top + 11);
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}