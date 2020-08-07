package com.emv.qrcode.model.cpm;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationTemplate extends AdditionalData implements BERTLV<String, List<ApplicationSpecificTransparentTemplate>> {

  private static final long serialVersionUID = 2418153324275018348L;

  private Integer length;

  private final List<ApplicationSpecificTransparentTemplate> value = new LinkedList<>();

  public void addApplicationSpecificTransparentTemplate(final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate) {
    value.add(applicationSpecificTransparentTemplate);
  }

  @Override
  public String getTag() {
    return ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE;
  }

  @Override
  public String toString() {

    if (value.isEmpty()) {
      return StringUtils.EMPTY;
    }

    final StringBuilder sb = new StringBuilder(super.toString());

    for (final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate : value) {
      sb.append(applicationSpecificTransparentTemplate.toString());
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02X%s", getTag(), string.length(), string);

  }

}
