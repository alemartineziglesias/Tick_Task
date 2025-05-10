package tick_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tick_task";
	String login = "root";
	String password = "Mr9+agar";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public boolean conectar()
	{
		boolean conexionCorrecta = true;

		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}
	
	public Usuarios obtenerUsuarioPorCredenciales(String nombre, String clave) 
	{
	    Usuarios usuario = null;

	    if (!conectar()) 
	    {
	        return null;
	    }

	    String sql = "SELECT idUsuario, nombreUsuario, claveUsuario FROM usuarios " +
	                 "WHERE nombreUsuario=? AND claveUsuario = SHA2(?, 256)";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) 
	    {
	        stmt.setString(1, nombre);
	        stmt.setString(2, clave);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) 
	        {
	            usuario = new Usuarios();
	            usuario.setIdUsuario(rs.getInt("idUsuario"));
	            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
	            usuario.setClaveUsuario(rs.getString("claveUsuario"));
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }

	    return usuario;
	}
	
	public List<Proyectos> obtenerProyectos() 
	{
	    List<Proyectos> lista = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    if (!conectar()) 
	    {
	        System.out.println("No se pudo establecer conexión con la base de datos.");
	        return lista;
	    }
	    String query = "SELECT idProyecto, nombreProyecto, nombreUsuario " + "FROM proyectos JOIN usuarios ON proyectos.idUsuarioFK = usuarios.idUsuario ORDER BY proyectos.idProyecto ASC";
	    try 
	    {
	        stmt = connection.prepareStatement(query);
	        rs = stmt.executeQuery();
	        while (rs.next()) 
	        {
	        	int idProyecto = rs.getInt("idProyecto");
	            String nombreProyecto = rs.getString("nombreProyecto");
	            String nombreUsuario = rs.getString("nombreUsuario");
	            lista.add(new Proyectos(idProyecto, nombreProyecto, nombreUsuario));
	        }

	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    } 
	    finally 
	    {
	        try 
	        {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    return lista;
	}
	
	@SuppressWarnings("deprecation")
	public boolean insertarProyecto(String nombreProyecto, Usuarios usuario) 
	{
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) 
	    {
	        session.beginTransaction();

	        ProyectosHibernate proyecto = new ProyectosHibernate();
	        proyecto.setNombreProyecto(nombreProyecto);
	        proyecto.setUsuario(usuario); // relación ManyToOne

	        session.save(proyecto);

	        session.getTransaction().commit();
	        return true;
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        return false;
	    }
	}

}