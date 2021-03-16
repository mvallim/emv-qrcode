package com.emv.qrcode.decoder.cpm;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;

public final class CommonDataTransparentTemplateDecoder extends DecoderCpm<CommonDataTransparentTemplate> {

  private static final Entry<Class<?>, BiConsumer<CommonDataTransparentTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData);

  public CommonDataTransparentTemplateDecoder(final byte[] source) {
    super(BERUtils.copyBytesOfLength(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTransparentTemplate decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final CommonDataTransparentTemplate result = new CommonDataTransparentTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.copyBytesOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("CommonDataTransparentTemplate", tag.toString(), Hex.encodeHexString(value, false));
      }

      tags.add(tag);

      final Class<?> clazz = defaultEntry.getKey();

      final BiConsumer consumer = defaultEntry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;

  }

}
