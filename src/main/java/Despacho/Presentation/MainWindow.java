package Despacho.Presentation;

import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Usuario;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final Usuario user;

    public MainWindow(Usuario user) {
        super("Recetas - " + user.getId() + " (" + (user.getRol()==null?"":user.getRol().toUpperCase()) + ")");
        this.user = user;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 450));

        JTabbedPane tabs = new JTabbedPane();

        String rol = user.getRol()==null? "" : user.getRol().toLowerCase();
        if ("administrador".equals(rol)) {
            tabs.addTab("Médicos", buildMedicosTab());
            tabs.addTab("Farmaceutas", buildFarmaceuticosTab());
            tabs.addTab("Pacientes", buildPacientesTab());
            tabs.addTab("Medicamentos", buildMedicamentosTab());
            tabs.addTab("Dashboard", buildDashboardTab());
            tabs.addTab("Histórico", new JPanel());
        } else if ("medico".equals(rol)) {
            tabs.addTab("Prescribir", buildPrescribirTab());
            tabs.addTab("Histórico", new JPanel());
            tabs.addTab("Dashboard", buildDashboardTab());
        } else if ("farmaceutico".equals(rol)) {
            tabs.addTab("Despacho", new JPanel());
            tabs.addTab("Histórico", new JPanel());
            tabs.addTab("Dashboard", buildDashboardTab());
        } else {
            tabs.add("Inicio", new JPanel());
        }

        setContentPane(tabs);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel buildMedicosTab() {
        var view = new Despacho.Presentation.Medico.MediAdmin();
        var model = new Despacho.Presentation.Medico.Model();
        new Despacho.Presentation.Medico.Controller(view, model);
        return view.getPanel();
    }

    private JPanel buildFarmaceuticosTab() {
        var view = new Despacho.Presentation.Farmaceutico.FarmaAdmin();
        var model = new Despacho.Presentation.Farmaceutico.Model();
        new Despacho.Presentation.Farmaceutico.Controller(view, model);
        return view.getPanel();
    }

    private JPanel buildPacientesTab() {
        var view = new Despacho.Presentation.Pacientes.PacientesAdmin();
        var model = new Despacho.Presentation.Pacientes.Model();
        new Despacho.Presentation.Pacientes.Controller(view, model);
        return view.getPanel();
    }

    private JPanel buildMedicamentosTab() {
        var view = new Despacho.Presentation.Medicamentos.MedicaAdmin();
        var model = new Despacho.Presentation.Medicamentos.ModelMedicamentos();
        var controller = new Despacho.Presentation.Medicamentos.ControllerMedicamentos(view, model);
        view.setModel(model);
        view.setController(controller);
        model.setList(Service.instance().findAllMedicamento());
        return view.getPanel();
    }

    private JPanel buildPrescribirTab() {
        var view = new Despacho.Presentation.Prescribir.Prescribir();
        var model = new Despacho.Presentation.Prescribir.Model();
        new Despacho.Presentation.Prescribir.Controller(view, model);
        return view.getPrescribir();
    }

    private JPanel buildDashboardTab() {
        var view = new Despacho.Presentation.DashBoard.ViewDB();
        var model = new Despacho.Presentation.DashBoard.Model();
        var controller = new Despacho.Presentation.DashBoard.Controller(view, model);
        controller.init();
        return view.getPanel();
    }
}
