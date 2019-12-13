package model;

import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;
/**
 * DISCLAIMER:
 *
 * The majority of this code is based of of MadProgrammer's response to this thread
 * https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components
 */
public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {

    private Container parent;
    private JPanel target;

    public DragGestureHandler(JPanel target) {

        this.target = target;

    }

    public JPanel getPanel() {
        return target;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public Container getParent() {
        return parent;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        // When the drag begins, we need to grab a reference to the
        // parent container so we can return it if the drop
        // is rejected
        Container parent = getPanel().getParent();
        System.out.println("Drag Gesture Recognized, parent = " + parent.toString());
        System.out.println("Drag Target: " + target.toString());
        setParent(parent);

        // Create our transferable wrapper
        Transferable transferable = new PanelTransferable(getPanel());
        // Start the "drag" process...
        DragSource ds = dge.getDragSource();
        ds.startDrag(dge, null, transferable, this);

        //parent.remove(getPanel());
        // Update the display
        parent.invalidate();
        parent.repaint();
    }

    /**
     * Called when dragged column starts to hover over a new column.
     * @param dsde
     */
    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    /**
     * Called when dragged column is above another column.
     * @param dsde
     */
    @Override
    public void dragOver(DragSourceDragEvent dsde) {

    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    /**
     * Called when the column which is being dragged leaves the column it is hovering over.
     * @param dse
     */
    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
//
//
//        // If the drop was not successful, we need to
//        // return the component back to it's previous
//        // parent
        if (!dsde.getDropSuccess()) {
            getParent().add(getPanel());
        } else {
            getPanel().remove(getPanel());
        }
        getParent().invalidate();
        getParent().repaint();
    }
}