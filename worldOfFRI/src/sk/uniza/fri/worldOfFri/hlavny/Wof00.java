package sk.uniza.fri.worldOfFri.hlavny;

/*
Ulohy - questy
- objav novu miestnost (zatajena - nie je v zozname vychodov)
- prejdi celu mapu
- zabi nepriatela
Npc - non-playable character
- dekan
- bufetarka
- vratnicka
- skolnik
- upratovacka - prisera
- velke serverove monstrum
- akcie:
  - dava questy
  - obchod
    > nakupuj bufetarka
  - rozhovor
    > oslov dekan
  - suboj
    > zautoc vlk
Predmety
- mec
- bageta, dodava staminu
- oravska mineralka, zvysuje agilitu
- peniaze
- isic - otvaranie miestnosti
- notebook - dekodovanie zaheslovanych vstupov
- akcie:
  - pouzitie
    > pouzi isic
  - uschovanie
    > zober isic
  - zahodenie
    > zahod isic
Inventar
Spelly
- dekodovanie pomocou notebooku
- alt enter
- meditacia - prida staminu za nejaky cas
Atributy
- stamina - ked bude dekodovat zobere 3 z 10
- agilita - rychlost pohybu
- inteligencia
- hp
- charizma, vystupenie zo suboja
*/

/**
 * Hlavna trieda hry WoF s metodou main - spustanie v NB
 * 
 * @author Lubomir Sadlon
 * @version 21.2.2012
 */
public class Wof00 {

    /**
     * @param args parametre programu
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.hraj();
    }
}
