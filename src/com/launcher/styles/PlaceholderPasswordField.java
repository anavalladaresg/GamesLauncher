package com.launcher.styles;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

/**
 * Un campo de contraseña con un marcador de posición.
 */
public class PlaceholderPasswordField extends JPasswordField {

    /**
     * El texto del marcador de posición.
     */
    private String prompt;

    /**
     * Crea un nuevo PlaceholderPasswordField.
     */
    public PlaceholderPasswordField() {
        super();
    }

    /**
     * Crea un nuevo PlaceholderPasswordField.
     *
     * @param pDoc El documento a utilizar.
     * @param pText El texto a utilizar.
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderPasswordField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    /**
     * Crea un nuevo PlaceholderPasswordField.
     *
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderPasswordField(final int pColumns) {
        super(pColumns);
    }

    /**
     * Crea un nuevo PlaceholderPasswordField.
     * @param pText El texto a utilizar.
     */
    public PlaceholderPasswordField(final String pText) {
        super(pText);
    }

    /**
     * Crea un nuevo PlaceholderPasswordField.
     *
     * @param pText El texto a utilizar.
     * @param pColumns El número de columnas a utilizar.
     */
    public PlaceholderPasswordField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    /**
     * Pinta el componente.
     *
     * @param pG El objeto gráfico.
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
     * Obtiene el marcador de posición.
     *
     * @return El marcador de posición.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Establece el marcador de posición.
     *
     * @param prompt El marcador de posición a establecer.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}