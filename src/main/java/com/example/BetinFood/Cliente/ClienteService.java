
package com.example.BetinFood.Cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private ICliente data;
    
    

    @Override
    public void Guardar(Cliente c) {
        data.save(c);
    }

}
