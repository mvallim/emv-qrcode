package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.UnreservedTemplateFieldCodes;
import com.emv.qrcode.mpm.model.UnreservedTemplateValue;

public final class UnreservedTemplateValueDecoder extends Decoder<UnreservedTemplateValue> {

  private static final Map<String, BiConsumer<UnreservedTemplateValue, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, UnreservedTemplateValue::setGloballyUniqueIdentifier);
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA, UnreservedTemplateValue::addContextSpecificData);
  }

  public UnreservedTemplateValueDecoder(final String source) {
    super(source);
  }

  @Override
  protected UnreservedTemplateValue decode() {
    final UnreservedTemplateValue result = new UnreservedTemplateValue();
    while (super.hasNext()) {
      mapConsumers.get(derivateId(super.getId())).accept(result, super.next());
    }
    return result;
  }

  private String derivateId(final String id) {

    if (betweenContextSpecificDataRange(id)) {
      return UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA;
    }

    return id;
  }

  private boolean betweenContextSpecificDataRange(final String value) {
    return value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_START) >= 0 && value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_END) <= 0;
  }

}
