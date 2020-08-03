package com.emv.qrcode.decoder.common;

import com.emv.qrcode.core.model.ListTagLengthString;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;

// @formatter:off
public final class ListTagLengthStringDecoder extends Decoder<ListTagLengthString> {

  public ListTagLengthStringDecoder(final String source) {
    super(source);
  }

  @Override
  protected ListTagLengthString decode() {
    final ListTagLengthString result = new ListTagLengthString();

    forEachRemaining(value -> result.add(Decoder.decode(value, TagLengthString.class)));

    return result;
  }

}

// @formatter:on
