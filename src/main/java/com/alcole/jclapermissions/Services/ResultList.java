package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultList implements Iterable<PermissionResult> {

  private final List<PermissionResult> results;

  public ResultList() {
    results = new ArrayList<>();
  }

  public final boolean add(final PermissionResult result) {
    return results.add(result);
  }

  @Override
  public final Iterator<PermissionResult> iterator() {
    return results.iterator();
  }
}
