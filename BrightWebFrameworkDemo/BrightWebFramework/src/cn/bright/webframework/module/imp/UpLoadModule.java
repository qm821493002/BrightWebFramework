package cn.bright.webframework.module.imp;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.FileDomain;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.module.Module;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.ResponseCache;
import java.util.*;

/**
 * Created by hp on 2014/8/5.
 */
public class UpLoadModule implements Module {

    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler) throws Exception {

        HttpServletRequest request = context.getRequest();

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        //如果有文件需要处理
        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = request.getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);

            //用于存放参数值
            Map<String, String[]> params = new HashMap<String, String[]>();
            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {   //如果是普通字段那么额外处理
                    processFormField(item, params);
                } else {
                    processUploadedFile(item, carrier, repository);
                }
            }
            carrier.getActionOriginal().setParams(params);
        }
        try {


            handler.next();

        }finally {


            //删除临时文件(如果有)
            FileDomain fileDomain = carrier.getFileDomain();
            if (fileDomain != null) {
                fileDomain.getFile().delete();
            }
        }

    }

    private void processUploadedFile(FileItem item, ActionCarrier carrier, File repository) throws Exception {

        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();

        FileDomain fileDomain = new FileDomain();

        fileDomain.setFieldName(fieldName);

        fileDomain.setFileName(fileName);

        fileDomain.setContentType(contentType);
        fileDomain.setInMemory(isInMemory);

        fileDomain.setSizeInBytes(sizeInBytes);

        //先存到磁盘上而不是内存中,以防文件过大
        File file = new File(repository.getAbsolutePath(), new Date().getTime() + "");
        item.write(file);

        fileDomain.setFile(file);

        carrier.setFileDomain(fileDomain);


    }

    private void processFormField(FileItem item, Map<String, String[]> params) {

        String name = item.getFieldName();
        String value = item.getString();
        params.put(name, new String[]{value});

    }
}
