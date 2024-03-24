package ru.vetis.qoorshach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    создаём все необходимые объекты - кнопку и все поля, с которыми будем работать, инициализируем в методе onCreate
    Button button;
    EditText startPoint;
    EditText infelicity;
    EditText iterations;
    TextView radicalView;
    TextView functionView;
    TextView iterationsView;
    TextView infelicityView;
    TextView errorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        инициализация объектов
        button =(Button)findViewById(R.id.button);
        startPoint = (EditText)findViewById(R.id.editStartPoint);
        infelicity = (EditText)findViewById(R.id.editInfelicity);
        iterations = (EditText)findViewById(R.id.editIterations);
        radicalView = (TextView)findViewById(R.id.radicalView);
        functionView = (TextView)findViewById(R.id.functionView);
        iterationsView = (TextView)findViewById(R.id.iterationsView);
        infelicityView = (TextView)findViewById(R.id.infelicityView);
        errorView = (TextView)findViewById(R.id.errorView);
    }

/*      метод для "прослушивания" кнопки. Описывает, что будет происходить при нажатии на кнопку.
        Также, в файле activity_main.xml в Button добавлена строка android:onClick="buttonClick"
        она указывает кнопки название метода, в котором описано, что происходит при нажатии */
    public void buttonClick (View view){
        newton();
    }

    public void newton () {
//        Проверяем, заполнены ли поля. Если поля не заполнены, на экране будет уведомление об этом
//        return завершает выполнение метода
        if (startPoint.getText().toString().equals("") || (infelicity.getText().toString().equals("")) || iterations.getText().toString().equals("")) {
            errorView.setText("Необходимо заполнить все поля");
            radicalView.setText("");
            functionView.setText("");
            iterationsView.setText("");
            infelicityView.setText("");
            return;
        }

        int i = 0;
    double x, pr;
    double x0 = Double.parseDouble(startPoint.getText().toString());
    double epsilon = Double.parseDouble(infelicity.getText().toString());
    int a = Integer.parseInt(iterations.getText().toString());
        do
                {
                x = x0;
                x0 = x - f(x) / f1(x);
                i++;
                if (x0 == 0)
                {
                pr = Math.abs(x - x0);
                }
                else
                {
                pr = Math.abs(x - x0) / Math.max(x,x0);
                }

                }
                while ((pr >= epsilon) && (i != a));

                double y = f(x0);

//        в этом блоке устанавливаем значения полей для отображения результатов
                if (pr <= epsilon)
                {
                errorView.setText("Решение найдено");
                radicalView.setText("Корень уравнения: " + Double.toString(x));
                functionView.setText("Значение функции: "+ Double.toString(y));
                iterationsView.setText("Количество выполненных итераций: " + Double.toString(i));
                infelicityView.setText("Вычисленная погрешность: " + Double.toString(pr));
                }
                else
                {
                errorView.setText("Корень уравнения не найден");
                radicalView.setText("Приближенное значение: " + Double.toString(x0));
                functionView.setText("Значение функции: " + Double.toString(y));
                iterationsView.setText(" ");
                infelicityView.setText(" ");
                }
                }

static double f(double x)
        {
        return Math.sin(x);
        }

static double f1(double x)
        {
        return Math.cos(x);

        }
        }
