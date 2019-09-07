package com.megathrone.mall.mbg;


import java.util.Properties;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * Customized comment generator
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;


    /**
     * configuration from users
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * add comments for fields
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
        IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        //根据参数和备注信息判断是否添加备注信息
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            addFieldJavaDoc(field, remarks);
        }
    }

    /**
     * add comments for models
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        field.addJavaDocLine("/**");
        // get db field comments
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for (String remarkLine : remarkLines) {
            field.addJavaDocLine(" * " + remarkLine);

        }
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }
}
