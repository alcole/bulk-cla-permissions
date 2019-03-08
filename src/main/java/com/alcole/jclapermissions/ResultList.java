package com.alcole.jclapermissions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ResultList implements Iterable<PermissionResult> {

    private List<PermissionResult> results;

    ResultList() {
        results = new ArrayList<>();
    }

    public boolean add(PermissionResult Result) {
        return results.add(Result);
    }

    @Override
    public Iterator<PermissionResult> iterator() {
        return results.iterator();
    }

//    public Iterator<PermissionResult> getResults() {
//        return set.iterator();
//    }
//    public Iterator<Penguin> iterator() {
//        return getPenguins();
//    }

    //return ordered?
    //print summary
}
