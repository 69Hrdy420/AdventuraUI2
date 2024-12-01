/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.logika;


import cz.vse.Observers.EndObserver;

import java.util.List;

/**
 * Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2014/2015
 */
public interface IHra {
    //== VEŘEJNÉ KONSTANTY =====================================================
    //== DEKLAROVANÉ METODY ====================================================

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    String vratUvitani();

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    String vratEpilog();

    /**
     * Vrací true, pokud hra skončila.
     */
    boolean konecHry();

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    String zpracujPrikaz(String radek);


    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    HerniPlan getHerniPlan();

    List<String> getPrikazy();

    void setEndObserver(EndObserver observer);

    //== ZDĚDĚNÉ METODY ========================================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
}
