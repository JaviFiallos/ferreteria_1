package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Validacion {

    public static final int num_provincias = 24;

    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {

                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        return cedulaCorrecta;
    }

    public boolean RucPersonaNatural(String ruc) {

        if (ruc.length() != 13) {
            return false;
        }

        if (!ruc.substring(10, 13).equals("001") || !validadorDeCedula(ruc.substring(0, 10))) {
            return false;
        }
        return true;
    }

    public static boolean esEntero(String cadena) {
        String expresionRegular = "^[1-9]\\d*$"; // Solo números enteros positivos
        Pattern patron = Pattern.compile(expresionRegular);
        return patron.matcher(cadena).matches();
    }

    public static boolean esDouble(String cadena) {
        String expresionRegular = "^\\d+(\\.\\d+)?$"; // Solo números enteros positivos
        Pattern patron = Pattern.compile(expresionRegular);
        return patron.matcher(cadena).matches();
    }

    public String eliminarEspacios(String cadena) {
        // Eliminar espacios duplicados y espacios al inicio y final
        String cadenaLimpia = cadena.trim().replaceAll("\\s+", " ");
        return cadenaLimpia;
    }

    public boolean validarCadena(int n, String cadena) {
        // Validar que la cadena esté compuesta solo de letras mayúsculas o minúsculas
        String cadenaLimpia = eliminarEspacios(cadena);
        if (!cadenaLimpia.matches("[a-zA-Z\\s]*")) {
            return false;
        }
        String[] palabras = cadenaLimpia.split(" ");
        return palabras.length == n;
    }

    public boolean verificarTelefono(String cadena) {
        String palabra = eliminarEspacios(cadena);
        if (palabra.contains(" ")) {// revisar

            return false;
        }
        try {
            return Integer.parseInt(palabra) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean alMenosDosPalabras(int n, String cadena) {
        String cadenaLimpia = eliminarEspacios(cadena);
        if (!cadenaLimpia.matches("[a-zA-Z\\s]*")) {
            return false;
        }
        // Contar el número de palabras
        String[] palabras = cadenaLimpia.split(" ");
        return palabras.length >= n;
    }

    public boolean validarDireccion(String texto) {
        // Expresión regular para verificar si el texto contiene solo letras mayúsculas, minúsculas o espacios
        return texto.matches("[a-zA-Z ]+");
    }

    public boolean validarNumeroCelular(String numero) {
        // Expresión regular para validar números de teléfono celular (ejemplo: 0123456789)
        return numero.matches("0\\d{9}");
    }

    public boolean esFormatoDeFechaValido(String entrada) {
        SimpleDateFormat formatoFecha1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoFecha2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            formatoFecha1.parse(entrada);
            return true;
        } catch (ParseException e1) {
            try {
                formatoFecha2.parse(entrada);
                return true;
            } catch (ParseException e2) {
                return false;
            }
        }
    }

    public boolean esFechaFinalDespuesDeFechaInicio(String fechaInicio, String fechaFin) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date inicio = formatoFecha.parse(fechaInicio);
            Date fin = formatoFecha.parse(fechaFin);

            return fin.after(inicio);
        } catch (ParseException e) {
            return false;
        }
    }
}
