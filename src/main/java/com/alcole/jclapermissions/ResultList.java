package com.alcole.jclapermissions;

import com.alcole.jclapermissions.Model.PermissionResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultList implements Iterable<PermissionResult> {

  private List<PermissionResult> results;

  public ResultList() {
    results = new ArrayList<>();
  }

  public boolean add(PermissionResult Result) {
    return results.add(Result);
  }

  @Override
  public Iterator<PermissionResult> iterator() {
    return results.iterator();
  }
}
