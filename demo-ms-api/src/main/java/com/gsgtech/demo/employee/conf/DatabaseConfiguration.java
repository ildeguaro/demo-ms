package com.gsgtech.demo.employee.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;


@Configuration
@EnableJpaRepositories("com.gsgtech.demo.employee.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource(DataSourceProperties dataSourceProperties, DemoProperties demoProperties) {
		log.debug("Configurando Datasource...");
		if (dataSourceProperties.getUrl() == null) {
			log.error("La configuración del pool de conexiones de la base de datos es incorrecta!. La Aplicación"
					+ " no se puede iniciar. Por favor, chequee su perfil Spring. Ejecutandose los siguientes perfiles de Spring: {}",
					Arrays.toString(env.getActiveProfiles()));
			throw new ApplicationContextException("El pool de conexiones de la base de datos no está configurado correctamente.");
		}
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
		config.addDataSourceProperty("url", dataSourceProperties.getUrl());
		config.addDataSourceProperty("user", dataSourceProperties.getUsername() != null
				? dataSourceProperties.getUsername() : ""); // HikariCP no permite usuario null
		config.addDataSourceProperty("password", dataSourceProperties.getPassword() != null
				? dataSourceProperties.getPassword() : ""); // HikariCP no permite contraseña null
		// Optimización MySQL, ver: https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(dataSourceProperties.getDriverClassName())) {
			config.addDataSourceProperty("cachePrepStmts", demoProperties.getDatasource().isCachePrepStmts());
			config.addDataSourceProperty("prepStmtCacheSize", demoProperties.getDatasource().getPrepStmtCacheSize());
			config.addDataSourceProperty("prepStmtCacheSqlLimit", demoProperties.getDatasource().getPrepStmtCacheSqlLimit());
		}
		return new HikariDataSource(config);
	}
	
    /**
     * Iniciar Servidor de Base de Datos H2, para que ésta sea accesible remotamente.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile("dev")
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers");
    }
}
