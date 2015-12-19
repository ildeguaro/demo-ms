package com.gsgtech.demo.employee.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Propiedades específicas para el Demo.
 * <p>Propiedades son configuradas en el archivo application.yml.</p>
 * 
 * @author Jorge Guerrero
 *
 */
@ConfigurationProperties(prefix = "demo", ignoreUnknownFields = false)
public class DemoProperties {
    private final Datasource datasource = new Datasource();

    public Datasource getDatasource() {
        return datasource;
    }

    public static class Datasource {
        private boolean cachePrepStmts = true;
        private int prepStmtCacheSize = 250;
        private int prepStmtCacheSqlLimit = 2048;
        private boolean useServerPrepStmts = true;

        public boolean isCachePrepStmts() {
            return cachePrepStmts;
        }

        public void setCachePrepStmts(boolean cachePrepStmts) {
            this.cachePrepStmts = cachePrepStmts;
        }

        public int getPrepStmtCacheSize() {
            return prepStmtCacheSize;
        }

        public void setPrepStmtCacheSize(int prepStmtCacheSize) {
            this.prepStmtCacheSize = prepStmtCacheSize;
        }

        public int getPrepStmtCacheSqlLimit() {
            return prepStmtCacheSqlLimit;
        }

        public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
            this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
        }

        public boolean isUseServerPrepStmts() {
            return useServerPrepStmts;
        }

        public void setUseServerPrepStmts(boolean useServerPrepStmts) {
            this.useServerPrepStmts = useServerPrepStmts;
        }
    }

}
