/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ID
 */
public class Process{

    
    //XỬ lÍ CÁC CHỨC NĂNG TÌM, THÊM, SỬA, XÓA
    
        
    //TÌM TỪ
    public int search(String w, ArrayList<String> keys1){
        if(w.compareTo(keys1.get(0))<= 0)
            return 0;
        int head = 0, rear = keys1.size()-1;
        /**
         * Tìm kiếm nhị phân 
         */
        while(head < rear){
            int between = (head+rear)/2;
            if(keys1.get(between    ).compareTo(w) < 0)
                head=between;
            else
                rear=between;
        }
        return rear;
    }
    
    //Sứa key hoặc meaninng
    /**
     * 
     * @param w
     * @param newWord1
     * @param newDefine
     * @param Word1
     * @param keys1 
     */
    public void modify(String w,String newWord1, String newDefine, HashMap<String,String> Word1, ArrayList<String> keys1){
        if(newWord1 == null) // k có newWord1 thì chỉ thay thế meaning = newDefine
            Word1.replace(w, newDefine);
        else if(newDefine == null){ // sửa key giữ  nguyên meaning
            String def = Word1.get(w); // lưu lại meaninng trước 
            remove(w,Word1,keys1); //Xóa key trong hashMap
            add(w, def,Word1,keys1); //Thêm meaning theo key
        }
        else
            remove(w,Word1,keys1);
            add(newWord1, newDefine,Word1,keys1); // gọi đến hàm add ở dưới
        
    }
    //THÊM TỪ
    public void add(String w, String define, HashMap<String,String>Word1, ArrayList<String>keys1){
        w=w.toLowerCase();
        //int position = search(w, keys1);
        keys1.add(w);
        Word1.put(w, define); 
    }
    //XÓA TỪ
    public void remove(String w, HashMap<String,String>Word1, ArrayList<String>keys1){
        int index = keys1.lastIndexOf(w);
        if(index != -1){
            keys1.remove(w);
            Word1.remove(w);
        }
    }
}

