package com.example.prypoliza1.controller;

public class PolizaController {
    public static String calcularPoliza(String nombre, double valorAutomovil, String modelo, int accidentes, String edad) {
        // Cargo por valor del automovil
        double cargoValor = 0.035 * valorAutomovil;

        double cargoAccidente = 17;
        // Cargo por accidene previos
        if (accidentes > 3) {
            cargoAccidente += 21 * (accidentes - 3);
        }

        double cargoModelo = valorAutomovil *
                ((modelo.equals("A") ? 0.011 :
                        (modelo.equals("B") ? 0.012 :
                                (modelo.equals("C") ? 0.015 : 0))));


        double cargoEdad = ((edad.equals("18-23")) ? 360 :
                ((edad.equals("24-55")) ? 240 :
                        ((edad.equals("Mayor de 35")) ? 430 : 0)));

        double costoTotal = cargoValor + cargoEdad + cargoAccidente + cargoModelo;

        return String.valueOf(costoTotal);

    }
}
