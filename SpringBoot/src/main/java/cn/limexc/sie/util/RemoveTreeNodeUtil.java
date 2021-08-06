package cn.limexc.sie.util;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/28 17:09
 */
public class RemoveTreeNodeUtil {


    public static List<String> RemoveList(Map<Integer, Integer> map,String id){
        List<String> renode = new ArrayList<String>();
        Set<Integer> keys=map.keySet();
        Iterator<Integer> iterator=keys.iterator();
        System.out.println("ID —— SID");
        while(iterator.hasNext()){
            Integer i=iterator.next();
            System.out.println(i+" —— "+map.get(i));
        }

        int k=Integer.parseInt(id);
        //递归删除k的所有子节点
        System.out.println("—— —— —— —— —— ——删除掉的节点 —— —— —— —— —— —— ——");
        removeTreeNodes(map,k,renode);
        //删除k节点本身
        renode.add(String.valueOf(k));
        map.remove(k);
        System.out.println();
        System.out.println("—— —— —— —— —— —— 递归删除["+k+"]的所有子节点后的Map —— —— ");

        iterator=keys.iterator();
        System.out.println("ID —— PID");
        while(iterator.hasNext()){
            Integer i=iterator.next();
            System.out.println(i+" —— "+map.get(i));
        }

        return renode;
    }




    //递归删除所有的子节点
    public static Map<Integer, Integer> removeTreeNodes(Map<Integer, Integer> t,Integer k,List<String> renode){
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
                    if(t.get(n).equals(s)){
                        temp.add(n);
                        renode.add(String.valueOf(n));
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
