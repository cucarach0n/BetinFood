package com.example.BetinFood.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaService implements IPersonaService{
    @Autowired
    private IPersona data;
    
    @Override
    public void Guardar(Persona p) {
        // TODO Auto-generated method stub
        data.save(p);
    }

    @Override
    public Persona buscarPersonaId(int idusuario) {
        // TODO Auto-generated method stub
        return data.buscarPersonaId(idusuario);
    }
    
}
