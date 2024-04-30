package Flatten;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Flatten {

    public static void main(String[] args) {

        // Para obtener un objeto Path utilizamos el método Path.of() o Paths.get()
        Path pathA1 = Path.of("/tmp/niats/aaa/a.txt");
        Path pathA2 = Path.of("/tmp/niats/aaa/a2.txt");
        Path pathB = Path.of("/tmp/niats/aaa/bbb/b.txt");
        Path pathC = Path.of("/tmp/niats/aaa/ccc/c.txt");
        Path pathD = Path.of("/tmp/niats/aaa/ccc/ddd/d.txt");
        Path pathE= Path.of("/tmp/niats/aaa/eee/e.txt");
        Path pathF= Path.of("/tmp/niats/fff/f.txt");
        Path pathN= Path.of("/tmp/niats/n.txt");
        Path pathRoot= Path.of("/tmp/niats");

        try {
            Files.move(pathA1, pathRoot.resolve(pathA1.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathA2, pathRoot.resolve(pathA2.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathB, pathRoot.resolve(pathB.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathC, pathRoot.resolve(pathC.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathD, pathRoot.resolve(pathD.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathE, pathRoot.resolve(pathE.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathF, pathRoot.resolve(pathF.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathN, pathRoot.resolve(pathN.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
