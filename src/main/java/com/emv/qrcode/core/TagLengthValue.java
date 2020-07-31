package com.emv.qrcode.core;

public interface TagLengthValue<T> {

  public abstract String getTag();

  public abstract Integer getLength();

  public abstract T getValue();

}
