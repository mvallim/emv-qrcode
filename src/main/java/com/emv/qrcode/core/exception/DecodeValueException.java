package com.emv.qrcode.core.exception;

import java.text.MessageFormat;

public class DecodeValueException extends PresentedModeException {

  private static final long serialVersionUID = 3381404607920642729L;

  private final String value;

  public DecodeValueException(final String value) {
    super(MessageFormat.format("Characters outside of the expected range Hex ''[0-9a-fA-F]''. Invalid value ''{0}''", value));
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
