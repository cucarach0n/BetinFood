package com.example.BetinFood.Persona;
import java.util.List;
import java.util.Optional;
public interface IPersonaService {
    public void Guardar(Persona p);
    public Persona buscarPersonaId(int idusuario);
}
