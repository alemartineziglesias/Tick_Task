package tick_task;

public class Proyectos
{
	private int idProyecto;
	private String nombreProyecto;
    private String nombreUsuario;

    public Proyectos(int idProyecto, String nombreProyecto, String nombreUsuario) 
    {
    	this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
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

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    @Override
    public String toString() 
    {
        return nombreProyecto + " - " + nombreUsuario;
    }
}
