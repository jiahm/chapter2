/**
 * Copyright (c) 2005-2015 https://github.com/jiahm
 * <p/>
 * Licensed under the Apache License,Version 2.0 (the "License")
 */
package org.smart4j.chapter2.service;

import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.smart4j.chapter2.util.PropsUtil;

/*
* Created by jiahongming on 15/11/30.
*/
public class CustomerService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

//    private static final String DRIVER;
//    private static final String URL;
//    private static final String USERNAME;
//    private static final String PASSWORD;
//
//    static {
//        Properties conf = PropsUtil.loadProps("config.properties");
//        DRIVER = conf.getProperty("jdbc.driver");
//        URL = conf.getProperty("jdbc.url");
//        USERNAME = conf.getProperty("jdbc.username");
//        PASSWORD = conf.getProperty("jdbc.password");
//
//        try {
//            Class.forName(DRIVER);
//        } catch(ClassNotFoundException e){
//            LOGGER.error("can not load jdbc driver", e);
//        }
//    }

    /**
     * @deprecated
     * @return
     */
    public List<Customer> getCustomerList_old() {
        //TODO
        Connection conn= null;
        try {
            List<Customer> customerList = new ArrayList<Customer>();
            String sql = "SELECT * from customer";
            //conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            conn = DatabaseHelper.getConnection(); //上一句换成当前句<1>
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
            return customerList;
        }catch (SQLException e){
            LOGGER.error("execute sql failure", e);
        }
//        finally {
//            if (conn != null) {
//                try{
//                    conn.close();
//                }catch(SQLException e){
//                    LOGGER.error("close connection failure",e);
//                }
//            }
//            DatabaseHelper.closeConnection(); //上几句换成当前句<2>
//        }
        return null;
    }

    public List<Customer> getCustomerList(){
//        Connection conn = DatabaseHelper.getConnection(); //上一句换成当前句<1>
//        try{
            String sql = "SELECT * from customer";
            return DatabaseHelper.queryEntityList(Customer.class,sql);
//        }
//        finally {
//            DatabaseHelper.closeConnection();
//        }
    }

    public Customer getCustomer(long id) {
        //TODO
//        Connection conn = DatabaseHelper.getConnection();
        String sql = "Select * from customer where id = "+id;
        DatabaseHelper.queryEntity(Customer.class,sql);
        return null;
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        //TODO
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }

    /**
     * 更新客户
     * @param id
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap) {
        //TODO
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id) {
        //TODO
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
}
