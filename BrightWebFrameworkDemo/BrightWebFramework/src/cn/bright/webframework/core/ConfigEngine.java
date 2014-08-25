package cn.bright.webframework.core;

import cn.bright.webframework.carrier.Config;
import cn.bright.webframework.carrier.ErrorConfig;
import cn.bright.webframework.enums.FailedBehavior;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2014/8/4.
 */
public class ConfigEngine {

    private static Config config;

    public static Config loadConfig(boolean debug) throws Exception {
        if (!debug) {
            if (config != null) {

                return config;
            }
        }

        config = new Config();

        parseBaseConfig();


        return config;
    }

    /**
     * 加载通用公共配置
     *
     * @throws Exception
     */
    private static void parseBaseConfig() throws Exception {

        URL baseConfig = ConfigEngine.class.getClassLoader().getResource("bright-global-config.xml");
        Document baseDocument = parse(baseConfig);

        List<String> modules = parseModulss(baseDocument);
        config.setModules(modules);


        String scanPackage = parseScanPackage(baseDocument);
        if (scanPackage != null) {
            config.setScanPackage(scanPackage);

        }


        String[] allclears = parseAllclears(baseDocument);

        config.setAllclears(allclears);


        ErrorConfig errorConfig = parseErrorConfig(baseDocument);


        config.setErrorConfig(errorConfig);
    }

    private static ErrorConfig parseErrorConfig(Document baseDocument) {
        ErrorConfig errorConfig = new ErrorConfig();
        Node node = baseDocument.selectSingleNode("/bright:bright-config/bright:error-config");
        if (node != null) {
            Node convertFailedNode = baseDocument.selectSingleNode("/bright:bright-config/bright:error-config/bright:convert-failed");
            Node validationFailedNode = baseDocument.selectSingleNode("/bright:bright-config/bright:error-config/bright:validation-failed");
            if (convertFailedNode != null) {

                if ("ADD_WARING_FIELD".equals(convertFailedNode.getText().trim())) {
                        errorConfig.setConvertfailedBehavior(FailedBehavior.ADD_WARING_FIELD);

                } else {
                    errorConfig.setConvertfailedBehavior(FailedBehavior.THROW_EXCEPTION);

                }
            }

            if (validationFailedNode!= null) {

                if ("ADD_WARING_FIELD".equals(validationFailedNode.getText().trim())) {
                    errorConfig.setValidationFailedBehavior(FailedBehavior.ADD_WARING_FIELD);

                } else {
                    errorConfig.setValidationFailedBehavior(FailedBehavior.THROW_EXCEPTION);

                }
            }

            return errorConfig;

        }
        return null;
    }

    private static String[] parseAllclears(Document baseDocument) {

        Node node = baseDocument.selectSingleNode("/bright:bright-config/bright:allclear");
        if (node != null) {
            return node.getText().split(",");
        }
        return null;
    }

    /**
     * (not-test)
     *
     * @param baseDocument
     * @return 返回搜索到的指定包名
     */
    private static String parseScanPackage(Document baseDocument) {

        List<Node> noteList = baseDocument.selectNodes("//scann-package");
        if (noteList.size() > 0) {
            return noteList.get(0).getText();
        }

        return null;

    }

    /**
     * 搜索moduls
     *
     * @param baseDocument 要进行搜索的文档
     * @return moduls字符串集合 按定义顺序排序
     */
    private static List<String> parseModulss(Document baseDocument) {
        List<String> list = new ArrayList<String>();

        List<Node> noteList = baseDocument.selectNodes("/bright:bright-config/bright:modules/bright:module");
        for (Node node : noteList) {
            list.add(node.getText().trim());
        }
        return list;
    }


    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("bright", "http://www.bright.cn");
        reader.getDocumentFactory().setXPathNamespaceURIs(namespaces);
        Document document = reader.read(url);
        return document;
    }

}
