package lesson22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class G_MatchesTester {
    // считаем из matches.txt все результаты в виде строк
    // разобьем строку по ", " на куски
    // из кусков соберем Match
    // Match добавим в список
    public static void main(String[] args) {
        List<Match> matches = new ArrayList<>();
        try (
                Reader reader = new FileReader("./src/main/java/lesson22/files/matches.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                ) {
            String line = bufferedReader.readLine();
            while (line != null) {
                // разобьем строку по ", " на куски
                String[] t = line.split(", ");
                Calendar calendar = new GregorianCalendar(
                        Integer.parseInt(t[0]),
                        Integer.parseInt(t[1]),
                        Integer.parseInt(t[2])
                );
                // из кусков соберем Match
                Match match = new Match(
                        calendar, // date
                        t[3], // team 1
                        t[4], // result
                        t[5], // team 2
                        t[7], // stadium
                        Integer.parseInt(t[6]) // watcher
                );
                // Match добавим в список
                matches.add(match);
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // посчитайте суммарное количество посетителей
        // посчитайте среднее количество посетителей
        DoubleSummaryStatistics watcherStatistics =
                matches.stream()
                        .collect(Collectors.summarizingDouble(
                                Match::getWatcher
                        ));
        System.out.println("total: " + watcherStatistics.getSum());
        System.out.println("average: " + watcherStatistics.getAverage());
    }
}
