package gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Armario;
import domain.Baño;
import domain.Tipo;

public class Modelo extends DefaultTableModel{
		private List<Object> lista;
		private List<String> lTitulos;
		private Tipo tipo;
		
		public Modelo(List<Object> l, List<String> lt, Tipo t) {
			lista = l;
			lTitulos = lt;
			tipo = t;
		}

		@Override
		public int getRowCount() {
			if(lista == null) {
				return 0;
				
			}
			return lista.size();
		}

		@Override
		public int getColumnCount() {
			return lTitulos.size();
		}

		@Override
		public String getColumnName(int column) {
			return lTitulos.get(column);
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (tipo) {
				case Tipo.Armario:
						Armario a = (Armario) lista.get(row);
						switch(column) {
							case 0: return a.getDescripcion();
							case 1: return a.getColor();
							default: return null;
						}
				case Tipo.Baño:
						Baño b = (Baño)lista.get(row);
						switch(column) {
							case 0: return b.getIdProducto();
							case 1: return b.getDescripcionB();
							default: return null;
						}	
				default: return null;
				
			}
		}
		
		
}
