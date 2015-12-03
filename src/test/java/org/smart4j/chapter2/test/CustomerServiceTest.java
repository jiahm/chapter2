package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;
import org.smart4j.chapter2.util.PropsUtil;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jiahongming on 15/11/30.
 */
public class CustomerServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() {
        //TODO 初始化数据库
        String file = "sql/customer_init.sql";
        DatabaseHelper.executeSqlFile(file);
    }


    @Test
    public void getCustomerListTest() throws Exception {
        java.util.List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() throws Exception {
        Map<String,Object> filedMap = new HashMap<String, Object>();
        filedMap.put("name","customer100");
        filedMap.put("contact", "John");
        filedMap.put("telephone","1234567890");
        boolean result = customerService.createCustomer(filedMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest() throws Exception {
        long id =1;
        Map<String,Object> filedMap = new HashMap<String, Object>();
        filedMap.put("contact", "Eric");
        filedMap.put("telephone","1234567890");
        boolean result = customerService.updateCustomer(id, filedMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        long id =1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
