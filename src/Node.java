/**
 * Created by DongDong on 2016-02-03.
 */
public class Node {

    public int level;
    public String name;
    public int size;

    public String source;
    public String target;

    public void setNode(int level, String name, int size)
    {
        this.level = level;
        this.name = name;
        this.target = name;
        this.size = size;
        this.source = "Root";
    }

}
