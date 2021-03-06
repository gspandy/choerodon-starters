package io.choerodon.liquibase.addition;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 读取环境变量
 * @author dongfan117@gmail.com
 */
@Configuration
public class ProfileMap implements EnvironmentAware {
    private RelaxedPropertyResolver springDatasourceProperty;
    private RelaxedPropertyResolver dataProperty;
    private RelaxedPropertyResolver additionDatasourceProperty;
    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.springDatasourceProperty = new RelaxedPropertyResolver(env, "spring.datasource.");
        this.dataProperty = new RelaxedPropertyResolver(env, "data");
        this.additionDatasourceProperty = new RelaxedPropertyResolver(env, "custom.datasource");
        this.env = env;
    }

    /**
     * 获取环境变量.
     *
     * @param key key
     * @return
     */
    public String getValue(String key) {
        String value = "";
        try {
            value = env.getProperty(key);
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    public String getAdditionValue(String key) {
        return getValue("addition.datasource." + key);
    }

    public String getSpringValue(String key) {
        return getValue("spring.datasource." + key);
    }

    public String getDataValue(String key) {
        return getValue("data." + key);
    }

    public RelaxedPropertyResolver getSpringDatasourceProperty() {
        return springDatasourceProperty;
    }

    public void setSpringDatasourceProperty(RelaxedPropertyResolver springDatasourceProperty) {
        this.springDatasourceProperty = springDatasourceProperty;
    }

    public RelaxedPropertyResolver getDataProperty() {
        return dataProperty;
    }

    public void setDataProperty(RelaxedPropertyResolver dataProperty) {
        this.dataProperty = dataProperty;
    }

    public RelaxedPropertyResolver getAdditionDatasourceProperty() {
        return additionDatasourceProperty;
    }

    public void setAdditionDatasourceProperty(RelaxedPropertyResolver additionDatasourceProperty) {
        this.additionDatasourceProperty = additionDatasourceProperty;
    }
}
