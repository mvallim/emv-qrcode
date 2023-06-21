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

package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.model.cpm.BERTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends AdditionalData implements BERTemplate<byte[]> {

  private static final long serialVersionUID = -4642312662946053298L;

  private static final BERTag tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE;

  private CommonDataTransparentTemplate commonDataTransparentTemplate;

  public BERTag getTag() {
    return tag;
  }

  public void setCommonDataTransparentTemplate(final CommonDataTransparentTemplate commonDataTransparentTemplate) {
    this.commonDataTransparentTemplate = commonDataTransparentTemplate;
  }

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

        if (Objects.nonNull(commonDataTransparentTemplate)) {
          stream.write(commonDataTransparentTemplate.getBytes());
        }

        final byte[] selfBytes = super.getBytes();
        final byte[] valueBytes = stream.toByteArray();

        final int len = selfBytes.length + valueBytes.length;

        if (len == 0) {
          return BERTLV.EMPTY_BYTES;
        }

        out.write(tag.getBytes());
        out.write(len);
        out.write(selfBytes);
        out.write(valueBytes);

        return out.toByteArray();
      }
    }
  }

}
