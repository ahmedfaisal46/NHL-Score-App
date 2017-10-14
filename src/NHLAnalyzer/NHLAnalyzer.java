package NHLAnalyzer;

import static java.lang.Double.parseDouble;
import webgrab.WebPageGrabber;

/**
 * This class is used to get the winning and losing percentages of a team
 * @author Ahmed
 * @version 0.0.1
 */
public class NHLAnalyzer {

    /**
     * This object is used to get the html code from a webpage
     */
    WebPageGrabber webpagegrabber = new WebPageGrabber();
    /**
     * holds the html code for the url entered in the constructor
     */
    private String webPage;

    /**
     * This constructor downloads the html code from a URL entered in the parameter 
    * @param url
     * HTML code stored in the instance variable webPage. The parameter is the
     * URL of the particular web page.
     */
    public NHLAnalyzer(String url) {
        //url = "http://sportsstats.cbc.ca/hockey/nhl-standings.aspx?page=/data/nhl/standings/2016-2017/league/standings.html";
        webPage = webpagegrabber.downloadPage(url);
    }

    /**
     *
     * @param team this parameter is where the team name is entered
     * @return
     * winning percentage of a particular hockey team specified by the
     * parameter.
     */
    public double getTeamWinningPercentage(String team) {
        double wins = 0;
        double gamesPlayed = 0;
        String allNumbers[] = webPage.split("</td>               "
                + " <td align=\"center\" class=\"sdi-datacell\">");
        for (int i = 0; i < allNumbers.length; i++) {
            if (allNumbers[i].contains(team)) {
                wins = parseDouble(allNumbers[i + 2].trim().substring(0, 2));
                gamesPlayed = parseDouble(allNumbers[i + 1].trim().substring(0, 2));
            }
        }
        System.out.print("The winning percentage of " + team + " is: ");
        return (wins / gamesPlayed) * 100;

    }

    /**
     *
     * @param team this parameter is where the team name is entered
     * @return This method returns the
     * losing percentage of a particular hockey team specified by the parameter.
     */
    public double getTeamLosingPercentage(String team) {
        double losses = 0;
        double gamesPlayed = 0;
        String allNumbers[] = webPage.split("</td>               "
                + " <td align=\"center\" class=\"sdi-datacell\">");
        for (int i = 0; i < allNumbers.length; i++) {
            if (allNumbers[i].contains(team)) {
                losses = parseDouble(allNumbers[i + 2].trim().substring(3, 5));
                gamesPlayed = parseDouble(allNumbers[i + 1].trim().substring(0, 2));
            }
        }
        System.out.print("The losing percentage of " + team + " is: ");
        return (losses / gamesPlayed) * 100;
    }

}

