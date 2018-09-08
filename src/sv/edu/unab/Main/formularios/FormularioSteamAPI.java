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

public class FormularioSteamAPI {
    public JPanel PanelPrincipal;
    private JTextField txtcodigo;
    private JTextField txtnombre;
    private JTextField txtapellido;
    private JComboBox cbmsexo;
    private JFormattedTextField txtfechaN;
    private JTextField txttelefono;
    private JPanel panelprincipal;
    private JButton btnNuevo;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTable tableusuario;
    private JButton btnGuardar;
    private JButton btnBuscar;
    private JButton btneliminarfiltro;
    private JButton btnAplicarFiltro;
    private JTextField txtparametro;
    private JTextField txtparametro2;
    private JComboBox cboxfiltro;
    private JLabel lblMayor;
    private JLabel lblEdadPromedio;
    private JLabel lblMenor;
    private JButton btnBUSCAR;


    List<usuario> listadoModel, filtradoModel;
    String Codigo;
    String Nombre;
    String Apellido;
    Integer Telefono;
    LocalDate FechaNacimiento;
    Character Sexo;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public FormularioSteamAPI() {
        initcomponents();

    }
    public void initcomponents(){

        tableusuario.setFillsViewportHeight(true);
        if (listadoModel==null){
            listadoModel=new ArrayList<>();
        }

    
         listadoModel.add(new usuario(String.valueOf(new Random().nextInt()), "Rosa Guadalupe", "Rivera", 70079032, LocalDate.of(1980,8,12),'F'));
        listadoModel.add(new usuario(String.valueOf(new Random().nextInt()), "Carlos Josue", "Gonzales", 78787878,LocalDate.of(1975,6,15),'M'));
        listadoModel.add(new usuario(String.valueOf(new Random().nextInt()), "Rene Antonio", "Mora", 75487845,LocalDate.of(1975,1,15),'M'));
        listadoModel.add(new usuario(String.valueOf(new Random().nextInt()), "Jesus Alejandro", "Valle", 70451245,LocalDate.of(1971,2,15),'M'));
        ActualizarDatos(listadoModel);


         btnBUSCAR.addActionListener(evt -> {
            List<usuario>BuscarPersona =listadoModel.stream().filter(e ->{
                boolean encontrado =e.getNombre().toUpperCase().contains(txtnombre.getText().toUpperCase());
                if(!txtapellido.getText().isEmpty()){
                    encontrado = encontrado && e.getApellido().toUpperCase().contains(txtapellido.getText().toUpperCase());
                }
                return encontrado;
            }).collect(Collectors.toList());
             ActualizarDatos(BuscarPersona);

         });

        btnRegistrar.addActionListener(e->{
            usuario p=new usuario();
            p.setCodigo(String.valueOf(new Random().nextInt()));
            p.setNombre(txtnombre.getText());
            p.setApellido(txtapellido.getText());
            p.setTelefono(Integer.parseInt(txttelefono.getText()));
            p.setFechaNacimiento(LocalDate.parse(txtfechaN.getText(),dtf));
            p.setSexo(cbmsexo.getSelectedItem().toString().toUpperCase().charAt(0));
            listadoModel.add(p);
            ActualizarDatos(listadoModel);
            Limpiar();
        });

        btnEditar.addActionListener(e->{
            txtnombre.setText(Nombre);
            txtapellido.setText(Apellido);
            txttelefono.setText(String.valueOf(Telefono));
            txtfechaN.setText(FechaNacimiento.format(dtf));

        });



        btnEliminar.addActionListener(e->{
            listadoModel.removeIf(p->p.getCodigo().equals(Codigo));
            ActualizarDatos(listadoModel);
        });


        btnGuardar.addActionListener(e->{
            listadoModel.stream().forEach(p->{
                if (p.getCodigo().equals(Codigo))
                {
                    p.setNombre(txtnombre.getText());
                    p.setApellido(txtapellido.getText());
                    p.setTelefono(Integer.valueOf(txttelefono.getText()));
                   p.setFechaNacimiento(LocalDate.parse(txtfechaN.getText(),dtf));
                    p.setSexo(cbmsexo.getSelectedItem().toString().charAt(0));
                }
               // Limpiar();
                ActualizarDatos(listadoModel);

            });
        });



       /* btnBuscar.addActionListener(e->{
            List<usuario> busqueda=listadoModel.stream().filter(m->{
                boolean respuesta=false;
                if (m.getNombre().equals(txtnombre.getText())){
                    respuesta=true;
                }
                return  respuesta;
            }).collect(Collectors.toList());
            ActualizarDatos(busqueda);
        });*/

        btneliminarfiltro.addActionListener(e->{
            ActualizarDatos(listadoModel);
        });
        btnAplicarFiltro.addActionListener(e->{
            switch (cboxfiltro.getSelectedIndex()){
                case 0:{//igual a
                    List<usuario> igual=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
                        if (edad.equals(Long.valueOf(txtparametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(igual);
                }break;
                case 1:{//mayor a
                    List<usuario> mayorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>(Long.valueOf(txtparametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(mayorA);
                }break;
                case 2:{//mayor o igual a
                    List<usuario> mayorIgualA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>=(Long.valueOf(txtparametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(mayorIgualA);
                }break;
                case 3:{//menor a
                    List<usuario> menorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad<(Long.valueOf(txtparametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(menorA);
                }break;
                case 4:{//menor o igual a
                    List<usuario> menorIgualA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad<=(Long.valueOf(txtparametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(menorIgualA);
                }break;
                case 5:{//entre
                    List<usuario> menorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>=(Long.valueOf(txtparametro.getText()))&&edad<=(Long.valueOf(txtparametro2.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(menorA);
                }break;
            }
        });


        tableusuario.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int i=tableusuario.getSelectedRow();
                Codigo =(tableusuario.getValueAt(i,0).toString());
                Nombre=(tableusuario.getValueAt(i,1).toString());
                Apellido=(tableusuario.getValueAt(i,2).toString());
                Telefono=Integer.parseInt(tableusuario.getValueAt(i,3).toString());
                FechaNacimiento=LocalDate.parse(tableusuario.getValueAt(i,4).toString(),dtf);
                Sexo=(tableusuario.getValueAt(i,6).toString().charAt(0));

            }
        });

        try{
            MaskFormatter mascara=new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            txtfechaN.setFormatterFactory(new DefaultFormatterFactory(mascara));
        }catch(ParseException e){
            e.printStackTrace();
        }




  }

    private void ActualizarDatos(List<usuario> listado) {

        usuario edadMenor=listado.stream().min((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        usuario edadMayor=listado.stream().max((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        lblMayor.setText(edadMayor.getNombre()+" "+edadMayor.getApellido());
       lblMenor.setText(edadMenor.getNombre()+" "+edadMenor.getApellido());

        BigDecimal edadPromedio=listado.stream()
                .map(e->{
                    Long edad=e.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                    return new BigDecimal(edad);
                })
                .reduce((e1,e2)->(e1.add(e2).divide(new BigDecimal(2)))).get();
        BigDecimal bd=edadPromedio.setScale(2, RoundingMode.HALF_UP);
        lblEdadPromedio.setText(bd.setScale(2,RoundingMode.HALF_UP).toString());

        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido ");
        modelo.addColumn("Telefono");
        modelo.addColumn("Fecha N");
        modelo.addColumn("Edad");
        modelo.addColumn("Sexo");


        listado.stream().forEach(p->{
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getTelefono(),
                    p.getFechaNacimiento().format(dtf),
                    p.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS),
                    p.getSexo()

            });
        });
        tableusuario.setModel(modelo);
    }

    private void Limpiar() {
        txtnombre.setText(null);
        txtapellido.setText(null);
        txtfechaN.setText(null);
        txttelefono.setText(null);
        cbmsexo.setSelectedItem(0);
    }

    private void accept(usuario p) {
        if (p.getCodigo().equals(Codigo)) {
            p.setNombre(txtnombre.getText());
            p.setApellido(txtapellido.getText());
            p.setTelefono(Integer.valueOf(txttelefono.getText()));
            p.setFechaNacimiento(LocalDate.parse(txtfechaN.getText(), dtf));
            p.setSexo(cbmsexo.getSelectedItem().toString().charAt(0));
        }
        Limpiar();
        ActualizarDatos(listadoModel);
    }
 }
