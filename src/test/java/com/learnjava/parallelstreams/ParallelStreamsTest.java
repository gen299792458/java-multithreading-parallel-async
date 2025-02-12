package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

class ParallelStreamsTest {

    ParallelStreams parallelStreams = new ParallelStreams();

    @Test
    void stringTransform() {
//        given
        List<String> strings = DataSet.namesList();

//        when
        startTimer();
        List<String> result = parallelStreams.stringTransform(strings);
        timeTaken();

//        then
        assertEquals(4, result.size());
        result.forEach(name->assertTrue(name.matches("[0-9]-[A-Za-z]+")));
    }
}