package ferreteria;

import controlador.configVentanaLog;

public class Ferreteria {

    public static void main(String[] args) {

        try {
            configVentanaLog v = new configVentanaLog();
            v.iniciar();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
