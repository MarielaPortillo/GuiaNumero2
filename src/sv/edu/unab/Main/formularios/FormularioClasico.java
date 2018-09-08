package sv.edu.unab.Main.formularios;

import sv.edu.unab.Main.dominio.usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FormularioClasico {
    //private static final Object MouseEvent = ;
    public JPanel panelclasico;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtapellido;
    private JTextField txtTelefono;
    private JFormattedTextField txtFecha;
    private JComboBox cboxSexo;
    private JPanel panel2;
    private JTable tblUsuario;
    private JButton btnNuevo;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnAplicarFiltro;
    private JTextField txtParametro;
    private JComboBox cboxFiltros;
    private JTextField txtParametro2;
    private JLabel lblEdadPromedio;
    private JButton btnBuscar;


    ArrayList<usuario> listado=new ArrayList<>();
    ArrayList<usuario> Filtrado=new ArrayList();
    String Codigo;
    String Nombre;
    String Apellido;
    Integer Telefono;
    LocalDate FechaNacimiento;
    Character Sexo;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    public FormularioClasico(){

        EdadPromedio();
        initcomponents();
        try{
            MaskFormatter mascara=new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            txtFecha.setFormatterFactory(new DefaultFormatterFactory(mascara));
        }catch(ParseException e){
            e.printStackTrace();
        }


     tblUsuario.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
            int i=tblUsuario.getSelectedRow();
            Codigo =(tblUsuario.getValueAt(i,0).toString());
            Nombre=(tblUsuario.getValueAt(i,1).toString());
            Apellido=(tblUsuario.getValueAt(i,2).toString());
            Telefono=Integer.parseInt(tblUsuario.getValueAt(i,3).toString());
            FechaNacimiento=LocalDate.parse(tblUsuario.getValueAt(i,4).toString(),dtf);
            Sexo=(tblUsuario.getValueAt(i,6).toString().charAt(0));

        }
    });


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario p=new usuario();
                p.setCodigo(String.valueOf(new Random().nextInt()));
                p.setNombre(txtNombre.getText());
                p.setApellido(txtapellido.getText());
                p.setTelefono(Integer.parseInt(txtTelefono.getText()));
                p.setFechaNacimiento(LocalDate.parse(txtFecha.getText(),dtf));
                p.setSexo(cboxSexo.getSelectedItem().toString().charAt(0));
                listado.add(p);
               Actualizar(listado);
                limpiarcomponentes();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText(Nombre);
                txtapellido.setText(Apellido);
                txtTelefono.setText(String.valueOf(Telefono));
                txtFecha.setText(FechaNacimiento.format(dtf));
                if (Sexo.equals('M')){
                    cboxSexo.setSelectedItem(0);
                }
                else{
                    cboxSexo.setSelectedItem(1);
                }
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<listado.size();i++){
                    if (Codigo.equals(listado.get(i).getCodigo())){
                        listado.get(i).setNombre(txtNombre.getText());
                        listado.get(i).setApellido(txtapellido.getText());
                        listado.get(i).setTelefono(Integer.valueOf(txtTelefono.getText()));
                        listado.get(i).setFechaNacimiento(LocalDate.parse(txtFecha.getText(),dtf));
                        listado.get(i).setSexo(cboxSexo.getSelectedItem().toString().charAt(0));
                    }
                }
                Actualizar(listado);
                limpiarcomponentes();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<listado.size(); i++){
                    if(Codigo==listado.get(i).getCodigo()){
                        listado.remove(i);
                    }
                    Actualizar(listado);
                    limpiarcomponentes();
                }
            }
        });
        btnAplicarFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cboxFiltros.getSelectedIndex()){
                    case 0:{//igual a
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if (edad1==Integer.parseInt(txtParametro.getText())){
                               usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());

                                Filtrado.add(per);

                            }
                        }
                       Actualizar(Filtrado);
                    }break;
                    case 1:{//mayor a
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if (edad1>Integer.valueOf(txtParametro.getText())){
                                usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }

                        }
                        Actualizar(Filtrado);
                    }break;
                    case 2:{//mayor o igual a
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if (edad1>=Integer.valueOf(txtParametro.getText())){
                                usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }

                        }
                        Actualizar(Filtrado);
                    }break;
                    case 3:{//menor a
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if (edad1<Integer.valueOf(txtParametro.getText())){
                                usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }

                        }
                       Actualizar(Filtrado);
                    }break;
                    case 4:{//menor o igual a
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if (edad1<=Integer.valueOf(txtParametro.getText())){
                                usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }

                        }
                        Actualizar(Filtrado);
                    }break;
                    case 5:{//entre
                        Filtrado.clear();
                        for (int i=0;i<listado.size();i++){
                            String edad=String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
                            int edad1=Integer.valueOf(edad);
                            if ((edad1>=Integer.valueOf(txtParametro.getText()))&&edad1<=Integer.valueOf(txtParametro2.getText())){
                                usuario per=new usuario();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellido(listado.get(i).getApellido());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }
                        }
                        Actualizar(Filtrado);
                    }break;
                  }
               }
            });

        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarcomponentes();
            }
        });
       /* btnEliminarFiltros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actualizar(listado);
            }
        });*/
      /* btnFiltroSexo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscar.addActionListener(evt -> {
                    List<usuario>BuscarPersona =listado.stream().filter(event->{
                        boolean encontrado =event.getNombre().toUpperCase().contains(txtNombre.getText().toUpperCase());
                        if(!txtapellido.getText().isEmpty()){
                            encontrado = encontrado && event.getApellido().toUpperCase().contains(txtapellido.getText().toUpperCase());
                        }
                        return encontrado;
                    }).collect(Collectors.toList());
                    Actualizar((ArrayList<usuario>) BuscarPersona);

                });

            /*  btnBuscar.addActionListener(event->{
                    List<usuario> busqueda=listado.stream().filter(m->{
                        boolean respuesta=false;
                        if (m.getNombre().equals(txtNombre.getText())){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    Actualizar((ArrayList<usuario>) busqueda);
                });*/


            }
        });
       /* btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarcomponentes();
            }
        });*/

    }


    public void initcomponents(){

        listado.add(new usuario(String.valueOf(new Random().nextInt()), "Orfa Marisol",  "Aleman", 70079032,LocalDate.of(1980,8,12),'F'));
        listado.add(new usuario(String.valueOf(new Random().nextInt()), "Ernesto Romeo",  "Gutierres", 78787878,LocalDate.of(1975,6,15),'M'));
        listado.add(new usuario(String.valueOf(new Random().nextInt()), "Rene Carlos",  "Portillo", 75487845,LocalDate.of(1975,1,15),'M'));
       listado.add(new usuario(String.valueOf(new Random().nextInt()), "Josue Caleb",  "Orellana", 71234567,LocalDate.of(1976,12,31),'M'));


        Actualizar(listado);


    }
    public void Actualizar(ArrayList<usuario> listado){
        String matriz[][] = new String[listado.size()][8];
        for(int i = 0; i<listado.size(); i++){
            matriz[i][0]= listado.get(i).getCodigo();
            matriz[i][1]= listado.get(i).getNombre();
            matriz[i][2]= listado.get(i).getApellido();
            matriz[i][3]= Integer.toString(listado.get(i).getTelefono());
            matriz[i][4]= String.valueOf(listado.get(i).getFechaNacimiento().format(dtf));
            matriz[i][5] = String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
            matriz[i][6]= String.valueOf(listado.get(i).getSexo().toString().charAt(0));
        }
      tblUsuario.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]
              {"Codigo","Nombre","Apellido ","Telefono","FechaN","Edad","Sexo"}));

       /* DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn( "Codigo");
        modelo.addColumn( "Nombre");
        modelo.addColumn( "Apellido");
        modelo.addColumn( "Telefono");
        modelo.addColumn( "FechaNacimiento");
        modelo.addColumn( "Edad");
        modelo.addColumn( "Sexo");

        this.tblUsuario.setModel(modelo);*/


       // EdadPromedio();


    }

    private void EdadPromedio(){
        double t=0;
        double p=0;
        double valor=0;
        int indice=0;
        if (tblUsuario.getRowCount()>0){
            for (int i=0;i<tblUsuario.getRowCount();i++){
                p=Double.parseDouble(tblUsuario.getValueAt(i,6).toString());
                t+=p;
                if (p>valor){
                 //  valor=p;
                 // lblMayor.setText(tblUsuario.getValueAt(i,2).toString()+" "+tblUsuario.getValueAt(i,3).toString() +", "+tblUsuario.getValueAt(i,1).toString());
                }
              // if (p<valor){
                  //  valor=p;
                 //  lblMenor.setText(tblUsuario.getValueAt(i,2).toString()+" "+tblUsuario.getValueAt(i,3).toString() +", "+tblUsuario.getValueAt(i,1).toString());
               // }


            }
            BigDecimal bd = new BigDecimal(t/tblUsuario.getRowCount());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            lblEdadPromedio.setText(String.valueOf(bd));

            for(int i=0;i<tblUsuario.getRowCount();i++){

            }
        }

    }

    private void limpiarcomponentes(){
        txtNombre.setText(null);
        txtapellido.setText(null);
        txtFecha.setText(null);
        txtTelefono.setText(null);
        cboxSexo.setSelectedItem(0);
    }

}

