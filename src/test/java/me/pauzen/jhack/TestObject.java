/*
 *  Created by Filip P. on 2/6/15 7:49 PM.
 */

package me.pauzen.jhack;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.reflection.ReflectionFactory;

public class TestObject {
    
    @Override
    public void finalize() {
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        for (Class aClass : ReflectionFactory.getCallerClasses()) {
            Classes.printAddresses(aClass);
            System.out.println(aClass);
        }
    }
    
}
