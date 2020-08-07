package com.emv.qrcode.model.cpm;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends AdditionalData implements BERTLV<String, List<CommonDataTransparentTemplate>> {

  private static final long serialVersionUID = -4642312662946053298L;

  private final List<BERTLV<String, String>> commonDataTransparentTemplates = new LinkedList<>();

  private final String tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE;

  private Integer length;

  private final List<CommonDataTransparentTemplate> value = new LinkedList<>();

  public void addCommonDataTransparentTemplate(final CommonDataTransparentTemplate commonDataTransparentTemplate) {
    value.add(commonDataTransparentTemplate);
  }

  @Override
  public String toString() {

    if (value.isEmpty()) {
      return StringUtils.EMPTY;
    }

    final StringBuilder sb = new StringBuilder(super.toString());

    for (final CommonDataTransparentTemplate commonDataTransparentTemplate : value) {
      sb.append(commonDataTransparentTemplate.toString());
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02X%s", tag, string.length(), string);

  }

}
