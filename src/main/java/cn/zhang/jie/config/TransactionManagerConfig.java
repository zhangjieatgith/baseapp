package cn.zhang.jie.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig implements TransactionManagementConfigurer{

	private DataSource dataSource = null;
	
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource getDataSource() {
    	if(dataSource == null) {
    		dataSource = new DruidDataSource();
    	}
        return dataSource;
    }

	/**
	 * 实现接口方法，注册注解事务，当@Transactional使用的时候产生数据库事务
	 */
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(getDataSource());
		return transactionManager;
	}

}
