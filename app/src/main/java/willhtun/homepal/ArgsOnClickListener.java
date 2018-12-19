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
    String period = "";
    int persons[] = new int [5];
    public ArgsOnClickListener(Context m_context, String m_period, String m_persons) {
        context = m_context;
        period = m_period;
        for (int i = 0; i < 5; i++)
            persons[i] = Character.getNumericValue(m_persons.charAt(i));
    }

    @Override
    public void onClick(View v)
    {

    }

};