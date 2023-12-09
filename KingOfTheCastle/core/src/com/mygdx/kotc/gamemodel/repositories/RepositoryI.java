package com.mygdx.kotc.gamemodel.repositories;

public interface RepositoryI {

    Long save(Object object);
    Object findById(Long Id);

}
