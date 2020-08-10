# EMV QRCode

[![Build Status](https://travis-ci.org/mvallim/emv-qrcode.svg?branch=master)](https://travis-ci.org/mvallim/emv-qrcode)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=alert_status)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=coverage)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Java Based EMV QR Code Generator and Parser (MPM, CPM)

## Specification

- [EMV QR Code Specification for Payment Systems: Merchant-Presented Mode](docs/EMVCo-Merchant-Presented-QR-Specification-v1-1.pdf)
- [EMV QR Code Specification for Payment Systems: Consumer Presented Mode](docs/EMVCo-Consumer-Presented-QR-Specification-v1-1.pdf)
- [EMV Book 3 Application Specification](docs/EMV_v4.3_Book_3_Application_Specification_20120607062110791.pdf)
- [EMV Book 4 Other Interfaces](docs/EMV_v4.3_Book_4_Other_Interfaces_20120607062305603.pdf)

## 1. Quick Start

This chapter will show you how to get started with EMV QR Code.

### 1.1 Prerequisite

In order to use EMV QR Code within a Maven project, simply add the following dependency to your pom.xml. There are no other dependencies for EMV QR Code, which means other unwanted libraries will not overwhelm your project.

You can pull it from the central Maven repositories:

```xml
<dependency>
    <groupId>com.github.mvallim</groupId>
    <artifactId>emv-qrcode</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

If you want to try a snapshot version, add the following repository:

```xml
<repository>
    <id>sonatype-snapshots</id>
    <name>Sonatype Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

### MPM (Merchant Presented Mode)

```java

final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
final MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
final UnreservedTemplate unreserved = getUnreserved();
final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
merchantPresentMode.setAdditionalDataField(additionalDataField);
merchantPresentMode.setCountryCode("CN");
merchantPresentMode.setMerchantCategoryCode("4111");
merchantPresentMode.setMerchantCity("BEIJING");
merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
merchantPresentMode.setMerchantName("BEST TRANSPORT");
merchantPresentMode.setPayloadFormatIndicator("01");
merchantPresentMode.setPointOfInitiationMethod("11");
merchantPresentMode.setPostalCode("1234567");
merchantPresentMode.setTipOrConvenienceIndicator("01");
merchantPresentMode.setTransactionAmount("23.72");
merchantPresentMode.setTransactionCurrency("156");
merchantPresentMode.setValueOfConvenienceFeeFixed("500");
merchantPresentMode.setValueOfConvenienceFeePercentage("5");
merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
merchantPresentMode.addUnreserved(unreserved);

System.out.println(merchantPresentMode.toString());
    //"00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C395";

private MerchantAccountInformationTemplate getMerchanAccountInformation() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
    merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("02");

    return merchantAccountInformation;
}

private UnreservedTemplate getUnreserved() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("80");

    return unreserved;
}

private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
    merchantInformationLanguageValue.setLanguagePreference("ZH");
    merchantInformationLanguageValue.setMerchantName("北京");
    merchantInformationLanguageValue.setMerchantCity("最佳运输");
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);

    return merchantInformationLanguage;
}

private AdditionalDataFieldTemplate getAddtionalDataField() {

    final TagLengthString purposeTransaction = new TagLengthString();
    purposeTransaction.setTag("08");
    purposeTransaction.setValue("pqres");

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest("tuvxy");
    additionalDataFieldValue.setBillNumber("12345");
    additionalDataFieldValue.setCustomerLabel("fghij");
    additionalDataFieldValue.setLoyaltyNumber("54321");
    additionalDataFieldValue.setMobileNumber("67890");
    additionalDataFieldValue.setPurposeTransaction("pqres");
    additionalDataFieldValue.setReferenceLabel("abcde");
    additionalDataFieldValue.setStoreLabel("09876");
    additionalDataFieldValue.setTerminalLabel("klmno");
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecific);
    additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(additionalDataFieldValue);

    return additionalDataField;
}
```

### CPM (Consumer Presented Mode)

```java
// Coming soon
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [GitHub](https://github.com/mvallim/emv-qrcode) for versioning. For the versions available, see the [tags on this repository](https://github.com/mvallim/emv-qrcode/tags).

## Authors

* **Marcos Vallim** - *Initial work, Development, Test, Documentation* - [mvallim](https://github.com/mvallim)

See also the list of [contributors](CONTRIBUTORS.txt) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
