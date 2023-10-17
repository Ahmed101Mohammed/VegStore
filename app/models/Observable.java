package app.models;

import java.util.ArrayList;

import app.IObserver;

public abstract class Observable {
    private ArrayList<IObserver> views =  new ArrayList<>();

    public void register(IObserver iObserver)
    {
        views.add(iObserver);
    }

    public void unRegister(IObserver iObserver)
    {
        views.remove(iObserver);
    }

    public void notifing()
    {
        for (IObserver iObserver:views)
        {
            iObserver.update();
        }
    }
}
