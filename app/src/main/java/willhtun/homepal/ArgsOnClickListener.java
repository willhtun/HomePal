package willhtun.homepal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ArgsOnClickListener implements View.OnClickListener
{
    Context context;
    String historyprice;
    String period = ""; // rent_10_2018
    String fperiod = ""; // 10/18
    int persons[] = new int [5];
    public ArgsOnClickListener(Context m_context, String m_price, String m_period, String m_formatperiod, String m_persons) {
        context = m_context;
        historyprice = m_price;
        period = m_period;
        fperiod = m_formatperiod;
        for (int i = 0; i < 5; i++)
            persons[i] = Character.getNumericValue(m_persons.charAt(i));
    }

    @Override
    public void onClick(View v)
    {

    }

};