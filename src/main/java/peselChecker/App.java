package peselChecker;


import peselChecker.model.Pesel;
import peselChecker.service.FileService;
import peselChecker.service.PeselService;
import peselChecker.service.PeselUtils;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        PeselUtils peselUtils = new PeselUtils();
        FileService fileService = new FileService();
        PeselService peselService = new PeselService();

        List<String> list = new ArrayList<>(fileService.convertFileToStringList(
                "C:\\Users\\48501\\Desktop\\testFolder\\pesel.txt"));
        List<Pesel> pesels = peselService.convertStringListToPeselList(list);

        fileService.writeJSONToFile("pesels.json",pesels);

        int[] years = peselUtils.mostPeopleBirthYear(pesels);
        for (int i = 0; i < years.length; i++) {
            System.out.println(years[i]);
        }

        System.out.println(peselUtils.sexCouner(pesels, 'M'));
        System.out.println(peselUtils.sexCouner(pesels, 'F'));

        List<Month> months = peselUtils.mostPeopleBirthMonth(pesels);
        for(Month month: months){
            System.out.println(month);
        }
        System.out.println(peselUtils.peopleBornInMonth(pesels, Month.NOVEMBER));


    }
}
