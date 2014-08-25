package cn.bright.webframework.helper.tags;

import cn.bright.webframework.carrier.Status;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.core.ExpressionEngine;
import cn.bright.webframework.utils.TextUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

/**
 * Created by hp on 2014/8/6.
 */
public class IteratorTag extends BaseTag {


    private String status;     //迭代集合中表示状态的
    private String item;       //代表集合中的每一项

    @Override
    public void doTag() throws JspException, IOException {


        Object eval = engine.eval(expression);


        if (eval instanceof Collection) {
            Status stat = new Status();
            if (!TextUtils.isEmpty(status)) {


                brightContext.put(status, stat);
            }

            Collection collection = (Collection) eval;
            int index = 0;
            for (Object obj : collection) {
                if (!TextUtils.isEmpty(status)) {
                    stat.setIndex(index);
                    if (index == 0) {

                        stat.setFirst(true);
                    } else {

                        stat.setFirst(false);
                    }

                    if (index == collection.size() - 1) {

                        stat.setEnd(true);
                    } else {
                        stat.setEnd(false);
                    }
                    if (index % 2 == 0) {
                        stat.setOdd(true);
                    } else {
                        stat.setOdd(false);
                    }
                }

                brightContext.put(item, obj);
                getJspBody().invoke(stringWriter);
                getJspContext().getOut().println(stringWriter);
                stringWriter.close();
                stringWriter = new StringWriter();


                index++;
            }

        }
        brightContext.put(item, null);
        brightContext.put(status, null);

    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
