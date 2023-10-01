/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BetinFood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.BetinFood.Util;
import com.example.BetinFood.Cliente.Cliente;
import com.example.BetinFood.Cliente.ClienteService;
import com.example.BetinFood.Persona.Persona;
import com.example.BetinFood.Persona.PersonaService;
import com.example.BetinFood.Usuario.Usuario;
import com.example.BetinFood.Usuario.UsuarioService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class Controlador {
    
    private static final int MAX_INTENTOS = 3;
    private int intentos = 0;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaService personaService;
    @GetMapping("/")
    public String inicio(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        
        return "principal";
    }
    
    @GetMapping("/principalAdmin")
    public String inicioAdmid(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        System.out.println(cli.getNombres());
        if(cli != null){
            //model.addAttribute("admin", null);
            if(cli.getNombres().equals("Administrador"))
            {   
                model.addAttribute("cliente", cli);
                return "principalAdmin";
            }
            else{
                return "login";
            }
        }
        return "login";

    }

    @GetMapping("/envio")
    public String direccionEnvio(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "envioMap";
    }

    @GetMapping("/broaster")
    public String inicio2(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "broaster";
    }
    
    @GetMapping("/promos")
    public String inicio3(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "promos";
    }
    
    @GetMapping("/hamburguesas")
    public String inicio4(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "hamburguesas";
    }
    
    @GetMapping("/bebidas")
    public String inicio5(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "bebidas";
    }

    @GetMapping("/login")
    public String inicio6(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli.getNombres().equals("Invitado"))
        {   
            model.addAttribute("cliente", null);
        }
        return "login";
    }

    @GetMapping("/ayuda")
    public String inicio10(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {
                model.addAttribute("cliente", null);
            }
        }
        return "ayuda";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        Cliente cliente = new Cliente();
        cliente.setNombres("Invitado");
        cliente.setApellidos("Invitado");
        cliente.setCorreo("Invitado");
        Util.setClienteSession(session, cliente);
        model.addAttribute("cliente", null);
        return "principal";
    }

    @PostMapping("/logear")
    public String logear(@RequestParam("correo") String username, @RequestParam("password") String password,HttpSession session, Model model){
        System.out.println("username: "+username);
        System.out.println("password: "+password);
        
        int intentos2 = Util.getIntentosSession(session);
        System.out.println(intentos2);
        if (intentos2 <= MAX_INTENTOS) {
            Util.setIntentosSession(session, intentos2+1);
        }
        else{
            model.addAttribute("maxAttemptsReached", true);
            //intentos = 0;
            return "login";
        }
        
        

        Usuario usuario = new Usuario();

        usuario = usuarioService.BuscarUsuario(username);
        
        if(usuario != null){
            System.out.println(passwordEncoder.matches(password,usuario.getContra_usuario()));
            if(passwordEncoder.matches(password, usuario.getContra_usuario())){
                Persona persona = new Persona();
                persona = personaService.buscarPersonaId(usuario.getId_Usuario());
                Cliente cliente = new Cliente();
                cliente.setNombres(persona.getNombres());
                cliente.setApellidos(persona.getApellidos());
                cliente.setCorreo(usuario.getEmail());
                model.addAttribute("cliente", cliente);
                Util.setClienteSession(session, cliente);
                //intentos = 0;
                Util.setIntentosSession(session, 0);
                return "principal";
            }
        }

        // Verificar si se ha alcanzado el máximo de intentos
        /*if (intentos >= MAX_INTENTOS) {
            model.addAttribute("maxAttemptsReached", true);
            intentos = 0;
            return "login";
        }*/
        

        if (username.equals("admin@betinfood.com") && password.equals("admin")) {
            Cliente cliente = new Cliente();
            cliente.setNombres("Administrador");
            cliente.setApellidos("Administrador");
            cliente.setCorreo("admin@betinfood.com");
            model.addAttribute("cliente", cliente);
            Util.setClienteSession(session, cliente);
            //intentos = 0;
            Util.setIntentosSession(session, 0);
            return "principalAdmin";
        } 
        if (username.equals("muriel@hotmail.com") && password.equals("ateg")) {
            Cliente cliente = new Cliente();
            cliente.setNombres("muriel");
            cliente.setApellidos("geta");
            cliente.setCorreo("muriel@hotmail.com");
            model.addAttribute("cliente", cliente);
            Util.setClienteSession(session, cliente);
            //intentos = 0;
            Util.setIntentosSession(session, 0);
            return "principal";
        } else {
            // Incrementar el contador de intentos
            //intentos++;
            model.addAttribute("loginFailed", true);  // Indicar que el inicio de sesión falló
            return "login";
        }
        
        
        
    }  
    @GetMapping("/Registrar")
    public String inicio7(HttpSession session, Model model){
        model.addAttribute("validacionError", false);
        Cliente cli = Util.getClienteSession(session);
        if(cli.getNombres().equals("Invitado"))
        {   
            model.addAttribute("cliente", null);
        }
        return "Registrar";
    }
    @PostMapping("/Registrar")
    public String registrarPost(HttpSession session, Model model,
                                @RequestParam("nombres") String nombres, @RequestParam("apellidos") String apellidos,
                                @RequestParam("email") String email,@RequestParam("contrasenia") String contrasena){
        /*if (Util.validar(email, contrasena) == false){ 
            model.addAttribute("validacionError", true);
            return "Registrar";
        }*/ 
        model.addAttribute("validacionError", false);
        List<String> errores = new ArrayList<>();
        if(Util.validarContra(contrasena) == false){
            errores.add("La contraseña debe contener minimo 8 caracteres, una letra mayuscula, una letra minuscula, un numero y un caracter especial");
        }
        if(Util.validarCorreo(email) == false){
            errores.add("El correo no es valido");
        }
        if(errores.size() > 0){
            model.addAttribute("validacionError", true);
            model.addAttribute("errores", errores);
            return "Registrar";
        }
        //System.out.println(passwordEncoder.encode(contrasena));
        Date fechaActual = new Date(System.currentTimeMillis());
        //System.out.println(passwordEncoder.matches(contrasena,"$2a$10$0WJ5GB7xxJv82SmIgGVet.cZN.PzTA9px3O6sQ9.4baWwTEhi/Uua"));
        Usuario user = new Usuario();
        Persona per = new Persona();
        user.setContra_usuario(passwordEncoder.encode(contrasena));
        user.setFecha_actualizacion(fechaActual);
        user.setFecha_creacion(fechaActual);
        user.setEmail(email);
        user.setEstado("A");
        usuarioService.Guardar(user); 
        
        per.setNombres(nombres);
        per.setApellidos(apellidos);
        per.setFechaActualizacion(fechaActual);
        per.setFechaCreacion(fechaActual);
        per.setUsuario(user);
        personaService.Guardar(per);                            
        return "Login";
    }
    @GetMapping("/Acercade")
    public String inicio8(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "Acercade";
    }
    @GetMapping("/carrito")
    public String carrito(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "carrito";
    }
    @GetMapping("/pagoTarjeta")
    public String pagoTarjeta(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
            }
        }
        return "pagoTarjeta";
    }

    @GetMapping("/platoAdmin")
    public String platoAdmin(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "platosAdmin";
        }
        else{
            return "login";
        }
    }
    @GetMapping("/personal")
    public String personal(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "personalRegistro";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/pedidoOrdenes")
    public String pedidoOrdenes(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "pedidosOrdenes";
        }
        else{
            return "login";
        }
    }
    @GetMapping("/reportes")
    public String reportes(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "reportes";
        }
        else{
            return "login";
        }
    }
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
                return "login";
            }
            return "modificar";
        }
        else{
            return "login";
        }
        
    }
    @GetMapping("/promoAdmin")
    public String promoAdmin(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "promoAdmin";
        }
        else{
            return "login";
        }
    }
    @GetMapping("/pedidoCliente")
    public String pedidoCliente(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
                return "login";
            }
            return "pedidoCliente";
        }
        else{
            return "login";
        }
    }
       @GetMapping("/proveedoresAdmin")
    public String proveedoresAdmin(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        
        if(cli.getNombres().equals("Administrador"))
        {   
            model.addAttribute("cliente", cli);
            return "proveedoresAdmin";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/detallePedido")
    public String detallePedido(HttpSession session, Model model){
        Cliente cli = Util.getClienteSession(session);
        if(cli != null){
            model.addAttribute("cliente", cli);
            if(cli.getNombres().equals("Invitado"))
            {   
                model.addAttribute("cliente", null);
                return "login";
            }
            return "detallePedido";
        }
        else{
            return "login";
        }
    }
}
