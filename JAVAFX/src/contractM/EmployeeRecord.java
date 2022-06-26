package contractM;

import java.sql.Date;

public class EmployeeRecord
{
    
    private Integer id;
    private String name;
    private String surname;
    private Date birthDate;
    private String Gender;
    private Boolean MarriageStatus;
    private String number;
    private String email;
    private String address;
    private Integer hours;
    private String job;
    private String department;
    private Date contractBegin;
    private Date contractEnd;
    private String salary;
    
    
    public EmployeeRecord(int id, String name, String surname, Date birthDate, String Gender, Boolean MarriageStatus,
                          String number, String email, String address, Integer hours, String job, String department,
                          Date contractBegin, Date contractEnd, String salary)
    {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthDate);
        this.setGender(Gender);
        this.setMarriageStatus(MarriageStatus);
        this.setNumber(number);
        this.setEmail(email);
        this.setAddress(address);
        this.setHours(hours);
        this.setJob(job);
        this.setDepartment(department);
        this.setContractBegin(contractBegin);
        this.setContractEnd(contractEnd);
        this.setSalary(salary);
    }
    
    
    public Integer getId() { return this.id; }
    
    public String getName() { return this.name; }
    
    public String getSurname() { return this.surname; }
    
    public Date getBirthday() { return this.birthDate; }
    
    public String getGender() { return this.Gender; }
    
    public Boolean getMarriageStatus() { return this.MarriageStatus; }
    
    public String getNumber() { return this.number; }
    
    public String getEmail() { return this.email; }
    
    public String getAddress() { return this.address; }
    
    public Integer getHours() { return this.hours;}
    
    public String getJob() { return this.job;}
    
    public String getDepartment() { return this.department;}
    
    public Date getContractBegin() { return this.contractBegin;}
    
    public Date getContractEnd() { return this.contractEnd;}
    
    public String getSalary() { return this.salary;}
    
    
    public void setId(int id) { this.id = id; }
    
    public void setBirthday(Date BirthDate) { this.birthDate = BirthDate; }
    
    public void setName(String name) { this.name = name; }
    
    public void setSurname(String surname) { this.surname = surname; }
    
    public void setGender(String gender) { this.Gender = gender; }
    
    public void setMarriageStatus(Boolean MarriageStatus) { this.MarriageStatus = MarriageStatus; }
    
    public void setNumber(String number) { this.number = number; }
    
    public void setEmail(String Email) { this.email = Email; }
    
    public void setAddress(String Address) { this.address = Address; }
    
    public void setHours(Integer Hours) { this.hours = Hours; }
    
    public void setJob(String job) { this.job = job; }
    
    public void setDepartment(String department) { this.department = department; }
    
    public void setContractBegin(Date contractBegin) { this.contractBegin = contractBegin; }
    
    public void setContractEnd(Date contractEnd) { this.contractEnd = contractEnd; }
    
    public void setSalary(String salary) { this.salary = salary; }
    
    
    
    
}
