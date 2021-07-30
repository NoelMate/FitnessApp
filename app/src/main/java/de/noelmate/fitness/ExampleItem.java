package de.noelmate.fitness;

public class ExampleItem {
    private int imageResource;
    private String text1;
    private String text2;

    public ExampleItem(int iResource, String t1, String t2)
    {
        imageResource = iResource;
        text1 = t1;
        text2 = t2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
