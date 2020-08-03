package com.emv.qrcode.core.model;

import java.io.Serializable;

public interface SimpleTLV<T> extends Serializable {

  public String getTag();

  public Integer getLength();

  public T getValue();

}
