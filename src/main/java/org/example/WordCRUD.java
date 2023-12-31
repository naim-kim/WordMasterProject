package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 :");
        int level = s.nextInt();
        String word = s.nextLine();
        //1. driveway
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();


        //driveway entered
        return new Word(0, level, word, meaning);
    }

    public void addItem() {
        Word one = (Word) add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가됐습니다 !!! \n");

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
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-----------------------");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("-----------------------");
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if (!word.contains(keyword)) continue;
            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("-----------------------");
        return idlist;
    }

    public void listAll(int level) {
        int j = 0;
        System.out.println("-----------------------");
        for (Word word : list) { //enhanced for loop
            int ilevel = word.getLevel();
            if (ilevel != level) continue;
            System.out.print((j + 1) + " "); //j
            System.out.println(word);
            j++;
        }
        System.out.println("-----------------------");
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택: ");
        int id = s.nextInt();
        s.nextLine(); //  엔터가 입력 안되도록

        System.out.print("=> 뜻 입력: ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id - 1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택: ");
        int id = s.nextInt();
        s.nextLine(); //  엔터가 입력 안되도록

        System.out.print("=> 정말로 삭제하겠습니까: ");
        String ans = s.next();
        if (ans.equalsIgnoreCase("y")) {
            list.remove((int) idlist.get(id - 1)); //  정수로 바꿈
            System.out.println("단어가 삭제되었습니다. ");
        } else
            System.out.println("취소괴었습닌다. ");

    }

    public void loadfile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;

            while (true) {
                line = br.readLine();
                if (line == null) break;

                String[] data = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==>" + count + "개 로딩 완료!!");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for (Word one : list) {
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료 !!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.println("==> 원하는 레벨은?");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.println("=> 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }

    public void sortWords() {
        Scanner scanner = new Scanner(System.in);

        int choice;
        System.out.println("단어 정렬 기준: 1.알파벳 2.난이도 0.돌아가기");

        while(true) {

            choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                break;
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("다시 입력하시오.\n 단어 정렬 기준: 1.알파벳 2.난이도 0.돌아가기");
            }
            System.out.println("-----------------------");
        }

        if (choice == 1) {
            // Sort alphabetically
            list.sort((word1, word2) -> word1.getWord().compareToIgnoreCase(word2.getWord()));
        } else {
            // Sort by level + alphabetically
            list.sort((word1, word2) -> {
                int compareLevel = Integer.compare(word1.getLevel(), word2.getLevel());
                if (compareLevel == 0) {
                    return word1.getWord().compareToIgnoreCase(word2.getWord());
                }
                return compareLevel;
            });
            System.out.println("-----------------------");
        }
        listAll();

    }
}

