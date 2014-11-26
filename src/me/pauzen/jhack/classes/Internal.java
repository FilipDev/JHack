package me.pauzen.jhack.classes;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

final class Internal {

    private Internal() {
    }

    static long getSize(long value) {

        System.out.println(value);

        Unsafe unsafe = UnsafeProvider.getUnsafe();

        for (int x = 0; x <= 128; x += 4) {
            long val = unsafe.getAddress(value + x);
            System.out.println(x + ": " + val);
        }

        Object a = new Object();
        Objects.setClass(a, unsafe.getAddress((value << 3) + 8L));
        System.out.println(a);

        return 0;
    }

}
