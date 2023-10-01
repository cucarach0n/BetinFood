package com.example.BetinFood.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPersona extends CrudRepository<Persona,Integer>{
    @Query(value = "SELECT * FROM persona p "
            + "WHERE p.id_usuario = ?1 limit 1", nativeQuery = true)

    public Persona buscarPersonaId(int idusuario);
}
