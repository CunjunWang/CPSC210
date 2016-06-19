package ca.ubc.cs.cpsc210.invoicemanager.ui.renderers;

import ca.ubc.cs.cpsc210.invoicemanager.model.Invoice;
import ca.ubc.cs.cpsc210.invoicemanager.ui.InvoiceTableModel;

import javax.swing.*;
import java.awt.*;

// Cell renderer for invoice table - base class for cells that render any of the fields of an invoice
public abstract class InvoiceRenderer extends BorderCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        Invoice invoice = ((InvoiceTableModel) table.getModel()).getInvoiceAtIndex(row);
        setForeground(invoice.isPaidInFull() ? FOREGROUND_DEFAULT : FOREGROUND_HIGHLIGHT);

        return this;
    }
}
