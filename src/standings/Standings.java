package standings;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Standings
{
    ArrayList<ArrayList<String>> positions;
    public Standings()
    {
        positions = new ArrayList<>();
        fillPos(new TeamPool());
    }

    private void fillPos(TeamPool teamPool)
    {
        for (String team: teamPool.getPool())
        {
            ArrayList<String> teamResults = new ArrayList<>();

            for (ArrayList<String> season: teamPool.getFinTables())
            {
                if (!season.contains(team)) teamResults.add("-");
                else teamResults.add(Integer.toString(season.indexOf(team) + 1));
            }

            positions.add(teamResults);
        }

        makeCSV(teamPool);
    }

    private void makeCSV(TeamPool teamPool)
    {
        File csvFile = new File("standings.csv");

        try
        {
            FileWriter writer = new FileWriter(csvFile);
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

            printer.printRecord("Team", "2004 - 2005", "2005 - 2006", "2006 - 2007", "2007 - 2008", "2008 - 2009", "2009 - 2010", "2010 - 2011", "2011 - 2012", "2012 - 2013", "2013 - 2014", "2014 - 2015", "2015 - 2016", "2016 - 2017", "2017 - 2018", "2018 - 2019", "2019 - 2020", "2020 - 2021", "2021 - 2022");
            for (int i = 0; i < teamPool.getPool().size(); i++) printer.printRecord(teamPool.getPool().get(i), positions.get(i).get(0), positions.get(i).get(1), positions.get(i).get(2), positions.get(i).get(3), positions.get(i).get(4), positions.get(i).get(5), positions.get(i).get(6), positions.get(i).get(7), positions.get(i).get(8), positions.get(i).get(9), positions.get(i).get(10), positions.get(i).get(11), positions.get(i).get(12), positions.get(i).get(13), positions.get(i).get(14), positions.get(i).get(15), positions.get(i).get(16), positions.get(i).get(17));

            printer.close();
        }
        catch (IOException e){e.printStackTrace();}
    }

}
