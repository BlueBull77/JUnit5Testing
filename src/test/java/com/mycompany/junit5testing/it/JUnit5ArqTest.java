/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.junit5testing.it;

import com.mycompany.junit5testing.StatelessBean;
import jakarta.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Marek
 */
@ExtendWith(ArquillianExtension.class)
public class JUnit5ArqTest {

    @Inject
    private StatelessBean bean;
    
    Logger L = Logger.getLogger(JUnit5ArqTest.class.getName());
            
    @Test
    @DisplayName("testing JUnit5ArtTest")
    public void JUnit5ArtTest() {
        assertThat(bean.square(2)).as("ist 4").isEqualTo(4);
        assertEquals(9, bean.square(3));
        
        
    }
    
    
    @Test
    @DisplayName("Exception Handling")
    public void testException() {
        
        Exception exception = assertThrows(NumberFormatException.class, () -> Integer.parseInt("blub"));
        L.log(Level.WARNING, exception.getMessage());
        exception = assertThrows(ArithmeticException.class, () ->
            bean.divide(1, 0));
        
        
        exception = assertThrows(ArithmeticException.class, () ->
            bean.divide(5, 0));
        //Tests if same Exception is handeld twice
//        Integer.parseInt("blub");
    }
    
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
            bean.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
        
        
        exception = assertThrows(ArithmeticException.class, () ->
            bean.divide(2, 0));
    }
    
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(StatelessBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
