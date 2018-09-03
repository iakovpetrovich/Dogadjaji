package start;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import komunikacija.KomunikacijaSaKlijentom;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaša
 */
public class TableModelLog extends AbstractTableModel {

    String[] columNames = {"Korisnik", "Datum"};
    LinkedList<KomunikacijaSaKlijentom> lista = new LinkedList<KomunikacijaSaKlijentom>();

    public TableModelLog(LinkedList<KomunikacijaSaKlijentom> lista) {
        this.lista = lista;
    }

    public TableModelLog() {
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return lista.get(rowIndex).getNalog().getIme();
        }
        if (columnIndex == 1) {
            return lista.get(rowIndex).getVremePrisupa();
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    public void promeniStanje(LinkedList<KomunikacijaSaKlijentom> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public LinkedList<KomunikacijaSaKlijentom> getListaKomunikacijaSaKlijentoma() {
        return lista;
    }
    
        public void dodaj(KomunikacijaSaKlijentom a){
        lista.add(a);
        fireTableDataChanged();
    }
    
    public void ukolni(KomunikacijaSaKlijentom a){
        lista.remove();
        fireTableDataChanged();
    }

}
