package com.gsgtech.demo.employee.db.dialect;

import java.sql.Types;

import org.hibernate.dialect.H2Dialect;

/**
 * 
 * @author Jorge Guerrero
 *
 */
public class FixedH2Dialect extends H2Dialect {

    public FixedH2Dialect() {
        super();
        registerColumnType(Types.FLOAT, "real");
    }
}
