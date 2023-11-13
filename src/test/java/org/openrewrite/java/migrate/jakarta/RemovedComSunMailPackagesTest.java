package org.openrewrite.java.migrate.jakarta;

import org.junit.jupiter.api.Test;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.config.Environment;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.*;
import static org.openrewrite.java.Assertions.java;
import static org.openrewrite.maven.Assertions.pomXml;

public class RemovedComSunMailPackagesTest  implements RewriteTest {
    @Override
    public void defaults(RecipeSpec spec) {
        spec.parser(JavaParser.fromJavaVersion()
            .classpathFromResources(new InMemoryExecutionContext(), "javax.mail-1.6.0"))
          .recipe(Environment.builder().scanRuntimeClasspath("org.openrewrite.java.migrate.jakarta")
            .build()
            .activateRecipes("org.openrewrite.java.migrate.jakarta.RemovedComSunMailPackages"));
    }

    @Test
    void comSunMail() {
        rewriteRun(
          //language=java
          java(
            """
               package io.test;
               
               import com.sun.mail.util.BASE64DecoderStream;
               
               public class Test {
               	
               	public void test() {
                  		BASE64DecoderStream baseDecovder = new BASE64DecoderStream(null, false);
                  	}
               
               }
               
               """,
            """
               package io.test;
               
               import com.sun.mail.util.logging.MailHandler;
               import com.sun.mail.imap.protocol.BASE64MailboxDecoder;
               
               public class Test {
               	
               	public void test() {
               		MailHandler mailHandler = new MailHandler();
               		
               		BASE64MailboxDecoder baseDecovder = new BASE64MailboxDecoder();
               	}
               
               }
               """));
    }
}
