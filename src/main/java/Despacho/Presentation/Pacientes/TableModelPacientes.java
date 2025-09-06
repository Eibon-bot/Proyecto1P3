package Despacho.Presentation.Pacientes;

import Despacho.AbstractTableModel;
import Despacho.Logic.Entidades.Paciente;

import java.util.List;

public class TableModelPacientes extends AbstractTableModel<Paciente> implements javax.swing.table.TableModel {
    public TableModelPacientes(int[] cols, List<Paciente> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
    }
    @Override
    protected Object getPropetyAt(Paciente e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            default:
                return "";
        }


    }

}
