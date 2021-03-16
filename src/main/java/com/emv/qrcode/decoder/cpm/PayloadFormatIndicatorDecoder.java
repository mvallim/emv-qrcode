package com.emv.qrcode.decoder.cpm;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

public final class PayloadFormatIndicatorDecoder extends DecoderCpm<PayloadFormatIndicator> {

  public PayloadFormatIndicatorDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected PayloadFormatIndicator decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final PayloadFormatIndicator result = new PayloadFormatIndicator();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.copyBytesOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("PayloadFormatIndicator", tag.toString(), Hex.encodeHexString(value, false));
      }

      tags.add(tag);

      result.setTag(tag);
      result.setValue(BERUtils.copyBytesOfLength(value));
    }

    return result;

  }

}
