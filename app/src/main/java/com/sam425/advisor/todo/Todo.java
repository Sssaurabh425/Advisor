package com.sam425.advisor.todo;



public class Todo {

    private String taskname;
    private String taskdesc;
    private String time;
    private String date;
    private String priority;

    public Todo() {

    }
    public Todo(String taskname,String taskdesc,String date,String time,String priority) {
        this.taskname=taskname;
        this.taskdesc=taskdesc;
        this.date=date;
        this.time=time;
        this.priority=priority;

    }
    public String getdate() {
        return date;
    }



    public String gettaskname() {
        return taskname;
    }


    public String gettaskdesc() {
        return taskdesc;
    }

   public String gettime(){return time;}
   public String getpriority(){return priority;};



}