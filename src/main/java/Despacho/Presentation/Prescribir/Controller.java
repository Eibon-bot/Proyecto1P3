package Despacho.Presentation.Prescribir;

import Despacho.Presentation.Prescribir.Prescribir;

import javax.swing.*;

public class Controller {
        Prescribir prescribirView;
        Model model;

        public Controller(Prescribir prescribirView, Model model) {
            this.prescribirView = prescribirView;
            this.model = model;
            prescribirView.setController(this);
            prescribirView.setModel(model);
        }




}
