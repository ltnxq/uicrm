package com.htsc.idp.service;


import com.htsc.idp.api.ICustService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustServiceTest {
    @Autowired
    private ICustService custService;
    @Test
    public void testGetName(){
      custService.getName();
    }
}
