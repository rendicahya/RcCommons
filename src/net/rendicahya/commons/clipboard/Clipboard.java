package net.rendicahya.commons.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Clipboard {

    private static final java.awt.datatransfer.Clipboard CLIPBOARD;

    static {
        CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public static void setString(String str) {
        CLIPBOARD.setContents(new StringSelection(str), null);
    }

    public static String getString() {
        try {
            return (String) CLIPBOARD.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException ex) {
            return null;
        }
    }
}
