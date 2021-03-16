package com.emv.qrcode.core.model.cpm;

import java.io.IOException;

public interface BERTemplate<T> {

  public T getBytes() throws IOException;

}
