package tick_task;

public class Proyectos
{
	private String nombreProyecto;
    private String nombreUsuario;

    public Proyectos(String nombreProyecto, String nombreUsuario) 
    {
        this.nombreProyecto = nombreProyecto;
        this.nombreUsuario = nombreUsuario;
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
