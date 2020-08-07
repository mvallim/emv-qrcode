package com.emv.qrcode.model.cpm;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

}
