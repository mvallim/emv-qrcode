package com.emv.qrcode.model.cpm;

import java.util.LinkedList;
import java.util.List;

import com.emv.qrcode.core.model.BERTLV;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends BERTLV {

  private static final long serialVersionUID = -4642312662946053298L;

  private final List<BERTLV> commonDataTransparentTemplates = new LinkedList<>();

  public void addCommonDataTransparentTemplate(final BERTLV applicationSpecificTransparentTemplate) {
    commonDataTransparentTemplates.add(applicationSpecificTransparentTemplate);
  }

}
