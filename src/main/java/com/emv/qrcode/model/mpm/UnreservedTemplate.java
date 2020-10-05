package com.emv.qrcode.model.mpm;

import com.emv.qrcode.core.model.TLV;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Setter
public class UnreservedTemplate implements TLV<String, Unreserved> {

    private static final long serialVersionUID = -1445641777082739037L;

    private String tag;

    private Unreserved value;

    public UnreservedTemplate() {
        super();
    }

    public UnreservedTemplate(final String tag) {
        super();
        this.setTag(tag);
    }

    public UnreservedTemplate(final String tag, final String globallyUniqueIdentifier) {
        super();
        this.setTag(tag);
        this.setValue(new Unreserved(globallyUniqueIdentifier));
    }

    public void addContextSpecificData(String tag, String value) {
        if (this.getValue() == null) {
            this.setValue(new Unreserved());
        }
        this.getValue().addContextSpecificData(tag, value);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Unreserved getValue() {
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
