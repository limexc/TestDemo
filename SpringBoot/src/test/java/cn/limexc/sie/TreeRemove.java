package cn.limexc.sie;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/28 17:05
 */

public class TreeRemove {

    @Test
    public void TreeTest(){
        test();

    }


    //测试removeSons方法
    public static void test(){

        //原始的Map
        Map<Integer, Integer> t=new HashMap<Integer, Integer>();
        //  ID PID
        t.put(1, 0);
        t.put(2, 1);
        t.put(3, 1);
        t.put(4, 2);
        t.put(5, 4);
        System.out.println("—— —— —— —— —— ——原始的Map —— —— —— —— —— —— ——");

        Set<Integer> keys=t.keySet();
        Iterator<Integer> iterator=keys.iterator();
        System.out.println("ID —— PID");
        while(iterator.hasNext()){
            Integer i=iterator.next();
            System.out.println(i+" —— "+t.get(i));
        }

        int k=2;
        //递归删除k的所有子节点
        System.out.println("—— —— —— —— —— ——删除掉的节点 —— —— —— —— —— —— ——");
        removeTreeNodes(t,k);
        //删除k节点本身
        t.remove(k);
        System.out.println();
        System.out.println("—— —— —— —— —— —— 递归删除["+k+"]的所有子节点后的Map —— —— ");

        iterator=keys.iterator();
        System.out.println("ID —— PID");
        while(iterator.hasNext()){
            Integer i=iterator.next();
            System.out.println(i+" —— "+t.get(i));
        }
    }

    //递归删除所有的子节点
    public static Map<Integer, Integer> removeTreeNodes(Map<Integer, Integer> t,Integer k){
        //所有需要删除的子节点
        List<Integer> sons=new ArrayList<Integer>();
        sons.add(k);
        List<Integer> temp=new ArrayList<Integer>();
        //循环递归删除，所有以k为父节点的节点
        while(true){
            for(Integer s:sons){
                Set<Integer> keys=t.keySet();
                Iterator<Integer> it=keys.iterator();
                while(it.hasNext()){
                    Integer n=it.next();
                    //如果父节点（即Map的value）为需要删除的节点，则记录此节点，并在Map中删除
                    if(t.get(n)==s){
                        temp.add(n);
                        it.remove();
                        System.out.println("删除了ID=["+n+"]的节点,其父节点为["+s+"]");
                    }
                }
            }

            //如果此节点包含子节点，则将子节点赋值给sons;否则表示所有子节点已经删除，结束循环
            if(temp.size()>0){
                sons=temp;
                temp=new CopyOnWriteArrayList<Integer>();
            }else{
                break;
            }
        }

        return t;
    }
}
