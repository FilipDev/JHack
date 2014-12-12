package me.pauzen.jhack;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.classes.Singleton;
import me.pauzen.jhack.misc.Addresses;

public class Main extends Singleton {

    public static void main(String[] args) {
        String a = "test";
        Main main = new Main();
        Classes.getSuperAddress(main);
        System.out.println(Classes.toClass(Addresses.denormalize(4017177783L)));
    }

}
