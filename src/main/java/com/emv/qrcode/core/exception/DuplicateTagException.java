package com.emv.qrcode.core.exception;

public class DuplicateTagException extends InvalidMerchantPresentedModeException {

  private static final long serialVersionUID = 3271139876825199269L;

  private final String tag;

  public DuplicateTagException(final String message, final String tag) {
    super(message);
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

}