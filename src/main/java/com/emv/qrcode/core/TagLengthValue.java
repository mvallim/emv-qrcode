package com.emv.qrcode.core;

public interface TagLengthValue<T> {

  public abstract String getTag();

  public abstract String getLength();

  public abstract T getValue();

}
