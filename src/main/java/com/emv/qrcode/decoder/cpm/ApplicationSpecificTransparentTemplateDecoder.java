package com.emv.qrcode.decoder.cpm;

import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.BERTLBinary;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;

public final class ApplicationSpecificTransparentTemplateDecoder extends DecoderCpm<ApplicationSpecificTransparentTemplate> {

  private static final Entry<Class<?>, BiConsumer<ApplicationSpecificTransparentTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, ApplicationSpecificTransparentTemplate::addAdditionalData);

  public ApplicationSpecificTransparentTemplateDecoder(final byte[] source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected ApplicationSpecificTransparentTemplate decode() {

    final ApplicationSpecificTransparentTemplate result = new ApplicationSpecificTransparentTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final Class<?> clazz = defaultEntry.getKey();

      final BiConsumer consumer = defaultEntry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;

  }

}
