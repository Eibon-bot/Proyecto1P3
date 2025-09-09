package Despacho.Presentation.Prescribir;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Paciente;

public class Controller {
        Prescribir prescribirView;
        Model model;

        public Controller(Prescribir prescribirView, Model model) {
            this.prescribirView = prescribirView;
            this.model = model;
            prescribirView.setController(this);
            prescribirView.setModel(model);
        }

        //Buscar Paciente Metodos
        public void searchPacienteNombre(String nombre) {
            Paciente p = new Paciente();
            p.setNombre(nombre);
            model.setListPaciente(Service.instance().searchPacienteNombre(p));
        }
        public void searchPacienteId(String id) {
        Paciente p = new Paciente();
        p.setId(id);
        model.setListPaciente(Service.instance().searchPacienteId(p));
        }
        public void setPaciente(int row) {
        model.setCurrentPaciente(model.getListPaciente().get(row));
        }

        public void loadPacientes() {
        model.setListPaciente(Service.instance().findAllPaciente());
        }
    public void searchMedicamentoNombre(String nombre) {
        Medicamento p = new Medicamento();
        p.setNombre(nombre);
        model.setListmedicamento(Service.instance().searchMedicamentoNombre(p));
    }
    public void searchMedicamentoCod(String cod) {
        Medicamento p = new Medicamento();
        p.setCodigo(cod);
        model.setListmedicamento(Service.instance().searchMedicamentoCod(p));
    }

    public void setMedicamento(int row) {
        model.setCurrentMedicamento(model.getListaMedicamentos().get(row));
    }

    public void loadMedicamentos() {
        model.setListmedicamento(Service.instance().findAllMedicamento());
    }









}
