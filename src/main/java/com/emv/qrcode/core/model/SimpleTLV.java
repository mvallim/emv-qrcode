package com.emv.qrcode.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleTLV<T> {

  private String tag;

  private Integer length;

  private T value;

}
