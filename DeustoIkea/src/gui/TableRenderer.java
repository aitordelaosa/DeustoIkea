package gui;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (column == 2) {
            cell.setBackground(java.awt.Color.darkGray);
        } else if (column == 1) {
            cell.setBackground(java.awt.Color.GRAY);
        } else {
            cell.setBackground(java.awt.Color.WHITE);
        }

        if (isSelected) {
            cell.setBackground(java.awt.Color.LIGHT_GRAY);
        }

        return cell;
    }
}

