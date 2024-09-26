package model;

import model.dpoo.Observable;
import model.dpoo.Observer;

import java.util.HashSet;
import java.util.Set;

public class Sorting implements Observable,Observer {
    Set<Observer> observers;

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
     */
    @Override
    public void notifyObservers(Object... args) {
        // intermédiaire :  permet passage notify update O-Observer entre SThread et Sorting et celui entre Sorting et Vue
        if(args.length != 1 ){
            // throw error
        }
        //PB : caster en abstractSort permet d'avoir accès attribut car protected... or on  veut pas y avoir accès direct dans Sorting
       // -> getter des attr jamais utilisé
        // on veut donner l'objet en param du update... mm pour la vue
      // la vue fera appel au getters du Sort ok de passer un objet d'une classe model a la vue

         observers.forEach(o -> o.update(args[0])) ;
    }

    public Sorting() {
        observers = new HashSet<>();
    }
    public void start(SortType sortType, int nbThreads,int size){
        DataProvider dataProvider = new DataProvider(size); //  generer les tab

        for (int i = 1; i <= nbThreads ; i++) {
            // creer et lancer threads
            var thread = new SortingThread(dataProvider, sortType);
            thread.registerObserver(this);
            thread.start();
        }
    }

    /**
     * TODO: expliquer ce qu'on attend comme arguments
     * @param args
     */
    @Override
    public void update(Object... args)
    {
        notifyObservers(args[0]);
    }

//classe abstraite dt herite les deux bubble et merge. avec les info : getter . c la classe abstraite qui va implémenter les getter
    //génerer les tab et les trier
    //faut génerer les tableau à l'avance avant de commencer à  trier le 1er
    // o-observer : observable facade et observer controller (pas vue fxml controller)
} // facade tri thread : quand un thread a fini de trier . elle prévient la facade (elle l'a crée)
// double o-o 1 dans le modele et 1 modele-vue
