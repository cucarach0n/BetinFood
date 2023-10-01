package com.example.BetinFood.Persona;
import java.sql.Date;


import com.example.BetinFood.Usuario.Usuario;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_persona;
    private String nombres;
    private String apellidos;
    private String direccion;
    private Double salario;
    private String documentoIdentidad;
    private String genero;
    private String foto;
    private Integer edad;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    @ManyToOne()
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
}
