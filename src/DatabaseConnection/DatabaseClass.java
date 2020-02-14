/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;
import java.sql.*; 
import java.util.Vector;
import Employee.*;
/**
 *
 * @author migo2
 */
public class DatabaseClass {
    int pos =0;
     ResultSet rs;
     Statement stmt;
     Connection con;
    public  Vector<Employee> EmployeesVector= new Vector<>();
    public DatabaseClass(){
        try{
        Class.forName("com.mysql.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");  
        stmt=con.createStatement();  
        rs=stmt.executeQuery("select * from employee");  
        while(rs.next()){
            EmployeesVector.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
    }catch(Exception ex){
        ex.printStackTrace();
    }   
    }
   
    public Employee getFirst(){
         pos = 0;
        if(EmployeesVector.isEmpty()){
            return new Employee(0," "," "," "," "," ");
        }else{
              return EmployeesVector.firstElement();
         }
    }
     public Employee getLast(){
        if(EmployeesVector.isEmpty()){
             pos = 0;
          return new Employee(0," "," "," "," "," ");
        }else{
         pos=EmployeesVector.size()-1;
        return EmployeesVector.lastElement();
        }
    }
    public Employee next(){
        if(EmployeesVector.isEmpty()){
             pos = 0;
          return new Employee(0," "," "," "," "," ");
        }else if(pos<EmployeesVector.size()-1)
            ++pos;
        return EmployeesVector.get(pos);
    } 
    public Employee prev(){
        if(EmployeesVector.isEmpty()){
             pos = 0;
          return new Employee(0," "," "," "," "," ");
        }else if(pos>0)
            --pos;  
        return EmployeesVector.get(pos);
    } 
    public Employee update(String fname,String mname,String lname,String email,String phone) throws SQLException{
        if(EmployeesVector.isEmpty()){
            return new Employee(0," "," "," "," "," ");
        }
        Employee tempEmp=EmployeesVector.get(pos);
        String query="UPDATE `employee` SET `firstName`='"
                +fname +"',`middleName`='"
                + mname+"',`lastName`='"
                + lname+"',`email`='"
                + email+"',`phone`='"
                + phone+"' WHERE id ="+tempEmp.id;
            stmt.executeUpdate(query); 
            tempEmp.fname=fname;
            tempEmp.mname=mname;
            tempEmp.lname=lname;
            tempEmp.email=email;
            tempEmp.phone=phone;

        return tempEmp;
    } 
     public Employee delete() throws SQLException{
         
           if(EmployeesVector.isEmpty()){
               return new Employee(0," "," "," "," "," ");
           }else if (EmployeesVector.size()==1){
                stmt.executeUpdate("delete from employee where id="+EmployeesVector.get(pos).id); 
                EmployeesVector.removeElementAt(pos);
               pos=0;
               return EmployeesVector.firstElement();
           }else{
               stmt.executeUpdate("delete from employee where id="+EmployeesVector.get(pos).id); 
                EmployeesVector.removeElementAt(pos);
               if(pos >0)
                pos--;
                
               return EmployeesVector.get(pos);
           }
           
           
    } 
      
     public Employee newEmp(){
        Employee emp=new Employee(EmployeesVector.size()+1," "," "," "," "," ");
        pos=EmployeesVector.size();
       EmployeesVector.add(emp);
        return emp;
    } 
}
