package com.emv.qrcode.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.mpm.constants.UnreservedTemplateFieldCodes;
import com.emv.qrcode.mpm.model.UnreservedTemplateValue;

import lombok.Getter;

@Getter
class UnreservedTemplateValueParser extends Parser<UnreservedTemplateValue> {

  private static final Map<String, BiConsumer<UnreservedTemplateValue, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_GLOBALLY_UNIQUE_IDENTIFIER, UnreservedTemplateValue::setGloballyUniqueIdentifier);
    mapConsumers.put(UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA, UnreservedTemplateValue::addContextSpecificData);
  }

  UnreservedTemplateValueParser(final String source) {
    super(source);
  }

  @Override
  protected UnreservedTemplateValue parse() {
    final UnreservedTemplateValue result = new UnreservedTemplateValue();
    while (hasNext()) {
      mapConsumers.get(derivateId(getId())).accept(result, next());
    }
    return result;
  }

  private static String derivateId(final String id) {

    if (betweenContextSpecificDataRange(id)) {
      return UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA;
    }

    return id;
  }

  private static boolean betweenContextSpecificDataRange(final String value) {
    return value.compareTo(UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA_START) >= 0 && value.compareTo(UnreservedTemplateFieldCodes.UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA_END) <= 0;
  }

}
