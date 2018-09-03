/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTabela;

import domen.Prisustvo;
import domen.Dogadjaj;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaša
 */
public class TableModelPrisustva extends AbstractTableModel {

    String[] columNames = {"Događaj", "Datum", "Broj rez. mesta"};
    LinkedList<Prisustvo> listaPrisustvoi = new LinkedList<>();

    public TableModelPrisustva(LinkedList<Prisustvo> listaPrisustvoi) {
        this.listaPrisustvoi = listaPrisustvoi;
    }

    public TableModelPrisustva() {
    }

    @Override
    public int getRowCount() {
        return listaPrisustvoi.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {

            return listaPrisustvoi.get(rowIndex).getDogadjaj().getNaziv();
        }
        if (columnIndex == 1) {
            return listaPrisustvoi.get(rowIndex).getDogadjaj().getDatum();
        }
        if (columnIndex == 2) {
            return listaPrisustvoi.get(rowIndex).getBrojOsoba();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    public void dodaj(Prisustvo a) {
        listaPrisustvoi.add(a);
        fireTableDataChanged();
    }

    public void ukolni(Prisustvo a) {
        listaPrisustvoi.remove();
        fireTableDataChanged();
    }

    public LinkedList<Prisustvo> getListaPrisustvoi() {
        return listaPrisustvoi;
    }

    public void promeniStanje(LinkedList<Prisustvo> pronadjiMojaPrisustva) {
        listaPrisustvoi = pronadjiMojaPrisustva;
        fireTableDataChanged();
    }

}
