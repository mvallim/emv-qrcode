package com.emv.qrcode.model.cpm;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;

import lombok.Getter;

@Getter
public abstract class AdditionalData implements Serializable {

  private static final long serialVersionUID = -2275311356136693642L;

  private BERTLV<String, String> applicationDefinitionFileName;

  private BERTLV<String, String> applicationLabel;

  private BERTLV<String, String> track2EquivalentData;

  private BERTLV<String, String> applicationPAN;

  private BERTLV<String, String> cardholderName;

  private BERTLV<String, String> languagePreference;

  private BERTLV<String, String> issuerURL;

  private BERTLV<String, String> applicationVersionNumber;

  private BERTLV<String, String> tokenRequestorID;

  private BERTLV<String, String> paymentAccountReference;

  private BERTLV<String, String> last4DigitsOfPAN;

  private BERTLV<String, String> cryptogramInformationData;

  private BERTLV<String, String> applicationTransactionCounter;

  private BERTLV<String, String> applicationCryptogram;

  private BERTLV<String, String> issuerApplicationData;

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder(super.toString());

    Optional.ofNullable(applicationDefinitionFileName).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(applicationLabel).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(track2EquivalentData).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(applicationPAN).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(cardholderName).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(languagePreference).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(issuerURL).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(applicationVersionNumber).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(tokenRequestorID).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(paymentAccountReference).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(last4DigitsOfPAN).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(cryptogramInformationData).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(applicationTransactionCounter).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(applicationCryptogram).ifPresent(btlv -> sb.append(btlv.toString()));
    Optional.ofNullable(issuerApplicationData).ifPresent(btlv -> sb.append(btlv.toString()));

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return sb.toString();

  }

}
