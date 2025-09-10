package Despacho.Presentation.Historico;

import Despacho.Logic.Entidades.Receta;
import Despacho.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel<Receta> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    public static final int PACIENTE_ID = 0;
    public static final int PACIENTE_NOMBRE = 1;
    public static final int MEDICO_ID = 2;
    public static final int FECHA_EMISION = 3;
    public static final int ESTADO = 4;


    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[PACIENTE_ID] = "ID Paciente";
        colNames[PACIENTE_NOMBRE] = "Nombre Paciente";
        colNames[MEDICO_ID] = "ID Médico";
        colNames[FECHA_EMISION] = "Fecha Emisión";
        colNames[ESTADO] = "Estado";

    }
    @Override
    protected Object getPropetyAt(Receta e, int col) {
        switch (cols[col]) {
        case PACIENTE_ID:
            return e.getPaciente().getId();
        case PACIENTE_NOMBRE:
            return  e.getPaciente().getNombre();
        case MEDICO_ID:
            return e.getMedico().getId();
        case FECHA_EMISION:
            return  e.getFechaEmision().toString();
        case ESTADO:
            return e.getEstado();
        default:
            return "";
    }


    }

}

