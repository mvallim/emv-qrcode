package com.emv.qrcode.model.mpm;

import com.emv.qrcode.core.model.TLV;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Setter
public class MerchantAccountInformationTemplate implements TLV<String, MerchantAccountInformation> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private MerchantAccountInformation value;

  public MerchantAccountInformationTemplate() {
    super();
  }

  public MerchantAccountInformationTemplate(final String tag) {
    this.setTag(tag);
  }

  public MerchantAccountInformationTemplate(final String tag, final String globallyUniqueIdentifier) {
    this.setTag(tag);
    this.setValue(new MerchantAccountInformation(globallyUniqueIdentifier));
  }

  public void addPaymentNetworkSpecific(final String tag, final String value) {
    this.setValue(Optional.ofNullable(this.getValue()).orElse(new MerchantAccountInformation()));
    this.getValue().addPaymentNetworkSpecific(tag, value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public MerchantAccountInformation getValue() {
    return value;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, string.length(), string);
  }
}
