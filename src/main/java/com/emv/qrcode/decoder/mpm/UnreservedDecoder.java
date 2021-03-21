package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.core.utils.TLVUtils;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes;

// @formatter:off
public final class UnreservedDecoder extends DecoderMpm<Unreserved> {

  private static final Map<String, Entry<Class<?>, BiConsumer<Unreserved, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, consumerTagLengthValue(String.class, Unreserved::setGloballyUniqueIdentifier));
    mapConsumers.put(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA, consumerTagLengthValue(TagLengthString.class, Unreserved::addContextSpecificData));
  }

  public UnreservedDecoder(final String source) {
    super(TLVUtils.valueOf(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected Unreserved decode() throws PresentedModeException {

    final Set<String> tags = new HashSet<>();

    final Unreserved result = new Unreserved();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = TLVUtils.valueOfTag(value);

      final String derivateId = derivateId(tag);

      if (tags.contains(tag)) {
        throw new DuplicateTagException("Unreserved", tag, value);
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<Unreserved, ?>> entry = mapConsumers.get(derivateId);

      if (Objects.isNull(entry)) {
        throw new InvalidTagException("Unreserved", tag, value);
      }

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderMpm.decode(value, clazz));
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
    return value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_START) >= 0
        && value.compareTo(UnreservedTemplateFieldCodes.ID_CONTEXT_SPECIFIC_DATA_END) <= 0;
  }

}
// @formatter:on
