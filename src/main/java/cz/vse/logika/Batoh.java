package cz.vse.logika;


import cz.vse.Observers.ItemObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trida Batoh
 *
 * @author Alena Buchalcevova
 * @version z kurzu 4IT101 pro školní rok 2014/2015
 */

public class Batoh {
    public static final int KAPACITA = 3;
    private final Map<String, Vec> seznamVeci;   // seznam věcí v batohu
    private final ItemObserver observer;

    public Batoh(ItemObserver observer) {
        seznamVeci = new HashMap<String, Vec>();
        this.observer = observer;
    }

    /**
     * Vloží věc do batohu
     *
     * @param vec instance věci, která se má vložit
     */
    public void vlozVec(Vec vec) {
        seznamVeci.put(vec.getJmeno(), vec);
    }

    /**
     * Vrací řetězec názvů věcí, které jsou v batohu
     *
     * @return řetězec názvů
     */
    public String nazvyVeci() {
        String nazvy = "věci v batohu: ";
        List<String> list = new ArrayList<>();
        for (String jmenoVeci : seznamVeci.keySet()) {
            nazvy += jmenoVeci + " ";
            list.add(jmenoVeci);
        }
        if (observer != null) {
            observer.update(list);
        }
        return nazvy;
    }

    /**
     * Hledá věc daného jména a pokud je v batohu, tak ji vrátí a vymaže ze seznamu
     *
     * @param jmeno Jméno věci
     * @return věc nebo
     * hodnota null, pokud tam věc daného jména není
     */
    public Vec vyberVec(String jmeno) {
        Vec nalezenaVec = null;
        if (seznamVeci.containsKey(jmeno)) {
            nalezenaVec = seznamVeci.get(jmeno);
            seznamVeci.remove(jmeno);
        }
        return nalezenaVec;
    }

}



