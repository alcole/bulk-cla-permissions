package com.alcole.jclapermissions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ResultList implements Iterable {

    private List<PermissionResult> results;

    ResultList() {
        results = new ArrayList<>();
    }

    boolean add(PermissionResult Result) {
        return this.add(Result);
    }

    @Override
    public Iterator<PermissionResult> iterator() {
        return results.iterator();
    }

    //return ordered?
    //print summary
}
