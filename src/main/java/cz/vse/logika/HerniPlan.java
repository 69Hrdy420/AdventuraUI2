package cz.vse.logika;


import cz.vse.Observers.ItemObserver;
import cz.vse.Observers.MapObserver;

import java.util.Arrays;
import java.util.List;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Alena Buchalcevova
 * @version z kurzu 4IT101 pro školní rok 2014/2015
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private final ItemObserver exitObserver;
    private final MapObserver mapObserver;
    private final ItemObserver itemObserver;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(ItemObserver exitObserver, MapObserver mapObserver, ItemObserver itemObserver) {
        zalozProstoryHry();
        this.exitObserver = exitObserver;
        this.mapObserver = mapObserver;
        this.itemObserver = itemObserver;
    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor domecek = new Prostor("domecek", "domeček, ve kterém bydlí Karkulka");
        Prostor chaloupka = new Prostor("chaloupka", "chaloupka, ve které bydlí babička Karkulky");
        Prostor jeskyne = new Prostor("jeskyne", "stará plesnivá jeskyně");
        Prostor les = new Prostor("les", "les s jahodami, malinami a pramenem vody");
        Prostor hlubokyLes = new Prostor("hluboky_les", "temný les, ve kterém lze potkat vlka");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        domecek.setVychod(les);
        les.setVychod(domecek);
        les.setVychod(hlubokyLes);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(jeskyne);
        hlubokyLes.setVychod(chaloupka);
        jeskyne.setVychod(hlubokyLes);
        chaloupka.setVychod(hlubokyLes);

        aktualniProstor = domecek;  // hra začíná v domečku  
        viteznyProstor = chaloupka;
        les.vlozVec(new Vec("maliny", true));
        les.vlozVec(new Vec("strom", false));
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        if (mapObserver != null) {
            mapObserver.updatePosition(prostor.getNazev());
        }
        if (exitObserver != null) {
            exitObserver.update(prostor.getSeznamVychodu());
        }
    }

    /**
     * Metoda vrací odkaz na vítězný prostor.
     *
     * @return vítězný prostor
     */

    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    public void notifyItemsObserver() {
        String veci = getAktualniProstor().nazvyVeci();
        List<String> seznamVeci = Arrays.asList(veci.split(" "));
        if (itemObserver != null) {
            itemObserver.update(seznamVeci);
        }
    }
}
