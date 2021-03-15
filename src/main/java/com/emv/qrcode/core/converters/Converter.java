package com.emv.qrcode.core.converters;

public interface Converter<T, U> {

  public U convert(final T value);

}
