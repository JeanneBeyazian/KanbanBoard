package model;

import view.KanbanBoard;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * DISCLAIMER:
 *
 * The majority of this code is based of of MadProgrammer's response to this thread
 * https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components
 */
public class DropHandler implements DropTargetListener, Serializable {

    private JPanel target;

    public DropHandler(JPanel target) {

        this.target = target;

    }
//
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("Drag Entered");

        // Determine if we can actually process the contents coming in.
        if (dtde.isDataFlavorSupported(PanelDataFlavour.SHARED_INSTANCE)) {
            System.out.println("ACCEPTING");

            dtde.acceptDrag(DnDConstants.ACTION_MOVE);

        } else {
            System.out.println("REJECTING");
            dtde.rejectDrag();

        }

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    // TODO - REFERENCE https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components

    /**
     * Callled when you let go of a column while moving it.
     * @param dtde
     */
    @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;
        System.out.println("Dropping...");

        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavour.SHARED_INSTANCE)) {
            KanbanColumn targetColumn = (KanbanColumn) target;
            BoardPanel parentBoard =  targetColumn.getColumnParent();
            ArrayList<KanbanColumn> colArray = parentBoard.getColumns();

            System.out.println("Target Index:"+ parentBoard.getColumns().indexOf(targetColumn));


            Transferable transferable = dtde.getTransferable();
            try {
                Object dropped = transferable.getTransferData(PanelDataFlavour.SHARED_INSTANCE);
                if (dropped instanceof KanbanColumn && dropped != targetColumn) {

                    KanbanColumn droppedColumn = (KanbanColumn) dropped;

                    int sourceIndex = -1;
                    for (int i = 0; i < parentBoard.getColumns().size(); i++){
                        KanbanColumn findCol = parentBoard.getColumns().get(i);
                        if(findCol.getId() == droppedColumn.getId()){
                            sourceIndex = i;
                            break;
                        }
                    }

                    System.out.println("Source Index: " + sourceIndex);
                    System.out.println("Source Column: "+ droppedColumn.getColumnTitle());


                    // looking at the indexes of the KanbanColumns' arrays
                    int targetIndex = parentBoard.getColumns().indexOf(targetColumn);

//                    if(sourceIndex < targetIndex){
//                        colArray.add(targetIndex, droppedColumn);
//                        colArray.remove(sourceIndex + 1);
//                    } else {
//                        colArray.add(targetIndex + 1, droppedColumn);
//                        colArray.remove(sourceIndex);
//                    }

                    colArray.remove(sourceIndex);
                    colArray.add(targetIndex, droppedColumn);

                    success = true;

                    //parentBoard.clearBoard();
                    parentBoard.redraw();

                    //KanbanBoard.redrawBoard(parentBoard);
                    //parentBoard.repaint();

//                    JPanel panel = (JPanel) data;
//                    DropTargetContext dtc = dtde.getDropTargetContext();
//                    Component component = dtc.getComponent();
//                    if (component instanceof JComponent) {
//                        Container parent = panel.getParent();
//                        if (parent != null) {
//                            parent.remove(panel);
//                            parent.revalidate();
//                            parent.repaint();
//                        }
//                        ((JComponent) component).add(panel);
//                        success = true;
//                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
//                        ((JComponent) component).invalidate();
//                        ((JComponent) component).repaint();
//                    } else {
//                        success = false;
//                        dtde.rejectDrop();
//                    }
                } else {
//                    success = false;
//                    dtde.rejectDrop();
                }
            } catch (Exception exp) {
//                success = false;
//                dtde.rejectDrop();
                exp.printStackTrace();
            }
        } else {
//            success = false;
//            dtde.rejectDrop();
        }
        dtde.dropComplete(success);
    }
}