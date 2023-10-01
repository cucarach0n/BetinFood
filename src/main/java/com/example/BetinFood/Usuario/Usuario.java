package com.example.BetinFood.Usuario;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_Usuario;
    private String contra_usuario;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
    private String email;
    private String estado;
}
