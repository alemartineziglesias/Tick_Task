package tick_task;

public class Tareas
{
	private int idTarea;
	private String nombreTarea;
	private String descripcionTarea;
	private String fechaTarea;
	private int estadoTarea;
	
	public Tareas(int idTarea, String nombreTarea, String descripcionTarea, String fechaTarea, int estadoTarea)
	{
		this.idTarea = idTarea;
		this.nombreTarea = nombreTarea;
		this.descripcionTarea = descripcionTarea;
		this.fechaTarea = fechaTarea;
		this.estadoTarea = estadoTarea;
	}

	public int getIdTarea()
	{
		return idTarea;
	}

	public String getNombreTarea()
	{
		return nombreTarea;
	}

	public String getDescripcionTarea()
	{
		return descripcionTarea;
	}

	public String getFechaTarea()
	{
		return fechaTarea;
	}

	public int getEstadoTarea()
	{
		return estadoTarea;
	}
}
