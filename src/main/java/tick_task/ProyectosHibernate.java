package tick_task;

import jakarta.persistence.*;

@Entity
@Table(name = "proyectos")
public class ProyectosHibernate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProyecto;
    
    private String nombreProyecto;

    @ManyToOne
    @JoinColumn(name = "idUsuarioFK", nullable = false)
    private Usuarios usuario;

    public int getIdProyecto() 
    {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) 
    {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() 
    {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) 
    {
        this.nombreProyecto = nombreProyecto;
    }

    public Usuarios getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) 
    {
        this.usuario = usuario;
    }
}