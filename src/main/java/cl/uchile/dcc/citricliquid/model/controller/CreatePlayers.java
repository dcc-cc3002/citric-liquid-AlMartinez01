package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Scanner;

public class CreatePlayers {
    UnitsPlayer[] unitsPlayers;

    //18//
    /**
    Caracteristicas Tanke
     */
    int[] tanke = {10,3,7,0};
    /**
     Caracteristicas Fuente de daño
     */
    int[] damageType = {5,8,3,2};
    /**
     Caracteristicas Ninja
     */
    int[] ninja = {5,5,3,5};
    /**
      Caracteristicas Ultra EVD
     */
    int[] mNinja = {5,5,1,7};

    int[][] caracteristicas = new int[][]{tanke,damageType,ninja,mNinja};

    public CreatePlayers() {
        Scanner entrada = new Scanner(System.in);
        System.out.printf("Inserte cantidad de jugadores(1-4): ");
        int i = entrada.nextInt();
        System.out.printf("\n");

        if (i<2){ //Se crea un solo jugador
            System.out.printf("se creara un solo jugador, incerte nombre: ");
            String text = entrada.next();
            System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
            i = entrada.nextInt();
            while (i>3 || i<0){
                System.out.println("error, reintentar \n");
                System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                i = entrada.nextInt();
            }
            unitsPlayers = new UnitsPlayer[] {new UnitsPlayer(text,caracteristicas[i][0],caracteristicas[i][1],caracteristicas[i][2],caracteristicas[i][3],
                    null,null,0,0,1)};
        }
        else if(i<5){//Se crea jugadores de 2 a 4
            System.out.println("se crearan "+i+" jugadores\n");
            unitsPlayers = new UnitsPlayer[i];

            int j = 0;
            while (j < i){
                System.out.printf("se creara un solo jugador, incerte nombre: ");
                String text = entrada.next();
                System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                i = entrada.nextInt();
                while (i > 3 || i < 0) {
                    System.out.println("error, reintentar \n");
                    System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                    i = entrada.nextInt();
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
                System.out.printf("se creara un solo jugador, incerte nombre: ");
                String text = entrada.next();
                System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                i = entrada.nextInt();
                while (i > 3 || i < 0) {
                    System.out.println("error, reintentar \n");
                    System.out.printf("\n quieres ser tanke(0), damageType(1), ninja(2) o mNinja(3): ");
                    i = entrada.nextInt();
                }
                unitsPlayers[j] = new UnitsPlayer(text, caracteristicas[i][0], caracteristicas[i][1], caracteristicas[i][2], caracteristicas[i][3],
                        null, null, 0, 0, 1);
                j++;
            }
        }
    }

    public UnitsPlayer[] jugadores(){
        return unitsPlayers;
    }
}
