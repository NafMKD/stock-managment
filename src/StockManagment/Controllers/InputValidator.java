/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import java.util.Scanner;

/**
 *
 * @author Naf
 */
public class InputValidator {
    public static String to_be_str;
    public static int to_bo_int;
    
    public static boolean checkFormatString(String format, String slice,String check){
        try{
            String[] partsFormat = format.split(slice);
            String[] partsCheck = check.split(slice);
            boolean ret = true;
            for(int i =0;i<partsFormat.length;i++){
                if(partsFormat[i].length() != partsCheck[i].length()){
                    ret = false;
                    break;
                }
            }
            return ret;
        }catch(Exception e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }
        
    }
    
    public static boolean checkContainsInt(String check, String slice, int n){
        try{
            String[] partsCheck = check.split(slice);
            int[] intarr = new int[n];
            boolean ret =true;
            for(int i=0;i<n;i++){
                try{
                    intarr[i] = Integer.valueOf(partsCheck[i]);
                }catch(Exception e){
                    //System.out.println("Error : "+e.getMessage());
                    ret=false;
                }

            }
            return ret;
        }catch(Exception e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }
        
    }
    
    public static boolean checkMonthYearDay(String check, int x, int y){
        try{
            String[] partsCheck = check.split("-");
            int[] intarr = new int[3];
            boolean ret =true;
            for(int i=0;i<3;i++){
                try{
                    intarr[i] = Integer.valueOf(partsCheck[i]);
                }catch(Exception e){
                    //System.out.println("Error : "+e.getMessage());
                    ret=false;
                }

            }
            if(intarr[0]>x || intarr[0]<y){
                ret = false;
            }
            if(intarr[1]>12 || intarr[1]<1){
                ret = false;
            }
            if(intarr[2]>31 || intarr[2]<1){
                ret = false;
            }
            return ret;
        }catch(Exception e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }
        
    }
    
    public static boolean checkInt(String check){
        try{
            boolean ret= true;
            int x;
            try{
                x = Integer.valueOf(check);
            }catch(NumberFormatException e){
                ret = false;
            }
            return ret;
        }catch(Exception e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }
        
    }
    
    public static boolean checkString(String[] check){
        try{
            int n = check.length;
            boolean ret= true;
            for(int i=0;i<n;i++){
                for(int j=0;j<check[i].length();j++){
                    int t = (int) check[i].charAt(j);
                    if(t<65 || t>122){
                        ret = false;
                        break;
                    }
                    if(t>90 && t<97){
                        ret = false;
                        break;
                    }
                }
            }
            return ret;
        }catch(UnsupportedOperationException e){
            //System.out.println("Not supported yet.");
            return false;
        }catch(Exception e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }
        
    }

}
