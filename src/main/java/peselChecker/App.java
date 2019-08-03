package peselChecker;


import peselChecker.model.Pesel;
import peselChecker.service.FileService;
import peselChecker.service.PeselService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        FileService fileService = new FileService();
        List<String> list = new ArrayList<>(fileService.convertFileToStringList(
                "C:\\Users\\48501\\Desktop\\testFolder\\pesel.txt"));
        PeselService peselService = new PeselService();
        List<Pesel> pesels = peselService.convertStringListToPeselList(list);
        fileService.writeJSONToFile("pesels.json",pesels);
    }
}
