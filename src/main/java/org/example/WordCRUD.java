package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
ArrayList<Word> list;
Scanner s;

WordCRUD(Scanner s) {
    list = new ArrayList<>();
    this.s = s;
}

    @Override
    public Object add() {
        System.out.print(" => Difficulty(1,2,3) & input new word: ");
        int level = s.nextInt();
        String word = s.nextLine();
        //1. driveway
        System.out.print("Input Definition: ");
        String meaning = s.nextLine();


        //driveway entered
        return new Word(0, level, word, meaning);
    }
    public void addWord(){
        Word one = (Word)add();
        list.add(one);
        System.out.println(" New word successfully added!");

    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int i) {

    }
    public void listAll() {
         System.out.println("-----------------------");
         for(int i = 0; i<list.size(); i++) {
             System.out.print((i+1) + " ");
             System.out.println(list.get(i).toString());
         }
        System.out.println("-----------------------");
    }
}
