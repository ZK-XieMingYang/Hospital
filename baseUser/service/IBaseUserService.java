package com.qhit.baseUser.service;

import java.util.List;
import com.qhit.baseUser.pojo.BaseUser;


public interface IBaseUserService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseUser findById(Object id);
    List search(BaseUser baseUser);

    BaseUser login(BaseUser baseUser);

    boolean findByIdAndPassword(BaseUser baseUser);
}