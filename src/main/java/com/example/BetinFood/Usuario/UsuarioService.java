
package com.example.BetinFood.Usuario;

import com.example.BetinFood.Cliente.Cliente;
import com.example.BetinFood.Cliente.ICliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuario data;

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public Usuario BuscarUsuario(String email) {
        // TODO Auto-generated method stub
        return data.buscarUsuarioCorreo(email);
    }


}
