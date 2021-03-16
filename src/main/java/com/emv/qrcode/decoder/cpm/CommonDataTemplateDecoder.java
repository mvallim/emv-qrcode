package com.emv.qrcode.decoder.cpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.BERTLBinary;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public final class CommonDataTemplateDecoder extends DecoderCpm<CommonDataTemplate> {

  private static final Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData);

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE, consumerTagLengthValue(CommonDataTransparentTemplate.class, CommonDataTemplate::setCommonDataTransparentTemplate));
  }

  public CommonDataTemplateDecoder(final byte[] source) {
    super(BERUtils.copyBytesOfLength(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTemplate decode() {

    final CommonDataTemplate result = new CommonDataTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.copyBytesOfTag(value));

      final Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>> entry = mapConsumers.getOrDefault(tag, defaultEntry);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;
  }

}
