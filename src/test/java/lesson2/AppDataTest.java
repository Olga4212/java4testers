package lesson2;

import org.junit.Assert;
import org.junit.Test;

public class AppDataTest {
    @Test
    public void SaveAndLoadTest() {
        String filename = "/tmp/app_data.csv";

        AppData appData = new AppData();
        appData.setHeader(new String[]{"Value 1", "Value 2", "Value 3"});
        appData.setData(new int[][]{
                new int[]{100, 200, 123},
                new int[]{300, 400, 500},
        });

        appData.save(filename);

        AppData appData2 = AppData.load(filename);
        Assert.assertArrayEquals(appData.getHeader(), appData2.getHeader());
        Assert.assertArrayEquals(appData.getData(), appData2.getData());
    }

}
