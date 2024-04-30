package com.launcher;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Clase CustomPasswordField que extiende JPasswordField.
 * Esta clase personaliza el campo de contraseña para tener una línea en la parte inferior que se extiende cuando el campo tiene el foco.
 */
public class CustomPasswordField extends JPasswordField {
    private Border defaultBorder; // Borde por defecto con una línea en la parte inferior
    private Border focusBorder; // Borde que se aplica cuando el campo tiene el foco

    /**
     * Constructor de CustomPasswordField.
     *
     * @param columns Número de columnas del campo de contraseña.
     */
    public CustomPasswordField(int columns) {
        super(columns);

        // Crea un borde con una línea en la parte inferior
        defaultBorder = new MatteBorder(0, 0, 1, 0, Color.black);
        focusBorder = new MatteBorder(0, 0, 2, 0, Color.black);

        // Establece el borde predeterminado
        setBorder(defaultBorder);

        // Hace que el campo de texto no tenga fondo
        setOpaque(false);

        // Agrega un listener para cambiar el borde cuando el campo de texto tiene el foco
        addFocusListener(new FocusAdapter() {
            /**
             * Método que se ejecuta cuando el campo de texto gana el foco.
             * Cambia el borde a focusBorder.
             * @param e Evento de foco.
             */
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(focusBorder);
            }

            /**
             * Método que se ejecuta cuando el campo de texto pierde el foco.
             * Cambia el borde a defaultBorder.
             * @param e Evento de foco.
             */
            @Override
            public void focusLost(FocusEvent e) {
                setBorder(defaultBorder);
            }
        });
    }
}