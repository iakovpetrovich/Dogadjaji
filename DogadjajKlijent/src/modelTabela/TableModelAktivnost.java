/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTabela;

import domen.Aktivnost;
import domen.Dogadjaj;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaša
 */
public class TableModelAktivnost extends AbstractTableModel {

    String[] columNames = {"Naziv", "Opis"};
    LinkedList<Aktivnost> listaAktivnosti = new LinkedList<>();

    public TableModelAktivnost(LinkedList<Aktivnost> listaAktivnosti) {
        this.listaAktivnosti = listaAktivnosti;
    }

    public TableModelAktivnost() {
    }
    
    @Override
    public int getRowCount() {
        return listaAktivnosti.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            System.out.println(listaAktivnosti.get(rowIndex).getNaziv());
            return listaAktivnosti.get(rowIndex).getNaziv();
        }
        if (columnIndex == 1) {
            return listaAktivnosti.get(rowIndex).getOpis();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }
    

    public void dodaj(Aktivnost a){
        listaAktivnosti.add(a);
        fireTableDataChanged();
    }
    
    public void ukolni(Aktivnost a){
        listaAktivnosti.remove();
        fireTableDataChanged();
    }
    
    public void nalepiDogadjaj(Dogadjaj d){
        for (Aktivnost aktivnost : listaAktivnosti) {
            aktivnost.setDogadjaj(d);
        }
    }

    public LinkedList<Aktivnost> getListaAktivnosti() {
        return listaAktivnosti;
    }
    
    

}
