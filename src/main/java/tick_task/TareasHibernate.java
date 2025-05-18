package tick_task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class TareasHibernate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarea;

    private String nombreTarea;

    private String descripcionTarea;

    private String fechaVencimientoTarea;

    private int estadoTarea;

    private int idProyectoFK;

    public TareasHibernate() {}

    public TareasHibernate(String nombre, String descripcion, String fecha, int estado, int proyecto) 
    {
        this.nombreTarea = nombre;
        this.descripcionTarea = descripcion;
        this.fechaVencimientoTarea = fecha;
        this.estadoTarea = estado;
        this.idProyectoFK = proyecto;
    }

	public int getIdTarea()
	{
		return idTarea;
	}

	public void setIdTarea(int idTarea)
	{
		this.idTarea = idTarea;
	}

	public String getNombreTarea()
	{
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea)
	{
		this.nombreTarea = nombreTarea;
	}

	public String getDescripcionTarea()
	{
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea)
	{
		this.descripcionTarea = descripcionTarea;
	}

	public String getFechaVencimientoTarea()
	{
		return fechaVencimientoTarea;
	}

	public void setFechaVencimientoTarea(String fechaVencimientoTarea)
	{
		this.fechaVencimientoTarea = fechaVencimientoTarea;
	}

	public int getEstadoTarea()
	{
		return estadoTarea;
	}

	public void setEstadoTarea(int estadoTarea)
	{
		this.estadoTarea = estadoTarea;
	}

	public int getProyecto()
	{
		return idProyectoFK;
	}

	public void setProyecto(int proyecto)
	{
		this.idProyectoFK = proyecto;
	}
}