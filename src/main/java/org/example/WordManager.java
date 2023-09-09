package org.example;

import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;

    WordManager() {
        wordCRUD = new WordCRUD(s);
    }

    public int selectMenu() {
        System.out.print("/*** 영단어 마스터 ***\n"
        + "----------------\n"
        + "1. Read all words\n"
        + "2. By difficulty\n"
        + "3. Search word\n"
        + "4. Add word\n"
        + "5. Edit word\n"
        + "6. Delete word\n"
        + "7. Save file\n"
        + "0. Exit\n"
        + " => Select Menu: ");

        return s.nextInt();
    }
    public void start(){
        while(true) {
            int menu = selectMenu();
            if (menu == 0) break;
            if (menu == 4) {
                wordCRUD.addWord();
            } else if (menu == 1) {
                //list
            }
        }
    }
}
