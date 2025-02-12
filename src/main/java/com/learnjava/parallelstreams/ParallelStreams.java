package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreams {

    public List<String> stringTransform(List<String> inputList) {

       return inputList
//                .stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public String addNameLengthTransform(String name){
        delay(500);
        return  name.length()+"-"+name;
    }


    public static void  main(String[] args) {
        List<String> names = DataSet.namesList();
        ParallelStreams parallelStreams = new ParallelStreams();
        stopWatch.start();
        log("result: "+parallelStreams.stringTransform(names));
        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
    }
}
