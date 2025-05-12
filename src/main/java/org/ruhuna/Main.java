package org.ruhuna;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        NameStore nameStore = new CacheStore(new FileBasedNameStore());

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if(line.equals("Show"))
            {
                nameStore.getNames().forEach(n -> System.out.print(n + " ,"));
            }
            else {
                nameStore.save(line);
            }

        }
        }

    }