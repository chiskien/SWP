package dao;


import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoang
 */
public interface IMethod<E> {
    List<E> getAll();
    E getOne(int id);
    boolean add(E obj);
    boolean remove(int id);
    boolean update(int id, E obj);
}
