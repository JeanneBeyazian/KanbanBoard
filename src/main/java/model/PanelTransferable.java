package model;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
/**
 * DISCLAIMER:
 *
 * This code is based of of MadProgrammer's response to this thread
 * https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components
 */
public class PanelTransferable implements Transferable {

    private DataFlavor[] flavors = new DataFlavor[]{PanelDataFlavour.SHARED_INSTANCE};
    private JPanel panel;

    public PanelTransferable(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }



    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        // Okay, for this example, this is overkill, but makes it easier
        // to add new flavor support by subclassing
        boolean supported = false;

        for (DataFlavor mine : getTransferDataFlavors()) {

            if (mine.equals(flavor)) {

                supported = true;
                break;

            }

        }

        return supported;

    }

    public JPanel getPanel() {

        return panel;

    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        Object data;
        if (isDataFlavorSupported(flavor)) {

            data = getPanel();

        } else {

            throw new UnsupportedFlavorException(flavor);

        }

        return data;

    }

}