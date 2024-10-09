package lesson30;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class D_ZoneDateTimeTester {
    // + для перевода времени между различными часовыми поясами
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt); // 2023-10-04T01:06:18.409543300+02:00[Europe/Berlin]

        System.out.println(
                zdt
                        .toInstant() // время без привязки к временной зоне
                        .atZone(
                                ZoneId.of("Pacific/Midway")
                        )
        ); // 2023-10-03T12:06:18.409543300-11:00[Pacific/Midway]

        ZonedDateTime t = ZonedDateTime.parse("2022-12-20T00:35:47.023323700+02:00[Europe/Helsinki]");
        System.out.println(t);
    }
}
