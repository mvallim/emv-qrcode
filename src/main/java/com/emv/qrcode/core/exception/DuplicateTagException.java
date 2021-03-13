package com.emv.qrcode.core.exception;

public class DuplicateTagException extends MerchantPresentedModeException {

  private static final long serialVersionUID = 3271139876825199269L;

  private final String tag;

  private final String value;

  public DuplicateTagException(final String segment, final String tag, final String value) {
    super(segment + " informed already contains '" + tag + "' tag");
    this.tag = tag;
    this.value = value;
  }

  public String getTag() {
    return tag;
  }

  public String getValue() {
    return value;
  }

}
