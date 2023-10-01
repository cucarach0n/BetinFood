package com.example.BetinFood.Login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.BetinFood.Usuario.IUsuarioService;
import com.example.BetinFood.Usuario.Usuario;
import com.example.BetinFood.Usuario.UsuarioService;

import com.example.BetinFood.Util;


public class IUsuarioServiceTest {
    
    @Mock
    private IUsuarioService service;
    
    @InjectMocks
    private UsuarioService service_usuario;

    //@Mock
    //private PasswordEncoder passwordEncoder;
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Usuario usuario;
    
    public IUsuarioServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        usuario = new Usuario();
        
        usuario.setContra_usuario("null");
        java.util.Date fecha = new java.util.Date();
        java.sql.Date fech_cre = new java.sql.Date(fecha.getTime());
        usuario.setFecha_creacion(fech_cre);
        usuario.setFecha_actualizacion(fech_cre);
        usuario.setContra_usuario("4583013Ma");
        usuario.setEmail("devalo19@gmail.com");
        usuario.setEstado("A");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of Find method, of class IUsuarioService.
     */
    @Test
    public void testBuscar() {
        System.out.println("El usuario se guardo correctamente");
        when(service.BuscarUsuario(usuario.getEmail())).thenReturn(usuario);
        Usuario usuario_test = service.BuscarUsuario(usuario.getEmail());
        try{
            if(usuario_test == null){
                fail("El usuario no existe");
            }
        else{
                System.out.println("El usuario se guardo correctamente");
                usuario_test = service_usuario.BuscarUsuario(usuario.getEmail());
                if(usuario_test == null){
                
                }
                else{
                    System.out.println(usuario_test.getEmail());
                    if(usuario_test.getEmail().equals(usuario_test.getEmail())){
                        System.out.println("Logeado con exito");
                    }
                    else{
                        System.out.println("No se logeo con exito");
                    }
                }
            }
        }
        catch(Exception e){
            fail("El usuario no existe");
        }
    }
    @Test
    public void testValidarUsuario() {
        System.out.println("Validando usuario");
        when(service.BuscarUsuario(usuario.getEmail())).thenReturn(usuario);
        Usuario usuario_test = service.BuscarUsuario(usuario.getEmail());
        
        if(usuario_test == null){
            fail("El usuario no existe");
        }
        else{
            System.out.println("Existe el usuario");
            //usuario_test = service.BuscarUsuario(usuario.getEmail());
            if(usuario_test == null){
                fail("El usuario no se guardo");
            }
            else{
                System.out.println(usuario_test.getEmail());
                if(usuario_test.getEmail().equals(usuario_test.getEmail())){
                    System.out.println(usuario_test.getContra_usuario());
                    if(passwordEncoder.matches("4583012",passwordEncoder.encode(usuario_test.getContra_usuario()))){
                        System.out.println("Logeado con exito");
                    }
                    else{
                        fail("Login incorrecto");
                    }
                }
                else{
                    fail("El usuario no existe");
                }
            }
        }
    }

    /**
     * Test para validar los validadores del registro de usuario
     */
    @Test
    public void testValidarValidadores(){
        System.out.println("Validando validadores");
        if(Util.validar(usuario.getEmail(),usuario.getContra_usuario()) == true){
            System.out.println("Validado con exito");
        }
        else{
            fail("No se valido con exito");
        }
    }
}
