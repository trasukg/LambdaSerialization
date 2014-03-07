package com.trasuk.lambda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author trasukg
 */
public class LambdaUsageTest {
    
    @Test
    public void testLambdaUsage() throws Exception {
        // So far, so good.  Now serialize it.
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("lambda.ser"));
        
        StringReturner hello=(StringReturner) ois.readObject();
        // Now let's use it
        assertEquals("value of lambda", "Greg", hello.call());
        
        ois.close();
        
    }
}
