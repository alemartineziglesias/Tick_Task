package tick_task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombreUsuario;
    private String claveUsuario;

    // Getters y Setters
    public int getIdUsuario() 
    {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) 
    {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() 
    {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) 
    {
        this.claveUsuario = claveUsuario;
    }
}