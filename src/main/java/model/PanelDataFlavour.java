package model;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
/**
 * DISCLAIMER:
 *
 * This code is based of of MadProgrammer's response to this thread
 * https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components
 */
public class PanelDataFlavour extends DataFlavor {

    // This saves me having to make lots of copies of the same thing
    public static final PanelDataFlavour SHARED_INSTANCE = new PanelDataFlavour();

    public PanelDataFlavour() {

        super(JPanel.class, null);

    }

}