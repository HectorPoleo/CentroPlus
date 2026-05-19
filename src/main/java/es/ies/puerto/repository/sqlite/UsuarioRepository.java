package es.ies.puerto.repository.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.connection.SQLiteConnectionManager;
import es.ies.puerto.modelo.Usuario;
import es.ies.puerto.repository.IUsuarioRepository;

public class UsuarioRepository extends SQLiteConnectionManager implements IUsuarioRepository{

    @Override
    public boolean save(Usuario usuario) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO usuarios (id, nombre, dni, email, telefono, tipo_usuario) VALUES (?,?,?,?,?,?)")){
            sentencia.setInt(1, usuario.getId());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getDni());
            sentencia.setString(4, usuario.getEmail());
            sentencia.setString(5, usuario.getTelefono());
            sentencia.setString(6, usuario.getTipo_usuario());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando usuario");
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE usuarios SET nombre = ?, dni = ?, email = ?, telefono = ?, tipo_usuario = ?")){
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getDni());
            sentencia.setString(3, usuario.getEmail());
            sentencia.setString(4, usuario.getTelefono());
            sentencia.setString(5, usuario.getTipo_usuario());
            sentencia.setInt(6, usuario.getId());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando usuario");
            return false;
        }
    }

    @Override
    public Usuario findById(int id) {
        Usuario usuario = null;
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM usuarios WHERE id =?")){
            setencia.setInt(1, id);
            ResultSet resultado = setencia.executeQuery();
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String dni = resultado.getString("dni");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");
                String tipoUsuario = resultado.getString("tipo_usuario");
                usuario = new Usuario(id, nombre, dni, email, telefono, tipoUsuario);
            }
            return usuario;
        } catch (Exception e) {
            System.err.println("Error buscando los usuarios");
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("SELECT * FROM usuarios")){
            ResultSet resultado = setencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String dni = resultado.getString("dni");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");
                String tipoUsuario = resultado.getString("tipo_usuario");
                usuarios.add(new Usuario(id, nombre, dni, email, telefono, tipoUsuario));
            }
            return usuarios;
            
        } catch (Exception e) {
            System.err.println("Error buscando los usuarios");
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = getConnection();
        PreparedStatement setencia = connection.prepareStatement("DELETE * FROM usuarios WHERE id =?")){
            setencia.setInt(1, id);
            return setencia.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario");
            return false;
        }
    }
    
}
