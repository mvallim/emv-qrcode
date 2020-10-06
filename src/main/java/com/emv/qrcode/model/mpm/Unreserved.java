package com.emv.qrcode.model.mpm;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Getter
public class Unreserved implements Serializable {

    private static final long serialVersionUID = -3465559955367881407L;

    // Globally Unique Identifier
    private TagLengthString globallyUniqueIdentifier;

    // Context Specific Data
    private final Map<String, TagLengthString> contextSpecificData = new LinkedHashMap<>();

    public Unreserved() {
        super();
    }

    public Unreserved(final String globallyUniqueIdentifier) {
        super();
        this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
        this.globallyUniqueIdentifier = new TagLengthString(UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    }

    public final void addContextSpecificData(final TagLengthString tagLengthString) {
        contextSpecificData.put(tagLengthString.getTag(), tagLengthString);
    }

    public final void addContextSpecificData(final String tag, final String value) {
        contextSpecificData.put(tag, new TagLengthString(tag, value));
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

        for (final Entry<String, TagLengthString> entry : contextSpecificData.entrySet()) {
            Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
        }

        final String string = sb.toString();

        if (StringUtils.isBlank(string)) {
            return StringUtils.EMPTY;
        }

        return string;
    }

}
