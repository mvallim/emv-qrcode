package com.emv.qrcode.model.cpm;

import java.util.LinkedList;
import java.util.List;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends AdditionalData implements BERTLV<String, List<AdditionalData>> {

  private static final long serialVersionUID = -4642312662946053298L;

  private final List<BERTLV<String, String>> commonDataTransparentTemplates = new LinkedList<>();

  private final String tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE;

  private Integer length;

  private List<AdditionalData> value;

}
