package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	    Font font = new Font("Times New Roman", Font.BOLD, 14);
	    if (column == 0) {
	        font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14);
	    }
	    
	    cell.setFont(font);
	    
	    setHorizontalAlignment(SwingConstants.CENTER);

	    if (column == 0) {
	        cell.setBackground(new java.awt.Color(210, 238, 255));
	    } else if (column == 1) {
	        cell.setBackground(new java.awt.Color(224, 255, 255));
	    } else if (column == 2) {
	        cell.setBackground(new java.awt.Color(173, 216, 230));
	    } else if (column == 3) {
	        cell.setBackground(new java.awt.Color(240, 248, 255));
	    } else {
	        cell.setBackground(java.awt.Color.WHITE);
	    }

	    if (isSelected) {
            cell.setBackground(new Color(135, 206, 250));
            cell.setForeground(Color.WHITE);
        } else {
            cell.setForeground(Color.BLACK);
        }
	    
	    if (hasFocus) {
            ((JComponent) cell).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        } else {
            ((JComponent) cell).setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        }

	    return cell;
	}


}

