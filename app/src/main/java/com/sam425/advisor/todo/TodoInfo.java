package com.sam425.advisor.todo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sam425.advisor.R;
import com.sam425.advisor.todo.Todo;

import java.util.List;

public class TodoInfo extends ArrayAdapter<Todo> {
    private Activity context;
    private List<Todo> todos;
    public TodoInfo(Activity context,List<Todo>todos){
        super(context,R.layout.todo_item,todos);
        this.context=context;
        this.todos=todos;

    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.todo_item,null,true);
        TextView date2=(TextView)listView.findViewById(R.id.listdate);
        TextView time2=(TextView)listView.findViewById(R.id.listtime);
        TextView task3=(TextView)listView.findViewById(R.id.listtask1);
        TextView priority2=(TextView)listView.findViewById(R.id.listprio);
        TextView descr2=(TextView)listView.findViewById(R.id.listdesc);
        Todo todo=todos.get(position);
        date2.setText(todo.getdate());
        time2.setText(todo.gettime());
        task3.setText(todo.gettaskname());
        priority2.setText(todo.getpriority());
        descr2.setText(todo.gettaskdesc());
        return listView;
    }
}
