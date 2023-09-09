package org.example;

public class Word {
    private int id;
    private int level;
    private String word;
    private String meaning;
Word() {}
    Word(int id, int level, String word, String meaning){
        this.id=id;
        this.level=level;
        this.word=word;
        this.meaning=meaning;

    }
    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}
