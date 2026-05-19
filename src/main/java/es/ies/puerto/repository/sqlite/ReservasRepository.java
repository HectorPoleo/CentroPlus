package es.ies.puerto.repository.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.connection.SQLiteConnectionManager;
import es.ies.puerto.modelo.Incidencias;
import es.ies.puerto.modelo.Reservas;
import es.ies.puerto.repository.IReservasRepository;

public class ReservasRepository extends SQLiteConnectionManager implements IReservasRepository{

    @Override
    public boolean save(Reservas reserva) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO reservas (id, id_usuario, id_actividad, fecha, estado) VALUES (?,?,?,?,?)")){
            sentencia.setInt(1, reserva.getIdReserva());
            sentencia.setInt(2, reserva.getId());
            sentencia.setInt(3, reserva.getIdActividad());
            sentencia.setString(4, reserva.getFecha());
            sentencia.setString(5, reserva.getEstado());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando reserva");
            return false;
        }
    }

    @Override
    public boolean update(Reservas reserva) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE reservas SET id_usuario = ?, id_actividad = ?, fecha = ?, estado = ?")){
            sentencia.setInt(2, reserva.getId());
            sentencia.setInt(3, reserva.getIdActividad());
            sentencia.setString(4, reserva.getFecha());
            sentencia.setString(5, reserva.getEstado());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando reserva");
            return false;
        }
    }

    @Override
    public Reservas findById(int idReserva) {
        Reservas reserva = null;
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM reservas WHERE id =?")){
            setencia.setInt(1, idReserva);
            ResultSet resultado = setencia.executeQuery();
            while (resultado.next()) {
                int idUsuario = resultado.getInt("id_usuario");
                int idActividad = resultado.getInt("id_actividades");
                String asunto = resultado.getString("asunto");
                String descripcion = resultado.getString("descripcion");
                String fecha = resultado.getString("fecha");
                String estado = resultado.getString("estado");
                reserva = new Reservas(idReserva, idUsuario, idActividad, fecha, estado);
            }
            return reserva;
        } catch (Exception e) {
            System.err.println("Error buscando los usuarios");
            return null;
        }
    }

    @Override
    public List<Reservas> findAll() {
        List<Reservas> reservas = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM reservas")){
            ResultSet resultado = setencia.executeQuery();
            while (resultado.next()) {
                int idReserva = resultado.getInt("id");
                int idUsuario = resultado.getInt("id_usuario");
                int idActividad = resultado.getInt("id_actividades");
                String asunto = resultado.getString("asunto");
                String descripcion = resultado.getString("descripcion");
                String fecha = resultado.getString("fecha");
                String estado = resultado.getString("estado");
                reservas.add(new Reservas(idReserva, idUsuario, idActividad, fecha, estado));
            }
            return reservas;
            
        } catch (Exception e) {
            System.err.println("Error buscando los reserva");
            return null;
        }
    }

    @Override
    public boolean delete(int idReserva) {
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("DELETE * FROM reservas WHERE id =?")){
            setencia.setInt(1, idReserva);
            return setencia.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar el reserva");
            return false;
        }
    }
    
}
