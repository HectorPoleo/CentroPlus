package es.ies.puerto.modelo;

import java.util.Objects;

public class Incidencias extends Reservas{
    
    int idIncidencia;
    String asunto;
    String descripcion;
    String fecha;
    String estado;

    public Incidencias(){
        super();
    }

    public Incidencias(int idIncidencia, int idUsuario, String asunto, String descripcion, String fecha, String estado) {
        super(idUsuario);
        this.idIncidencia = idIncidencia;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdIncidencia() {
        return this.idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getAsunto() {
        return this.asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(idIncidencia);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Incidencias other = (Incidencias) obj;
        return idIncidencia == other.idIncidencia;
    }

    @Override
    public String toString() {
        return "{" +
            " idIncidencia='" + getIdIncidencia() + "'" +
            ", idUsuario='" + getId() +"'"+
            ", asunto='" + getAsunto() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }

}
