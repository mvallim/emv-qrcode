package com.emv.qrcode.core.model;

import java.io.Serializable;

public interface TLV<T, V> extends Serializable {

  public T getTag();

  public Integer getLength();

  public V getValue();

}
