package com.megathrone.mall.mbg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * To generate MyBatis codes
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        // warning
        List<String> warngings = new ArrayList<>();
        // when found duplication, overwrite the original one
        boolean overwrite = true;

        // read MBG file
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warngings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // Created MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warngings);

        // execute generator
        myBatisGenerator.generate(null);

        //if there are warnings
        for (String warning: warngings) {
            System.out.println(warning);
        }
    }
}
