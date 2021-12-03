import mapTask.file.FileMapInput;
import mapTask.generic.MapTaskResult;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Tema2 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: Tema2 <workers> <in_file> <out_file>");
            return;
        }
        int D, N = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert reader != null;
            D = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            N = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> fileNames = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            try {
                fileNames.add(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(fileNames);
        File file  = new File(fileNames.get(0));

        ExecutorService es = Executors.newFixedThreadPool(Integer.parseInt(args[0]));
        List<MapTaskResult> results = fileNames.stream()
                .map(fileName -> new FileMapInput("file", fileName))
                .map(new MapWorkers(es))
                .flatMap(List::stream).collect(Collectors.toList());

        System.out.println(results);

    }
}
