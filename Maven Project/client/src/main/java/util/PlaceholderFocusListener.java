package util;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Adds placeholder text to passed component.
 *
 * @author Konrad Rej
 * @version 2021-03-03
 */
public class PlaceholderFocusListener implements FocusListener {
    private final JTextComponent component;
    private final String text;

    /**
     * Initializes the object.
     *
     * @param component the item to add placeholder text to
     * @param text      the placeholder text to add
     */
    public PlaceholderFocusListener(JTextComponent component, String text) {
        this.component = component;
        this.text = text;
    }

    /**
     * On focus gain if the text equals the placeholder text
     * remove it.
     *
     * @param e the event itself
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (component.getText().equals(text)) {
            component.setText("");
            component.setForeground(Color.BLACK);
        }
    }

    /**
     * On focus loss if the text is empty add the placeholder
     * text again.
     *
     * @param e the event itself
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (component.getText().isEmpty()) {
            component.setForeground(Color.GRAY);
            component.setText(text);
        }
    }
}
