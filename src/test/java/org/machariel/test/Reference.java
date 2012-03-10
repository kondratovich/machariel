package org.machariel.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import org.machariel.core.serialization.ReferenceSerializationManager;
import org.machariel.test.data.Bean0;
import org.machariel.test.data.Bean1;
import org.machariel.test.util.Common;

public class Reference {
  private Random r = new Random();
  
  @Test
  public void testGeneric() throws InstantiationException, IllegalArgumentException, IllegalAccessException {
    Bean0 bean0 = new Bean1();
    ReferenceSerializationManager<Bean0> sm = ReferenceSerializationManager.acquire(Bean0.class);
    
    bean0.randomize();
    long ref = sm.serialize(bean0, 2);
    Bean0 bean1 = sm.deserialize(ref);
    
    assertTrue(Common.equal(bean0, bean1));
  }
  
  @Test
  public void test() throws InstantiationException, IllegalArgumentException, IllegalAccessException {
    ReferenceSerializationManager<Bean1> sm = ReferenceSerializationManager.acquire(Bean1.class);
    
    Bean1 bean0 = new Bean1();
    bean0.randomize();
    long ref = sm.serialize(bean0, 2);
    Bean1 bean1 = sm.deserialize(ref);
    
    assertTrue(Common.equal(bean0, bean1));
  }
  
  @Test
  public void testCollection() throws InstantiationException, IllegalArgumentException, IllegalAccessException {
    ArrayList<Integer> col0 = new ArrayList<Integer>();
    ReferenceSerializationManager<ArrayList<Integer>> sm = ReferenceSerializationManager.acquire(col0);
    
    for (int i = 0; i < r.nextInt(10); i++) col0.add(r.nextInt());
    
    long ref = sm.serialize(col0, 4);
    ArrayList<Integer> col1 = sm.deserialize(ref);
    
//    for (int i = 0; i < col0.size(); i++) {
//      System.out.println(col0.get(i) + " " + col1.get(i));
//    }
    
    assertTrue(Common.array_equal(col0.toArray(), col1.toArray()));
  }
}