/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee;

/**
 *
 * @author migo2
 */
public class Employee {
     public int id;
    public String fname;
     public String mname;
      public String lname;
       public String email;
        public String phone;
        
    public Employee(int id,String fname,String mname,String lname,String email,String phone){
        this.id=id;
        this.fname=fname;
        this.mname=mname;
        this.lname=lname;
        this.email=email;
        this.phone=phone;
    }
}
