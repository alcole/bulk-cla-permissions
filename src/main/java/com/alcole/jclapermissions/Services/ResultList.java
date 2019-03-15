package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * class to store the permission results.
 */
public class ResultList implements Iterable<PermissionResult> {

  private final List<PermissionResult> results;

  public ResultList() {
    results = new ArrayList<>();
  }

  /**
   * add permission results to the list.
   * @param result the permission result to add the the list
   * @return true if added
   */
  public final boolean add(final PermissionResult result) {
    return results.add(result);
  }

  /**
   * sets up an iterator.
   * @return iterator to enable for loop
   */
  @Override
  public final Iterator<PermissionResult> iterator() {
    return results.iterator();
  }
}
