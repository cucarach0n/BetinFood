package com.example.BetinFood;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.example.BetinFood.Cliente.Cliente;

import jakarta.servlet.http.HttpSession;

public class Util {
    public static void setClienteSession(HttpSession session,Cliente cli){
        Cliente cliente = new Cliente();
        cliente.setNombres(cli.getNombres());
        cliente.setApellidos(cli.getApellidos());
        cliente.setCorreo(cli.getCorreo());
        session.setAttribute("cliente", cliente);
    }
    public static Cliente getClienteSession(HttpSession session){
        Cliente cliente = (Cliente)session.getAttribute("cliente");
        if(cliente==null){
            cliente = new Cliente();
            cliente.setNombres("Invitado");
            cliente.setApellidos("Invitado");
            cliente.setCorreo("Invitado");

        }

        return cliente;
    }
    //metodo para validar la cantidad de intentos de inicio de sesion con el fin de bloquear la cuenta hasta que se cumpla el tiempo de baneo
    public static void setIntentosSession(HttpSession session,int intentos){
        Date fecha = new Date();
        session.setAttribute("intentos", intentos);
        if(intentos==3){
            session.setAttribute("fechaBloqueo", fecha);
        }
    }   
    public static int getIntentosSession(HttpSession session){
        if(session.getAttribute("intentos")==null){
            session.setAttribute("intentos", 0);
        }
        int intentos = (int)session.getAttribute("intentos");
        if(intentos==4){
            Date fecha = (Date)session.getAttribute("fechaBloqueo");
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime()-fecha.getTime();
            long segundos = diferencia/1000;
            System.out.println("segundos: "+segundos);
            if(segundos>=60){
                intentos=0;
                session.setAttribute("intentos", intentos);
            }
        }
        return intentos;
    }


    //validar una cadena de texto que sea un correo electronico
    public static boolean validarCorreo(String correo){
        // Patrón de expresión regular para validar direcciones de correo electrónico
        String regex = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}";
        
        // Compila el patrón
        Pattern pattern = Pattern.compile(regex);
        
        // Crea un objeto Matcher
        Matcher matcher = pattern.matcher(correo);
        
       
        // Realiza la validación
        return matcher.matches();
    }
    //validar una cadena de texto que sea una contraseña con expresiones regulares que contenga al menos una letra mayuscula, una minuscula, un numero y un caracter especial
    public static boolean validarContra(String contra){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=])(?=\\S+$).{8,}$";
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(contra);

        return matcher.matches();
    }
    //validar validaciones
    public static boolean validar(String correo,String contra){
        if(validarCorreo(correo) == true && validarContra(contra)==true){
            return true;
        }
        return false;
    }
    
}
