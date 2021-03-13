package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.InvalidMerchantPresentedModeException;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

// @formatter:off
public final class UnreservedTemplateDecoder extends DecoderMpm<UnreservedTemplate> {

  public UnreservedTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected UnreservedTemplate decode() throws InvalidMerchantPresentedModeException {
    final UnreservedTemplate result = new UnreservedTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = value.substring(0, DecodeMpmIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setValue(DecoderMpm.decode(string, Unreserved.class));
    }

    return result;
  }

}
// @formatter:on
