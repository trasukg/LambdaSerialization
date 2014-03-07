/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trasuk.lambda;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author trasukg
 */
public class LambdaGenerationTest {
    
    @Test
    public void testLambda() throws Exception {
        // First, make a lambda
        StringReturner hello=(() ->  "Greg");
        
        // Now let's use it
        assertEquals("value of lambda", "Greg", hello.call());
        
        // So far, so good.  Now serialize it.
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("lambda.ser"));
        oos.writeObject(hello);
        oos.close();
    }
}
