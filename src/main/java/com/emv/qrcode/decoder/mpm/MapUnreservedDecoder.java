package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.model.MapUnreserved;
import com.emv.qrcode.mpm.model.Unreserved;

// @formatter:off
public final class MapUnreservedDecoder extends Decoder<MapUnreserved> {

  public MapUnreservedDecoder(final String source) {
    super(source);
  }

  @Override
  protected MapUnreserved decode() {
    final MapUnreserved result = new MapUnreserved();

    forEachRemaining(value -> {
      final Unreserved unreserved = Decoder.decode(value, Unreserved.class);
      result.put(unreserved.getTag(), unreserved);
    });

    return result;
  }

}

// @formatter:on
