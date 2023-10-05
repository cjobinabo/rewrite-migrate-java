/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.migrate;

import org.junit.jupiter.api.Test;
import org.openrewrite.config.Environment;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;
import static org.openrewrite.java.Assertions.version;

public class UpgradeToJava6Test implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(Environment.builder()
          .scanRuntimeClasspath("org.openrewrite.java.migrate")
          .build()
          .activateRecipes("org.openrewrite.java.migrate.UpgradeToJava6"));
    }

    @Test
    void testDataSource() {
        rewriteRun(
          version(
            //language=java
            java("""
                      package com.test.withoutWrapperMethods;
                    
                      import java.io.PrintWriter;
                      import java.sql.Connection;
                      import java.sql.SQLException;
                    
                      import javax.sql.DataSource;
                    
                      public class JRE6WrapperDataSource implements DataSource {
                    
                      	public Connection getConnection() throws SQLException {
                      		// TODO Auto-generated method stub
                      		return null;
                      	}
                    
                      	public Connection getConnection(String username, String password)
                      			throws SQLException {
                      		// TODO Auto-generated method stub
                      		return null;
                      	}
                    
                      	public PrintWriter getLogWriter() throws SQLException {
                      		// TODO Auto-generated method stub
                      		return null;
                      	}
                    
                      	public void setLogWriter(PrintWriter out) throws SQLException {
                      		// TODO Auto-generated method stub
                    
                      	}
                    
                      	public void setLoginTimeout(int seconds) throws SQLException {
                      		// TODO Auto-generated method stub
                    
                      	}
                    
                      	public int getLoginTimeout() throws SQLException {
                      		// TODO Auto-generated method stub
                      		return 0;
                      	}
                    
                      }
                      """,
              """
                   package com.test.withoutWrapperMethods;
                 
                   import java.io.PrintWriter;
                   import java.sql.Connection;
                   import java.sql.SQLException;
                 
                   import javax.sql.DataSource;
                 
                   public class JRE6WrapperDataSource implements DataSource {
                 
                   	public Connection getConnection() throws SQLException {
                   		// TODO Auto-generated method stub
                   		return null;
                   	}
                 
                   	public Connection getConnection(String username, String password)
                   			throws SQLException {
                   		// TODO Auto-generated method stub
                   		return null;
                   	}
                 
                   	public PrintWriter getLogWriter() throws SQLException {
                   		// TODO Auto-generated method stub
                   		return null;
                   	}
                 
                   	public void setLogWriter(PrintWriter out) throws SQLException {
                   		// TODO Auto-generated method stub
                 
                   	}
                 
                   	public void setLoginTimeout(int seconds) throws SQLException {
                   		// TODO Auto-generated method stub
                 
                   	}
                 
                   	public int getLoginTimeout() throws SQLException {
                   		// TODO Auto-generated method stub
                   		return 0;
                   	}
                 
                       public boolean isWrapperFor(Class<?> iface) throws java.sql.SQLException {
                           return false;
                       }
                 
                       public <T> T unwrap(Class<T> iface) throws java.sql.SQLException {
                           return null;
                       }
                 
                   }
                   """
            ), 6)
        );
    }
}
