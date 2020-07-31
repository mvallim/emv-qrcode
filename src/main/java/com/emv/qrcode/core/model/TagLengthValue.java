package com.emv.qrcode.core.model;

public interface TagLengthValue<T> {

  public abstract String getTag();

  public abstract Integer getLength();

  public abstract T getValue();

}
