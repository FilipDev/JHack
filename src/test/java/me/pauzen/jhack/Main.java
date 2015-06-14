package me.pauzen.jhack;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.utils.Address;
import me.pauzen.jhack.unsafe.UnsafeProvider;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new TestObject();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void test() {
        int denom = (int) Math.pow(10, 6);
        int denom2 = (int) Math.pow(10, 10);
        for (int i = -89000000; i > -89900000; i -= 1) {
            if (i == -89063424) continue;
            try {
                long address = Address.shiftOOPs(Address.normalize(i));
                long a = UnsafeProvider.getUnsafe().getInt(address + 8L);
                long b = UnsafeProvider.getUnsafe().getInt(address + 0L);
                //if (a == -277602112) {
                //skips = 6;
                //  continue;
                //}
                System.out.println(a + " " + b);
                //if (a == -277751024 || a == -277748663 || a == -277748005 || a == -277788074) continue;
                if (a == 0)continue;
                if (b == 1 && a / denom == -277 && ((int) (a / denom2)) == 0) {
                    System.out.println(i + ": " + Objects.toObject(i).getClass());
                }
            } catch (NullPointerException ignored) {}

        }
    }
}