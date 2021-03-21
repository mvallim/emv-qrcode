package com.emv.qrcode.core.exception;

import java.text.MessageFormat;

public class InvalidTagException extends PresentedModeException {

  private static final long serialVersionUID = 2158566424345876356L;

  private final String tag;

  private final String value;

  public InvalidTagException(final String scope, final String tag, final String value) {
    super(MessageFormat.format("Scope: ''{0}'' invalid ''{1}'' tag", scope, tag));
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
