package com.toast.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class SybTimestampConverter implements Converter {

    //com.sybase.jdbc3.tds.SybTimestamp
    
        private Locale locale;

        public SybTimestampConverter(Locale locale) {
                super();
                this.locale = locale;
        }

        public boolean canConvert(Class clazz) {
                return clazz.equals(Timestamp.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
                writer.setValue("1-01-2009 12:12:12");
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                GregorianCalendar calendar = new GregorianCalendar();
                return new Timestamp(calendar.getTimeInMillis());
        }

}