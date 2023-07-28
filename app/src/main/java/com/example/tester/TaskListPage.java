package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.material.button.MaterialButton;

public class TaskListPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        EditText task_name = (EditText) findViewById(R.id.task_name);
        MaterialButton add_task = (MaterialButton) findViewById(R.id.add_task);
        MaterialButton timer = (MaterialButton) findViewById(R.id.timer);
        MaterialButton task_list_button = (MaterialButton) findViewById(R.id.task_list_button);
        MaterialButton log_out = (MaterialButton) findViewById(R.id.log_out);

        TextView task1 = (TextView) findViewById(R.id.task1);
        TextView task2 = (TextView) findViewById(R.id.task2);
        TextView task3 = (TextView) findViewById(R.id.task3);
        TextView task4 = (TextView) findViewById(R.id.task4);
        TextView task5 = (TextView) findViewById(R.id.task5);
        TextView task6 = (TextView) findViewById(R.id.task6);
        TextView task7 = (TextView) findViewById(R.id.task7);
        TextView task8 = (TextView) findViewById(R.id.task8);
        TextView task9 = (TextView) findViewById(R.id.task9);
        TextView task10 = (TextView) findViewById(R.id.task10);

        ImageButton close1 = (ImageButton) findViewById(R.id.close1);
        ImageButton close2 = (ImageButton) findViewById(R.id.close2);
        ImageButton close3 = (ImageButton) findViewById(R.id.close3);
        ImageButton close4 = (ImageButton) findViewById(R.id.close4);
        ImageButton close5 = (ImageButton) findViewById(R.id.close5);
        ImageButton close6 = (ImageButton) findViewById(R.id.close6);
        ImageButton close7 = (ImageButton) findViewById(R.id.close7);
        ImageButton close8 = (ImageButton) findViewById(R.id.close8);
        ImageButton close9 = (ImageButton) findViewById(R.id.close9);
        ImageButton close10 = (ImageButton) findViewById(R.id.close10);

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence tv = (CharSequence) task_name.getText();

                if (task1.toString().length() <= 3) {
                    task1.append(tv);
                }
                else if (task2.toString().length() <= 3) {
                    task2.append(tv);
                }
                else if (task3.toString().length() <= 3) {
                    task3.append(tv);
                }
                else if (task4.toString().length() <= 3) {
                    task4.append(tv);
                }
                else if (task5.toString().length() <= 3) {
                    task5.append(tv);
                }
                else if (task6.toString().length() <= 3) {
                    task6.append(tv);
                }
                else if (task7.toString().length() <= 3) {
                    task7.append(tv);
                }
                else if (task8.toString().length() <= 3) {
                    task8.append(tv);
                }
                else if (task9.toString().length() <= 3) {
                    task9.append(tv);
                }
                else if (task10.toString().length() <= 4) {
                    task10.append(tv);
                }
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TimerUI
            }
        });

        task_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TaskListUI
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open UserRegUI
            }
        });

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task1.setText("1. ");
                task1.append(task2.getText());

                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task10.setText("10. ");
            }
        });

    }
}
