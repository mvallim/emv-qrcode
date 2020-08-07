package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      
      if(Objects.nonNull(payloadFormatIndicator)) {
        out.write(payloadFormatIndicator.getBytes());
      }
      
      for (final ApplicationTemplate applicationTemplate : applicationTemplates) {
        out.write(applicationTemplate.getBytes());
      }

      for (final CommonDataTemplate commonDataTemplate : commonDataTemplates) {
        out.write(commonDataTemplate.getBytes());
      }   
      
      return out.toByteArray();
    }
  }
  
  public String toBase64() throws IOException {
    return Base64.encodeBase64String(getBytes());
  }

  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
