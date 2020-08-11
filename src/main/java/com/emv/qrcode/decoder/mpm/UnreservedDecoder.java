package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes;

// @formatter:off
public final class UnreservedDecoder extends Decoder<Unreserved> {

  private static final Map<String, Entry<Class<?>, BiConsumer<Unreserved, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, consumerTagLengthValue(String.class, Unreserved::setGloballyUniqueIdentifier));
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA, consumerTagLengthValue(TagLengthString.class, Unreserved::addContextSpecificData));
  }

  public UnreservedDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked", "java:S3740" })
  protected Unreserved decode() {
    final Unreserved result = new Unreserved();

    iterator.forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, DecodeIterator.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<Unreserved, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

    return result;
  }

  private String derivateId(final String id) {

    if (betweenContextSpecificDataRange(id)) {
      return UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA;
    }

    return id;
  }

  private boolean betweenContextSpecificDataRange(final String value) {
    return value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_START) >= 0
        && value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_END) <= 0;
  }

}
// @formatter:on
