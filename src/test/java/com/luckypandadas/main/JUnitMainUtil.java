package com.luckypandadas.main;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Innodev-E531 on 2015/12/23.
 */

@WebAppConfiguration
@ContextConfiguration(locations = {  "classpath*:spring-context.xml" ,"classpath*:mvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JUnitMainUtil {
}
