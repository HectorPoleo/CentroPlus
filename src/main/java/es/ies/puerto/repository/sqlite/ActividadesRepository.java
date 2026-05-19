package es.ies.puerto.repository.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.modelo.Actividades;
import es.ies.puerto.modelo.Usuario;
import es.ies.puerto.repository.IActividadesRepository;

public class ActividadesRepository implements IActividadesRepository{

    @Override
    public boolean save(Actividades actividad) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO actividades (id, nombre, tipo_actividad, duracion, precio, plazas_maximos, plazas_ocupadas) VALUES (?,?,?,?,?,?,?)")){
            sentencia.setInt(1, actividad.getId());
            sentencia.setString(2, actividad.getNombre());
            sentencia.setString(3, actividad.getTipoActividad());
            sentencia.setInt(4, actividad.getDuracion());
            sentencia.setInt(5, actividad.getPrecio());
            sentencia.setInt(6, actividad.getPlazas_maximas());
            sentencia.setInt(7, actividad.getPlazas_ocupadas());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando usuario");
            return false;
        }
    }

    @Override
    public boolean update(Actividades actividad) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE actividades SET nombre = ?, tipo_actividad = ?, duracion = ?, precio = ?, plazas_maximos = ?, plazas_ocupadas = ?")){
            sentencia.setString(2, actividad.getNombre());
            sentencia.setString(3, actividad.getTipoActividad());
            sentencia.setInt(4, actividad.getDuracion());
            sentencia.setInt(5, actividad.getPrecio());
            sentencia.setInt(6, actividad.getPlazas_maximas());
            sentencia.setInt(7, actividad.getPlazas_ocupadas());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando usuario");
            return false;
        }
    }

    @Override
    public Actividades findById(int id) {
        Actividades actividad = null;
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM actividades WHERE id =?")){
            setencia.setInt(1, id);
            ResultSet resultado = setencia.executeQuery();
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String tipoActividad = resultado.getString("tipo_actividad");
                int duracion = resultado.getInt("duracion");
                int precio = resultado.getInt("precio");
                String plazas_maximas = resultado.getString("plazas_maximas");
                String plazas_ocupadas = resultado.getString("plazas_ocupadas");
                actividad = new Actividades(id, nombre, tipoActividad, duracion, precio, duracion, precio);
            }
            return actividad;
        } catch (Exception e) {
            System.err.println("Error buscando los usuarios");
            return null;
        }
    }

    @Override
    public List<Actividades> findAll() {
        List<Actividades> actividades = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM actividades")){
            ResultSet resultado = setencia.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String tipoActividad = resultado.getString("tipo_actividad");
                int duracion = resultado.getInt("duracion");
                int precio = resultado.getInt("precio");
                String plazas_maximas = resultado.getString("plazas_maximas");
                String plazas_ocupadas = resultado.getString("plazas_ocupadas");
                actividades.add(new Actividades(id, nombre, tipoActividad, duracion, precio, duracion, precio));
            }
            return actividades;
            
        } catch (Exception e) {
            System.err.println("Error buscando los usuarios");
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("DELETE * FROM actividades WHERE id =?")){
            setencia.setInt(1, id);
            return setencia.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario");
            return false;
        }
    }

}
