
package com.example.BetinFood.Usuario;

import com.example.BetinFood.Cliente.Cliente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario,Integer>{
    @Query(value = "SELECT * FROM usuario u "
            + "WHERE u.email = ?1 limit 1", nativeQuery = true)

    public Usuario buscarUsuarioCorreo(String correo);
}
