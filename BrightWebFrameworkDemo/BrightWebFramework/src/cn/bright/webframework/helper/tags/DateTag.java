package cn.bright.webframework.helper.tags;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hp on 2014/8/7.
 */
public class DateTag extends BaseTag {
    String format  ;

    @Override
    public void doTag() throws JspException, IOException {
        if (expression==null) {
          Date  date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                String datestr = simpleDateFormat.format(date);
                if (name != null) {
                    brightContext.put(name, date);
                }
                getJspContext().getOut().println(datestr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ;
        }
        Object date = engine.eval(expression);
        if (date != null && date instanceof Date) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                String datestr = simpleDateFormat.format(date);
                if (name != null) {
                    brightContext.put(name, date);
                }
                getJspContext().getOut().println(datestr);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
