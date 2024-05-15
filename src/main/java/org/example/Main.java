package org.example;

import java.util.Scanner;

class UkazatelNarustu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Instance Scanner pro načítání vstupu

        int aktualniHodnota = 0;
        int cilovaHodnota = 100; // Počet znaků, které chceme vypsat
        int rychlost = 1; // Změna za jednu sekundu (celé číslo)

        System.out.println();
        System.out.println("*******************************************************************************************************");
        System.out.println("*                                          UKAZATEL ČASU                                              *");
        System.out.println("*                                      Autor: František Belay                                         *");
        System.out.println("*                                     Kontakt: frabel@volny.cz                                        *");
        System.out.println("*******************************************************************************************************");
        System.out.println();
        System.out.println(" |<--------|---------|---------|---------|---- Time-line ----|---------|---------|---------|-------->|  ");
        System.out.println(" 0         10        20        30        40        50        60        70        80        90       100 "); // Stupnice
        System.out.println(" |╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|╷╷╷╷╷╷╷╷╷|  "); // Dílky stupnice

        double casovyOdpocet = 0;

        do {    /* Zadání rychlosti čerpání vody čerpadlem od uživatele s opětovnou kontrolou platnosti vstupu. */
            System.out.print("Zadej časový interval v SEKUNDÁCH (0-100000 sekund): ");
            int input = scanner.nextInt(); // Načtení celého čísla od uživatele
            casovyOdpocet = input;

        } while (casovyOdpocet < 0 || casovyOdpocet > 100000);

        double casovyOdpocetMiliSekund = casovyOdpocet * 1000;
        long delkaPauzySec = 0; // Deklarujeme proměnnou mimo cyklus while
        try {
            // Smyčka pro vykreslování ukazatele
            while (aktualniHodnota < cilovaHodnota) {

                System.out.print("["); // Ohraničuje počátek ukazatele

                for (int i = 0; i <= 100; i++) {
                    if (i < (aktualniHodnota % 100)) {
                        System.out.print("|"); // Znaky, které se budou periodicky vypisovat
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.print("] " + aktualniHodnota + "%"); // Ohraničuje konec ukazatele

//            long delkaPauzySec = 0;
                long pred = System.currentTimeMillis(); // Čas před pauzou
                try {
                    Thread.sleep((long) casovyOdpocetMiliSekund / 100); // Pauza mezi znaky: 1 sekunda = 1000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long po = System.currentTimeMillis(); // Čas po pauze

                long rozdil = po - pred; // Rozdíl času před a po pauze
                delkaPauzySec = rozdil / 10; // Převod z milisekund na sekundy * 100 dílků

                aktualniHodnota += rychlost;
                System.out.print("\r"); // Vrátíme kurzor na začátek řádku
            }

        } finally {
            // V tomto bloku finally vypíšeme hodnotu času mimo cyklus while

            System.out.println("[|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||] ");
            System.out.println("_______________________________________ Hotovo! Čas " + delkaPauzySec + " sekund se naplnil._________________________________________");
        }

    }
}