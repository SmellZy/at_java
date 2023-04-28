package itstep.task_5;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTask {
    //V14
    //Stream:
    //a) Generate 10 random object using class from previous class
    //b) Sort it using any two fields using stream.
    //b) Filter it by any two fields custom filter.
    //c) Collect it to List with *main field(s).

    //step 1 init user collection
    public static void main(String[] args) {

        Integer n= 10;
        if(args.length>0){
            try {
                n = Integer.parseInt(args[0]);
            }catch (Exception e){
                System.out.println("cant parse int from " + args[0]);
                e.printStackTrace();
            }
        }
        List<data> dataList = generateDataList(10);
        System.out.println(dataList);

        //step 2 sort
        System.out.println("sort: ");
        System.out.println(dataList.stream().sorted().collect(Collectors.toList()));

        //step 3 filter
        System.out.println("filter by time: ");
        System.out.println(dataList.stream().filter(d->{ return d.getTime() != "" ;}).collect(Collectors.toList()));
        //we have the user with name Semen in our generated list, the last user in list :)
        System.out.println("filter by time without 10:30AM: ");
        System.out.println(dataList.stream().filter(d->{ return d.getTime() == "10:30 AM";}).collect(Collectors.toList()));

        //step 4 mapping
        System.out.println("stringList: ");
        System.out.println(dataList.stream().map(data::getTime).collect(Collectors.toList()));
    }
        private static List<data> generateDataList (int n){
            Random random = new Random();
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            List<data> dataList = new ArrayList<>();
            IntStream.range(1, n).forEach(i -> {
                data data = new data();
                data.setTime("10:" + random.ints(1,60) + " AM");
                dataList.add(data);
            });
            data data2 = new data();
            data2.setTime("10:30 AM");
            dataList.add(data2);
            return dataList;
        }
    }