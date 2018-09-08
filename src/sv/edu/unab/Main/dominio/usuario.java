package sv.edu.unab.Main.dominio;

import java.time.LocalDate;
import java.util.StringJoiner;

public class usuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private  int  telefono;
    private LocalDate fechaNacimiento;
    private Character sexo;


    public usuario() {
    }


    public usuario(String codigo) {
        this.codigo = codigo;
    }


    public usuario(String codigo, String nombre, String apellido,  int telefono, LocalDate fechaNacimiento, Character sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof usuario)) return false;

        usuario Usuarios = (usuario) o;

        return codigo.equals(Usuarios.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner("")
                .add(codigo)
                .toString();
    }


    public String toString1() {
        return new StringJoiner(", ", usuario.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("nombre='" + nombre + "'")
                .add("apellido='" + apellido + "'")
                .add("telefono=" + telefono)
                .add("fechaNacimiento=" + fechaNacimiento)
                .add("sexo=" + sexo)
                .toString();
    }


}
