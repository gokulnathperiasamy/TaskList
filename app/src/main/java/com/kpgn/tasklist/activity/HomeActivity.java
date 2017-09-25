package com.kpgn.tasklist.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kpgn.tasklist.R;
import com.kpgn.tasklist.entity.Task;
import com.kpgn.tasklist.test.TestData;
import com.kpgn.tasklist.view.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fab_new_task)
    FloatingActionButton mFabNewTask;

    @BindView(R.id.rv_task_list)
    RecyclerView mRvTaskList;

    private List<Task> taskList = new ArrayList<>();
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setupToolbar();
        setupData();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupData() {
        taskList = TestData.getTaskListData();
    }

    private void setupRecyclerView() {
        tasksAdapter = new TasksAdapter(this, taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvTaskList.setLayoutManager(mLayoutManager);
        mRvTaskList.setItemAnimator(new DefaultItemAnimator());
        mRvTaskList.setAdapter(tasksAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
