package sv.edu.unab.Main;

import sv.edu.unab.Main.formularios.FormularioClasico;
import sv.edu.unab.Main.formularios.FormularioSteamAPI;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        //Dimension resolucionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //*Ajustando tamaño del formulario
       // Dimension ajusteTamaño = new Dimension(resolucionPantalla.width/2,resolucionPantalla.height - 100);
        //JFrame frm = new JFrame("Usuario a Registrar");
        //frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frm.setContentPane(new FormularioClasico().panelClasico);
        //frm.setPreferredSize(ajusteTamaño);
       // frm.setResizable(false);
        //frm.pack();
       // frm.setLocationRelativeTo(null);
       // frm.setVisible(true);


        JFrame frm=new JFrame("Asignacion Registro");
         frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FormularioSteamAPI().PanelPrincipal);
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}
