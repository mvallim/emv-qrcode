package com.emv.qrcode.model.cpm;

import java.util.LinkedList;
import java.util.List;

import com.emv.qrcode.core.model.BERTLV;

import lombok.Getter;

@Getter
public class ApplicationTemplate extends BERTLV {

  private static final long serialVersionUID = 2418153324275018348L;

  private final List<BERTLV> applicationSpecificTransparentTemplates = new LinkedList<>();

  public void addApplicationSpecificTransparentTemplate(final BERTLV applicationSpecificTransparentTemplate) {
    applicationSpecificTransparentTemplates.add(applicationSpecificTransparentTemplate);
  }

}
