/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTabela;

import domen.Dogadjaj;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaša
 */
public class TableModelDogadjaji extends AbstractTableModel {

    String[] columNames = {"Naziv", "Mesto", "Opis", "Datum"};
    LinkedList<Dogadjaj> listaDogadjaja = new LinkedList<>();

    public TableModelDogadjaji(LinkedList<Dogadjaj> listaDogadjaja) {
        this.listaDogadjaja = listaDogadjaja;
    }

    public TableModelDogadjaji() {
    }

    @Override
    public int getRowCount() {
        return listaDogadjaja.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return listaDogadjaja.get(rowIndex).getNaziv();
        }
        if (columnIndex == 1) {
            return listaDogadjaja.get(rowIndex).getMesto().getNaziv();
        }
        if (columnIndex == 2) {
            return listaDogadjaja.get(rowIndex).getOpis();
        }
        if (columnIndex == 3) {
            return listaDogadjaja.get(rowIndex).getDatum();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    public void promeniStanje(LinkedList<Dogadjaj> listaDogadjaja) {
        this.listaDogadjaja = listaDogadjaja;
        fireTableDataChanged();
    }

    public LinkedList<Dogadjaj> getListaDogadjaja() {
        return listaDogadjaja;
    }

}
