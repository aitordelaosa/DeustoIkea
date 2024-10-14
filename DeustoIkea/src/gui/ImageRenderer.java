package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class ImageRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    JLabel label = new JLabel();
	    if (value instanceof ImageIcon) {
	        label.setIcon((ImageIcon) value);
	        label.setHorizontalAlignment(JLabel.CENTER);
	        label.setVerticalAlignment(JLabel.CENTER);
	    } else {
	        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }
	    
	    label.setPreferredSize(new java.awt.Dimension(100, 100));
	    return label;
	}
}





