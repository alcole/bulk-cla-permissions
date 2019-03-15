package com.alcole.jclapermissions.Model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * A permission result to store the data from the permission call,
 * the metadata associated with the
 * supplied identifier and the associated permissions.
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PermissionResult {

  private String title;
  private String identifier;
  private String format;
  private String publisher;
  private String publisherCountry;

  private String photocopyingPermission;
  private String scanningPermission;
  private String digitalPermission;

  /**
   * permission result as array.
   * @return array of the result of a permission call
   */
  public final String[] toArray() {
    return new String[] {
      identifier,
      title,
      format,
      publisher,
      publisherCountry,
      photocopyingPermission,
      scanningPermission,
      digitalPermission
    };
  }

  @Override
  public final String toString() {
    return identifier + "," + title + "," + format + "," + publisher;
  }
}
