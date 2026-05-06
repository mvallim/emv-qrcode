/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

/**
 * Decoder for PayloadFormatIndicator in Consumer Presented Mode (CPM).
 * Decodes BER-TLV encoded byte arrays into PayloadFormatIndicator objects
 * which contain the tag and value indicating the CPM specification version.
 *
 * @see com.emv.qrcode.model.cpm.PayloadFormatIndicator
 * @see com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes#ID_PAYLOAD_FORMAT_INDICATOR
 * @see DecoderCpm
 */
public final class PayloadFormatIndicatorDecoder extends DecoderCpm<PayloadFormatIndicator> {

  public PayloadFormatIndicatorDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected PayloadFormatIndicator decode() throws PresentedModeException {

    final PayloadFormatIndicator result = new PayloadFormatIndicator();

    final byte[] value = iterator.next();

    final BERTag tag = new BERTag(BERUtils.valueOfTag(value));
    result.setTag(tag);
    result.setValue(BERUtils.valueOf(value));

    return result;

  }

}
