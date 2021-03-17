package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;

// @formatter:off
public final class AdditionalDataFieldTemplateDecoder extends DecoderMpm<AdditionalDataFieldTemplate> {

  public AdditionalDataFieldTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataFieldTemplate decode() throws PresentedModeException {
    final AdditionalDataFieldTemplate result = new AdditionalDataFieldTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();
      result.setValue(DecoderMpm.decode(value, AdditionalDataField.class));
    }

    return result;
  }

}
// @formatter:on
