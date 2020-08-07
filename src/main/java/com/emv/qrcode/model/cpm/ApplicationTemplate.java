package com.emv.qrcode.model.cpm;

import java.util.List;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationTemplate extends AdditionalData implements BERTLV<String, List<AdditionalData>> {

  private static final long serialVersionUID = 2418153324275018348L;

  private final String tag = ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE;

  private Integer length;

  private List<AdditionalData> value;

}
