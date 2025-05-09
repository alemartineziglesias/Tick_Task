package tick_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean comprobarCredenciales(String usuario, String clave)
	{
		boolean credencialesCorrectas = true;
		
		if (conectar() == false) 
		{
			return false;
		}
		
		String sentencia = "SELECT * FROM usuarios " 
		+ "WHERE nombreUsuario='" + usuario + "' " 
		+ "AND claveUsuario = SHA2('" + clave + "', 256);";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if(!rs.next())
			{
				credencialesCorrectas = false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return credencialesCorrectas;
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
	    String query = "SELECT nombreProyecto, nombreUsuario " + "FROM proyectos JOIN usuarios ON proyectos.idUsuarioFK = usuarios.idUsuario ORDER BY proyectos.idProyecto ASC";
	    try 
	    {
	        stmt = connection.prepareStatement(query);
	        rs = stmt.executeQuery();
	        while (rs.next()) 
	        {
	            String nombreProyecto = rs.getString("nombreProyecto");
	            String nombreUsuario = rs.getString("nombreUsuario");
	            lista.add(new Proyectos(nombreProyecto, nombreUsuario));
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
}