package Flatten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Flatten {

    public static void main(String[] args) {

        // Para obtener un objeto Path utilizamos el método Path.of() o Paths.get()
        Path pathA = Path.of("/tmp/niats/aaa/a.txt");
        Path pathB = Path.of("/tmp/niats/aaa/bbb/b.txt");
        Path pathC = Path.of("/tmp/niats/aaa/ccc/c.txt");
        Path pathD = Path.of("/tmp/niats/aaa/ccc/ddd/d.txt");
        Path pathE= Path.of("/tmp/niats/aaa/eee/e.txt");
        Path pathF= Path.of("/tmp/niats/fff/f.txt");


        Path pathRootA = Path.of("/tmp/niats/a.txt");
        Path pathRootB = Path.of("/tmp/niats/b.txt");
        Path pathRootC = Path.of("/tmp/niats/c.txt");
        Path pathRootD = Path.of("/tmp/niats/d.txt");
        Path pathRootE= Path.of("/tmp/niats/e.txt");
        Path pathRootF= Path.of("/tmp/niats/f.txt");

        Path FileA = Path.of("/tmp/niats/aaa");
        Path FileB = Path.of("/tmp/niats/aaa/bbb");
        Path FileC = Path.of("/tmp/niats/aaa/ccc");
        Path FileD = Path.of("/tmp/niats/aaa/ccc/ddd");
        Path FileE= Path.of("/tmp/niats/aaa/eee");
        Path FileF= Path.of("/tmp/niats/fff");


        try {
            Files.move(pathA, pathRootA);
            Files.move(pathB, pathRootB);
            Files.move(pathC, pathRootC);
            Files.move(pathD, pathRootD);
            Files.move(pathE, pathRootE);
            Files.move(pathF, pathRootF);
            Files.delete(FileD);
            Files.delete(FileC);
            Files.delete(FileB);
            Files.delete(FileE);
            Files.delete(FileA);
            Files.delete(FileF);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
