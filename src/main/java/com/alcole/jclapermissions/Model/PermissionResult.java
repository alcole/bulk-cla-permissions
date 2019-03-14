package com.alcole.jclapermissions.Model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
