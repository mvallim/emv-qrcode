package com.emv.qrcode.core.exception;

import java.text.MessageFormat;

public class DuplicateTagException extends PresentedModeException {

  private static final long serialVersionUID = 3271139876825199269L;

  private final String tag;

  private final String value;

  public DuplicateTagException(final String scope, final String tag, final String value) {
    super(MessageFormat.format("Scope: ''{0}'' informed already contains ''{1}'' tag", scope, tag));
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
