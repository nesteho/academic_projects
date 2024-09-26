package model;

import model.dpoo.Observable;
import model.dpoo.Observer;

import java.util.HashSet;
import java.util.Set;

public class SortingThread extends Thread implements Observable  {
    Set<Observer> observers;
    DataProvider dataProvider; // pr appeler next() et creer tri concret en donnant un tab
    SortType sortType;

    public SortingThread(DataProvider dataProvider, SortType sortType) {
        this.dataProvider = dataProvider;
        this.sortType = sortType;
        observers = new HashSet<>();
    }

    @Override
    public void run() { // IMPORTANT :  run et pas start
        // si cette meth s'appelait start écrase le start du parent (classe Thread) qui crée thread et appelle run() sur ce nv thread
        /*  ds Sorting  var thread = new SortingThread(dataProvider, sortType);
            thread.start();  -> au lieu d'appeler start de Thread -> appelle start dans cette classe : les fils d'ecution indep st jamais crée
         */
        var tab = dataProvider.getNext();
        while(tab != null) { // boucle sur thread (ds Sorting) : dès que thread fini un sort si encore un next le trie

            Sort mySort = null;
            switch (sortType) {
                case TRI_FUSION:
                    mySort = new MergeSort(sortType, tab.length);
                    break;
                case TRI_BULLES:
                    mySort = new BubbleSort(sortType, tab.length);
                    break;
                case TRI_INSERTION:
                    mySort = new InsertionSort(sortType, tab.length);
                    break;
                case TRI_SELECTION:
                    mySort = new SelectionSort(sortType, tab.length);
                    break;
                case TRI_RAPIDE:
                    mySort = new QuickSort(sortType, tab.length);
                    break;
            }

            // while: chq X cliquer sur bouton : 10 affichage et notif
            mySort.sort(tab);
            notifyObservers(mySort);
            tab = dataProvider.getNext();
        }
   }
    /**
     * Registers a new observer
     * @param obs The observer to register
     */
    @Override
    public void registerObserver(Observer obs) {observers.add(obs);}
    /**
     * Removes a new observer
     * @param obs The observer to remove
     */
    @Override
    public void removeObserver(Observer obs) { observers.remove(obs); }

    /**
     * Notifies all the observers about a change
     * @param args TODO
     */
    @Override
    public void notifyObservers(Object... args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Le nombre de paramètres attendus est incorrect");
        }
        var sort = (AbstractSort) args[0]; // comme c'est classe abstraite, sait que le vrai type / type concret est un enfant de
        observers.forEach(o -> o.update(sort)) ;
    }
}
