package fr.upem.rmi.car.loan;

public class Comment {

    public final int mark;
    public final String summary;

    /**
     * create comment
     * @param mark
     * @param summary
     */
    public Comment(int mark, String summary){
        this.mark = mark;
        this.summary = summary;
    }

    /**
     * return the mark of the comment
     * @return mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * return the summary of the comment
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

}
