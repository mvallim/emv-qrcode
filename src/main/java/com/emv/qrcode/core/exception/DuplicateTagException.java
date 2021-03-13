package com.emv.qrcode.core.exception;

public class DuplicateTagException extends MerchantPresentedModeException {

  private static final long serialVersionUID = 3271139876825199269L;

  private final String tag;

  public DuplicateTagException(final String segment, final String tag) {
    super(segment + " informed already contains '" + tag + "' tag");
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

}
