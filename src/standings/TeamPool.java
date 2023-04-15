package standings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TeamPool
{
    private ArrayList<ArrayList<String>> finTables;
    private ArrayList<String> pool;
    protected TeamPool()
    {
        finTables = new ArrayList<>();
        pool = new ArrayList<>();

        fillSeasons();
        fillPool();

        Collections.sort(pool);
    }

    protected ArrayList<String> getPool() {return pool;}

    protected ArrayList<ArrayList<String>> getFinTables() {return finTables;}

    private void fillSeasons()
    {
        File file = new File("raw-data.txt");

        try
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null)
            {
                ArrayList<String> temp = new ArrayList<>();

                int loc = 0;
                while (loc < line.length())
                {
                    if (line.charAt(loc) != ',')
                    {
                        String team = null;

                        while (loc < line.length() && line.charAt(loc) != ',')
                        {
                            if (team == null) team = Character.toString(line.charAt(loc));
                            else team += Character.toString(line.charAt(loc));

                            loc++;
                        }

                        temp.add(team);
                    }

                    loc++;
                }


                finTables.add(temp);
            }
        }
        catch (IOException e){e.printStackTrace();}

    }

    private void fillPool()
    {
        for (int i = 0; i < finTables.size(); i++)
        {
            for (int j = 0; j < finTables.get(i).size(); j++)
            {
                if (!pool.contains(finTables.get(i).get(j))) pool.add(finTables.get(i).get(j));
            }
        }
    }



}
