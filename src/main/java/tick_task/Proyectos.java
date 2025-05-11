package tick_task;

public class Proyectos
{
	private int idProyecto;
	private String nombreProyecto;
	private int idUsuario;
    private String nombreUsuario;

    public Proyectos(int idProyecto, String nombreProyecto, int idUsuario, String nombreUsuario) 
    {
    	this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }
    
    public int getIdProyecto() 
    {
        return idProyecto;
    }

    public String getNombreProyecto() 
    {
        return nombreProyecto;
    }
    
    public int getIdUsuario() 
    {
        return idUsuario;
    }

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }
}
