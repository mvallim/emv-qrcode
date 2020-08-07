package com.emv.qrcode.model.cpm;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@Getter
public class ConsumerPresentedMode implements Serializable {

  private static final long serialVersionUID = -1395429978639674565L;

  // Payload Format Indicator
  private PayloadFormatIndicator payloadFormatIndicator;

  // Application Template
  private final List<ApplicationTemplate> applicationTemplates = new LinkedList<>();

  // Application Template
  private final List<CommonDataTemplate> commonDataTemplates = new LinkedList<>();

  public void addApplicationTemplate(final ApplicationTemplate applicationTemplate) {
    applicationTemplates.add(applicationTemplate);
  }

  public void addCommonDataTemplate(final CommonDataTemplate commonDataTemplate) {
    commonDataTemplates.add(commonDataTemplate);
  }

  public String toBase64() {
    return Base64.encodeBase64String(toString().getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(payloadFormatIndicator).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final ApplicationTemplate applicationTemplate : applicationTemplates) {
      sb.append(applicationTemplate.toString());
    }

    for (final CommonDataTemplate commonDataTemplate : commonDataTemplates) {
      sb.append(commonDataTemplate.toString());
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return sb.toString();
  }

}
