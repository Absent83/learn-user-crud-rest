package com.myhome.springCrudRest.model;

public interface UserDAO {
    UserDataSet get(long id);
    UserDataSet getByName(String name);

    void add(UserDataSet dataSet);
    void delete(long id);
}
