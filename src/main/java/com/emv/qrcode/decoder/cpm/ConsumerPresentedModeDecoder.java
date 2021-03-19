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
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public final class ConsumerPresentedModeDecoder extends DecoderCpm<ConsumerPresentedMode> {

  private static final Set<BERTag> denyDuplicateTags = new HashSet<>();

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<ConsumerPresentedMode, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, consumerTagLengthValue(PayloadFormatIndicator.class, ConsumerPresentedMode::setPayloadFormatIndicator));
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE, consumerTagLengthValue(ApplicationTemplate.class, ConsumerPresentedMode::addApplicationTemplate));
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE, consumerTagLengthValue(CommonDataTemplate.class, ConsumerPresentedMode::addCommonDataTemplate));

    denyDuplicateTags.add(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR);
  }

  public ConsumerPresentedModeDecoder(final byte[] source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected ConsumerPresentedMode decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final ConsumerPresentedMode result = new ConsumerPresentedMode();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.valueOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("ConsumerPresentedMode", tag.toString(), Hex.encodeHexString(value, false));
      }

      if (denyDuplicateTags.contains(tag)) {
        tags.add(tag);
      }

      final Entry<Class<?>, BiConsumer<ConsumerPresentedMode, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;
  }

}
