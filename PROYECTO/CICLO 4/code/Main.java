
/**
 * Class Main
 * 
 * @author (DANIEL Y MIGUEL) 
 * @version (CICLO 4)
 */
public class Main
{
    public static void main(String args[]){
        SpiderWeb spiderWeb = new SpiderWeb(11, 300);
        spiderWeb.addBridge("blue", 80, 1);
        spiderWeb.addBridge("red", 130, 2);
        spiderWeb.addBridge("weak","magenta", 200, 3);
        spiderWeb.addBridge("green", 230, 2);
        spiderWeb.addBridge("orange", 270, 1);
        spiderWeb.addBridge("cyan", 50, 19);
        spiderWeb.addSpot("yellow", 2);
        spiderWeb.addSpot("bouncy","aquamarine", 4);
        spiderWeb.addSpot("brown", 20);
        spiderWeb.spiderWalk(true);
    }
}
