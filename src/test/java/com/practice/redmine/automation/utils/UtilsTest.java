package com.practice.redmine.automation.utils;

import lombok.extern.log4j.Log4j;
import org.junit.Ignore;
import org.junit.Test;


@Log4j
@Ignore
public class UtilsTest {

    @Test
    public void getTimestamp() {

        try {
            log.debug(Utils.getTimestamp());
            Thread.sleep(1000);
            log.debug(Utils.getTimestamp());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}