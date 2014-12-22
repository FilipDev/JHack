import me.pauzen.jhack.misc.Pointer;
import me.pauzen.jhack.objects.Objects;

public class Main {

    public static void main(String[] args) throws Exception {
    }

    public static long address = 0;

    public static class Test extends Thread {

        @Override
        public void run() {
            Pointer a = new Pointer(4);
            Pointer b = new Pointer(8);
            int c = 11;
            System.out.println("asdfasdf " + Objects.getAddress(a));
            Main.address = Objects.getAddress(a);
            Objects.printInternals(a);
        }
    }
}