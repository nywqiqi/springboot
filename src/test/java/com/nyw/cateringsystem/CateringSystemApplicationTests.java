package com.nyw.cateringsystem;

import com.nyw.cateringsystem.consts.RoleEnum;
import com.nyw.cateringsystem.util.EnumUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class CateringSystemApplicationTests {

    public static void main(String[] args) {
        System.out.println("B01".matches("^[a-zA-Z][0-9]{2,3}"));
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testUtil() {
        System.out.println(EnumUtils.isBelongTo("staf", RoleEnum.class));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        StringBuilder time = new StringBuilder();
        String timeFormat = simpleDateFormat.format(new Date());
        String[] splits = timeFormat.split(" ")[0].split("-");
//        time.append(splits[0]);
//        time.append(splits[1]);
//        time.append(splits[2]);
        time.append(timeFormat);

        System.out.println(time.toString());
    }
}
