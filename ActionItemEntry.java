import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import backend.ActionItem;
import backend.Priority;

class ActionItemEntry extends JPanel
		implements ListCellRenderer<Object>, Transferable {
	private JLabel label;
	private ActionItem item;
	private int index;
	private ActionItemEntry prevItemEntry;
	private ActionItemEntry nextItemEntry;
	protected static DataFlavor actionFlavor = new DataFlavor(
			ActionItemEntry.class, "Action Item Entry");
	protected static DataFlavor[] supportedFlavors = { actionFlavor };

	ActionItemEntry(ActionItem item) {
		this.setItem(item);
		this.initItem(item.getTitle(), item.getPriority());
	}
	
	public void setItem(ActionItem item) {
		this.item = item;
	}
	
	public void setIndex(int i) {
		this.index = i;
	}
	
	public int getIndex() {
		return this.index;
	}

	public ActionItem getActionItem() {
		return this.item;
	}
	
	public void setPrev(ActionItemEntry item) {
		this.prevItemEntry = item;
	}

	public void setNext(ActionItemEntry item) {
		this.nextItemEntry = item;
	}
	
	public ActionItemEntry getPrev() {
		return this.prevItemEntry;
	}
	
	public ActionItemEntry getNext() {
		return this.nextItemEntry;
	}
	
	public String toString() {
		return item.getTitle();
	}

	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		initItem(value.toString(), ((ActionItemEntry) value).getActionItem().getPriority());
		return this;
	}
	
	private void initItem(String s, Priority p) {
		this.removeAll();
		this.setLayout(new BorderLayout());
		this.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5), BorderFactory.createLineBorder(new Color(230, 230, 230), 15, true)));
		this.setBackground(Color.white);
		this.setMaximumSize(new Dimension(9999, 70));
		label = new JLabel();
		label.setText(s);	
		if (p == Priority.URGENT)
			label.setFont(MainScreen.BOLD_FONT);
		else if (p == Priority.INACTIVE)
			label.setFont(MainScreen.ITALIC_FONT);
		else if (p == Priority.EVENTUAL)
			label.setFont(MainScreen.LIGHT_ITALIC_FONT);
		else
			label.setFont(MainScreen.NORMAL_FONT);
		label.setForeground(Color.decode("#134D37"));
		label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		label.setOpaque(true);
		label.setBackground(new Color(230, 230, 230));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(label);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return flavor.equals(actionFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		if (flavor.equals(actionFlavor))
			return this;
		else
			throw new UnsupportedFlavorException(flavor);
	}

}