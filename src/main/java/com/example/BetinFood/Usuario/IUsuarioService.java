
package com.example.BetinFood.Usuario;

import com.example.BetinFood.Cliente.Cliente;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public void Guardar(Usuario u);
    public Usuario BuscarUsuario(String email);

}
