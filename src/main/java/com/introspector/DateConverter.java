package com.introspector;

import net.sf.cglib.core.Converter;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 简易DateConverter.
 * 供Apache BeanUtils 做转换,默认时间格式为yyyy-MM-dd,可由构造函数改变.
 * Created by ${denghb} on 2016/9/6.
 */
public class DateConverter implements Converter {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public DateConverter(String formatPattern) {
        if (StringUtils.isNotBlank(formatPattern)) {
            format = new SimpleDateFormat(formatPattern);
        }
    }

    @Override
    public Object convert(Object value, Class target, Object context) {

        if (java.util.Date.class.isAssignableFrom(target)) {
            String dateStr = (String) value;
            if (StringUtils.isNotBlank(dateStr)) {
                try {
                    return format.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return value;
    }
}