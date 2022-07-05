package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Scanner;

public class CreatePlayers {
    UnitsPlayer[] unitsPlayers;

    //18//

    public CreatePlayers(int trat) {
        if (trat == 0){
            unitsPlayers = new UnitsPlayer[] {};
            return;
        }
        Scanner entrada = new Scanner(System.in);
        System.out.print("Inserte cantidad de jugadores(1-4): ");
        int i = entrada.nextInt();
        System.out.print("\n");

        /**
         Caracteristicas Tanke
         */
        int[] tanke = {10, 3, 7, 0};
        /**
         Caracteristicas Fuente de daño
         */
        int[] damageType = {5, 8, 3, 2};
        /**
         Caracteristicas Ninja
         */
        int[] ninja = {5, 5, 3, 5};
        /**
         Caracteristicas Ultra EVD
         */
        int[] mNinja = {5, 5, 1, 7};
        int[][] caracteristicas = new int[][]{tanke, damageType, ninja, mNinja};
        if (i<2){ //Se crea un solo jugador
            System.out.print("se creara un solo jugador, incerte nombre: ");
            String text = entrada.next();
            System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
            i = entrada.nextInt();
            while (i>3 || i<0){
                System.out.println("error, reintentar \n");
                System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                i = entrada.nextInt();
            }
            unitsPlayers = new UnitsPlayer[] {new UnitsPlayer(text, caracteristicas[i][0], caracteristicas[i][1], caracteristicas[i][2], caracteristicas[i][3],
                    null,null,0,0,1)};
        }
        else if(i<5){//Se crea jugadores de 2 a 4
            System.out.println("se crearan "+i+" jugadores\n");
            unitsPlayers = new UnitsPlayer[i];

            int j = 0;
            while (j < i){
                System.out.print("se creara un jugador, incerte nombre: ");
                String text = entrada.next();
                System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                int t = entrada.nextInt();
                while (t > 3 || t < 0) {
                    System.out.println("error, reintentar \n");
                    System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                    t = entrada.nextInt();
                }
                unitsPlayers[j] = new UnitsPlayer(text, caracteristicas[i][0], caracteristicas[i][1], caracteristicas[i][2], caracteristicas[i][3],
                        null, null, 0, 0, 1);
                j++;
            }
        }
        else { //condicion de borde se crearan el maximo de jugadores
            System.out.println("se crearan " + 4 + " jugadores\n");
            unitsPlayers = new UnitsPlayer[4];

            int j = 0;
            while (j < 4) {
                System.out.print("se creara un solo jugador, incerte nombre: ");
                String text = entrada.next();
                System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                i = entrada.nextInt();
                while (i > 3 || i < 0) {
                    System.out.println("error, reintentar \n");
                    System.out.print("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                    i = entrada.nextInt();
                }
                unitsPlayers[j] = new UnitsPlayer(text, caracteristicas[i][0], caracteristicas[i][1], caracteristicas[i][2], caracteristicas[i][3],
                        null, null, 0, 0, 1);
                j++;
            }
        }
    }

    public int ubicMax(int[] valores){
        int memori = 0;
        int contador = 0;
        for(int i: valores){
            if (i >= valores[memori]){
                memori = contador;
            }
            contador++;
        }
        valores[memori] = 0;
        return memori;
    }
    public UnitsPlayer[] desordenar() {
        int cantidad = 0;
        for (UnitsPlayer ignored : unitsPlayers) {
            cantidad++;
        }
        int[] rolls = new int[cantidad];
        int i = 0;
        while (i < cantidad) {
            rolls[i] = unitsPlayers[i].roll();
            System.out.println("el jugador " + unitsPlayers[i].getId() +" obtuvo en el roll: " +rolls[i] );
            i++;
        }
        UnitsPlayer[] newUnitsPlayers = new UnitsPlayer[cantidad];

        i = 0;
        while (i < cantidad){
            newUnitsPlayers[i] = unitsPlayers[ubicMax(rolls)];
            i++;
        }
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(2*1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\n\n\nEl orden de la partida sera:\n");
        for (UnitsPlayer unidades:newUnitsPlayers){
            System.out.println(unidades.getId());
        }
        return newUnitsPlayers;
    }
}
