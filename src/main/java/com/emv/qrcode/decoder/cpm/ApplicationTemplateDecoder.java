package com.emv.qrcode.decoder.cpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.BERTLBinary;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public final class ApplicationTemplateDecoder extends DecoderCpm<ApplicationTemplate> {

  private static final Entry<Class<?>, BiConsumer<ApplicationTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, ApplicationTemplate::addAdditionalData);

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<ApplicationTemplate, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE, consumerTagLengthValue(ApplicationSpecificTransparentTemplate.class, ApplicationTemplate::setApplicationSpecificTransparentTemplate));
  }

  public ApplicationTemplateDecoder(final byte[] source) {
    super(BERUtils.copyBytesOfLength(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected ApplicationTemplate decode() {

    final ApplicationTemplate result = new ApplicationTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.copyBytesOfTag(value));

      final Entry<Class<?>, BiConsumer<ApplicationTemplate, ?>> entry = mapConsumers.getOrDefault(tag, defaultEntry);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;
  }

}
