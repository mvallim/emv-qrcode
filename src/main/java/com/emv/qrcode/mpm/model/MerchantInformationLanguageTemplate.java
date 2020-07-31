package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;

import lombok.Getter;

@Getter
public class MerchantInformationLanguageTemplate implements Serializable, DrawData {

  private static final long serialVersionUID = 6163271793010568887L;

  // Language Preference
  private TagLengthString languagePreference;

  // Merchant Name
  private TagLengthString merchantName;

  // Merchant City
  private TagLengthString merchantCity;

  // RFU for EMVCo
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // TODO: Implements this after
  public void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new TagLengthString("", languagePreference);
  }

  // TODO: Implements this after
  public void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString("", merchantName);
  }

  // TODO: Implements this after
  public void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString("", merchantCity);
  }

  // TODO: Implements this after
  public void addRFUforEMVCO(final String value) {
    rFUforEMVCo.add(new TagLengthString("", value));
  }

  public void addRFUforEMVCOs(final List<String> values) {
    if (Objects.nonNull(values) && !values.isEmpty()) {
      values.forEach(this::addRFUforEMVCO);
    }
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    sb.append(EMVQRFieldCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE);

    Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    return sb.toString();
  }

  @Override
  @SuppressWarnings("all")
  public String draw(final DataType type) {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    return String.format("%s %02d \n%s", EMVQRFieldCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE, toString().length(), sb.toString());
  }

}
