package com.launcher.styles;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

/**
 * Un campo de texto con un marcador de posición.
 */
public class PlaceholderTextField extends JTextField {

    /**
     * El texto del marcador de posición.
     */
    private String prompt;

    /**
     * Crea un nuevo PlaceholderTextField.
     */
    public PlaceholderTextField() {
        super();
    }

    /**
     * Crea un nuevo PlaceholderTextField.
     *
     * @param pDoc El documento a utilizar.
     * @param pText El texto a utilizar.
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    /**
     * Crea un nuevo PlaceholderTextField.
     *
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    /**
     * Crea un nuevo PlaceholderTextField.
     * @param pText El texto a utilizar.
     */
    public PlaceholderTextField(final String pText) {
        super(pText);
    }

    /**
     * Crea un nuevo PlaceholderTextField.
     *
     * @param pText El texto a utilizar.
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    /**
     * Protege el <code>Graphics</code> del componente.
     * @param pG the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (getText().isEmpty() && isEnabled()) {
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
     * Devuelve el texto del marcador de posición.
     * @return String
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Establece el texto del marcador de posición.
     * @param prompt El texto del marcador de posición.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}