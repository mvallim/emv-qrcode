package com.emv.qrcode.decoder.cpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTag;
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
    super(BERUtils.copyBytesOfValue(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTemplate decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final CommonDataTemplate result = new CommonDataTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.copyBytesOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("CommonDataTemplate", tag.toString(), Hex.encodeHexString(value, false));
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>> entry = mapConsumers.getOrDefault(tag, defaultEntry);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;
  }

}
