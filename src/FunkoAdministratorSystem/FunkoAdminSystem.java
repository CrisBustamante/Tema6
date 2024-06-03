package FunkoAdministratorSystem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FunkoAdminSystem {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        menu();
    }
    public static void showMenu(){
        System.out.print("" +
                "\n---MENÚ---\n" +
                "1.Añadir funko\n" +
                "2.Borrar funko\n" +
                "3.Mostrar funkos\n" +
                "4.Funko mas caro\n" +
                "5.Mostrar media de los precios\n" +
                "6.Mostrar funkos por modelos\n" +
                "7.Funkos 2023\n" +
                "8.Salir\n" +
                "Que quieres hacer? ");
    }
    public static void menu() throws IOException {
        int option;
        Path csv = Paths.get(System.getProperty("user.home"), "Escritorio", "funkos.csv");
        List<Funko> funkosList = createArrayList(csv);

        do {
            showMenu();
            option = in.nextInt();
            switch (option) {
                case 1:
                    addFunko(funkosList, csv);
                    break;
                case 2:
                    removeFunko(funkosList, csv);
                    break;
                case 3:
                    showFunkos(funkosList);
                    break;
                case 4:
                    mostExpensiveFunko(funkosList);
                    break;
                case 5:
                    avgPrice(funkosList);
                    break;
                case 6:
                    byCompany(funkosList);
                    break;
                case 7:
                    funkos2023(funkosList);
                    break;
                case 8:
                    System.out.println("Has salido del programa.");
                    break;
                default:
                    System.out.println("Porfavor selecciona una opción válida.");
            }
        } while (option != 8);

    }
    public static List<Funko> createArrayList(Path csv) throws IOException {
        Scanner scan = new Scanner(csv);
        List<String> lineas = Files.readAllLines(csv);
        List<Funko> funkosList = new ArrayList<>();
        boolean firstLine = true;
        for (String linea: lineas) {
            if (firstLine) firstLine = false;
            else {
                String[] funko = linea.trim().split(",");
                Funko newFunko = new Funko(funko[0], funko[1], funko[2], funko[3], funko[4]);
                funkosList.add(newFunko);
            }
        }
        return funkosList;
    }
    public static void addFunko(List<Funko> funkoList, Path csv){
        System.out.print("Código: ");
        String cod = in.next();
        System.out.print("Nombre: ");
        String name = in.next();
        System.out.print("Compañia: ");
        String company = in.next();
        System.out.print("Precio: ");
        String price = in.next();
        System.out.print("Fecha Lanzamiento(yyyy-MM-dd): ");
        String date = in.next();

        Funko newFunko = new Funko(cod, name, company, price, date);
        funkoList.add(newFunko);

        try {
            Files.writeString(csv, (newFunko.toString() + "\n"), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public static void removeFunko(List<Funko> funkoList, Path csv){
        System.out.println("Nombre del Funko a borrar: ");
        String name = in.next();
        funkoList.removeIf(funko -> Objects.equals(funko.getName(), name));
        try {
            Files.writeString(csv, "", CREATE);
        } catch (IOException e) {
            System.err.println("Error al intentar borrar las líneas del archivo CSV: " + e.getMessage());
        }
        loadFile(funkoList, csv);
    }
    public static void loadFile(List<Funko> funkoList, Path csv){
        try {
            for (Funko funko : funkoList){
                Files.writeString(csv, funko.toString(), CREATE, APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showFunkos(List<Funko> funkosList){
        boolean firstLine = true;
        System.out.println("\n");
        for(Funko funko : funkosList){
            if (firstLine) {
                firstLine = false;
            } else {
                System.out.println(funko.toString());
            }
        }
    }
    public static void mostExpensiveFunko(List<Funko> funkosList){
        float price;
        float maxPrice = 0;
        boolean firstLine = true;
        for (Funko funko : funkosList){
            if(firstLine){
                firstLine = false;
            } else {
                price = Float.parseFloat(funko.getPrice());
                if (price > maxPrice){
                    maxPrice = price;
                }
            }
        }
        System.out.println(" - Funko/s mas caro/s: ");
        firstLine = true;
        for (Funko funko : funkosList){
            if(firstLine){
                firstLine = false;
            } else if (maxPrice == Float.parseFloat(funko.getPrice())) {
                System.out.println(funko.toString());
            }
        }
    }
    public static void avgPrice(List<Funko> funkoList){
        float totalPrice = 0;
        boolean firstLine = true;
        for(Funko funko : funkoList){
            if(firstLine){
                firstLine = false;
            } else {
                totalPrice += Float.parseFloat(funko.getPrice());
            }
        }
        float avg = totalPrice / (funkoList.size() - 1);
        avg = (float) Math.round(avg * 100) / 100;
        System.out.println(STR." - Media de precio: \{avg}€");
    }
    public static void byCompany(List<Funko> funkoList){
        List<String> companyList = new ArrayList<>();
        boolean firstLine = true;
        for (Funko funko : funkoList) {
            if (firstLine) {
                firstLine = false;
            } else if (!companyList.contains(funko.getCompany())) {
                companyList.add(funko.getCompany());
            }
        }
        System.out.println("--- FUNKOS POR COMPAÑIA ---");
        for (String company : companyList) {
            System.out.println(" - " + company.toUpperCase() + ": ");
            for (Funko funko : funkoList) {
                if (funko.getCompany().equals(company)) {
                    System.out.println(funko);
                }

            }
        }
    }
    public static void funkos2023(List<Funko> funkoList){
        boolean firstLine = true;
        System.out.println(" - FUNKO/S DE 2023: ");
        for (Funko funko : funkoList) {
            if (firstLine){
                firstLine = false;
            }else {
                String[] dateArray = funko.getDate().trim().split("-");
                if (Integer.parseInt(dateArray[0]) == 2023) {
                    System.out.println(funko.toString());
                }
            }
        }
    }
}